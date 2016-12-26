package by.karavay.entity;

public class FacultyStudent {
    private int idFaculty;
    private int idStudent;

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public FacultyStudent(int idFaculty, int idStudent) {
        this.idFaculty = idFaculty;
        this.idStudent = idStudent;
    }
}
