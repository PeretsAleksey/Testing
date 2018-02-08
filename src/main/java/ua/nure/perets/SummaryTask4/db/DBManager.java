package ua.nure.perets.SummaryTask4.db;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DB manager. Works with MySQL DB. Only the required DAO methods are defined!
 *
 * @author A.Perets
 */
public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static DataSource ds;

    /*private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/testing?verifyServerCertificate=false&useSSL=true";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }*/

    /**
     * gets connection with DB
     *
     * @return Connections
     * @throws SQLException if cannot obtain connection
     */
    public static Connection getConnection() throws SQLException {

        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/testing");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException e) {
            LOG.error("Cannot obtain connection", e);
        }
        return ds.getConnection();
    }

    /**
     * gets PreparedStatement
     *
     * @return PreparedStatement
     */
    public static PreparedStatement getPreparedStatement(Connection connection, String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            LOG.error("Cannot obtain prepareStatement", e);
        }
        return preparedStatement;
    }

    /**
     * gets ResultSet
     *
     * @return ResultSet
     */
    public static ResultSet getResultSet(PreparedStatement preparedStatement) {
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOG.error("Cannot obtain resultSet", e);
        }
        return resultSet;
    }

    /**
     * closes ResultSet
     *
     * @param resultSet Connection
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOG.error("Cannot close resultSet", e);
            }
        }
    }

    /**
     * closes PreparedStatement
     *
     * @param preparedStatement Connection
     */
    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOG.error("Cannot preparedStatement resultSet", e);
            }
        }
    }

    /**
     * closes Connection
     *
     * @param connection Connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error("Cannot connection resultSet", e);
            }
        }
    }

    /**
     * rollback, if transaction was failed
     *
     * @param connection Connection
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOG.error("Cannot rollback transaction", e);
            }
        }
    }
}


