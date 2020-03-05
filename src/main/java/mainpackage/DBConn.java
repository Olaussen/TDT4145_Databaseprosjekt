package mainpackage;

import java.sql.*;
import java.util.Properties;

public abstract class DBConn {
  protected Connection conn;
  public DBConn () {
  }
  public void connect() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      // Class.forName("com.mysql.cj.jdbc.Driver"); when you are using MySQL 8.0.
      // Properties for user and password.
      Properties p = new Properties();
      p.put("user", "jonatbr_demo");
      p.put("password", "12345678");
      conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/jonatbr_databaseprosjekt", p);
    } catch (Exception e)
    {
      throw new RuntimeException("Unable to connect", e);
    }
  }}
