package ua.nure.perets.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
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
    }


    public static Command get(String command) {
        return commands.get(command);
    }
}
