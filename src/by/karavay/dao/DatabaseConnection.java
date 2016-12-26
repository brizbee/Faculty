package by.karavay.dao;

import java.sql.*;

public class DatabaseConnection {
    static Connection connection;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/faculty";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(ResultSet rs, Statement st, Connection conn){
        try{
            if (rs != null)
                rs.close();
            closeConnection(st, conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Statement st, Connection conn) {
        try {
            if(st != null)
                st.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
