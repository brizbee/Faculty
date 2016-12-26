package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.dao.CourseDAO;
import by.karavay.dao.FacultyDAO;
import by.karavay.dao.UserDAO;
import by.karavay.entity.Course;
import by.karavay.entity.Faculty;
import by.karavay.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetEndedCoursesForTeacher implements ActionCommand {

    private final static Logger logger = Logger.getLogger(GetEndedCoursesForTeacher.class);
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("ended");

//        выборт факультативов которые закончены +
        // выбор курсов +
//        и список студентов по ним Map<Integer, User);
//         у студента кнопка - выскакивает окно - ставишь оценку.
        List<Faculty> endedFac = FacultyDAO.getEndedFac();
        int idFac;
        Map<Integer, Course> courseMap = new HashMap<>();
        Map<Integer, List<User>> userMap = new HashMap<>();
        List<User> userList= null;

        for (Faculty f: endedFac){
            courseMap.put(f.getId(), CourseDAO.getCourseById(f.getIdCourse()));
            userList = UserDAO.getUserList(f.getId());
            userMap.put(f.getId(), userList);
        }
logger.info(endedFac);
        logger.info(userMap);
        logger.info(courseMap);
        req.getSession().setAttribute("endedFac", endedFac);
        req.getSession().setAttribute("userMap", userMap);
        req.getSession().setAttribute("courseMap", courseMap);
        return page;
    }
}
