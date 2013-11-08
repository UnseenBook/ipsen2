
package leen.meij;

import leen.meij.utilities.*;

public class Factuur extends Entity
{

	private int factuurID;

	private int reserveringID;

	private Reservering reservering;

	private int factuurnummer;

	private double bedrag;

	private String datum;

	private String reden;

	
	public Factuur(){}
	
	/**
	 * 
	 * @param factuurID
	 */
	public Factuur(int factuurID)
	{
		this.factuurID = factuurID;
	}

	public Reservering getReservering()
	{
		return this.reservering;
	}

	/**
	 * 
	 * @param reservering
	 */
	public void setReservering(Reservering reservering)
	{
		this.reservering = reservering;
	}

	public int getFactuurID()
	{
		return this.factuurID;
	}

	/**
	 * 
	 * @param factuurID
	 */
	public void setFactuurID(int factuurID)
	{
		this.factuurID = factuurID;
	}

	public int getFactuurnummer()
	{
		return this.factuurnummer;
	}

	/**
	 * 
	 * @param factuurnummer
	 */
	public void setFactuurnummer(int factuurnummer)
	{
		this.factuurnummer = factuurnummer;
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

	public String getDatum()
	{
		return this.datum;
	}

	/**
	 * 
	 * @param datum
	 */
	public void setDatum(String datum)
	{
		this.datum = datum;
	}

	public String getReden()
	{
		return this.reden;
	}

	/**
	 * 
	 * @param reden
	 */
	public void setReden(String reden)
	{
		this.reden = reden;
	}

	public int getReserveringID()
	{
		return this.reserveringID;
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public void setReserveringID(int reserveringID)
	{
		this.reserveringID = reserveringID;
	}

	public void validateFields()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}