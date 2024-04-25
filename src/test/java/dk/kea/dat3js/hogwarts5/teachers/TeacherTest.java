package dk.kea.dat3js.hogwarts5.teachers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeacherTest {

    @Test
    void getFullName() {
        // arrange
        Teacher teacher = new Teacher("Severus", "Prince", "Snape", null, null, null);

        // act
        String fullName = teacher.getFullName();

        // assert
        assertEquals("Severus Prince Snape", fullName);
    }

    @Test
    void setFullNameWithMiddleName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        teacher.setFirstName("Minerva");
        teacher.setMiddleName("McGonagall");
        teacher.setLastName("McGonagall");

        // assert
        assertEquals("Minerva", teacher.getFirstName());
        assertEquals("McGonagall", teacher.getMiddleName());
        assertEquals("McGonagall", teacher.getLastName());
    }

    @Test
    void setFullNameWithoutMiddleName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        teacher.setFirstName("Alastor");
        teacher.setMiddleName(null);
        teacher.setLastName("Moody");

        // assert
        assertEquals("Alastor", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertEquals("Moody", teacher.getLastName());
    }

    @Test
    void setFullNameWithOnlyAFirstName() {
        // arrange
        Teacher teacher = new Teacher("first", "Middle", "Last", null, null, null);

        // act
        teacher.setFirstName("Minerva");

        // assert
        assertEquals("Minerva", teacher.getFirstName());
        assertEquals("Middle", teacher.getMiddleName());
        assertEquals("Last", teacher.getLastName());
    }

    @Test
    void setFullNameWithMultipleMiddleNames() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        teacher.setFirstName("Albus");
        teacher.setMiddleName("Percival Wulfric Brian");
        teacher.setLastName("Dumbledore");

        // assert
        assertEquals("Albus", teacher.getFirstName());
        assertEquals("Percival Wulfric Brian", teacher.getMiddleName());
        assertEquals("Dumbledore", teacher.getLastName());
    }

    @Test
    void setFullNameWithEmptyString() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        teacher.setFullName("");

        // assert
        assertEquals("", teacher.getFirstName());
        assertNull(teacher.getMiddleName());
        assertNull(teacher.getLastName());
    }

    @Test
    void setFullNameWithNull() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        teacher.setFullName(null);

        // assert
        assertEquals("first", teacher.getFirstName());
        assertEquals("middle", teacher.getMiddleName());
        assertEquals("last", teacher.getLastName());
    }

    @Test
    void capitalizeBySettingTeacherFullName() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        teacher.setFullName("severus snape");

        // assert
        assertEquals("Severus Snape", teacher.getFullName());
    }

    @Test
    void capitalizeWithCrazyCapitalizATION(){
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        String capitalized = teacher.capitalize("hArRy jAMEs pOTtEr");

        // assert
        assertEquals("Harry James Potter", capitalized);
    }

    @Test
    void capitalize() {
        // arrange
        Teacher teacher = new Teacher("first", "middle", "last", null, null, null);

        // act
        String capitalized = teacher.capitalize("harry james potter");

        // assert
        assertEquals("Harry James Potter", capitalized);
    }
}
