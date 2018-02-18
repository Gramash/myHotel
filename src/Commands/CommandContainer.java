package Commands;

import Commands.customerTask.*;
import Commands.generalCommands.*;
import Commands.managerTask.*;
import Commands.generalCommands.LoginCommand;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer  {

//        private static final Logger LOG = Logger.getLogger(CommandContainer.class);

        private static Map<String, Command> commands = new TreeMap<String, Command>();

        static {
            // common Commands
            commands.put("login", new LoginCommand());
            commands.put("logout", new LogoutCommand());
            commands.put("register", new RegisterCommand());
            commands.put("home", new HomeViewCommand());
            commands.put("registerView", new RegisterViewCommand());
            commands.put("loginView", new LoginViewCommand());

            // client Commands
            commands.put("productView", new ProductViewCommand());
            commands.put("bookARoom", new BookARoomCommand());
            commands.put("makeApplication", new MakeApplicationCommand());
            commands.put("dashboard", new DashboardCommand());
            commands.put("cancelApp", new CancelApplication());
            commands.put("confirmOrder", new ConfirmOrder());

            //manager Commands
            commands.put("managerTask", new ManagerTaskCommand());
            commands.put("updateProduct", new UpdateProductCommand());
            commands.put("filterSuggestions", new FilterApplications());
            commands.put("submitSuggestion", new SubmitSuggestionCommand());

        }


        public static Command get(String commandName) {
            if (commandName == null || !commands.containsKey(commandName)) {
                return commands.get("noCommand");
            }

            return commands.get(commandName);
        }
}
