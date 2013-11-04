
package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Gebruiker;
import leen.meij.utilities.DataAccess;

public class GebruikerDataAccess extends DataAccess
{
	private PreparedStatement prepareStatement = null;
	private ResultSet resultSet                = null;
	private Gebruiker gebruiker;


	private Gebruiker buildModel(ResultSet resultSet)
	{
		gebruiker = new Gebruiker();
		try
		{
			while (resultSet.next())
			{
				gebruiker.setGebruikerID(resultSet.getInt("id"));
				gebruiker.setPersoneelnummer(resultSet.getInt("personeelnummer"));
				gebruiker.setGebruikersnaam(resultSet.getString("gebruikersnaam")); 
				gebruiker.setWachtworod(resultSet.getString("wachtwoord"));
				gebruiker.setAfdeling("afdeling placeholder");
				gebruiker.setVoornaam(resultSet.getString("voornaam"));
				gebruiker.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
				gebruiker.setAchternaam(resultSet.getString("achternaam"));
				
			}
		} catch (SQLException negeer) {}
		return gebruiker;
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public Gebruiker select(int gebruikerID)
	{
		openConnection();
		try 
		{
			prepareStatement = connection.prepareStatement("SELECT * FROM gebruiker WHERE id = ?");
			prepareStatement.setInt(1, gebruikerID);
			resultSet = prepareStatement.executeQuery();
			gebruiker = buildModel(resultSet);
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} finally {
		if (resultSet != null) try { resultSet.close(); } catch (SQLException logOrIgnore) {}
		if (prepareStatement != null) try { prepareStatement.close(); } catch (SQLException logOrIgnore) {}
		if (connection != null) try { connection.close(); } catch (SQLException logOrIgnore) {}
		}

		// TODO - implement {class}.{operation}
		//throw new UnsupportedOperationException();
		return new Gebruiker();
	}

	/**
	 * 
	 * @param gebruikersNaam
	 * @param wachtwoord
	 */
	public Gebruiker select(String gebruikersNaam, String wachtwoord)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	public ArrayList<Gebruiker> selectAll()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public Gebruiker add(Gebruiker gebruiker)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public void delete(int gebruikerID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public Gebruiker edit(Gebruiker gebruiker)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}