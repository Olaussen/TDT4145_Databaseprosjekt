package mainpackage;

import java.sql.ResultSet;
import java.sql.Statement;

public class SkuespillerRolleCtrl extends DBConn {

  public void printSkuespillerRolle (String skuespillerNavn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "select distinct medvirkningsrolle.rollenavn, videomedia.tittel from medvirkningsrolle join person on " +
          "person.id = medvirkningsrolle.personid join videomedia on medvirkningsrolle.filmid = videomedia.id where person.navn = '" + skuespillerNavn +"' " +
          "and medvirkningsrolle.medvirkningsrolle = " + "'Skuespiller'";


      ResultSet rs = stmt.executeQuery(query);
      int nr = 1;
      System.out.println("\nResultatliste for skuespiller "+ skuespillerNavn);
      System.out.println(skuespillerNavn + " har rollen(e):");
      while (rs.next()) {
        System.out.println(" " + nr++ + " " +  rs.getString("rollenavn") + " i filmen "
                           + rs.getString("tittel"));
      }
      if(nr == 1){
        System.out.println(skuespillerNavn + " har ingen aktive roller");
      }

    } catch (Exception e) {
      System.out.println("db error during select of skuespiller = "+e);
    }
  }
}
