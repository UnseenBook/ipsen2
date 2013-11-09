package leen.meij;

import leen.meij.utilities.*;

public class Onderhoud extends Entity
{

	private int onderhoudID;

	private Klant klant;

	private Voertuig voertuig;

	private String locatie;

	private String handeling;

	private boolean voldaan;

	private String beschrijving;

	public Klant getKlant()
	{
		return this.klant;
	}

	/**
	 * 
	 * @param klant
	 */
	public void setKlant(Klant klant)
	{
		this.klant = klant;
	}

	public Voertuig getVoertuig()
	{
		return this.voertuig;
	}

	/**
	 * 
	 * @param voertuig
	 */
	public void setVoertuig(Voertuig voertuig)
	{
		this.voertuig = voertuig;
	}

	public int getOnderhoudID()
	{
		return this.onderhoudID;
	}

	/**
	 * 
	 * @param onderhoudID
	 */
	public void setOnderhoudID(int onderhoudID)
	{
		this.onderhoudID = onderhoudID;
	}

	public String getLocatie()
	{
		return this.locatie;
	}

	/**
	 * 
	 * @param locatie
	 */
	public void setLocatie(String locatie)
	{
		this.locatie = locatie;
	}

	public String getHandeling()
	{
		return this.handeling;
	}

	/**
	 * 
	 * @param handeling
	 */
	public void setHandeling(String handeling)
	{
		this.handeling = handeling;
	}

	public boolean isVoldaan()
	{
		return this.voldaan;
	}

	/**
	 * 
	 * @param voldaan
	 */
	public void setVoldaan(boolean voldaan)
	{
		this.voldaan = voldaan;
	}

	public void validateFields()
	{
		isValid = true;
	}

	public Integer getKlantID()
	{
		if (this.klant == null) return null;
		return this.klant.getKlantID();
	}

	/**
	 * @return the beschrijving
	 */
	public String getBeschrijving()
	{
		return beschrijving;
	}

	/**
	 * @param beschrijving
	 *            the beschrijving to set
	 */
	public void setBeschrijving(String beschrijving)
	{
		this.beschrijving = beschrijving;
	}

}