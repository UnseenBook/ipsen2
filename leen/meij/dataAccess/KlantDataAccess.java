package leen.meij.dataAccess;

import java.util.*;

import leen.meij.utilities.*;
import leen.meij.*;

public class KlantDataAccess extends DataAccess
{

	private static ArrayList<Klant> tempData = (ArrayList<Klant>) Arrays.asList(new Klant[] { new Klant(10, "Thijs"),
			new Klant(11, "Daan"), new Klant(12, "Jovanny"), new Klant(13, "Angelo") });

	/**
	 * 
	 * @param klantID
	 */
	public Klant select(int klantID)
	{
		for (Klant klant : tempData)
		{
			if (klant.getKlantID() == klantID) { return klant; }
		}
		return null;
	}

	public ArrayList<Klant> selectAll()
	{
		return tempData;
	}

	/**
	 * 
	 * @param klant
	 */
	public Klant add(Klant klant)
	{
		tempData.add(klant);
		// select klant from newly inserted ID
		return klant;
	}

	/**
	 * 
	 * @param klantID
	 */
	public void delete(int klantID)
	{
		Klant toRemove = null;
		for (Klant klant : tempData)
		{
			if (klant.getKlantID() == klantID)
			{
				toRemove = klant;
			}
		}
		if (toRemove != null)
		{
			tempData.remove(toRemove);
		}
	}

	/**
	 * 
	 * @param klant
	 */
	public Klant edit(Klant klant)
	{
		return klant;
	}

}