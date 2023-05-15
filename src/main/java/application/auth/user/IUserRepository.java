package application.auth.user;

public interface IUserRepository {
    boolean add(User user);
    boolean changePassword(User user);
    boolean delete(String login);
    boolean check(User user);
}
