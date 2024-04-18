package dk.kea.dat3js.hogwarts5.students;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // get all students
  @GetMapping
  public List<Student> getAllStudents() {
    return studentService.findAll();
  }

  // get student by id
  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudent(@PathVariable int id) {
    return ResponseEntity.of(studentService.findById(id));
  }

  // create post, put, patch, delete methods
  @PostMapping
  public Student createStudent(@RequestBody Student student) {
    return studentService.save(student);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student) {
    return ResponseEntity.of(studentService.updateIfExists(id, student));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Student> partialUpdateStudent(@PathVariable int id, @RequestBody Student student) {
    return ResponseEntity.of(studentService.partialUpdate(id, student));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable int id) {
    return ResponseEntity.of(studentService.deleteById(id));
  }
}
