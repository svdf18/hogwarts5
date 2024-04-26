package dk.kea.dat3js.hogwarts5.students;

public record StudentResponseDTO(int id, String firstName, String middleName, String lastName, String fullName, String gender, String house, boolean isPrefect, Integer schoolYear) {
}
