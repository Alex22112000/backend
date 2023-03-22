package DBlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.Encryption;
import utils.ClassFactory;
import entity.Eat_Product;


public class DBController implements IDBController{
	
	private IDBpool DBpool = null;
	private Connection conn = null;
	
	private Encryption CP = new Encryption();
	
	
	//============================================================
	private void GetConnectToBase() throws SQLException {
		this.addDBPool(ClassFactory.injectDBPool());
		this.conn = DBpool.getConnection();
	}
	
	//============================================================
	private ResultSet GetSelectResult(String select) throws SQLException{
		this.GetConnectToBase();
		PreparedStatement preparedStatement = this.conn.prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();
        this.ReturnConnectToPool();
		return resultSet;
	}
	//============================================================
	private void EnterQwery(String qwery) throws SQLException {
		this.GetConnectToBase();
		Statement statement = this.conn.createStatement();
        statement.executeUpdate(qwery);
        this.ReturnConnectToPool();
	}
	//============================================================
	@Override
	public boolean DataIsCorrect(String login, String password) throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from users");
		String a_login;
		String a_password;
		while (result.next()) {
            a_login = result.getString("user_login");
            a_password = result.getString("user_password");
            
            if(a_login.equals(login) && a_password.equals(CP.getCryptPassword(password))) {
            	return true;
            }
        }
		return false;
	}
	
	//============================================================
	@Override
	public boolean RegistrationNewUser(String login, String password){
		int size = 0;
		try {
			ResultSet result = this.GetSelectResult("Select * from users");
			while (result.next()) {
				size++;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String SELECT_SQL = "insert into users(user_login, user_password, user_id) values('"+login +"', '"+CP.getCryptPassword(password)+"'," + ++size + ")";
		try {
			
			if(!login.isEmpty() && !password.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	//============================================================
	@Override
	public ArrayList<Eat_Product> GetCatalog() throws SQLException {
		ResultSet result = this.GetSelectResult("Select * from product");
		ArrayList<Eat_Product> return_list = new ArrayList<Eat_Product>();
		int iter = 0;
		
		while (result.next()){
			Eat_Product add_prod = new Eat_Product();
			
			add_prod.product_id = result.getInt("product_id");
			add_prod.product_cost = result.getInt("product_cost");
			add_prod.product_name = result.getString("product_name");
			add_prod.product_count = result.getInt("product_count");
			add_prod.img = result.getString("img");
			return_list.add(iter, add_prod);
            iter++;
		}
		return return_list;
	}
	
	
	@Override
	public boolean DeleteProduct(String name) {
		String SELECT_SQL = "DELETE from product where product_name = '"+ name +"'";
				
		try {
			
			if(!name.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	
	
	@Override
	public boolean AddProduct(String name, String cost, String count, String img) {
		int size = 0;
		try {
			ResultSet result = this.GetSelectResult("Select * from product");
			while (result.next()) {
				size++;
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String SELECT_SQL = "insert into product(product_id, product_cost, product_name, product_count, img)" +
					"values("+ ++size +", "+ Integer.parseInt(cost) +" , '" + name + "' ,  "+ Integer.parseInt(count) +", '"+img+"')";
		
		try {
			
			if(!name.isEmpty() && !cost.isEmpty() && !count.isEmpty() && !img.isEmpty()) {
				this.EnterQwery(SELECT_SQL);
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	
	
	
	//============================================================
	@Override
	public void ReturnConnectToPool() {
		try {
			this.DBpool.returnConnection(this.conn);
			this.conn = null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//============================================================
	
	private void addDBPool(IDBpool pool) {
		if(this.DBpool == null) {
			this.DBpool = pool;
		}
	}
	

}
