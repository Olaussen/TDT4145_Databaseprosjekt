package mainpackage;

import java.sql.ResultSet;
import java.sql.Statement;

public class SkuespillerRolleCtrl extends DBConn {

  public void printSkuespillerRolle (String skuespillerNavn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "select medvirkningsrolle.rollenavn from medvirkningsrolle join person on " +
          "person.id = medvirkningsrolle.personid where person.navn = '" + skuespillerNavn +"' " +
          "and medvirkningsrolle.medvirkningsrolle = " + "'Skuespiller'";
      System.out.println(query);


      ResultSet rs = stmt.executeQuery(query);
      int nr = 1;
      System.out.println("Resultatliste for skuespiller "+ skuespillerNavn);
      System.out.println("Jonathan Brooks har rollen(e)");
      while (rs.next()) {
        System.out.println(" " + nr++ + " " +  rs.getString("rollenavn"));
      }

    } catch (Exception e) {
      System.out.println("db error during select of skuespiller = "+e);
    }

  }
  public void printKlasseStrekktid (String klasseNavn) {

  }
  public void printAlleKlasserResultat () {

  }
  public void printAlleKlasserStrekktid () {

  }
}
