package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentRepository;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrefectService {


    private final StudentRepository studentRepository;
    private final StudentService studentService;

    public PrefectService(StudentRepository studentRepository, StudentService studentService) {
        this.studentRepository = studentRepository;
        this.studentService = studentService;
    }

    public StudentResponseDTO getPrefectById(int id) throws Exception {
        StudentResponseDTO student = studentService.findById(id).orElseThrow(() -> new Exception("Student with studentId: " + id + " not found"));
        if (!student.isPrefect()) {
            throw new Exception("Student with studentId: " + id + " is not a prefect");
        }
        return student;
    }

    public void setStudentAsPrefect(int id) throws Exception {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent()) {
            Student studentToUpdate = studentOptional.get();

            // Check eligibility criteria
            checkStudentPrefectEligibility(studentToUpdate);

            // Update the student as prefect
            studentToUpdate.setPrefect(true);
            studentRepository.save(studentToUpdate);
        } else {
            throw new Exception("Student not found");
        }
    }

    private void checkStudentPrefectEligibility(Student student) throws Exception {
        System.out.println(student.getFirstName());

        checkIfAlreadyPrefect(student);
        checkSchoolYear(student);
        checkHousePrefects(student.getHouse());
        prefectsHaveSameGender(student);
    }

    private void checkIfAlreadyPrefect(Student student) throws Exception {
        if (student.isPrefect()) {
            throw new Exception("Student is already a prefect");
        }
        System.out.println(student.isPrefect());
    }

    private void checkSchoolYear(Student student) throws Exception {
        if (student.getSchoolYear() < 5) {
            throw new Exception("Student must be in school year 5 or above to become a prefect");
        }
        System.out.println(student.getSchoolYear());
    }

    public void checkHousePrefects(House house) throws Exception {
        long numberOfPrefectsForHouse = studentRepository.countByHouseAndIsPrefect(house, true);
        if (numberOfPrefectsForHouse >= 2) {
            throw new Exception("Maximum number of prefects reached for house " + house.getName());
        }
        System.out.println(numberOfPrefectsForHouse + " prefects for house " + house.getName());
    }

    private void prefectsHaveSameGender(Student student) throws Exception {
        List<Student> prefects = studentRepository.findByHouseAndIsPrefect(student.getHouse(), true);
        for (Student prefect : prefects) {
            if (prefect.getGender() == student.getGender()) {
                throw new Exception("A prefect of the same gender already exists");
            }
        }
    }

    public List<StudentResponseDTO> findAllPrefects() {
        return studentRepository.findAllByIsPrefect(true).stream().map(studentService::toDTO).toList();
    }

    public List<StudentResponseDTO> getPrefectsByHouse(House house) {
        List<Student> prefectsByHouse = studentRepository.findAllPrefectsByHouse(house);
        return prefectsByHouse.stream()
                .filter(Student::isPrefect) // Filter only the prefects
                .map(studentService::toDTO)
                .toList();
    }

    public Student removePrefect(int id) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student studentToUpdate = optionalStudent.get();
            studentToUpdate.setPrefect(false);
            return studentRepository.save(studentToUpdate);
        } else {
            throw new Exception("Student not found");
        }
    }
}
