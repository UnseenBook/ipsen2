
package leen.meij;

import leen.meij.utilities.*;

public class Gebruiker extends Entity
{

	private int gebruikerID;

	private int personeelnummer;

	private String gebruikersnaam;

	private String wachtworod;

	private String afdeling;

	private String voornaam;

	private String tussenvoegsel;

	private String achternaam;

	public int getGebruikerID()
	{
		return this.gebruikerID;
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public void setGebruikerID(int gebruikerID)
	{
		this.gebruikerID = gebruikerID;
	}

	public int getPersoneelnummer()
	{
		return this.personeelnummer;
	}

	/**
	 * 
	 * @param personeelnummer
	 */
	public void setPersoneelnummer(int personeelnummer)
	{
		this.personeelnummer = personeelnummer;
	}

	public String getGebruikersnaam()
	{
		return this.gebruikersnaam;
	}

	/**
	 * 
	 * @param gebruikersnaam
	 */
	public void setGebruikersnaam(String gebruikersnaam)
	{
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtworod()
	{
		return this.wachtworod;
	}

	/**
	 * 
	 * @param wachtworod
	 */
	public void setWachtworod(String wachtworod)
	{
		this.wachtworod = wachtworod;
	}

	public String getAfdeling()
	{
		return this.afdeling;
	}

	/**
	 * 
	 * @param afdeling
	 */
	public void setAfdeling(String afdeling)
	{
		this.afdeling = afdeling;
	}

	public String getVoornaam()
	{
		return this.voornaam;
	}

	/**
	 * 
	 * @param voornaam
	 */
	public void setVoornaam(String voornaam)
	{
		this.voornaam = voornaam;
	}

	public String getTussenvoegsel()
	{
		return this.tussenvoegsel;
	}

	/**
	 * 
	 * @param tussenvoegsel
	 */
	public void setTussenvoegsel(String tussenvoegsel)
	{
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getAchternaam()
	{
		return this.achternaam;
	}

	/**
	 * 
	 * @param achternaam
	 */
	public void setAchternaam(String achternaam)
	{
		this.achternaam = achternaam;
	}

	public void validateFields()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}