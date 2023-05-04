package DBlayer.repositories;

import DBlayer.entities.EUser;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.UserTransaction;
import model.dto.User;
import model.interfaces.out.IUserRepository;

public class UserRepository implements IUserRepository {

    @PersistenceUnit(unitName = "shopProducts_PersistenceUnit")
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @Resource
    UserTransaction userTransaction;


    @Override
    public User getUser(String login) {
        entityManager = entityManagerFactory.createEntityManager();
        String query = "select u from EUser u where u.login=:login";
        EUser eUser = entityManager.createQuery(query, EUser.class).setParameter("login", login).getSingleResult();
        User user = new User();
        user.setId(eUser.getId());
        user.setLogin(eUser.getLogin());
        user.setPassword(eUser.getPassword());
        user.setRole(eUser.getRole());
        return user;
    }

    @Override
    public boolean addUser(User user) {
        entityManager = entityManagerFactory.createEntityManager();
        boolean status = true;
        try {
            EUser eUser = new EUser();
            eUser.setLogin(user.getLogin());
            eUser.setPassword(user.getPassword());
            eUser.setRole(user.getRole());
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.persist(eUser);
            userTransaction.commit();
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    @Override
    public boolean changePassword(User user) {
        entityManager = entityManagerFactory.createEntityManager();
        String query = "update EUser u set u.password=:password where u.login=:login";
        boolean status = true;
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.createQuery(query, EUser.class).setParameter("password", user.getPassword())
                    .setParameter("login", user.getLogin()).executeUpdate();
            userTransaction.commit();
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

    @Override
    public boolean deleteUser(String login) {
        entityManager = entityManagerFactory.createEntityManager();
        String query = "delete from EUser u where u.login=:login";
        boolean status = true;
        try {
            userTransaction.begin();
            entityManager.joinTransaction();
            entityManager.createQuery(query, EUser.class)
                    .setParameter("login", login).executeUpdate();
            userTransaction.commit();
        } catch (Exception e) {
            System.out.println(e);
            status = false;
        }
        return status;
    }

}
