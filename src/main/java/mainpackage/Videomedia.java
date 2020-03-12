package mainpackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Videomedia extends ActiveDomainObject {
  private int id;
  private String tittel;
  private String lagetfor;
  private int lengde;
  private String lanseringsdato;
  private int utgivelsesaar;
  private String beskrivelse;
  private int utgittSomVideo;
  private String selskapsURL;
  private List<Person> manusForfattere;
  private Person regissor;
  private List<Skuespiller> skuespillere;
  private List<String> categories;

  public Videomedia(String tittel, String lagetfor, int lengde, String lanseringsdato,
                    int utgivelsesaar, String beskrivelse, int utgittSomVideo, String selskapsURL,
                    List<Person> manusForfattere, Person regissor, List<Skuespiller> skuespillere,
                    List<String> categories) {
    this.tittel = tittel;
    this.lagetfor = lagetfor;
    this.lengde = lengde;
    this.lanseringsdato = lanseringsdato;
    this.utgivelsesaar = utgivelsesaar;
    this.beskrivelse = beskrivelse;
    this.utgittSomVideo = utgittSomVideo;
    this.selskapsURL = selskapsURL;
    this.manusForfattere = manusForfattere;
    this.regissor = regissor;
    this.skuespillere = skuespillere;
    this.categories = categories;
  }

  public static void getAll(Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      String query = "select * from videomedia";
      System.out.println(query);

      ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
        String id = rs.getString("id");
        String tittel = rs.getString("tittel");
        String lagetfor = rs.getString("lagetfor");
        String lengde = rs.getString("lengde");
        String lanseringsdato = rs.getString("lanseringsdato");
        String utgivelsesaar = rs.getString("utgivelsesaar");
        String beskrivelse = rs.getString("beskrivelse");
        String utgittsomvideo = rs.getString("utgittsomvideo");
        String selskapsURL = rs.getString("selskapurl");

        System.out.println(String.format("%s : %s : %s : %s : %s : %s : %s : %s : %s", id, tittel, lagetfor, lengde, lanseringsdato, utgivelsesaar, beskrivelse, utgittsomvideo, selskapsURL));

      }
    } catch (Exception e) {
      System.out.println("db error during insert of videomedia= " + e);
      return;
    }
  }


  public int getFilmId(String tittel, Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from videomedia where tittel = '" + tittel + "'");
      while (rs.next()) {
        return rs.getInt("id");
      }
    } catch (Exception e) {
      System.out.println("neger as");
    }
    return 0;
  }

  @Override
  public void initialize(Connection conn) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from videomedia where tittel='" + tittel + "'");
      while (rs.next()) {
        id = rs.getInt("id");
        tittel = rs.getString("tittel");
        lagetfor = rs.getString("lagetfor");
        lengde = rs.getInt("lengde");
        lanseringsdato = rs.getString("lanseringsdato");
        utgivelsesaar = rs.getInt("utgivelsesaar");
        beskrivelse = rs.getString("beskrivelse");
        utgittSomVideo = rs.getInt("utgittsomvideo");
        selskapsURL = rs.getString("selskapurl");
      }

    } catch (Exception e) {
      System.out.println("db error during select of avtale= " + e);
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
      String query = "insert into videomedia values (NULL,'" + tittel + "','" + lagetfor + "'," +
          lengde + ",'" + lanseringsdato + "'," + utgivelsesaar + ",'" + beskrivelse + "'," +
          utgittSomVideo + ",'" + selskapsURL + "')";
      stmt.executeUpdate(query);
      int id = getFilmId(tittel, conn);

      if (id == 0) throw new IllegalArgumentException("Film does not exist");

      String skuespillerstring = "Skuespiller";
      for (Skuespiller skuespiller : skuespillere) {
        query = "insert into medvirkningsrolle (filmid, personid, medvirkningsrolle, rollenavn) values (" + id + ", " + skuespiller.getId() + ", " + "'" + skuespillerstring + "'" + ", " + "'" + skuespiller.getRolle() + "'" + ");";
        stmt.executeUpdate(query);
      }

      String manusforfatterstring = "Manusforfatter";
      for (Person manusforfatter : manusForfattere) {
        query = "insert into medvirkningsrolle (filmid, personid, medvirkningsrolle) values (" + id + ", " + manusforfatter.getId() + ", " + "'" + manusforfatterstring + "'" + ");";
        stmt.executeUpdate(query);
      }

      String regissorstring = "Regissor";
      query = "insert into medvirkningsrolle (filmid, personid, medvirkningsrolle) values (" + id + ", " + regissor.getId() + ", " + "'" + regissorstring + "'" + ");";
      stmt.executeUpdate(query);

      for (String cat : categories) {
        query = "insert into kategori values ('"+ cat + "'"+","+ id + ");";
        stmt.executeUpdate(query);
      }

    } catch (Exception e) {
      e.printStackTrace();
      return;
    }


  }
}

