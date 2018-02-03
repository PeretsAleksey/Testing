package ua.nure.perets.SummaryTask4.dao;

import ua.nure.perets.SummaryTask4.bean.Theme;
import ua.nure.perets.SummaryTask4.exeption.DBException;

import java.util.List;

public interface ThemeDao {

    void updateThemeName(String name, String id) throws DBException;

    List<Theme> getThemes() throws DBException;

    void createTheme(String name) throws DBException;

    void deleteTheme(String themeId) throws DBException;

    boolean updateThemeStatusById(int id) throws DBException;

    Theme findThemeById(int id) throws DBException;

}