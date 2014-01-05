package leen.meij.utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * The base class of every DataAccess class.
 * 
 * @author Thijs
 * 
 */
public abstract class DataAccess
{
	private String username;
	private String password;
	private String url;

	protected Connection connection;

	/**
	 * Gets a value indicating whether a specified ResultSet has a specified
	 * column.
	 * 
	 * @param resultSet
	 *            The ResultSet.
	 * @param kolom
	 *            The column to check for.
	 * @return A value indicating whether a specified ResultSet has a specified
	 *         column.
	 * @throws SQLException
	 *             Throws an SQLException if something is wrong with the
	 *             ResultSet.
	 */
	protected boolean heeftKolom(ResultSet resultSet, String kolom) throws SQLException
	{
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int kolommen = rsmd.getColumnCount();
		for (int i = 1; i <= kolommen; i++)
		{
			if (kolom.equals(rsmd.getColumnName(i))) { return true; }
		}
		return false;
	}

	/**
	 * Opens the connection to the database.
	 */
	protected void openConnection()
	{
		try
		{
			this.username = "postgres";
			this.password = "1234";
			this.url = "jdbc:postgresql://145.97.16.201:5432/leenmeij";

			connection = DriverManager.getConnection(url, username, password);
			DatabaseMetaData dbmd = connection.getMetaData();

		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

	/**
	 * Closes the connection to the database if one is open
	 */
	protected void closeConnection()
	{
		if (connection != null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}

	}

}