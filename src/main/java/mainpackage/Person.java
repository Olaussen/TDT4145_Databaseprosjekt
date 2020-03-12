package mainpackage;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Person {

  private int personid;
  private String navn;
  private int fodselsaar;
  private String fodselsland;

  public Person(int personid) {
    this.personid = personid;
  }

  public int getId() {
    return this.personid;
  }

  public void initialize(Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from person where id=" + this.personid);

      if(!rs.next()) {
        System.out.println("User not found!");
        return;
      }
      rs.previous();
      while (rs.next()) {
        navn = rs.getString("navn");
        fodselsaar = rs.getInt("fodselsaar");
        fodselsland = rs.getString("fodselsland");
        System.out.println(personid + "  " + navn + "  " + fodselsaar + "  " + fodselsland);
      }

    } catch (Exception e) {
      System.out.println("db error during select of bruker= " + e);
      return;
    }
  }


}
