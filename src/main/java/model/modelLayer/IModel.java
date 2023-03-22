package model.modelLayer;

import java.util.ArrayList;
import DBlayer.IDBController;
import entity.Eat_Product;


public interface IModel {
	boolean checkUserData(String login, String password);
	boolean registrateNewUser(String login, String password);
	boolean addNewProduct(String name, String cost, String count, String img);
	boolean delProduct(String name);
	ArrayList<Eat_Product> 	getFullCatalog();
	void injectIDBController(IDBController repository);
	String createToken(String login) throws Exception;
    boolean checkToken(String login, String token);
}