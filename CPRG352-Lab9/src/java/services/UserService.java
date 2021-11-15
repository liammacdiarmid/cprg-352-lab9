package services;

import dataaccess.UserDB;
import java.util.List;
import models.User;

public class UserService {

  public List<User> getAll() throws Exception {
    UserDB userDB = new UserDB();
    List<User> users = userDB.getAll();
    return users;
  }

  public void insert(
    String email,
    boolean active,
    String firstname,
    String lastname,
    String password,
    int role
  )
    throws Exception {
    User newUser = new User(email, active, firstname, lastname, password, role);
    UserDB userDB = new UserDB();
    userDB.insert(newUser);
  }

  public void delete(String email) throws Exception {
    UserDB userDB = new UserDB();
    userDB.delete(email);
  }

  public User get(String email) throws Exception {
    UserDB userDB = new UserDB();
    User user = userDB.get(email);
    return user;
  }

  public void update(User user) throws Exception {
    UserDB userDB = new UserDB();
    userDB.update(user);
  }
}
