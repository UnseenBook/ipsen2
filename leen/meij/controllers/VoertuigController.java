
package leen.meij.controllers;

import leen.meij.dataAccess.VoertuigDataAccess;
import leen.meij.utilities.*;
import leen.meij.*;
import leen.meij.views.VoertuigView;

public class VoertuigController extends Controller
{

	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	public View voertuigOverzichtRaadplegenTask()
	{
		// TODO - implement {class}.{operation}
		return new VoertuigView(voertuigDataAccess.selectAll());
	}

	public View voertuigToevoegenTask()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voertuig
	 */
	public View voertuigToevoegenTask(Voertuig voertuig)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voertuigID
	 */
	public View voertuigWijzigenTask(Integer voertuigID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voertuig
	 */
	public View voertuigWijzigenTask(Voertuig voertuig)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voertuigID
	 */
	public View voertuigVerwijderenTask(Integer voertuigID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voertuigID
	 */
	public View onderhoudToevoegenTask(Integer voertuigID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param onderhoud
	 */
	public View onderhoudToevoegenTask(Onderhoud onderhoud)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}