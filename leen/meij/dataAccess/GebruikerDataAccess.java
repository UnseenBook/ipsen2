
package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Gebruiker;
import leen.meij.utilities.DataAccess;

public class GebruikerDataAccess extends DataAccess
{

	/**
	 * 
	 * @param gebruikerID
	 */
	public Gebruiker select(int gebruikerID)
	{
		openConnection();
		try 
		{
			PreparedStatement ps = connection.prepareStatement("SELECT id, naam FROM gebruiker WHERE id = ?");
			ps.setInt(1, gebruikerID);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				System.out.println("ID: " + rs.getString("id"));
				System.out.println("Naam: " + rs.getString("naam"));
			}
		} catch(SQLException sqle) 
		{
			sqle.printStackTrace();
		}
		
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
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