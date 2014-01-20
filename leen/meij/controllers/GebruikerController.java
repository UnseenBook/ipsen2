package leen.meij.controllers;

import java.util.*;
import leen.meij.dataAccess.*;
import leen.meij.utilities.*;
import leen.meij.views.*;
import leen.meij.*;

/**
 * 
 * @author Daan
 *
 */
public class GebruikerController extends Controller
{
	private GebruikerDataAccess gebruikerDataAccess = new GebruikerDataAccess();

	public View inloggenTask()
	{
		return new LoginView(new Gebruiker());
	}

	/**
	 * Shows the LoginView with the given Gebruiker object
	 * @param gebruikersnaam The udername of the Gebruiker object
	 * @param wachtwoord The password of the Gebruiker object
	 * @return The LoginView with the Gebruiker object
	 */
	public View inloggenTask(String gebruikersnaam, String wachtwoord)
	{
		Gebruiker gebruiker = gebruikerDataAccess.select(gebruikersnaam, wachtwoord);
		
		if (gebruiker != null)
		{ 
			Site.getInstance().setGebruiker(gebruiker);
			
			return new TemporaryDefaultView(); 
		}

		gebruiker = new Gebruiker();
		gebruiker.setGebruikersnaam(gebruikersnaam);
		gebruiker.getErrors().add("Gebruikersnaam of wachtwoord klopt niet.");

		return new LoginView(gebruiker);

	}

	/**
	 * Shows the page with all the Gebruiker objects
	 * @return
	 */
	public View gebruikersOverzichtRaadplegenTask()
	{
		return new GebruikerView(gebruikerDataAccess.selectAll());
	}

	/**
	 * Shows the Gebruiker Add view
	 * 
	 */
	public View gebruikerToevoegenTask()
	{
		return new GebruikerDetailsView(new Gebruiker());
	}

	/**
	 * Adds a Gebruiker object to the database
	 * @param gebruiker The Gebruiker object
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
	 * Shows the Gebruiker Edit View
	 * @param gebruikerID The id of the Gebruiker object
	 */
	public View gebruikerWijzigenTask(Integer gebruikerID)
	{
		return new GebruikerDetailsView(gebruikerDataAccess.select(gebruikerID));
	}

	/**
	 * Edits the Gebruiker in the database
	 * @param gebruiker The Gebruiker object
	 */
	public View gebruikerWijzigenTask(Gebruiker gebruiker)
	{
		gebruiker.validateFields();

		if (gebruiker.isValid())
		{
			gebruikerDataAccess.edit(gebruiker);

			return gebruikersOverzichtRaadplegenTask();
		}

		return new GebruikerDetailsView(gebruiker);
	}

	/**
	 * Deletes the Gebruiker object from the database
	 * @param gebruikerID The id of the Gebruiker object
	 */
	public View gebruikerVerwijderenTask(Integer gebruikerID)
	{
		gebruikerDataAccess.delete(gebruikerID);

		return gebruikersOverzichtRaadplegenTask();
	}

	/**
	 * Changes the password of the Gebruiker object
	 * @param gebruikerID The id of the Gebruiker object
	 * @param wachtwoord The new password of the Gebruiker object
	 */
	public View wachtwoordVeranderenTask(Integer gebruikerID, String wachtwoord)
	{
		Gebruiker gebruiker = gebruikerDataAccess.select(gebruikerID);
		gebruiker.setWachtworod(wachtwoord);
		
		gebruiker.validateFields();
		
		if(gebruiker.isValid())
		{
			gebruiker = gebruikerDataAccess.editWachtwoord(gebruikerID,wachtwoord);
			
		}
		return new GebruikerDetailsView(gebruiker);
	}

}