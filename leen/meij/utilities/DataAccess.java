
package leen.meij.utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DataAccess
{
	private String username;
	private String password;
	private String url;
	
	protected Connection connection;
	
	/*public DataAccess () {
		this.username = "postgres";
		this.password = "1234";
		this.url = "jdbc:postgresql://192.168.170.128:5432/leenmeij";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException cnfe) {
			System.out.println(cnfe);
		}
	}*/

	protected void openConnection()
	{
		try {
			this.username = "postgres";
			this.password = "1234";
			this.url = "jdbc:postgresql://145.101.93.154:5555/leenmeij";
			
			connection = DriverManager.getConnection(url, username, password);
			DatabaseMetaData dbmd = connection.getMetaData();
			
			
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		// TODO - implement {class}.{operation}
		//throw new UnsupportedOperationException();
	}

	protected void closeConnection()
	{
		if (connection != null) {
			try
			{
				connection.close();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		// TODO - implement {class}.{operation}
		//throw new UnsupportedOperationException();
	}

}