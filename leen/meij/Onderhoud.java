
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
        
        public Onderhoud(){
            
        }
        
        public Onderhoud(int onderhoudID, String handeling){
            this.onderhoudID = onderhoudID;
            this.handeling = handeling;
        }

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
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}