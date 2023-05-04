package model;

import model.dto.User;
import model.interfaces.in.IModelUser;
import model.interfaces.out.IUserRepository;
import utils.Encryption;

public class UserModel implements IModelUser {

    private IUserRepository userRepository;

    private Encryption encryption = new Encryption();

    @Override
    public void setRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean checkAuth(String login, String password) {
        User user = userRepository.getUser(login);
        if (user != null && user.getPassword().equals(encryption.getCryptPassword(password))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(encryption.getCryptPassword(password));
        user.setRole("user");
        return userRepository.addUser(user);
    }

    @Override
    public boolean changePassword(String login, String newPassword) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(newPassword);
        return userRepository.changePassword(user);
    }

    @Override
    public boolean deleteUser(String login) {
        return userRepository.deleteUser(login);
    }

    @Override
    public User getUser(String login) {
        return userRepository.getUser(login);
    }

}
