package dk.kea.dat3js.hogwarts5.students;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  public Optional<Student> findById(int id) {
    return studentRepository.findById(id);
  }

  public Student save(Student student) {
    return studentRepository.save(student);
  }

  public Optional<Student> updateIfExists(int id, Student student) {
    if (studentRepository.existsById(id)) {
      student.setId(id);
      return Optional.of(studentRepository.save(student));
    } else {
      return Optional.empty();
    }
  }

  public Optional<Student> partialUpdate(int id, Student student) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    if(existingStudent.isPresent()) {
      Student studentToUpdate = existingStudent.get();
      if(student.getFirstName() != null) {
        studentToUpdate.setFirstName(student.getFirstName());
      }
      if(student.getMiddleName() != null) {
        studentToUpdate.setMiddleName(student.getMiddleName());
      }
      if(student.getLastName() != null) {
        studentToUpdate.setLastName(student.getLastName());
      }
      if(student.getHouse() != null) {
        studentToUpdate.setHouse(student.getHouse());
      }
      if(student.getSchoolYear() != null) {
        studentToUpdate.setSchoolYear(student.getSchoolYear());
      }
      return Optional.of(studentRepository.save(studentToUpdate));
    } else {
      // TODO: handle error
      return Optional.empty();
    }
  }

  public Optional<Student> deleteById(int id) {
    Optional<Student> existingStudent = studentRepository.findById(id);
    studentRepository.deleteById(id);
    return existingStudent;
  }
}
