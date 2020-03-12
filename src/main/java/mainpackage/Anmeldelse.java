package mainpackage;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Anmeldelse extends ActiveDomainObject {

  private int anmeldelseid;
  private String tekst;
  private int rating;
  private String anmelder;
  private int filmid;

  public Anmeldelse(String tekst, int rating, String anmelder, int filmid) {
    this.tekst = tekst;
    this.rating = rating;
    this.anmelder = anmelder;
    this.filmid = filmid;
  }

  public Anmeldelse(int id) {
    this.anmeldelseid = id;
  }

  public static void getAll(Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "select * from anmeldelse";
      System.out.println(query);

      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        int id = rs.getInt("id");
        String tekst = rs.getString("tekst");
        int rating = rs.getInt("rating");
        String anmelder = rs.getString("anmelder");
        int filmid = rs.getInt("filmid");

        System.out.println(String.format("%d : %s : %d : %s : %d", id, tekst, rating, anmelder, filmid));

      }
    } catch (Exception e) {
      System.out.println("db error during select of anmeldelse= " + e);
      return;
    }
  }

  public void initialize(Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from anmeldelse where id=" + this.anmeldelseid);

      if(!rs.next()) {
        System.out.println("Anmeldelse not found!");
        return;
      }
      rs.previous();
      while (rs.next()) {
        anmeldelseid = rs.getInt("id");
        tekst = rs.getString("tekst");
        rating = rs.getInt("rating");
        anmelder = rs.getString("anmelder");
        filmid = rs.getInt("filmid");
        System.out.println(anmeldelseid + "  " + tekst + "  " + rating + "  " + anmelder + "   " + filmid);
      }

    } catch (Exception e) {
      System.out.println("db error during select of anmeldelse= " + e);
      return;
    }
  }

  @Override
  public void refresh(Connection conn) {
    initialize(conn);
  }

  @Override
  public void save(Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "insert into anmeldelse values (NULL,'" + tekst + "'," + rating + ",'" +
          anmelder + "'," + filmid + ")";
      stmt.executeUpdate(query);
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}
