
package leen.meij.dataAccess;

import java.util.ArrayList;
import java.util.Arrays;

import leen.meij.utilities.*;
import leen.meij.*;



public class VoertuigDataAccess extends DataAccess
{
	private static ArrayList<Voertuig> tempData = new ArrayList<Voertuig>( Arrays.asList(new Voertuig[] { 
            new Voertuig(1,"Volkswagen"),
            new Voertuig(2,"Toyota"), 
            new Voertuig(3,"Chevrolet"), 
            new Voertuig(4,"Ford"),
            new Voertuig(5,"Audi"),
            new Voertuig(6,"Honda")   }));

	/**
	 * 
	 * @param voertuigID
	 */
	public Voertuig select(int voertuigID)
	{
		for (Voertuig voertuig : tempData)
		{
			if (voertuig.getVoertuigID() == voertuigID)
			{
				return voertuig; 
				}
		}
		return null;
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
		//throw new UnsupportedOperationException();
            System.out.println("toegevoegd");
            
            tempData.add(voertuig);
		// select klant from newly inserted ID
		return voertuig;
            
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
		return voertuig;
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