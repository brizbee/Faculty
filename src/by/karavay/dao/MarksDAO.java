package by.karavay.dao;

import by.karavay.entity.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarksDAO {
    public static final String GET_USER_MARKS = "select * from mark where STUDENT=?";

    public static List<Mark> getUserMarks(int idStudent){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Mark> marks = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_USER_MARKS);
            ps.setInt(1, idStudent);
            rs = ps.executeQuery();

            int idFaculty;
            String mark;
            while (rs.next()){
                idFaculty = rs.getInt(2);
                mark = rs.getString(3);
                marks.add(new Mark(idStudent, idFaculty, mark));
            }
            return marks;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(rs, ps, connection);
        }
        return marks;
    }
}
