package application.auth.service;

import application.auth.token.IToken;
import application.auth.user.IUserRepository;
import application.auth.user.User;

public class Auth implements IAuth {

    private IUserRepository userRepository;
    private IToken tokenService;

    @Override
    public boolean login(User user) {
        return userRepository.check(user);
    }

    @Override
    public boolean registration(User user) {
        return userRepository.add(user);
    }

    @Override
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void setTokenService(IToken token) {
        this.tokenService = token;
    }

    @Override
    public String createToken(String login) {
        return tokenService.createToken(login);
    }

    @Override
    public boolean checkToken(String login, String token) {
        return tokenService.checkToken(login, token);
    }

    @Override
    public boolean changePassword(User user) {
        return userRepository.changePassword(user);
    }

    @Override
    public boolean delete(User user) {
        return userRepository.delete(user.getLogin());
    }
    
}
