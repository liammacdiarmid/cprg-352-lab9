package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

public class RoleService {

  public List<Role> getAll() throws Exception {
    System.out.println("In the Role Service");
    RoleDB roleDB = new RoleDB();
    List<Role> roles = roleDB.getAll();
    return roles;
  }
}
