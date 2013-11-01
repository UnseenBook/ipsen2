
package leen.meij.dataAccess;

import java.util.ArrayList;
import java.util.Arrays;

import leen.meij.utilities.*;
import leen.meij.*;

public class ReserveringDataAccess extends DataAccess
{
	
	private static ArrayList<Reservering> tempData = new ArrayList<Reservering>(Arrays.asList(new Reservering []{new Reservering
			(1,new Klant(10,"Thijs"),null,null,null), new Reservering(2,new Klant(11,"Daan"),null, null, null),
			new Reservering(3,new Klant(12, "Jovanny"),null, null, null), new Reservering (4,new Klant(13, "Angelo"),null, null, null)
}));
	

	/**
	 * 
	 * @param reserveringID
	 */
	public Reservering select(int reserveringID)
	{
		for (Reservering reservering : tempData)
		{
			if (reservering.getKlantID() == reserveringID) { return reservering; }
		}
		return null;
	}

	public ArrayList<Reservering> selectAll()
	{
		return tempData;
	}

	/**
	 * 
	 * @param reservering
	 */
	public Reservering add(Reservering reservering)
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
	 * @param klant
	 */
	public Klant edit(Klant klant)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}