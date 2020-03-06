package mainpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    /*

    SkuespillerRolleCtrl resultatCtrl = new SkuespillerRolleCtrl ();
    resultatCtrl.connect();
    resultatCtrl.printSkuespillerRolle("Jonathan Brooks");

    SkuespillerIFilmCtrl resultatCtrl = new SkuespillerIFilmCtrl ();
    resultatCtrl.connect();
    resultatCtrl.printSkuespillerIFilm("Jonathan Brooks");*/
   /* Person t = new Person(5);

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
    a.fullfor();*/

   /*AddReviewCtrl arc = new AddReviewCtrl();
    arc.getAll();
    //arc.fullfor();*/

    System.out.println("Welcome to the film database program!");
    System.out.println("The best way for you to DAB out some informastion about your " +
        "favorite movies and series! \n");

    Main.run();


  }

  public static void run() {
    SkuespillerRolleCtrl sir = new SkuespillerRolleCtrl();
    SkuespillerIFilmCtrl sif = new SkuespillerIFilmCtrl();
    AddVideoMediaCtrl avm = new AddVideoMediaCtrl();

    System.out.println("\n\n\nWhat do you want to do?");
    System.out.println("1) Find all the roles a spesific actor plays");
    System.out.println("2) Find which movies a specific actor appears");
    System.out.println("3) ---");
    System.out.println("4) Insert a new movie (you provide the information)");
    System.out.println("5) Create a new review of a movie or episode \n");
    System.out.println("Enter your choice: ");

    Scanner scanner = new Scanner(System.in);
    int choice = Integer.valueOf(scanner.nextLine());
    System.out.println("You chose option: " + choice + "\n");

    switch (choice) {
      case 1:
        System.out.println("Enter the actor you want to find the roles: ");
        String actor = scanner.nextLine();
        sir.connect();
        sir.printSkuespillerRolle(actor);
        break;

      case 2:
        System.out.println("Enter the actor name to get all the movies he/she plays in: ");
        actor = scanner.nextLine();
        sif.connect();
        sif.printSkuespillerIFilm(actor);
        break;

      case 4:
        System.out.println("Enter the title of the movie: ");
        String title = scanner.nextLine();
        System.out.println("Enter the medium the movie was made for (Kino?): ");
        String lagetfor = scanner.nextLine();
        System.out.println("Enter the length of the movie in minutes: ");
        int length = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the date of release: ");
        String releasedate = scanner.nextLine();
        System.out.println("Enter the year of release: ");
        int year = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter a description of the movie: ");
        String desc = scanner.nextLine();
        System.out.println("Enter 1 if the movie was released as a video, 0 if not: ");
        int ufv = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the URL of the company: ");
        String url = scanner.nextLine();

        System.out.println("Enter the id of the director: ");
        Person director = new Person(Integer.valueOf(scanner.nextLine()));

        List<Person> mff = new ArrayList<>();
        System.out.println("Enter the ids of the scriptwriters (1,2,3,4): ");
        String[] input = scanner.nextLine().split(",");
        for (String id : input) {
          mff.add(new Person(Integer.valueOf(id)));
        }

        List<Skuespiller> ssr = new ArrayList<>();
        System.out.println("Enter the ids of the actors (1,2,3,4): ");
        String[] ids = scanner.nextLine().split(",");
        System.out.println("Enter the roles of the actors (1,2,3,4): ");
        String[] roles = scanner.nextLine().split(",");
        int count = 0;
        for (String id : ids) {
          ssr.add(new Skuespiller(Integer.valueOf(id), roles[count]));
          count++;
        }

        List<String> cats = new ArrayList<>();
        System.out.println("Enter the categories of the film (Horror,Love,etc.): ");
        String[] categ = scanner.nextLine().split(",");
        for (String cat : categ) {
          cats.add(cat);
        }

        avm.lagVideomedia(title, lagetfor, length, releasedate, year, desc, ufv, url, mff, director, ssr, cats);
        avm.fullfor();
        break;
      default:
        System.out.println("Invalid input");
        break;
    }
    Main.run();
  }
}
