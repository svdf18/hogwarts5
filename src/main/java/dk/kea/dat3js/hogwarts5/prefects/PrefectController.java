package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.house.House;
import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.students.StudentResponseDTO;
import dk.kea.dat3js.hogwarts5.students.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prefects")
public class PrefectController {

    private final PrefectService prefectService;
    private final StudentService studentService;


    public PrefectController(PrefectService prefectService, StudentService studentService) {
        this.prefectService = prefectService;
        this.studentService = studentService;
    }

    @GetMapping
    List<StudentResponseDTO> getAllPrefects() {
        return prefectService.findAllPrefects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPrefectById(@PathVariable int id) {
        try {
            StudentResponseDTO studentResponseDTO = prefectService.getPrefectById(id);
            return ResponseEntity.ok(studentResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/house/{house}")
    public ResponseEntity<?> getPrefectsByHouse(@PathVariable House house) {
        List<StudentResponseDTO> prefectsByHouse = prefectService.getPrefectsByHouse(house);

        if (prefectsByHouse.isEmpty()) {
            String message = "No prefect has been appointed for " + house.getName();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        } else {
            return ResponseEntity.ok(prefectsByHouse);
        }
    }

    @PostMapping("/set")
    public ResponseEntity<?> setStudentAsPrefect(@RequestBody StudentResponseDTO studentResponseDTO) {
        try {
            prefectService.setStudentAsPrefect(studentResponseDTO.id());
            StudentResponseDTO updatedStudent = studentService.findById(studentResponseDTO.id())
                    .orElseThrow(() -> new Exception("Failed to retrieve updated student"));
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<StudentResponseDTO> removePrefect(@PathVariable int id) {
        try {
            Student updatedStudent = prefectService.removePrefect(id);
            StudentResponseDTO studentResponseDTO = studentService.toDTO(updatedStudent);
            return ResponseEntity.ok(studentResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
