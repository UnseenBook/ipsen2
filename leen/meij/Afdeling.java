package leen.meij;

import leen.meij.utilities.*;

public class Afdeling extends Entity
{
	public static final Afdeling[] Afdelingen = {
			new Afdeling(1, "Directie", Rechten.All),
			new Afdeling(2, "Baliemedewerker", Rechten.Klanten | Rechten.Inleverlijst | Rechten.Huurlijst | Rechten.Reserveringen),
			new Afdeling(3, "Onderhoudsmedewerker", Rechten.Voertuigen | Rechten.Huurlijst) };

	private int afdelingID;
	private String afdelingNaam;
	private int rechten;

	public Afdeling()
	{

	}

	public Afdeling(int id, String naam, int rechten)
	{
		this.afdelingID = id;
		this.afdelingNaam = naam;
		this.rechten = rechten;
	}

	public String toString()
	{
		return this.afdelingNaam;
	}

	public void setAfdelingID(int afdelingID)
	{
		this.afdelingID = afdelingID;
	}

	public void setAfdelingNaam(String afdelingNaam)
	{
		this.afdelingNaam = afdelingNaam;
	}

	public void setRechten(int rechten)
	{
		this.rechten = rechten;
	}

	public int getAfdelingID()
	{
		return this.afdelingID;
	}

	public String getAfdelingNaam()
	{
		return this.afdelingNaam;
	}

	public int getRechten()
	{
		return this.rechten;
	}

	public void validateFields()
	{
		isValid = true;
	}

}