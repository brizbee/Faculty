package by.karavay.factory;


import by.karavay.MessageManager;
import by.karavay.command.ActionCommand;
import by.karavay.command.CommandEnum;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private final static Logger logger = Logger.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest req){
        ActionCommand current = null;
        String action = req.getParameter("command");
        if (action == null || action.isEmpty()){
            return current;
        }

        try{
            String act = action;
            String accToSign = null;
            String facToStart = null;
            logger.info(action);
            if (action.contains("signToFaculty")){
                accToSign = action.substring(13);
                logger.info(accToSign);
                act = "signToFaculty";
                req.getSession().setAttribute("accToSign", accToSign);
            }
            if (action.contains("startFac")) {
                facToStart = action.substring(8);
                act = "startFac";
                req.getSession().setAttribute("facToStart", facToStart);
            }
            CommandEnum currentEnum = CommandEnum.valueOf(act.toUpperCase());
            current = currentEnum.getCurrentCommand();
        }catch (IllegalArgumentException ex) {
            req.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}
