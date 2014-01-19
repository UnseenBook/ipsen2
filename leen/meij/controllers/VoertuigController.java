package leen.meij.controllers;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import leen.meij.dataAccess.VoertuigDataAccess;
import leen.meij.utilities.*;
import leen.meij.*;
import leen.meij.views.VoertuigDetailsView;
import leen.meij.views.VoertuigView;

/**
 * 
 * @author abetcke
 * 
 */
public class VoertuigController extends Controller
{

	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();

        /**
	 * 
	 * Shows the voertuigView view with a list of vehicles (Voertuig)
	 */
	public View voertuigOverzichtRaadplegenTask()
	{
		return new VoertuigView(voertuigDataAccess.selectAll());
	}

        /**
	 * 
	 * Shows a empty VoertuigDetailsView for adding a vehicle (Voertuig)
	 */
	public View voertuigToevoegenTask()
	{
		return new VoertuigDetailsView(new Voertuig());
	}

	/**
	 * Adds a vehicle (voertuig) to the database after validation
	 * @param voertuig
	 */
	public View voertuigToevoegenTask(Voertuig voertuig)
	{
		// validate user data
		voertuig.validateFields();

		if (voertuig.isValid())
		{

			voertuig = voertuigDataAccess.add(voertuig);

			// redirect back to the overview
			return voertuigOverzichtRaadplegenTask();
		}

		// show the same view with error messages
		return new VoertuigDetailsView(voertuig);
	}

	/**
	 * Shows the VoertuigDetailsView with an existing vehicle (voertuig) for editing the vehicle
	 * @param voertuigID
	 */
	public View voertuigWijzigenTask(Integer voertuigID)
	{
		Voertuig voertuig = voertuigDataAccess.select(voertuigID);

		return new VoertuigDetailsView(voertuig);
	}

	/**
	 * Updates a vehicle (voertuig) from the database after validation
	 * @param voertuig
	 */
	public View voertuigWijzigenTask(Voertuig voertuig)
	{
		voertuig.validateFields();

		if (voertuig.isValid())
		{
			voertuig = voertuigDataAccess.edit(voertuig);

			// redirect back to the overview
			return voertuigOverzichtRaadplegenTask();
		}

		// show the same view with error messages
		return new VoertuigDetailsView(voertuig);
	}

	/**
	 * Removes a vehicle (voertuig) from the database
	 * @param voertuigID
	 */
	public View voertuigVerwijderenTask(Integer voertuigID)
	{
		voertuigDataAccess.delete(voertuigID);

		return voertuigOverzichtRaadplegenTask();
	}

	/**
	 * Adds a maintenance item to the database after validation
	 * @param onderhoud
	 */
	public View onderhoudToevoegenTask(Onderhoud onderhoud)
	{
		onderhoud.validateFields();
		if (onderhoud.isValid())
		{
			onderhoud = voertuigDataAccess.addOnderhoud(onderhoud);
		}
		
		return new VoertuigDetailsView(voertuigDataAccess.select(onderhoud.getVoertuig().getVoertuigID()));
	}
        
        /**
	 * Updates a maintenance item from the database after validation
	 * @param onderhoud
	 */
	public View onderhoudWijzigenTask(Onderhoud onderhoud)
	{
		onderhoud.validateFields();
		if (onderhoud.isValid())
		{
			onderhoud = voertuigDataAccess.editOnderhoud(onderhoud);
		}

		return new VoertuigDetailsView(voertuigDataAccess.select(onderhoud.getVoertuig().getVoertuigID()));
	}
}