package mainpackage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    // TODO code application logic her
       /* RegMaalCtrl maalCtrl = new RegMaalCtrl ();
        maalCtrl.connect();
        maalCtrl.startReg(123123);
        maalCtrl.regPost(0, 0, 70);
        maalCtrl.regPost(1, 31, 100);
        maalCtrl.regPost(2, 32, 120);
        maalCtrl.regPost(3, 33, 140);
        maalCtrl.regPost(4, 34, 160);
        maalCtrl.regPost(5, 35, 180);
        maalCtrl.regPost(6, 36, 200);
        maalCtrl.regPost(7, 37, 220);
        maalCtrl.regPost(8, 150, 230);
        maalCtrl.regPost(9, 175, 245);
        if (maalCtrl.sluttReg()) {
          System.out.println("Profit!!");
        }

    SkuespillerRolleCtrl resultatCtrl = new SkuespillerRolleCtrl ();
    resultatCtrl.connect();
    resultatCtrl.printSkuespillerRolle("Jonathan Brooks");

    SkuespillerIFilmCtrl resultatCtrl = new SkuespillerIFilmCtrl ();
    resultatCtrl.connect();
    resultatCtrl.printSkuespillerIFilm("Jonathan Brooks");*/
    Person t = new Person(5);

    Person n = new Person(4);
    Person b = new Person(1);
    List<Person> manusforfattere = new ArrayList<>();
    manusforfattere.add(n);
    manusforfattere.add(b);

    Skuespiller p = new Skuespiller(2, "Bolle");
    Skuespiller h = new Skuespiller(3, "Besteladden");
    List<Skuespiller> skuespillere = new ArrayList<>();
    skuespillere.add(p);
    skuespillere.add(h);


    AddVideoMediaCtrl a = new AddVideoMediaCtrl();
    a.lagVideomedia("Shing shong bong", "Kino",
        210, "2011-7-11", 2011,
        "Skrootjinfokal", 1, "hauk.no", manusforfattere, t, skuespillere);
    //a.getAll();
    a.fullfor();

  }
}
