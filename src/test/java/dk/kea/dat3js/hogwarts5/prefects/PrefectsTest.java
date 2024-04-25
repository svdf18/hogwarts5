package dk.kea.dat3js.hogwarts5.prefects;

import dk.kea.dat3js.hogwarts5.students.Student;
import dk.kea.dat3js.hogwarts5.house.House;
import org.junit.jupiter.api.Test;

import static dk.kea.dat3js.hogwarts5.students.Student.Gender.MALE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrefectsTest {


    @Test
    void getAllStudentPrefects() {
        // arrange
        Student student1 = new Student("Harry", "James", "Potter", MALE, null, true, 1);
        Student student2 = new Student("Hermione", "Jean", "Granger", MALE, null, true, 1);
        Student student3 = new Student("Ron", "Bilius", "Weasley", MALE,null, false, 1);

        // act
        boolean isPrefect1 = student1.isPrefect();
        boolean isPrefect2 = student2.isPrefect();
        boolean isPrefect3 = student3.isPrefect();

        // assert
        assertTrue(isPrefect1);
        assertTrue(isPrefect2);
        assertTrue(!isPrefect3);
    }

    @Test
    void getStudentByIdIfStudentIsPrefect() {
        // arrange
        Student student = new Student("Harry", "James", "Potter", MALE, null, true, 1);

        //act
        boolean isPrefect = student.isPrefect();

        //assert
        assertTrue(isPrefect);
    }

    @Test
    void getAllPrefectsFromAHouse() {
        // arrange
        House house = new House("Gryffindor", "Godric Gryffindor", new String[]{"Red", "Gold"});

        Student student1 = new Student("Harry", "James", "Potter", MALE, house, true, 1);
        Student student2 = new Student("Hermione", "Jean", "Granger", MALE, house, true, 1);
        Student student3 = new Student("Ron", "Bilius", "Weasley", MALE, house, false, 1);

        // act
        boolean isPrefect1 = student1.isPrefect();
        boolean isPrefect2 = student2.isPrefect();
        boolean isPrefect3 = student3.isPrefect();

        // assert
        assertTrue(isPrefect1);
        assertTrue(isPrefect2);
        assertFalse(isPrefect3);

    }

    @Test
    void setStudentAsPrefect() {
        // arrange
        Student student = new Student("Harry", "James", "Potter", MALE,null, false, 1);

        // act
        student.setPrefect(true);

        // assert
        assertTrue(student.isPrefect());
    }

    @Test
    void isStudentAlreadyAPrefect() {
        // arrange
        Student student = new Student("Harry", "James", "Potter", MALE, null, true, 1);

        // act
        boolean isPrefect = student.isPrefect();

        // assert
        assertTrue(isPrefect);
    }
}
