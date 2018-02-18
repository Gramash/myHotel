package Commands.generalCommands;

import Commands.Command;
import Commands.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class HomeViewCommand extends  Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return Paths.JSP_HOME;
    }
}
