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
      p.put("user", "myuser");
      p.put("password", "mypassword");
      //            conn = DriverManager.getConnection("jdbc:mysql://mysql.ansatt.ntnu.no/sveinbra_ektdb?autoReconnect=true&useSSL=false",p);
      conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ekt?autoReconnect=true&useSSL=false",p);
    } catch (Exception e)
    {
      throw new RuntimeException("Unable to connect", e);
    }
  }
}
