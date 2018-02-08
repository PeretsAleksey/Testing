package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);


    private static Map<String, Command> commands = new TreeMap<>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("RegistrationPage", new RegistrationPageCommand());
        commands.put("registration", new RegistrationCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("blockUser", new BlockUserCommand());
        commands.put("setting", new SettingsCommand());
        commands.put("homepage", new HomePageCommand());
        commands.put("getUsers", new GetUsersCommand());
        commands.put("refactorPage", new RefactorPageCommand());
        commands.put("refactor", new RefactorCommand());
        commands.put("answerStatus", new AnswerStatusCommand());
        commands.put("blockTheme", new BlockThemeCommand());
        commands.put("editQuestion", new EditQuestionCommand());
        commands.put("result", new GetResultCommand());
        commands.put("editTest", new EditTestCommand());
        commands.put("getTests", new GetTestsCommand());
        commands.put("passTest", new PassTestCommand());
        commands.put("reports", new GeneratorReportsCommand());
        commands.put("changeLocale", new ChangeLocaleCommand());
        commands.put("noCommand", new NoCommand());
        commands.put("loginpage", new LoginPageCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }


    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}
