package avtalebok;

/**
 *
 * @author sveinbra
 */
public class AvtaleBok {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LagAvtaleCtrl lagAvtaleCtrl = new LagAvtaleCtrl ();
        lagAvtaleCtrl.lagAvtale(100, 120);
        lagAvtaleCtrl.velgBruker(1);
        lagAvtaleCtrl.velgBruker(2);
        lagAvtaleCtrl.velgTidspunkt(110, 1);
        lagAvtaleCtrl.fullforAvtale();
    }
}
