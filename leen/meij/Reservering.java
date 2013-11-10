package leen.meij;

import java.util.ArrayList;
import java.util.Date;

import leen.meij.dataAccess.ReserveringDataAccess;
import leen.meij.utilities.Entity;
/*****
 * 
 * @author Jovanny Martis
 * 
 * 
 */

public class Reservering extends Entity
{

	private int reserveringID;
	private int klantID;
	private int voertuigID;
	private Klant klant;
	private Voertuig voertuig;
	private Gebruiker gebruiker; // dit is tijdelijk (bij inleverlijst
									// [gereserveerd door: gebruiker x]
	private Date reserveerDatum;
	private Date beginDatum;
	private Date eindDatum;
	private int kilometer;
	private double bedrag;
	private String status;

	/**
	 * @author Jovanny
	 * **/

	public Reservering()
	{
		this.klant = null;
		this.voertuig = null;
		this.gebruiker = null;
	}

	public Reservering(int klantID, Klant klant, Voertuig voertuig, Date begin, Date eind, int kilometer, double bedrag, String status)
	{
		this.klantID = klantID;
		this.klant = klant;
		this.voertuig = voertuig;
		this.beginDatum = begin;
		this.eindDatum = eind;
		this.kilometer = kilometer;
		this.bedrag = bedrag;
		this.status = status;
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

	public Gebruiker getGebruiker()
	{
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker)
	{
		this.gebruiker = gebruiker;
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

	private boolean gereserveerd()
	{
		ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();
		ArrayList<Reservering> reserveringen = reserveringDataAccess.selectDatumByVoertuigID(voertuigID);

		Date reserveringBeginDatum;
		Date reserveringEindDatum;

		for (Reservering reservering : reserveringen)
		{
			reserveringBeginDatum = new Date(reservering.getBeginDatum().getTime());
			reserveringEindDatum = new Date(reservering.getEindDatum().getTime());
			if ((! beginDatum.before(reservering.getBeginDatum()) && ! beginDatum.after(reservering.getEindDatum())) || (! eindDatum.before(reservering.getBeginDatum()) && ! eindDatum.after(reservering.getEindDatum())))
			{
				return false;
			}
		}
		return true;
	}

	public void validateFields()
	{
		getErrors().clear();
		if (voertuigID == 0 && (voertuig == null || voertuig.getVoertuigID() == 0))
		{
			getErrors().add("Kies a.u.b. een voertuig.");
			isValid = false;
		}
		else if (klantID == 0 && (klant == null || klant.getKlantID() == 0))
		{
			getErrors().add("Kies a.u.b. een klant.");
			isValid = false;
		}
		else if (reserveerDatum == null)
		{
			getErrors().add("Kies a.u.b. een reserveer datum.");
			isValid = false;
		}
		else if (beginDatum == null)
		{
			getErrors().add("Kies a.u.b. een begin datum.");
			isValid = false;
		}
		else if (eindDatum == null)
		{
			getErrors().add("Kies a.u.b. een eind datum.");
			isValid = false;
		}
		else if (gereserveerd())
		{
			getErrors().add("Het geselecteerde voertuig is in deze tijd al gereserveerd.");
			isValid = false;
		}
		else if (! voertuig.getVerhuurbaar())
		{
			getErrors().add("Dit voertuig is op het moment niet verhuurbaar. Kies a.u.b. een ander voertuig.");
			isValid = false;
		}
		else
		{
			isValid = true;
		}
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

	// debug set STATUS
	public void setStatus(String status)
	{
		this.status = status;
	}

	// debug get STATUS
	public String getStatus()
	{
		return status;
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
