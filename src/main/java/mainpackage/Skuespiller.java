package mainpackage;

public class Skuespiller extends Person {

  private String rolle;

  public Skuespiller(int id, String rolle) {
    super(id);
    this.rolle = rolle;
  }

  public String getRolle() {
    return this.rolle;
  }
}
