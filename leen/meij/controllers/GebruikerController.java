
package leen.meij.controllers;

import java.util.ArrayList;

import leen.meij.dataAccess.GebruikerDataAccess;
import leen.meij.utilities.*;
import leen.meij.views.LoginView;
import leen.meij.views.ReserveringView;
import leen.meij.*;

public class GebruikerController extends Controller
{

	public View inloggenTask()
	{
		return new LoginView(new Gebruiker());
	}

	/**
	 * 
	 * @param gebruikersnaam
	 * @param wachtwoord
	 */
	public View inloggenTask(String gebruikersnaam, String wachtwoord)
	{
		GebruikerDataAccess gebruikerDataAccess = new GebruikerDataAccess();
		
		Gebruiker gebruiker = gebruikerDataAccess.select(gebruikersnaam, wachtwoord);
		if(gebruiker != null)
		{
			return new ReserveringView(new ArrayList<Reservering>());
		} 
	
		gebruiker = new Gebruiker();
		gebruiker.setGebruikersnaam(gebruikersnaam);
		gebruiker.getErrors().add("Gebruikersnaam of wachtwoord klopt niet.");
		return new LoginView(gebruiker);
	}

	public View gebruikersOverzichtRaadplegenTask()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	public View gebruikerToevoegenTask()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public View gebruikerToevoegenTask(Gebruiker gebruiker)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public View gebruikerWijzigenTask(Integer gebruikerID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public View gebruikerWijzigenTask(Gebruiker gebruiker)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public View gebruikerVerwijderenTask(Integer gebruikerID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}