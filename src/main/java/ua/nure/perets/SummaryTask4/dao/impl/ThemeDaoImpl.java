package ua.nure.perets.SummaryTask4.dao.impl;

import ua.nure.perets.SummaryTask4.bean.Theme;
import ua.nure.perets.SummaryTask4.dao.ThemeDao;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.nure.perets.SummaryTask4.db.DBManager.*;
import static ua.nure.perets.SummaryTask4.db.DBManager.closeConnection;
import static ua.nure.perets.SummaryTask4.db.DBManager.closePreparedStatement;

public class ThemeDaoImpl implements ThemeDao {

    private static final String ID = "theme_id";
    private static final String NAME = "name";
    private static final String STATUS = "status";

    private static final String UPDATE_THEME_NAME = "UPDATE theme SET name = ? WHERE theme_id = ?";
    private static final String FIND_ALL_THEMES = "SELECT * FROM theme";
    private static final String CREATE_THEME = "INSERT INTO theme VALUES (DEFAULT, ?, DEFAULT)";
    private static final String DELETE_THEME = "DELETE FROM theme WHERE theme_id = ?";
    private static final String UPDATE_THEME_STATUS = "UPDATE theme SET status = ? WHERE  theme_id = ?";


    @Override
    public void updateThemeName(String name, String id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_THEME_NAME);
            int i = 1;
            preparedStatement.setString(i++, name);
            preparedStatement.setString(i++, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update theme name", e);
        }
        closePreparedStatement(preparedStatement);
        closeConnection(connection);
    }

    @Override
    public List<Theme> getThemes() throws DBException {
        List<Theme> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, FIND_ALL_THEMES);
            resultSet = getResultSet(preparedStatement);
            while (resultSet.next()) {
                list.add(getTheme(resultSet));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot get theme", e);
        }
        return list;
    }

    @Override
    public void createTheme(String name) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, CREATE_THEME);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot create theme", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public void deleteTheme(String themeId) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, DELETE_THEME);
            preparedStatement.setInt(1, Integer.parseInt(themeId));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("Cannot delete theme", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
    }

    @Override
    public boolean updateThemeStatusById(int id) throws DBException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Boolean status = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = getPreparedStatement(connection, UPDATE_THEME_STATUS);
            Theme theme = findThemeById(id);
            int i = 1;
            if (theme.isBlocked()) {
                preparedStatement.setBoolean(i++, false);
                status = true;
            } else {
                preparedStatement.setBoolean(i++, true);
                status = false;
            }
            preparedStatement.setInt(i++, theme.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DBException("cannot update theme", e);
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return status;
    }

    public Theme findThemeById(int id) throws DBException {
        Theme theme = null;
        List<Theme> list = getThemes();
        for (Theme el : list) {
            if (el.getId() == id) {
                theme = el;
                break;
            }
        }
        return theme;
    }


    private Theme getTheme(ResultSet resultSet) throws SQLException {
        Theme theme = new Theme();
        theme.setId(resultSet.getInt(ID));
        theme.setName(resultSet.getString(NAME));
        theme.setBlocked(resultSet.getBoolean(STATUS));
        return theme;

    }
}
