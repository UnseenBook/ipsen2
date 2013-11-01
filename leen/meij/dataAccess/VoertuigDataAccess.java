
package leen.meij.dataAccess;

import java.util.ArrayList;
import java.util.Arrays;

import leen.meij.utilities.*;
import leen.meij.*;



public class VoertuigDataAccess extends DataAccess
{
	private static ArrayList<Voertuig> tempData = new ArrayList<Voertuig>( Arrays.asList(new Voertuig[] { new Voertuig("Audi"),
			new Voertuig("Volkswagen"), new Voertuig("Rotbus"), new Voertuig("Snorauto") }));

	/**
	 * 
	 * @param voertuigID
	 */
	public Voertuig select(int voertuigID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	public ArrayList<Voertuig> selectAll()
	{
		return tempData;
	}

	/**
	 * 
	 * @param voertuig
	 */
	public Voertuig add(Voertuig voertuig)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param klantID
	 */
	public void delete(int klantID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param voertuig
	 */
	public Voertuig edit(Voertuig voertuig)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param onderhoud
	 */
	public Onderhoud addOnderhoud(Onderhoud onderhoud)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}