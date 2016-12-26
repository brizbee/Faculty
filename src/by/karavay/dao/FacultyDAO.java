package by.karavay.dao;

import by.karavay.entity.Course;
import by.karavay.entity.Faculty;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacultyDAO {
    private final static String GET_STARTED_FACULTY = "select ID, COURSE, TEACHER from faculty where status='started'";
    private final static String GET_COURSES_BU_ID = "select * from course where id=?";
    private final static String GET_FACULTY_TEACHER = "select NAME from account where id=?";
    private final static String GET_FACULTY_BY_ID = "select * from faculty where ID=?";
    private final static String SET_STUDENT_TO_FAC = "insert into facultystudent values(?, ?)";
    private final static String SET_ACTIVE_FAC = "update faculty set status='started' where id=?";
    private final static String GET_ENDED_FACULTY = "select * from faculty where status='ended'";

    public static List<Faculty> getEndedFac(){
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Faculty> endedFac = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(GET_ENDED_FACULTY);
            while (rs.next()){
                endedFac.add(new Faculty(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
            }
            return endedFac;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(rs, statement, connection);
        }
        return endedFac;
    }

    public static void setStudentToFaculty(int idFac, int idStud){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(SET_STUDENT_TO_FAC);
            ps.setInt(1, idFac);
            ps.setInt(2, idStud);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(ps, connection);
        }
    }

    public static void setFacActive(int idFac){
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(SET_ACTIVE_FAC);
            ps.setInt(1, idFac);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(ps, connection);
        }
    }

    public Faculty getFacById(int id){
        Faculty fac = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_FACULTY_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            fac = new Faculty(id, rs.getInt(2), rs.getString(3), rs.getInt(4));
            return fac;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(rs, ps, connection);
        }
        return fac;
    }

    public void getStartedCourses(HttpServletRequest req) {
        Connection connection = null;
        PreparedStatement ps = null;
        Statement st = null;
        ResultSet rs = null;
        ResultSet rsCourses = null;

        List<Integer> facultId = new ArrayList<>();
        Map<Integer, Course> courses = new HashMap<>();
        Map<Integer, String> tutorName = new HashMap();

        try {
            connection = DatabaseConnection.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_STARTED_FACULTY);

            int idFac;
            int idCourse;
            int tutor;
            while (rs.next()){
                idFac = rs.getInt(1);
                facultId.add(idFac);
                idCourse = rs.getInt(2);
                tutor = rs.getInt(3);
                ps = connection.prepareStatement(GET_COURSES_BU_ID);
                ps.setInt(1, idCourse);
                rsCourses = ps.executeQuery();

                int id;
                String title;
                String kaf;
                String desc;
                while (rsCourses.next()){
                    id = rsCourses.getInt(1);
                    title = rsCourses.getString(2);
                    kaf = rsCourses.getString(3);
                    desc = rsCourses.getString(4);
                    courses.put(idFac, new Course(id, title, kaf, desc));
                }


                String name;
                ps = connection.prepareStatement(GET_FACULTY_TEACHER);
                ps.setInt(1, tutor);
                ResultSet rsTeach = null;
                rsTeach = ps.executeQuery();
                while(rsTeach.next()){
                    tutorName.put(idFac, rsTeach.getString(1));
                }


                if(rsTeach != null){
                    rsTeach.close();
                }
            }
            req.getSession().setAttribute("facultId", facultId);
            req.getSession().setAttribute("courses", courses);
            req.getSession().setAttribute("tutorName", tutorName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(rs, st, connection);
            DatabaseConnection.closeConnection(rsCourses, ps, connection);
        }
    }
}
