package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.dao.FacultyDAO;

import javax.servlet.http.HttpServletRequest;

public class GetStartedFaculty implements ActionCommand {
    private final static String STARTED_COURSES = "startedCourses";

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("startedFaculty");
        FacultyDAO facultyDAO = new FacultyDAO();
        facultyDAO.getStartedCourses(req);
        return page;
    }
}
