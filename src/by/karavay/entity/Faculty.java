package by.karavay.entity;

public class Faculty {
    private int id;
    private int idCourse;
    private String status;
    private int idTeacher;

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", idCourse=" + idCourse +
                ", status=" + status +
                ", idTeacher=" + idTeacher +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public Faculty(int id, int idCourse, String status, int idTeacher) {
        this.id = id;
        this.idCourse = idCourse;
        this.status = status;
        this.idTeacher = idTeacher;
    }
}
