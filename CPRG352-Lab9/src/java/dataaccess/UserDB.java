package dataaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import models.Role;
import models.User;

public class UserDB {

  public List<User> getAll() throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    try {
      List<User> users = em
        .createNamedQuery("User.findAll", User.class)
        .getResultList();
      return users;
    } finally {
      em.close();
    }
  }

  public void insert(User newUser) throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();

    try {
      trans.begin();
      em.persist(newUser);
      trans.commit();
    } catch (Exception ex) {
      trans.rollback();
    } finally {
      em.close();
    }
  }

  public void delete(String email) throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();

    try {
      trans.begin();
      em.remove(em.merge(get(email)));
      trans.commit();
    } catch (Exception ex) {
      trans.rollback();
    } finally {
      em.close();
    }
  }

  public User get(String email) throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    try {
      User user = em
        .createNamedQuery("User.findByEmail", User.class)
        .setParameter("email", email)
        .getSingleResult();
      return user;
    } finally {
      em.close();
    }
  }

  public void update(User user) throws Exception {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    EntityTransaction trans = em.getTransaction();

    try {
      trans.begin();
      em.merge(user);
      trans.commit();
    } catch (Exception ex) {
      trans.rollback();
    } finally {
      em.close();
    }
  }
}
