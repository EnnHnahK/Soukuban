package core;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Database {
	java.sql.Connection connection;
	public Database() {
		try {
			String URL = "jdbc:sqlserver://localhost:1433"+";databaseName=Soukuban";
			connection = DriverManager.getConnection(URL);
			if(connection != null) {
				JOptionPane.showMessageDialog(null, "Successful Connection");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "" +e);	
		}
	}
}
