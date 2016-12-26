package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.MessageManager;
import by.karavay.dao.UserDAO;
import by.karavay.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand{

    private final static Logger logger = Logger.getLogger(LoginCommand.class);

    private static final String ID = "id";
    private static final String FIO = "fio";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String IS_SIGNED = "isSignedIn";
    private static final String ACTIVE_USER = "activeUser";

    @Override
    public String execute(HttpServletRequest req) {
        String page = null;
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        UserDAO userDAO = new UserDAO();
        User user = userDAO.checkLogin(login, password, req);

        if (user != null){
            req.getSession().setAttribute(ID, user.getId());
            req.getSession().setAttribute(FIO, user.getName());
            req.getSession().setAttribute(LOGIN, user.getLogin());
            req.getSession().setAttribute(PASSWORD, user.getPassword());
            req.getSession().setAttribute(ROLE, user.getRole());
            req.getSession().setAttribute(IS_SIGNED, true);
            req.getSession().setAttribute(ACTIVE_USER, user);
            userDAO.setUserAccountToSession(user, req);
            if (user.getRole().toLowerCase().equals("teacher"))
            {
                page = ConfigurationManager.getProperty("path.page.tutorPage");
                userDAO.setTeacherToSession(user, req);
            }
            else if (user.getRole().toLowerCase().equals("student"))
            {
                page = ConfigurationManager.getProperty("path.page.studentPage");
                userDAO.setUserAccountToSession(user, req);
            }
            else
                page = ConfigurationManager.getProperty("path.page.admin");
        } else {
            req.setAttribute("errorLoginOrPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
