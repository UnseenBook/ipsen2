
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
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		
		klanten.add(new Klant(10,"Thijs"));
		klanten.add(new Klant(11,"Daan"));
		klanten.add(new Klant(12,"Jovanny"));
		
		
		return new KlantView(klanten);
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
	 * @param klant The Klant object to add to the database
	 */
	public View klantToevoegenTask(Klant klant)
	{
		// TODO validation
		// TODO: add klant to database
		// klant = klantDataAccess.add(klant);
		
		klant.getErrors().add("KlantController::klantToevoegenTask(Klant) is not yet implemented");
		// redirect back to the overview
		return new KlantDetailsView(klant);
	}

	/**
	 * Shows a klant edit View of a specific Klant
	 * @param klantID The ID of the Klant to edit.
	 */
	public View klantWijzigenTask(Integer klantID)
	{
		// TODO select klant from database
		// Klant klant = klantDataAccess.select(klantID);
		
		return new KlantDetailsView(new Klant(324,"Jaaap"));
	}
	
	/**
	 * Commits changes of a Klant object to the database.
	 * @param klant
	 */
	public View klantWijzigenTask(Klant klant)
	{
		// TODO: edit klant to database
		// klant = klantDataAccess.edit(klant);
		
		return new KlantDetailsView(klant);
	}

	/**
	 * Removes a klant from the database
	 * @param klantID
	 */
	public View klantVerwijderenTask(Integer klantID)
	{
		
		// TODO: delete klant from database
		// klantDataAccess.delete(klantID);
		
		// redirect back to the overview
		return klantOverzichtRaadplegenTask();
	}

}