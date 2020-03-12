package mainpackage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyCategoryCtrl extends DBConn {
  private Videomedia film;

  public CompanyCategoryCtrl() {
    connect();
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      System.out.println("db error during setAuoCommit of CompanyCategoryCtrl=" + e);
      return;
    }
  }

  public void getList() {
    List<String> categories = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      String query = "select distinct kategorinavn from kategori";
      System.out.println(query);

      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String cat = rs.getString("kategorinavn");
        categories.add(cat);
      }

      for (String category : categories) {
        query =
            "select kategorinavn, selskapurl, count(v.selskapurl) as t2 from kategori k join videomedia v on  v.id = k.filmId WHERE kategorinavn = "
                + "'" + category + "'" + "group by selskapurl, kategorinavn LIMIT 1";
        rs = stmt.executeQuery(query);
        while (rs.next()) {
          String cat = rs.getString("kategorinavn");
          String url = rs.getString("selskapurl");

          System.out.format("Category: %11s | Company: %10s\n", cat, url);
        }
      }

    } catch (Exception e) {
      System.out.println("db error during getting of ccc= " + e);
      return;
    }
  }
}
