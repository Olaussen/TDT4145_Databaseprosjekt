package mainpackage;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import java.sql.SQLException;

public class AddReviewCtrl extends DBConn{

  private Anmeldelse anmeldelse;

  public AddReviewCtrl() {
    connect();
    // La laging av avtale vÃ¦re en transaksjon
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      System.out.println("db error during setAuoCommit of AddVideoMediaCtrl=" + e);
      return;
    }
  }

  public void lagAnmeldelse(String tekst, int rating, String anmelder, int filmid) {
    anmeldelse = new Anmeldelse(tekst, rating, anmelder, filmid);
  }

  public void getAnmeldelse(int id){
    Anmeldelse p = new Anmeldelse(id);
    p.initialize(conn);
  }

  public void getAll() {
    Anmeldelse.getAll(conn);
  }


  public void fullfor() {
    anmeldelse.save(conn);
    try {
      conn.commit();
    } catch (SQLException e) {
      System.out.println("db error during commit of LagAvtaleCtrl=" + e);
      return;
    }
  }
}
