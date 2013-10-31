
package leen.meij.controllers;

import java.util.*;
import leen.meij.utilities.*;
import leen.meij.views.KlantView;
import leen.meij.*;

public class KlantController extends Controller
{

	public View klantOverzichtRaadplegenTask()
	{
		ArrayList<Klant> klanten = new ArrayList<Klant>();
		
		klanten.add(new Klant(10,"Thijs"));
		klanten.add(new Klant(11,"Daan"));
		klanten.add(new Klant(12,"Jovanny"));
		
		
		return new KlantView(klanten);
	}

	public View klantToevoegenTask()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param klant
	 */
	public View klantToevoegenTask(Klant klant)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param klantID
	 */
	public View klantWijzigenTask(Integer klantID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param klant
	 */
	public View klantWijzigenTask(Klant klant)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param klantID
	 */
	public View klantVerwijderenTask(Integer klantID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}