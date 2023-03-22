package DBlayer;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBpool {
	Connection getConnection() throws SQLException;
	void returnConnection(Connection conn) throws SQLException;
}
