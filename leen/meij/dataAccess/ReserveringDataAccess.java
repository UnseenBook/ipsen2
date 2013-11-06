package leen.meij.dataAccess;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import leen.meij.utilities.*;
import leen.meij.*;

public class ReserveringDataAccess extends DataAccess
{

	@SuppressWarnings("deprecation")
	protected static ArrayList<Reservering> tempData = new ArrayList<Reservering>(Arrays.asList(new Reservering[] {
			new Reservering(1, new Klant(10, "Thijs"), new Voertuig(1, "Volkswagen"), new Date("08/16/2013"), new Date("08/20/2013")),
			new Reservering(2, new Klant(11, "Daan"), new Voertuig(2, "Toyota"), new Date("08/16/2013"), new Date("08/22/2013")),
			new Reservering(3, new Klant(12, "Jovanny"), new Voertuig(3, "Chevrolet"), new Date("10/09/2013"), new Date("10/17/2013")),
			new Reservering(4, new Klant(13, "Angelo"), new Voertuig(4, "Ford"), new Date("10/18/2013"), new Date("10/31/2013")) }));

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
		tempData.add(reservering);
		return reservering;
	}

	/**
	 * 
	 * @param klantID
	 */
	public void delete(int reserveringID)
	{
		Reservering toRemove = null;
		for (Reservering reservering : tempData)
		{
			if (reservering.getKlantID() == reserveringID)
			{
				toRemove = reservering;
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

	public Reservering edit(Reservering reservering)
	{
		return reservering;
	}

}