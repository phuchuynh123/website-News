package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* https://ngockhuong.com
*/

public class DatabaseConnection {
	private static String db = "shareit";
	private static String url = "jdbc:mysql://localhost:3306/" + db + "?useUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
