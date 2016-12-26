package by.karavay.command;

import by.karavay.ConfigurationManager;
import by.karavay.MessageManager;
import by.karavay.dao.UserDAO;
import by.karavay.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class SigninCommand implements ActionCommand {
    private final static Logger logger = Logger.getLogger(SigninCommand.class);

    private static final String ID = "id";
    private static final String FIO = "fio";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "passwordRepeat";
    private static final String ERROR_IN_PASSWORD = "errorInPass";
    private static final String ERROR_IN_LOGIN = "errorInLogin";
    private static final String ROLE = "role";
    private static final String ERROR_IN_SIGNIN = "errorSIGNIN";
    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty("path.page.signin");
        String fio = req.getParameter(FIO);
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String repeatPassword = req.getParameter(REPEAT_PASSWORD);
        String role = req.getParameter(ROLE);
        if (fio.length() <= 0){
            req.setAttribute(ERROR_IN_LOGIN, MessageManager.getProperty("message.fioerror"));
        }
        else if (password.length() > 0 && password.equals(repeatPassword)){
            UserDAO userDAO = new UserDAO();
            User client = userDAO.checkLogin(login, password, req);
            if (client == null){
                if (userDAO.registrateUser(fio, login, password, role)){
                    page = ConfigurationManager.getProperty("path.page.success");
                }
                else {
                    req.setAttribute(ERROR_IN_SIGNIN, "Error in signin");
                }
            }
            else {
                req.setAttribute(ERROR_IN_LOGIN, MessageManager.getProperty("message.onlyloginerror"));
            }
        }
        else {
            req.setAttribute(ERROR_IN_PASSWORD, MessageManager.getProperty("message.onlypassworderror"));
        }
        return page;
    }
}
