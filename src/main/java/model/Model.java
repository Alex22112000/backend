package model;

import model.interfaces.in.IModel;
import controller.token.Token;

public class Model implements IModel {

	public String createToken(String login, String role) throws Exception {
		return Token.create(login, role);
	}

	public boolean checkToken(String login, String token) {
		try {
			return Token.check(login, token);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
