package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (ID BIGINT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE INT)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = session.load(User.class, id);
            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List usersList = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            usersList = session.createCriteria(User.class).list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = Util.HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String sql = "TRUNCATE users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
