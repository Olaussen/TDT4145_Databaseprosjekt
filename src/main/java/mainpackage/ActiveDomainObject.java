package mainpackage;

import java.sql.Connection;

public abstract class ActiveDomainObject {

  public abstract void initialize(Connection conn);

  public abstract void refresh(Connection conn);

  public abstract void save(Connection conn);

}
