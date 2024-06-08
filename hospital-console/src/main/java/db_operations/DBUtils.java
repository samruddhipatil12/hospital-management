package db_operations;

import dto.LoginRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

  static Connection con;
  static Statement stmt;

  static {
    try {
      con =
          DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/directory_service_db", "root", "Samruddhi@12");
      stmt = con.createStatement();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void executeQuery(String query) {
    try {
      stmt.execute(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Select
  public static ResultSet executeQueryGetResult(String query) {
    ResultSet resultset = null;
    try {
      resultset = stmt.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resultset;
  }

  public static LoginRequest getLoginCredentials() throws SQLException {
    LoginRequest loginRequest = new LoginRequest();
    String query = "SELECT mobile_number, password FROM user_credentials WHERE user_id = 1";

    ResultSet resultSet = executeQueryGetResult(query);
    if (resultSet.next()) {
      loginRequest.setMobileNumber(resultSet.getString("mobile_number"));
      loginRequest.setPassword(resultSet.getString("password"));
    }
    resultSet.close();
    return loginRequest;
  }
}
