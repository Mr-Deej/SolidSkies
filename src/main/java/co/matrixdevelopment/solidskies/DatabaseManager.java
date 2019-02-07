package co.matrixdevelopment.solidskies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DatabaseManager {

    public static DatabaseManager instance;

    public static DatabaseManager getInstance() {
        return instance == null ? (instance = new DatabaseManager()) : instance;
    }

    public void createIslandTableIfNotExists() {
        String createTable = "CREATE TABLE IF NOT EXISTS islands(id int not null identity, uuid varchar(255) not null, level int, x int, y int );";
        try {
            PreparedStatement createStatement = getDB().prepareStatement(createTable);
            createStatement.executeUpdate();
            createStatement.close();
            getDB().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertValuesIntoTable(String table, HashMap<String, String> values) {
        int index = 0;
        String constructed = "INSERT INTO " + table + "(";
        for (String key : values.keySet()) {
            constructed += key + ",";
        }
        constructed += ") VALUES (";
        for (String key : values.keySet()) {
            index++;
            try {
                Integer.parseInt(values.get(key));
                constructed += values.get(key);

            } catch (NumberFormatException e) {
                constructed += "\"" + values.get(key) + "\"";
            }
            if (values.keySet().size() >= index + 1) {
                constructed += ",";
            }
        }
        constructed += ");";
        System.out.println(constructed);
        PreparedStatement insertStatement;
        try {
            insertStatement = getDB().prepareStatement(constructed);
            insertStatement.executeUpdate();
            insertStatement.close();
            getDB().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getValuesFromTable(String table) {
        String sql = "SELECT * FROM " + table + ";";
        PreparedStatement selectStatement;
        ResultSet result = null;
        try {
            selectStatement = getDB().prepareStatement(sql);
            result = selectStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Connection getDB() {
        Connection dbConnection = null;
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            dbConnection = DriverManager.getConnection(
                    "jdbc:hsqldb:" + SolidSkies.getInstance().getDataFolder().getAbsolutePath() + "/maindb", "sa", "");
            dbConnection.setAutoCommit(true);
            return dbConnection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

}