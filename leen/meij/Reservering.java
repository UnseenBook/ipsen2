package leen.meij;

import java.util.Date;



/****
 * 
 * @author Jovanny Martis
 * 
 * 
 */
import leen.meij.utilities.Entity;

public class Reservering extends Entity
{
	
	private int reserveringID;
	private int klantID;
	private int voertuigID;
	private Klant klant;
	private Voertuig voertuig;
	private Date reserveerDatum;
	private Date beginDatum;
	private Date eindDatum;
	private int kilometer;
	private double bedrag;
	
	/**
	 * @author Jovanny
	 * **/
	
	
	public Reservering(){};
	public Reservering(int klantID,Klant klant, Voertuig voertuig, Date begin, Date eind)
	{
		this.klantID = klantID;
		this.klant = klant;
		this.voertuig = voertuig;
		this.beginDatum = begin;
		this.eindDatum = eind;
	}

	
	public int getReserveringID()
	{
		return this.reserveringID;
	}
	
	public void setReserveringID(int reserveringID)
	{
		this.reserveringID = reserveringID;
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

	public int getKlantID()
	{
		return this.klantID;
	}

	/**
	 * 
	 * @param klantID
	 */
	public void setKlantID(int klantID)
	{
		this.klantID = klantID;
	}

	public int getVoertuigID()
	{
		return this.voertuigID;
	}

	/**
	 * 
	 * @param voertuigID
	 */
	public void setVoertuigID(int voertuigID)
	{
		this.voertuigID = voertuigID;
	}

	public Date getReserveerDatum()
	{
		return this.reserveerDatum;
	}

	public Date getBeginDatum()
	{
		return this.beginDatum;
	}

	public Date getEindDatum()
	{
		return this.eindDatum;
	}
	
	public int getKilometer()
	{
		return this.kilometer;
	}

	/**
	 * 
	 * @param kilometer
	 */
	public void setKilometer(int kilometer)
	{
		this.kilometer = kilometer;
	}

	public double getBedrag()
	{
		return this.bedrag;
	}

	/**
	 * 
	 * @param bedrag
	 */
	public void setBedrag(double bedrag)
	{
		this.bedrag = bedrag;
	}

	public void validateFields()
	{
	
	}

	/**
	 * 
	 * @param reserveerDatum
	 */
	public void setReserveerDatum(Date reserveerDatum)
	{
		this.reserveerDatum = reserveerDatum;
	}

	/**
	 * 
	 * @param beginDatum
	 */
	public void setBeginDatum(Date beginDatum)
	{
		this.beginDatum = beginDatum;
	}

	/**
	 * 
	 * @param eindDatum
	 */
	public void setEindDatum(Date eindDatum)
	{
		this.eindDatum = eindDatum;
	}

}