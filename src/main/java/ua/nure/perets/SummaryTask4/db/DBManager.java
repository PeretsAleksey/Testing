package ua.nure.perets.SummaryTask4.db;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

public class DBManager {

    private static InitialContext ic;
    private static DataSource ds;

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/testing?verifyServerCertificate=false&useSSL=true";

    private static DBManager instance;

    public DBManager() {
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

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
    }

    /*public static Connection getConnection() throws SQLException {

        try {
            ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:/comp/env/jdbc/testing");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds.getConnection();
    }*/

    public static PreparedStatement getPreparedStatement(Connection connection, String query) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static ResultSet getResultSet(PreparedStatement preparedStatement) {
        ResultSet resultSet = null;
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


