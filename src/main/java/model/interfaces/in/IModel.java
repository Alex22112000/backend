package model.interfaces.in;

public interface IModel {
	// boolean checkUserData(String login, String password);
	// boolean registrateNewUser(String login, String password);
	// boolean changePassword(String login, String newPassword);
	// boolean deleteUser(String login);
	// boolean addNewProduct(String name, String cost, String count, String img);
	// boolean delProduct(String name);
	// ArrayList<Eat_Product> getFullCatalog();
	// void injectIDBController(IDBController repository);
	String createToken(String login, String role) throws Exception;

	boolean checkToken(String login, String token);
}