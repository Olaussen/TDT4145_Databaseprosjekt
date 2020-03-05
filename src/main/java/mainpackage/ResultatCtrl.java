package mainpackage;

import java.sql.ResultSet;
import java.sql.Statement;

public class ResultatCtrl extends DBConn {

  public void printKlasseResultat (String klasseNavn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "select navn, klubb, lopstid from Loper where klasse='"+klasseNavn+"' and status='ok' order by lopstid";
      //System.out.println(query);

      ResultSet rs = stmt.executeQuery(query);
      int nr = 1;
      System.out.println("Resultatliste for klasse "+klasseNavn);
      while (rs.next()) {
        System.out.println(" " + nr++ + " "+ rs.getString("navn") +
            " " + rs.getString("klubb") + " " + rs.getInt("lopstid"));
      }

    } catch (Exception e) {
      System.out.println("db error during select of loper = "+e);
    }

  }
  public void printKlasseStrekktid (String klasseNavn) {

  }
  public void printAlleKlasserResultat () {

  }
  public void printAlleKlasserStrekktid () {

  }
}
