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
		 * GebruikerDataAccess gebruikerDataAccess = new GebruikerDataAccess();
		 * 
		 * 
		 * 
		 * Gebruiker gebruiker = gebruikerDataAccess.select(gebruikersnaam,
		 * wachtwoord); if(gebruiker != null) { return new ReserveringView(new
		 * ArrayList<Reservering>()); }
		 * 
		 * gebruiker = new Gebruiker();
		 * gebruiker.setGebruikersnaam(gebruikersnaam);
		 * gebruiker.getErrors().add
		 * ("Gebruikersnaam of wachtwoord klopt niet.");
		 * 
		 * return new LoginView(gebruiker);
		 */
	}

	public View gebruikersOverzichtRaadplegenTask()
	{
		return new GebruikerView(gebruikerDataAccess.selectAll());
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
		gebruiker.validateFields();
		if (gebruiker.isValid())
		{
			gebruiker = gebruikerDataAccess.add(gebruiker);
			
			return gebruikersOverzichtRaadplegenTask();
		}
		
		return new GebruikerDetailsView(gebruiker);
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public View gebruikerWijzigenTask(Integer gebruikerID)
	{
		return new GebruikerDetailsView(gebruikerDataAccess.select(gebruikerID));
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public View gebruikerWijzigenTask(Gebruiker gebruiker)
	{
		gebruiker.validateFields();
		
		if(gebruiker.isValid()){
			gebruikerDataAccess.edit(gebruiker);
			
			return gebruikersOverzichtRaadplegenTask();
		}
		
		return new GebruikerDetailsView(gebruiker);
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public View gebruikerVerwijderenTask(Integer gebruikerID)
	{
		gebruikerDataAccess.delete(gebruikerID);
		
		
		return gebruikersOverzichtRaadplegenTask();
	}

}