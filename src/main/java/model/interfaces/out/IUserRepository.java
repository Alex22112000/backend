package model.interfaces.out;

import model.dto.User;

public interface IUserRepository {
    public User getUser(String login);
    public boolean addUser(User user);
    public boolean changePassword(User user);
    public boolean deleteUser(String login);
}
