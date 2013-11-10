package leen.meij;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ***
 *
 * @author Jovanny Martis
 *
 *
 */
import leen.meij.utilities.Entity;

public class Reservering extends Entity {

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
	 * *
     */
    public Reservering() {
        this.klant = null;
        this.voertuig = null;
        this.gebruiker = null;
    }

    public Reservering(int klantID, Klant klant, Voertuig voertuig, Date begin, Date eind, int kilometer, double bedrag, String status) {
        this.klantID = klantID;
        this.klant = klant;
        this.voertuig = voertuig;
        this.beginDatum = begin;
        this.eindDatum = eind;
        this.kilometer = kilometer;
        this.bedrag = bedrag;
        this.status = status;
    }

    public int getReserveringID() {
        return this.reserveringID;
    }

    public void setReserveringID(int reserveringID) {
        this.reserveringID = reserveringID;
    }

    public Klant getKlant() {
        return this.klant;
    }

    /**
     *
     * @param klant
     */
    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Voertuig getVoertuig() {
        return this.voertuig;
    }

    /**
     *
     * @param voertuig
     */
    public void setVoertuig(Voertuig voertuig) {
        this.voertuig = voertuig;
    }

    public Gebruiker getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(Gebruiker gebruiker) {
        this.gebruiker = gebruiker;
    }

    public int getKlantID() {
        return this.klantID;
    }

    /**
     *
     * @param klantID
     */
    public void setKlantID(int klantID) {
        this.klantID = klantID;
    }

    public int getVoertuigID() {
        return this.voertuigID;
    }

    /**
     *
     * @param voertuigID
     */
    public void setVoertuigID(int voertuigID) {
        this.voertuigID = voertuigID;
    }

    public Date getReserveerDatum() {
        return this.reserveerDatum;
    }

    public Date getBeginDatum() {
        return this.beginDatum;
    }

    public Date getEindDatum() {
        return this.eindDatum;
    }

    public int getKilometer() {
        return this.kilometer;
    }

    /**
     *
     * @param kilometer
     */
    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
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

    public void validateFields() {

        getErrors().clear();
        if (!validateRegex(Integer.toString(this.kilometer), "[0-9]*(,|.)[0-9]{1,}+")) {
            getErrors().add("Kilometer foutief of niet ingevuld.");
            isValid = false;
        } else if (!validateRegex(Double.toString(this.bedrag), "[0-9]*(,|.)[0-9]{1,}+")) {
            getErrors().add("Bedrag foutief of niet ingevuld.");
            isValid = false;
        } else if (!validateRegex(this.status, "[a-zA-Z0-9\\s-]+")) {
            getErrors().add("Status foutief of niet ingevuld.");
            isValid = false;
        } else {
            isValid = true;
        }
        //id's nog checken?
    }

    /**
     *
     * @param reserveerDatum
     */
    public void setReserveerDatum(Date reserveerDatum) {
        this.reserveerDatum = reserveerDatum;
    }

    /**
     *
     * @param beginDatum
     */
    public void setBeginDatum(Date beginDatum) {
        this.beginDatum = beginDatum;
    }

    // debug set STATUS
    public void setStatus(String status) {
        this.status = status;
    }

    // debug get STATUS
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param eindDatum
     */
    public void setEindDatum(Date eindDatum) {
        this.eindDatum = eindDatum;
    }

    private boolean validateRegex(String input, String regex) {

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        if (m.matches()) {
            return true;
        } else {
            System.err.println("Error met " + input);
            return false;
        }

    }
}
