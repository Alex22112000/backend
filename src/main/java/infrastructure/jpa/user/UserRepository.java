package infrastructure.jpa.user;

import java.util.List;
import application.auth.user.IUserRepository;
import application.auth.user.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserRepository implements IUserRepository {

    @PersistenceContext(unitName = "shopProducts_PersistenceUnit")
    private EntityManager entityManager;

    @Override
    public boolean add(User user) {
        try {
            EUser eUser = new EUser();
            eUser.setLogin(user.getLogin());
            eUser.setPassword(user.getPassword());
            eUser.setRole(user.getRole());
            entityManager.persist(eUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(User user) {
        String query = "update EUser u set u.password=:password where u.login=:login";
        try {
            entityManager.createQuery(query, EUser.class).setParameter("password", user.getPassword())
                    .setParameter("login", user.getLogin()).executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String login) {
        String query = "delete from EUser u where u.login=:login";
        try {
            entityManager.createQuery(query, EUser.class)
                    .setParameter("login", login).executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean check(User user) {
        try {
            List<EUser> users = entityManager
            .createQuery("SELECT p FROM EUser p WHERE p.login = :login AND p.password = :password", EUser.class)
            .setParameter("login", user.getLogin())
            .setParameter("password", user.getPassword())
            .getResultList();
            if(users != null && !users.isEmpty()){
                return true;
            } else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
