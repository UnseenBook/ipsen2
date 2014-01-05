package leen.meij;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import leen.meij.utilities.*;

/**
 * The Gebruiker entity class.
 * 
 * @author Thijs
 * 
 */
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

	public void setGebruikerID(int gebruikerID)
	{
		this.gebruikerID = gebruikerID;
	}

	public int getPersoneelnummer()
	{
		return this.personeelnummer;
	}

	public void setPersoneelnummer(int personeelnummer)
	{
		this.personeelnummer = personeelnummer;
	}

	public String getGebruikersnaam()
	{
		return this.gebruikersnaam;
	}

	public void setGebruikersnaam(String gebruikersnaam)
	{
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getWachtwoord()
	{
		return this.wachtwoord;
	}

	public void setWachtworod(String wachtworod)
	{
		this.wachtwoord = wachtworod;
	}

	public Afdeling getAfdeling()
	{
		return this.afdeling;
	}

	public void setAfdeling(Afdeling afdeling)
	{
		this.afdeling = afdeling;
	}

	public String getVoornaam()
	{
		return this.voornaam;
	}

	public void setVoornaam(String voornaam)
	{
		this.voornaam = voornaam;
	}

	public String getTussenvoegsel()
	{
		return this.tussenvoegsel;
	}

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

	/**
	 * Validates the gebruiker class.
	 */
	public void validateFields()
	{
		getErrors().clear();
		if (!validateRegex(this.voornaam, "[a-zA-Z0-9\\s-]+"))
		{
			getErrors().add("Voornaam foutief of niet ingevuld.");
			isValid = false;
		}
		else if (!validateRegex(this.tussenvoegsel, "[a-zA-Z0-9\\s-]+"))
		{
			getErrors().add("Tussenvoegsel foutief of niet ingevuld.");
			isValid = false;
		}
		else if (!validateRegex(this.achternaam, "[a-zA-Z0-9\\s-]+"))
		{
			getErrors().add("Achternaam foutief of niet ingevuld.");
			isValid = false;
		}
		else if (!validateRegex(this.wachtwoord, "[a-zA-Z0-9@#$%^&+=-]+"))
		{
			getErrors().add("Bedrijfsnaam foutief of niet ingevuld.");
			isValid = false;
		}
		else if (!validateRegex(this.gebruikersnaam, "[a-zA-Z0-9\\s-]+"))
		{
			getErrors().add("Kvk nummer foutief of niet ingevuld.");
			isValid = false;
		}
		else if (!validateRegex(Integer.toString(this.personeelnummer), "[0-9]+"))
		{
			getErrors().add("Straat foutief of niet ingevuld.");
			isValid = false;
		}
		else
		{
			isValid = true;
		}

	}

	/**
	 * Returns a value indicating whether this specified input matches the specified regex.
	 * @param input The input string to validate.
	 * @param regex The regex string to match.
	 * @return A value indicating whether this specified input matches the regex.
	 */
	private boolean validateRegex(String input, String regex)
	{
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.matches();
	}
}