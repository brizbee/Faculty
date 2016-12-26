package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.dao.UserDAO;
import by.karavay.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UpdateFaculty implements ActionCommand {
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.studentPage");
        String role = (String) req.getSession().getAttribute("role");
        User user = (User) req.getSession().getAttribute("activeUser");
        if (role.equals("teacher")){
            page = ConfigurationManager.getProperty("path.page.tutorPage");
        }
        UserDAO userDAO = new UserDAO();
        userDAO.setUserAccountToSession(user, req);
        return page;
    }
}
