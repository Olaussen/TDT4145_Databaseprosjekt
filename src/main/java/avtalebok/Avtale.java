package avtalebok;

/**
 *
 * @author sveinbra
 */

import java.sql.*;
import java.util.*;

public class Avtale extends ActiveDomainObject {
    private ArrayList<Bruker> brukere;
    private int aid;
    private int startTid;
    private int timer;
    private int type;
    public static final int MÃ˜TE=1;
    public static final int PROGRAM=2;
    private static final int NOID = -1;
    
    public Avtale (int startTid, int timer, int type) {
        aid = NOID;
        this.startTid = startTid;
        this.timer = timer;
        this.type = type;
        brukere = new ArrayList<Bruker>();
    }

    public void regBruker (int bid, Connection conn) {
        Bruker b = new Bruker (bid);
        b.initialize (conn);
        brukere.add(b);
    }
    
    public void regTid (int startTid, int timer) {
        this.startTid = startTid;
        this.timer = timer;
    }
    
    @Override
    public void initialize (Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select starttid, timer, alarmtype from Avtale where aid=" + aid);
            while (rs.next()) {
                startTid =  rs.getInt("starttid");
                timer = rs.getInt("timer");
                type = rs.getInt("avtaletype");
            }

        } catch (Exception e) {
            System.out.println("db error during select of avtale= "+e);
            return;
        }
    
    }
    
    @Override
    public void refresh (Connection conn) {
        initialize (conn);
    }
    
    @Override
    public void save (Connection conn) {
        try {    
            Statement stmt = conn.createStatement(); 
            stmt.executeUpdate("insert into Avtale values (NULL,"+startTid+","+timer+","+type+")");
        } catch (Exception e) {
            System.out.println("db error during insert of Avtale="+e);
            return;
        }
        for (int i=0;i<brukere.size();i++) {
            try {    
                Statement stmt = conn.createStatement(); 
                stmt.executeUpdate("insert into HarAvtale values ("+brukere.get(i).getBid()+",LAST_INSERT_ID())");
            } catch (Exception e) {
                System.out.println("db error during insert of HarAvtale="+e);
                return;
            }
        }
    }
}
