package dataaccess;

import javax.persistence.*;

public class DBUtil {

  private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory(
    "UserPU"
  );

  public static EntityManagerFactory getEmFactory() {
    return emf;
  }
}
