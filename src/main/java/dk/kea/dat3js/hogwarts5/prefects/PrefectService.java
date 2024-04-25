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
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student studentToUpdate = student.get();
            studentToUpdate.setPrefect(true);
            studentRepository.save(studentToUpdate);
        } else {
            throw new Exception("Student not found");
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
}
