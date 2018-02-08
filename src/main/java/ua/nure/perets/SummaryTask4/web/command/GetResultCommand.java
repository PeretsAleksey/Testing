package ua.nure.perets.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.perets.SummaryTask4.Path;
import ua.nure.perets.SummaryTask4.bean.User;
import ua.nure.perets.SummaryTask4.bean.UserTest;
import ua.nure.perets.SummaryTask4.dao.impl.UserTestDaoImpl;
import ua.nure.perets.SummaryTask4.exeption.AppException;
import ua.nure.perets.SummaryTask4.utils.Comparators;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GetResultCommand extends Command {

    private static final Logger LOG = Logger.getLogger(GetResultCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException, SQLException {

        LOG.debug("Command starts");

        String page = Path.TEST_RESULT_PAGE;

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");
        LOG.trace("Session attribute: user --> " + user);

        UserTestDaoImpl userTestDao = new UserTestDaoImpl();

        List<UserTest> testList;

        if (user.getRoleId() == 0) {
            testList = userTestDao.getUsersTests();
        } else {
            testList = userTestDao.findUsersTestsByUserId((Integer) session.getAttribute("userId"));
        }

        Collections.sort(testList, new Comparators.CompareByUsersTestsId());

        session.setAttribute("testsResult", testList);
        LOG.trace("Set the session attribute: testsResult --> " + testList);

        LOG.debug("Command finished");

        return page;
    }
}
