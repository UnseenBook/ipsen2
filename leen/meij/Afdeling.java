package leen.meij;

import leen.meij.utilities.*;

public class Afdeling extends Entity
{
	private int afdelingID;
	private String afdelingNaam;
	private int rechten;

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