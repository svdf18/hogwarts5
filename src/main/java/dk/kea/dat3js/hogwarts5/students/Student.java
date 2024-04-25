package dk.kea.dat3js.hogwarts5.students;

import dk.kea.dat3js.hogwarts5.common.PersonWithNames;
import dk.kea.dat3js.hogwarts5.house.House;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Student implements PersonWithNames {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;


  public enum Gender {
    MALE, FEMALE, OTHER
  }
  @Enumerated(EnumType.STRING)
  private Gender gender;
  
  @ManyToOne
  private House house;

  private boolean isPrefect;
  private Integer schoolYear; // 1-7

  public Student() {
  }

  public Student(String firstName, String lastName, Gender gender, House house, boolean isPrefect, int schoolYear) {
    this(firstName, null, lastName, gender, house, isPrefect, schoolYear);
  }

  public Student(String firstName, String middleName, String lastName, Gender gender, House house, boolean isPrefect, int schoolYear) {
    setFirstName(firstName);
    setMiddleName(middleName);
    setLastName(lastName);
    this.gender = gender;
    this.house = house;
    this.isPrefect = isPrefect;
    this.schoolYear = schoolYear;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public House getHouse() {
    return house;
  }

  public void setHouse(House house) {
    this.house = house;
  }


  public boolean isPrefect() {
    return isPrefect;
  }

  public void setPrefect(boolean prefect) {
    isPrefect = prefect;
  }

  public Integer getSchoolYear() {
    return schoolYear;
  }

  public void setSchoolYear(Integer schoolYear) {
    this.schoolYear = schoolYear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getMiddleName(), student.getMiddleName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getHouse().getName(), student.getHouse().getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getFirstName(), getMiddleName(), getLastName(), getHouse().getName());
  }
}
