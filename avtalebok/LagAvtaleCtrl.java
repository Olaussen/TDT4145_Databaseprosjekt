package avtalebok;

/**
 *
 * @author sveinbra
 */

import java.sql.*;
import java.util.*;

public class LagAvtaleCtrl extends DBConn {
    private Avtale avtale;
    private int startMuligTid;
    private int sluttMuligTid;

    public LagAvtaleCtrl () {
        connect();
        // La laging av avtale vÃ¦re en transaksjon
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("db error during setAuoCommit of LagAvtaleCtrl="+e);
            return;
        }
    }

    public void lagAvtale (int startMTid, int sluttMTid){
        avtale = new Avtale (startMTid,0,Avtale.MÃ˜TE);
        this.startMuligTid = startMTid;
        this.sluttMuligTid = sluttMTid;
    }
    public void velgBruker (int bid) {
        avtale.regBruker (bid, conn);
        // Vis hvilke tider som er aktuelle, gjÃ¸r noe her .....
    }
    public void velgTidspunkt (int startTid, int antall) {
        avtale.regTid(startTid, antall);
    }
    public void lagAlarm (String alarmTekst) {
        
    }
    public void fullførAvtale () {
        avtale.save(conn);
        try {
            conn.commit();
        } catch (SQLException e) {
            System.out.println("db error during commit of LagAvtaleCtrl="+e);
            return;
        }
    }
}
