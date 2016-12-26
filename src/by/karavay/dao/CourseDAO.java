package by.karavay.dao;

import by.karavay.entity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDAO {
    private final static String GET_COURSE_BY_ID = "select * from course where id=?";

    public static Course getCourseById(int id){
        Course course = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

            try {
                connection = DatabaseConnection.getConnection();
                ps = connection.prepareStatement(GET_COURSE_BY_ID);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();
                course = new Course(id, rs.getString(2), rs.getString(3), rs.getString(4));
                return course;
        } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(rs, ps, connection);
            }
            return course;
        }
}
