package application.auth.token;

public interface IToken {
    String createToken(String login);
	boolean checkToken(String login, String token);
}
