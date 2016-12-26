package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.dao.FacultyDAO;
import by.karavay.dao.UserDAO;
import by.karavay.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class StartFaculty implements ActionCommand {
    private final static Logger logger = Logger.getLogger(StartFaculty.class);
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.tutorPage");
        String startFac = (String) req.getSession().getAttribute("facToStart");
        User user = (User) req.getSession().getAttribute("activeUser");
logger.info("here^ " + startFac);
        int stFac = Integer.valueOf(startFac);
        int idUs = user.getId();
        FacultyDAO.setFacActive(stFac);
        UserDAO userDAO = new UserDAO();
        userDAO.setTeacherToSession(user, req);
        return page;
    }
}
