
package leen.meij;

import leen.meij.utilities.*;

public class Gebruiker extends Entity
{

	private int gebruikerID;

	private int personeelnummer;

	private String gebruikersnaam;

	private String wachtwoord;

	private int afdelingID;

	private Afdeling afdeling;

	private String voornaam;

	private String tussenvoegsel;

	private String achternaam;

	public int getAfdelingID()
	{
		return this.afdelingID;
	}

	public void setAfdelingID(int afdelingID)
	{
		this.afdelingID = afdelingID;
	}

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

	public String getWachtwoord()
	{
		return this.wachtwoord;
	}

	/**
	 * 
	 * @param wachtworod
	 */
	public void setWachtworod(String wachtworod)
	{
		this.wachtwoord = wachtworod;
	}

	public Afdeling getAfdeling()
	{
		return this.afdeling;
	}

	/**
	 * 
	 * @param afdeling
	 */
	public void setAfdeling(Afdeling afdeling)
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
		getErrors().clear();
		if(gebruikersnaam.length() < 5)
		{
			getErrors().add("Vul a.u.b. een gebruikernaam is van minimaal 5 tekens");
			isValid = false;
		}
		else if (afdelingID == 0 && (afdeling == null || afdeling.getAfdelingID() == 0))
		{
			getErrors().add("Kies a.u.b. een afdeling");
			isValid = false;
		}
		else if(wachtwoord.length() < 5)
		{
			getErrors().add("Kies a.u.b. een wachtwoord is van minimaal 5 tekens");
			isValid = false;
		}
		else
		{
			isValid = true;
		}
		
		
	}

}