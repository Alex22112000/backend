package jms;

public interface Tokenable {
    String createToken(String login, String role);
	boolean checkToken(String login, String token);
}
