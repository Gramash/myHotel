package Commands;

import Commands.customerTask.*;
import Commands.generalCommands.LoginCommand;
import Commands.generalCommands.LogoutCommand;
import Commands.generalCommands.ProductViewCommand;
import Commands.generalCommands.RegisterCommand;
import Commands.managerTask.FilterApplications;
import Commands.managerTask.ManagerTaskCommand;
import Commands.managerTask.SubmitSuggestionCommand;
import Commands.managerTask.UpdateProductCommand;

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
