package by.karavay.dao;

import by.karavay.entity.Course;
import by.karavay.entity.Faculty;
import by.karavay.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {

    private final static Logger logger = Logger.getLogger(UserDAO.class);
    private final static String ACCOUNT_MESSAGE = "accountMessage";
    private static final String ACTIVE_USER = "activeUser";
    private final static String FACULTY_LIST = "facultyList";
    private final static String COURSES_LIST = "coursesList";

    private final static String GET_USER_BY_ID = "select * from account where id=?";
    private final static String GET_USER_BY_LOGIN = "select * from account where login=?";
    private final static String ADD_NEW_USER = "insert into account(NAME, LOGIN, PASSWORD, ROLE) values (?, ?, ?, ?)";
    private final static String GET_USER_FACULTY = "select * from facultystudent where STUDENT=?";
    private final static String GET_FACULTY_BY_ID = "select * from faculty where id=?";
    private final static String GET_COURSES_BY_ID = "select * from course where id=?";
    private final static String GET_TEACHER_FACULTY = "select * from faculty where teacher=?";
    private final static String GET_STUDENT_FrOM_FACULTY = "select * from account inner join facultystudent on account.id = facultystudent.student where facultystudent.faculty=?;";

    public static List<User> getUserList(int idFac) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_STUDENT_FrOM_FACULTY);
            ps.setInt(1, idFac);
            rs = ps.executeQuery();
            int id;
            String name;
            String login;
            String password;
            String role;
            while(rs.next()){
                logger.info("in usDAO " + rs);
                id = rs.getInt(1);
                name = rs.getString(2);
                login = rs.getString(3);
                password = rs.getString(4);
                role = rs.getString(5);
                users.add(new User(id, name, login, password, role));
            }
            logger.info(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public boolean registrateUser(String fio, String login, String password, String role){
        Connection connection = null;
        PreparedStatement ps = null;

        try{
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(ADD_NEW_USER);
            ps.setString(1, fio);
            ps.setString(2, login);
            ps.setString(3, password);
            ps.setString(4, role);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(ps, connection);
        }
        return false;
    }

   public User checkLogin(String enterLogin, String enterPass, HttpServletRequest req){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_USER_BY_LOGIN);
            ps.setString(1, enterLogin);
            rs = ps.executeQuery();
            int id;
            String name;
            String login;
            String password;
            String role;
            while(rs.next()){
                id = rs.getInt(1);
                name = rs.getString(2);
                login = rs.getString(3);
                password = rs.getString(4);
                role = rs.getString(5);
                if (password.equals(enterPass)){
                    user = new User(id, name, login, password, role);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(rs, ps, connection);
        }
        return user;
   }

    public void setTeacherToSession(User user, HttpServletRequest req){
       int idUser = user.getId();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Faculty> facultyList = new ArrayList<>();

        List<Faculty> started = new ArrayList<>();
        List<Faculty> ended = new ArrayList<>();

        PreparedStatement psCourse = null;
        ResultSet rsCourse = null;
        Map<Integer, Course> courseMap = new HashMap<>();

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_TEACHER_FACULTY);
            psCourse = connection.prepareStatement(GET_COURSES_BY_ID);
            ps.setInt(1, idUser);
            rs = ps.executeQuery();
            while (rs.next()){
                facultyList.add(new Faculty(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4)));
                int idCou = rs.getInt(2);
                psCourse.setInt(1, idCou);
                rsCourse = psCourse.executeQuery();
                rsCourse.next();
                courseMap.put(rs.getInt(1), new Course(rsCourse.getInt(1), rsCourse.getString(2), rsCourse.getString(3), rsCourse.getString(4)));
                //
            }

            for (Faculty f : facultyList){
                if (f.getStatus().equals("started"))
                    started.add(f);
                else
                    ended.add(f);
            }
            req.getSession().setAttribute("facultyList", facultyList);
            req.getSession().setAttribute("started", started);
            req.getSession().setAttribute("ended", ended);
            req.getSession().setAttribute("courseMap", courseMap);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUserAccountToSession(User user, HttpServletRequest req){
        int idUser = user.getId();
        String role = user.getRole();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        PreparedStatement psFaculty = null;
        ResultSet rsFaculty = null;

        PreparedStatement psCourse = null;
        ResultSet rsCourse = null;

        List<Faculty> facultyList = new ArrayList<>();
        Map<Integer, Course> courses = new HashMap<>();

        if (role.equals("student")){
            try {
                connection = DatabaseConnection.getConnection();
                ps = connection.prepareStatement(GET_USER_FACULTY);
                psFaculty = connection.prepareStatement(GET_FACULTY_BY_ID);
                ps.setInt(1, idUser);
                rs = ps.executeQuery();
                while(rs.next()){
                    psFaculty.setInt(1, rs.getInt(1));
                    rsFaculty = psFaculty.executeQuery();

                    int idFac;
                    int idCourse;
                    String status;
                    int idTeacher;
                    while (rsFaculty.next()){
                        idFac = rsFaculty.getInt(1);
                        idCourse = rsFaculty.getInt(2);
                        status = rsFaculty.getString(3);
                        idTeacher = rsFaculty.getInt(4);
                        if (status.toLowerCase().equals("started")){
                            psCourse = connection.prepareCall(GET_COURSES_BY_ID);
                            psCourse.setInt(1, idCourse);
                            rsCourse = psCourse.executeQuery();

                            int courseId;
                            String courseTitle;
                            String kaf;
                            String desc;
                            while (rsCourse.next()){
                                courseId = rsCourse.getInt(1);
                                courseTitle = rsCourse.getString(2);
                                kaf = rsCourse.getString(3);
                                desc = rsCourse.getString(4);
                                courses.put(courseId, new Course(courseId, courseTitle, kaf, desc));
                            }
                            facultyList.add(new Faculty(idFac, idCourse, status, idTeacher));
                        }
                    }
                    req.getSession().setAttribute(COURSES_LIST, courses);
                    req.getSession().setAttribute(FACULTY_LIST, facultyList);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(rs, ps, connection);
                DatabaseConnection.closeConnection(rsFaculty, psFaculty, connection);
                DatabaseConnection.closeConnection(rsCourse, psCourse, connection);
            }
        }
    }

    public User getUserById(int id){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            connection = DatabaseConnection.getConnection();
            ps = connection.prepareStatement(GET_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
