package stud.ntnu.idatt1005.pantrypal.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQL {

  private static final String database = "jdbc:sqlite:src/main/resources/db/database.sqlite";

  public static List<Map<String, Object>> executeQuery(String query, Object... params) {
    List<Map<String, Object>> resultList = new ArrayList<>();
    try (Connection connection = DriverManager.getConnection(database);
        PreparedStatement statement = connection.prepareStatement(query)) {

      for(int i = 0; i < params.length; i++) {
        statement.setObject(i + 1, params[i]);
      }

      try (ResultSet rs = statement.executeQuery()) {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
          Map<String, Object> row = new HashMap<>();
          for (int i = 1; i <= columnCount; i++) {
            String colName = rsmd.getColumnName(i);
            Object colValue = rs.getObject(i);
            row.put(colName, colValue);
          }
          resultList.add(row);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return resultList;
  }

  public static int executeUpdate(String query, Object... params) {
    try (Connection connection = DriverManager.getConnection(database);
        PreparedStatement statement = connection.prepareStatement(query)) {

      for(int i = 0; i < params.length; i++) {
        statement.setObject(i + 1, params[i]);
      }

      int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("No rows affected");
      }

      return affectedRows;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public static int executeUpdateWithGeneratedKeys(String query, Object... params){
    try (Connection connection = DriverManager.getConnection(database);
        PreparedStatement statement = connection.prepareStatement(query)) {

      for(int i = 0; i < params.length; i++) {
        statement.setObject(i + 1, params[i]);
      }

      int affectedRows = statement.executeUpdate();

      if (affectedRows == 0) {
        throw new SQLException("No rows affected");
      }

      try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          return generatedKeys.getInt(1);
        } else {
          throw new SQLException("Creating user failed, no ID obtained.");
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
