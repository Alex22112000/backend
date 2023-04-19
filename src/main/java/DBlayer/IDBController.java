package DBlayer;

import java.sql.SQLException;
import java.util.ArrayList;
import entity.Eat_Product;


public interface IDBController{
	boolean DataIsCorrect(String login, String password) throws SQLException;
	boolean RegistrationNewUser(String login, String password);
	boolean ChangePassword(String login, String newPassword);
	boolean DeleteUser(String login);
	
	boolean DeleteProduct(String name);
	boolean AddProduct(String name, String cost, String count, String img);
	
	ArrayList<Eat_Product> GetCatalog() throws SQLException;
	void ReturnConnectToPool();
}
