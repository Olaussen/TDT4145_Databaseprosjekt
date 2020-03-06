package mainpackage;

import java.sql.ResultSet;
import java.sql.Statement;

public class SkuespillerIFilmCtrl extends DBConn {

  public void printSkuespillerIFilm (String skuespillerNavn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "select distinct videomedia.tittel from videomedia join medvirkningsrolle on " +
          "videomedia.id = medvirkningsrolle.filmid join person on medvirkningsrolle.personid = person.id " +
          "where person.navn = '" + skuespillerNavn + "' " + "and medvirkningsrolle.medvirkningsrolle = " + "'Skuespiller'";

      ResultSet rs = stmt.executeQuery(query);
      int nr = 1;
      System.out.println("Resultatliste for skuespiller "+ skuespillerNavn);
      System.out.println(skuespillerNavn + " spiller i filmen(e): ");
      while (rs.next()) {
        System.out.println(" " + nr++ + " " +  rs.getString("tittel"));
      }

      if(nr == 1) {
        System.out.println(skuespillerNavn + " spiller ikke i noen filmer");
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
