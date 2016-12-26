package by.karavay.entity;

public class Mark {
    private int idStudent;
    private int idFaculty;
    private String mark;

    @Override
    public String toString() {
        return "Mark{" +
                "idStudent=" + idStudent +
                ", isFaculty=" + idFaculty +
                ", mark='" + mark + '\'' +
                '}';
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int isFaculty) {
        this.idFaculty = isFaculty;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Mark(int idStudent, int isFaculty, String mark) {
        this.idStudent = idStudent;
        this.idFaculty = isFaculty;
        this.mark = mark;
    }
}
