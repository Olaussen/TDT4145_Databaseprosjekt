package mainpackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddVideoMediaCtrl extends DBConn {

  private Videomedia film;

  public AddVideoMediaCtrl () {
    connect();
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      System.out.println("db error during setAuoCommit of AddVideoMediaCtrl="+e);
      return;
    }
  }

  public void lagVideomedia (String tittel, String lagetfor, int lengde, String lanseringsdato,
                             int utgivelsesaar, String beskrivelse, int utgittSomVideo,
                             String selskapsURL, List<Person> manusforfatter, Person regissor,
                             List<Skuespiller> skuespillere, List<String> categories){
    film = new Videomedia (tittel, lagetfor, lengde, lanseringsdato, utgivelsesaar, beskrivelse,
        utgittSomVideo, selskapsURL, manusforfatter, regissor, skuespillere, categories);
  }

  public void fullfor () {
    film.save(conn);
    try {
      conn.commit();
    } catch (SQLException e) {
      System.out.println("db error during commit of avmc="+e);
      return;
    }
  }
}
