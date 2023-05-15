package application.auth.service;

import application.auth.token.IToken;
import application.auth.user.IUserRepository;
import application.auth.user.User;

public interface IAuth {
    boolean login(User user);
    boolean registration(User user);
    boolean changePassword(User user);
    boolean delete(User user);
    String createToken(String login);
	boolean checkToken(String login, String token);
    void setUserRepository(IUserRepository userRepository);
    void setTokenService(IToken token);
}
