package leen.meij.controllers;

import java.util.*;

import leen.meij.dataAccess.KlantDataAccess;
import leen.meij.utilities.*;
import leen.meij.views.KlantDetailsView;
import leen.meij.views.KlantView;
import leen.meij.*;

/**
 * 
 * @author Thijs
 * 
 */
public class KlantController extends Controller
{
	private KlantDataAccess klantDataAccess = new KlantDataAccess();

	/**
	 * Shows the klant overzicht View.
	 */
	public View klantOverzichtRaadplegenTask()
	{
		return new KlantView(klantDataAccess.selectAll());
	}

	/**
	 * Shows the klant add View
	 */
	public View klantToevoegenTask()
	{
		return new KlantDetailsView(new Klant());
	}

	/**
	 * Adds a Klant to the database
	 * 
	 * @param klant
	 *            The Klant object to add to the database
	 */
	public View klantToevoegenTask(Klant klant)
	{
		// validate user data
		klant.validateFields();

		if (klant.isValid())
		{

			klant = klantDataAccess.add(klant);
			
			// redirect back to the overview
			return klantOverzichtRaadplegenTask();
		}
		
		// show the same view with error messages
		return new KlantDetailsView(klant);
	}

	/**
	 * Shows a klant edit View of a specific Klant
	 * 
	 * @param klantID The ID of the Klant to edit.
	 */
	public View klantWijzigenTask(Integer klantID)
	{
		Klant klant = klantDataAccess.select(klantID);

		return new KlantDetailsView(klant);
	}

	/**
	 * Commits changes of a Klant object to the database.
	 * 
	 * @param klant
	 */
	public View klantWijzigenTask(Klant klant)
	{
		klant.validateFields();
		
		if(klant.isValid())
		{
			klant = klantDataAccess.edit(klant);
			
			// redirect back to the overview
			return klantOverzichtRaadplegenTask();
		}
		
		// show the same view with error messages
		return new KlantDetailsView(klant);
	}

	/**
	 * Removes a klant from the database
	 * 
	 * @param klantID
	 */
	public View klantVerwijderenTask(Integer klantID)
	{
		klantDataAccess.delete(klantID);

		// redirect back to the overview
		return klantOverzichtRaadplegenTask();
	}

}