package leen.meij;

import java.util.Date;

import leen.meij.utilities.*;
/**
 * The Gebruiker entity class.
 * 
 * @author Thijs
 * 
 */
public class Factuur extends Entity {

    private int factuurID;
    private int reserveringID;
    private Reservering reservering;
    private int factuurnummer;
    private double bedrag;
    private Date datum;
    private String reden;

    public Factuur() {
    }

    /**
     *
     * @param factuurID
     */
    public Factuur(int factuurID) {
        this.factuurID = factuurID;
    }

    public Reservering getReservering() {
        return this.reservering;
    }

    /**
     *
     * @param reservering
     */
    public void setReservering(Reservering reservering) {
        this.reservering = reservering;
    }

    public int getFactuurID() {
        return this.factuurID;
    }

    /**
     *
     * @param factuurID
     */
    public void setFactuurID(int factuurID) {
        this.factuurID = factuurID;
    }

    public int getFactuurnummer() {
        return this.factuurnummer;
    }

    /**
     *
     * @param factuurnummer
     */
    public void setFactuurnummer(int factuurnummer) {
        this.factuurnummer = factuurnummer;
    }

    public double getBedrag() {
        return this.bedrag;
    }

    /**
     *
     * @param bedrag
     */
    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public Date getDatum() {
        return this.datum;
    }

    /**
     *
     * @param datum
     */
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getReden() {
        return this.reden;
    }

    /**
     *
     * @param reden
     */
    public void setReden(String reden) {
        this.reden = reden;
    }

    public int getReserveringID() {
        return this.reserveringID;
    }

    /**
     *
     * @param reserveringID
     */
    public void setReserveringID(int reserveringID) {
        this.reserveringID = reserveringID;
    }

    public void validateFields() {
        isValid = true;
    }
}