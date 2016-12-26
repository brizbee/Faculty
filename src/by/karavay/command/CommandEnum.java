package by.karavay.command;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SIGNIN {
        {
            this.command = new SigninCommand();
        }
    },
    GETSTUDENTFACULTY{
        {
            this.command = new GetStudentFaculty();
        }
    },
    GETSTARTEDFACULTY{
        {
            this.command = new GetStartedFaculty();
        }
    },
    UPDATEFAC{
        {
            this.command = new UpdateFaculty();
        }
    },
    GETMYMARKS{
        {
            this.command = new GetMyMarks();
        }
    },
    SIGNTOFACULTY {
        {
            this.command = new SignToFaculty();
        }
    },
    GETFACTEACH{
        {
            this.command = new UpdateFacultyForTeach();
        }
    },
    STARTFAC {
        {
            this.command = new StartFaculty();
        }
    },
    GETENDTEACH{
        {
            this.command = new GetEndedCoursesForTeacher();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}
