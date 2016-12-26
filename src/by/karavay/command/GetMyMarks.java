package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.dao.CourseDAO;
import by.karavay.dao.FacultyDAO;
import by.karavay.dao.MarksDAO;
import by.karavay.entity.Course;
import by.karavay.entity.Faculty;
import by.karavay.entity.Mark;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMyMarks implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("getMarks");
        FacultyDAO facultyDAO = new FacultyDAO();
        Map<Integer, Faculty> facultyMap = new HashMap<>();
        Map<Integer, Course> coursesMap = new HashMap<>();
        Map<Integer, Mark> marksMap = new HashMap<>();

        List<Mark> marks = MarksDAO.getUserMarks((int) req.getSession().getAttribute("id"));

        for (Mark m :marks) {
            facultyMap.put(m.getIdFaculty(), facultyDAO.getFacById(m.getIdFaculty()));
            marksMap.put(m.getIdFaculty(), m);
        }

        List<Faculty> facultyList = new ArrayList<>(facultyMap.values());
        for (Faculty f: facultyList){
            coursesMap.put(f.getId(), CourseDAO.getCourseById(f.getIdCourse()));
        }

        req.getSession().setAttribute("facultyList", facultyList);
        req.getSession().setAttribute("facultyMap", facultyMap);
        req.getSession().setAttribute("coursesMap", coursesMap);
        req.getSession().setAttribute("marksMap", marksMap);

        return page;
    }
}
