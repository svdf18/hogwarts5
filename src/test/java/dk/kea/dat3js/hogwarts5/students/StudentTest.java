package dk.kea.dat3js.hogwarts5.students;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getFullName() {
        // arrange
        Student student = new Student("Harry", "James", "Potter", null, false, 1);

        // act
        String fullName = student.getFullName();

        // assert
        assertEquals("Harry James Potter", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        student.setFirstName("Hermione");
        student.setMiddleName("Jean");
        student.setLastName("Granger");

        // assert
        assertEquals("Hermione", student.getFirstName());
        assertEquals("Jean", student.getMiddleName());
        assertEquals("Granger", student.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        student.setFirstName("Neville");
        student.setMiddleName(null);
        student.setLastName("Longbottom");

        // assert
        assertEquals("Neville", student.getFirstName());
        assertNull(student.getMiddleName());
        assertEquals("Longbottom", student.getLastName());
    }

    @Test
    void setFullNameWithOnlyAFirstName() {
        // arrange
        Student student = new Student("first", "Middle", "Last", null, false, 1);

        // act
        student.setFirstName("Leanne");

        // assert
        assertEquals("Leanne", student.getFirstName());
        assertEquals("Middle", student.getMiddleName());
        assertEquals("Last",student.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        student.setFirstName("Albus");
        student.setMiddleName("Percival Wulfric Brian");
        student.setLastName("Dumbledore");

        // assert
        assertEquals("Albus", student.getFirstName());
        assertEquals("Percival Wulfric Brian", student.getMiddleName());
        assertEquals("Dumbledore", student.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        student.setFullName("");

        // assert
        assertEquals("", student.getFirstName());
        assertNull(student.getMiddleName());
        assertNull(student.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        student.setFullName(null);

        // assert
        assertEquals("first", student.getFirstName());
        assertEquals("middle", student.getMiddleName());
        assertEquals("last", student.getLastName());
    }

    @Test
    void capitalizeBySettingStudentFullName() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        student.setFullName("harry james potter");

        // assert
        assertEquals("Harry James Potter", student.getFullName());
    }

    @Test
    void capitalizeWithCrazyCapitalizATION(){
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        String capitalized = student.capitalize("hArRy jAMEs pOTtEr");

        // assert
        assertEquals("Harry James Potter", capitalized);
    }

    @Test
    void capitalize() {
        // arrange
        Student student = new Student("first", "middle", "last", null, false, 1);

        // act
        String capitalized = student.capitalize("harry james potter");

        // assert
        assertEquals("Harry James Potter", capitalized);
    }
}