package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.house.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByIsPrefect(boolean isPrefect);

    List<Student> findAllPrefectsByHouse(House house);

    long countByHouseAndIsPrefect(House house, boolean isPrefect);

    List<Student> findByHouseAndIsPrefect(House house, boolean isPrefect);
}
