package mainpackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Serie {

  public static void getEpisodes(Connection conn, String title) {
    List<Integer> episodeIds = new ArrayList<>();
    try {
      Statement stmt = conn.createStatement();
      String query = "select id from serie where serietittel = " + "'"+title+"'";
      ResultSet rs = stmt.executeQuery(query);
      int id = 0;
      while (rs.next()) {
        id = rs.getInt("id");
      }
      if(id == 0) {
        System.out.println("Series does not exist");
      }
      int nr = 0;
      query = "select filmid from episodeiserie where serieid = " + id;
      rs = stmt.executeQuery(query);
      System.out.println(query);
      while (rs.next()) {
        nr++;
        int filmid = rs.getInt("filmid");
        episodeIds.add(filmid);
      }

      if(nr == 0){
        System.out.println("No episodes exists for: " + title);
      }

      for (Integer eid : episodeIds) {
        query = "select from videomedia where id = " + eid;
        System.out.println(query);
        rs = stmt.executeQuery(query);
        System.out.println(query);
        System.out.println("Episoder for: " + title);
        while (rs.next()) {
          System.out.println("ID: " + eid + " " + rs.getString("tittel"));
        }
      }

    } catch (Exception e) {
      System.out.println("db error during the getting of episodes from the series= " + e);
      e.printStackTrace();
      return;
    }
  }
}

