package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.dao.FacultyDAO;
import by.karavay.dao.UserDAO;
import by.karavay.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SignToFaculty implements ActionCommand {

    private final static Logger logger = Logger.getLogger(ActionCommand.class);
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.studentPage");
        String signToFaculty = (String)req.getSession().getAttribute("accToSign");
        Integer id = (Integer)req.getSession().getAttribute("id");
        User user = (User) req.getSession().getAttribute("activeUser");

            int idFaculty = Integer.valueOf(signToFaculty);
            int idStud = Integer.valueOf(id);
            FacultyDAO.setStudentToFaculty(idFaculty, idStud);
            UserDAO userDAO = new UserDAO();
            userDAO.setUserAccountToSession(user, req);
        return page;
    }
}
