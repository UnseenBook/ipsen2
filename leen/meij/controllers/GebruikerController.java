
package leen.meij.controllers;

import java.util.*;
import leen.meij.dataAccess.*;
import leen.meij.utilities.*;
import leen.meij.views.*;
import leen.meij.*;

public class GebruikerController extends Controller
{
	private GebruikerDataAccess gebruikerDataAccess = new GebruikerDataAccess();
	
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
		return new TemporaryDefaultView();
		/*
		GebruikerDataAccess gebruikerDataAccess = new GebruikerDataAccess();
		
		
		
		Gebruiker gebruiker = gebruikerDataAccess.select(gebruikersnaam, wachtwoord);
		if(gebruiker != null)
		{
			return new ReserveringView(new ArrayList<Reservering>());
		} 
	
		gebruiker = new Gebruiker();
		gebruiker.setGebruikersnaam(gebruikersnaam);
		gebruiker.getErrors().add("Gebruikersnaam of wachtwoord klopt niet.");
		
		return new LoginView(gebruiker);*/
	}

	public View gebruikersOverzichtRaadplegenTask()
	{
		return new GebruikerView(new ArrayList<Gebruiker>());
	}

	public View gebruikerToevoegenTask()
	{

		return new GebruikerDetailsView(new Gebruiker());
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