package mainpackage;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main extends DBConn {

  public static void main(String[] args) {
    System.out.println("Welcome to the film database program!");
    System.out.println("The best way for you to DAB out some informastion about your "
        + "favorite movies and series! \n");

    Main.run();
  }

  public static void run() {
    SkuespillerRolleCtrl sir = new SkuespillerRolleCtrl();
    SkuespillerIFilmCtrl sif = new SkuespillerIFilmCtrl();
    AddVideoMediaCtrl avm = new AddVideoMediaCtrl();
    AddReviewCtrl arc = new AddReviewCtrl();
    CompanyCategoryCtrl ccc = new CompanyCategoryCtrl();
    Main m = new Main();

    System.out.println("\n\n\nWhat do you want to do?");
    System.out.println("1) Find all the roles a spesific actor plays");
    System.out.println("2) Find which movies a specific actor appears");
    System.out.println("3) Get a list of which company produces the most film in each category");
    System.out.println("4) Insert a new movie (you provide the information)");
    System.out.println("5) Create a new review of a movie or episode \n");
    System.out.println("Enter your choice: ");

    System.out.println("Enter 'q' at anytime to quit \n");

    Scanner scanner = new Scanner(System.in);
    String choice = scanner.nextLine();
    if (choice.toLowerCase().equals("q") || choice.equals(""))
      System.exit(0);
    System.out.println("You chose option: " + choice + "\n");

    switch (Integer.valueOf(choice)) {
      case 1:
        System.out.println("Enter the actor you want to find the roles: ");
        String actor = scanner.nextLine();
        if (actor.toLowerCase().equals("q") || actor.equals(""))
          break;
        sir.connect();
        sir.printSkuespillerRolle(actor);
        break;

      case 2:
        System.out.println("Enter the actor name to get all the movies he/she plays in: ");
        actor = scanner.nextLine();
        if (actor.toLowerCase().equals("q") || actor.equals(""))
          break;
        sif.connect();
        sif.printSkuespillerIFilm(actor);
        break;

      case 3:
          System.out.println("Here is the list: \n");
          ccc.getList();
          break;
      case 4:
        try {
          System.out.println("Enter the title of the movie: ");
          String title = scanner.nextLine();
          if (title.toLowerCase().equals("q") || title.equals(""))
            break;

          System.out.println("Enter the medium the movie was made for (Kino?): ");
          String lagetfor = scanner.nextLine();
          if (lagetfor.toLowerCase().equals("q") || lagetfor.equals(""))
            break;

          System.out.println("Enter the length of the movie in minutes: ");
          int length = Integer.valueOf(scanner.nextLine());
          if (String.valueOf(length).equals("") || String.valueOf(length).toLowerCase().equals("q"))
            break;

          System.out.println("Enter the date of release: (YYYY-MM-DD) ");
          String releasedate = scanner.nextLine();
          if (releasedate.toLowerCase().equals("q") || !Pattern
              .matches("^(19|20)\\d\\d([- /.])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$",
                  releasedate))
            break;

          System.out.println("Enter the year of release: ");
          int year = Integer.valueOf(scanner.nextLine());
          if (String.valueOf(year).equals("") || String.valueOf(year).toLowerCase().equals("q"))
            break;

          System.out.println("Enter a description of the movie: ");
          String desc = scanner.nextLine();
          if (String.valueOf(desc).toLowerCase().equals("q") || desc.equals(""))
            break;

          System.out.println("Enter 1 if the movie was released as a video, 0 if not: ");
          int ufv = Integer.valueOf(scanner.nextLine());
          if (String.valueOf(ufv).toLowerCase().equals("q") || String.valueOf(ufv).equals(""))
            break;

          System.out.println("Enter the URL of the company: ");
          m.hentURLer();
          String url = scanner.nextLine();
          if (url.toLowerCase().equals("q") || url.equals(""))
            break;

          System.out.println("Enter the id of the director: ");
          m.hentRegissorer();
          String id = scanner.nextLine();
          if (String.valueOf(id).toLowerCase().equals("q") || String.valueOf(id).equals(""))
            break;

          Person director = new Person(Integer.valueOf(id));
          List<Person> mff = new ArrayList<>();
          System.out.println("Enter the ids of the scriptwriters (1,2,3,4): ");
          String[] input = scanner.nextLine().split(",");
          for (String mid : input) {
            if (mid.toLowerCase().equals("q") || mid.equals(""))
              break;
            mff.add(new Person(Integer.valueOf(mid)));
          }

          List<Skuespiller> ssr = new ArrayList<>();
          System.out.println("Enter the ids of the actors (1,2,3,4): ");
          String[] ids = scanner.nextLine().split(",");
          if (ids[0].toLowerCase().equals("q") || ids[0].equals(""))
            break;

          System.out.println("Enter the roles of the actors (1,2,3,4): ");
          String[] roles = scanner.nextLine().split(",");
          if (roles[0].toLowerCase().equals("q") || roles[0].equals(""))
            break;
          int count = 0;
          for (String sid : ids) {
            ssr.add(new Skuespiller(Integer.valueOf(id), roles[count]));
            count++;
          }

          List<String> cats = new ArrayList<>();
          System.out.println("Enter the categories of the film (Horror,Love,etc.): ");
          String[] categ = scanner.nextLine().split(",");
          if (categ[0].toLowerCase().equals("q") || categ[0].equals(""))
            break;
          for (String cat : categ) {
            cats.add(cat);
          }

          avm.lagVideomedia(title, lagetfor, length, releasedate, year, desc, ufv, url, mff,
              director, ssr, cats);
          avm.fullfor();
          System.out.println("Videomedia added to database!");
          break;
        } catch (Exception e) {
          System.out.println("An error occured!");
          break;
        }

      case 5:
        try {
          System.out.println("Enter the title of the series: ");
          String title = scanner.nextLine();
          if (title.toLowerCase().equals("q") || title.equals(""))
            break;
          if (!arc.getEpisodes(title)) {
            break;
          }

          System.out.println("Enter the id of the episode you want to review: \n");
          String input = scanner.nextLine();
          if (input.toLowerCase().equals("q") || input.equals(""))
            break;
          int id = Integer.valueOf(input);

          System.out.println("Enter your name: \n");
          String name = scanner.nextLine();
          if (name.toLowerCase().equals("q") || name.equals(""))
            break;

          System.out.println("Enter your rating out of 10: \n");
          input = scanner.nextLine();
          if (input.toLowerCase().equals("q") || input.equals(""))
            break;
          int rating = Integer.valueOf(input);

          System.out.println("Write your review now: \n");
          String review = scanner.nextLine();
          if (review.toLowerCase().equals("q") || review.equals(""))
            break;

          arc.lagAnmeldelse(review, Integer.valueOf(rating), name, Integer.valueOf(id));
          arc.fullfor();
          break;
        } catch (Exception e) {
          System.out.println("An error occured!");
          break;
        }
      default:
        System.out.println("Invalid input");
        break;
    }
    Main.run();
  }

  public void hentRegissorer() {
    try {
      this.connect();
      Statement st = conn.createStatement();
      String directors =
          "SELECT DISTINCT medvirkningsrolle.personID, person.navn FROM medvirkningsrolle, person WHERE medvirkningsrolle.personID = person.id;";
      ResultSet r = st.executeQuery(directors);
      while (r.next()) {
        System.out.println(r.getString("personID") + ") " + r.getString("navn"));
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public void hentURLer() {
    try {
      this.connect();
      Statement st = conn.createStatement();
      String directors = "SELECT url FROM selskap;";
      ResultSet r = st.executeQuery(directors);
      while (r.next()) {
        System.out.println(r.getString("url"));
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

}
