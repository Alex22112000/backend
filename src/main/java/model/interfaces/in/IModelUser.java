package model.interfaces.in;

import model.dto.User;
import model.interfaces.out.IUserRepository;

public interface IModelUser {
    
    public void setRepository(IUserRepository userRepository);

    public boolean checkAuth(String login, String password);

    public User getUser(String login);

    public boolean addUser(String login, String password);

    public boolean changePassword(String login, String newPassword);

    public boolean deleteUser(String login);
}
