package dk.kea.dat3js.hogwarts5.common;

public interface PersonWithNames {

    String getFirstName();
    String getMiddleName();
    String getLastName();

    void setFirstName(String firstName);
    void setMiddleName(String middleName);
    void setLastName(String lastName);

    default String getFullName() {
        return getFirstName() + " " + (getMiddleName()!=null?getMiddleName()+ " ":"") + getLastName();
    }

    default void setFullName(String fullName) {
        if (fullName == null) {
            return;
        }

        fullName = capitalize(fullName);

        int firstSpace = fullName.indexOf(' ');
        int lastSpace = fullName.lastIndexOf(' ');

        if (firstSpace == -1) {
            // Only first name provided
            setFirstName(fullName);
            setMiddleName(null);
            setLastName(null);
            return;
        }

        // First name provided
        setFirstName(fullName.substring(0, firstSpace));

        if (lastSpace == firstSpace) {
            // Only first and last name provided
            setMiddleName(null);
            setLastName(fullName.substring(firstSpace + 1));
            return;
        }

        // First, middle, and last name provided
        setMiddleName(fullName.substring(firstSpace + 1, lastSpace));
        setLastName(fullName.substring(lastSpace + 1));
    }

    default String capitalize(String fullName) {
        if(fullName == null) {
            return null;
        }
        if (fullName.isEmpty()) {
            return "";
        } if(fullName.length() < 2) {
            return fullName.toUpperCase();
        }
        if (fullName.contains(" ")) {
            return fullName.substring(0, 1).toUpperCase() + fullName.substring(1, fullName.indexOf(' ')).toLowerCase() + " " + capitalize(fullName.substring(fullName.indexOf(' ') + 1));
        }
        return fullName.substring(0, 1).toUpperCase() + fullName.substring(1).toLowerCase();
    }

}
