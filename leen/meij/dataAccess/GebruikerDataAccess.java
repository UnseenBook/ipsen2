
package leen.meij.dataAccess;

import java.util.ArrayList;

import leen.meij.utilities.*;
import leen.meij.*;

public class GebruikerDataAccess extends DataAccess
{

	/**
	 * 
	 * @param gebruikerID
	 */
	public Gebruiker select(int gebruikerID)
	{
		openConnection();
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