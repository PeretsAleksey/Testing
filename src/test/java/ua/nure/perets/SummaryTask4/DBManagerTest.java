package ua.nure.perets.SummaryTask4;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import ua.nure.perets.SummaryTask4.db.DBManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManagerTest {

    private static DBManager dbManager;
    private Connection connection;


    @BeforeClass
    public static void constructorTest() {
        dbManager = new DBManager();
    }

    @Before
    public void before() throws SQLException {
        connection = DBManager.getConnection();
    }

    @After
    public void after() {
        DBManager.closeConnection(connection);
    }
}
