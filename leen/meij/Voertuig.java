
package leen.meij;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import leen.meij.utilities.*;

public class Voertuig extends Entity
{
	public static final String[] types = {"Scooter","Motor","Personen auto","Bestel bus"};

	public static final String[] categorieen = {"Budget","Familie","Zakelijk","Luxe"};

	private ArrayList<Onderhoud> onderhoud = new ArrayList<Onderhoud>();

	private int voertuigID;

	private String categorie;

	private String merk;

	private String type;

	private String kleur;

	private String beschrijving;

	private boolean verhuurbaar;

	private String kenteken;

	private int bouwJaar;

	private int kilometerStand;

	private String brandstof;

	private boolean airco;

	private boolean station;

	private double dagPrijs;

	private double brandstofPrijs;

	private double kilometerPrijs;

	private double borgPrijs;

	public String toString()
	{
		return merk + " " + type;
	}
	
	public Voertuig(){}
	
	public Voertuig(int voertuigID,String merk)
	{
		this.voertuigID = voertuigID;
		this.merk = merk;
	}
	

	public ArrayList<Onderhoud> getOnderhoud()
	{
		return this.onderhoud;
	}

	/**
	 * 
	 * @param onderhoud
	 */
	public void setOnderhoud(ArrayList<Onderhoud> onderhoud)
	{
		this.onderhoud = onderhoud;
	}

	public void setKenteken (String kenteken)
	{
		this.kenteken = kenteken;
	}
	public void setBouwJaar (int bouwJaar)
	{
		this.bouwJaar = bouwJaar;
	}
	public void setKilometerStand (int kilometerStand)
	{
		this.kilometerStand = kilometerStand;
	}
	public void setBrandstof (String brandstof)
	{
		this.brandstof = brandstof;
	}
	public void setAirco (boolean airco)
	{
		this.airco = airco;
	}
	public void setStation (boolean station)
	{
		this.station = station;
	}
	public void setDagPrijs (double dagPrijs)
	{
		this.dagPrijs = dagPrijs;
	}
	public void setBrandstofPrijs (double brandstofPrijs)
	{
		this.brandstofPrijs = brandstofPrijs;
	}
	public void setKilometerPrijs (double kilometerPrijs)
	{
		this.kilometerPrijs = kilometerPrijs;
	}
	public void setBorgPrijs (double borgPrijs)
	{
		this.borgPrijs = borgPrijs;
	}

	public String getKenteken()
	{
		return this.kenteken;
	}
	public int getBouwJaar()
	{
		return this.bouwJaar;
	}
	public int getKilometerStand()
	{
		return this.kilometerStand;
	}
	public String getBrandstof()
	{
		return this.brandstof;
	}
	public boolean getAirco()
	{
		return this.airco;
	}
	public boolean getStation()
	{
		return this.station;
	}
	public double getDagPrijs()
	{
		return this.dagPrijs;
	}
	public double getBrandstofPrijs()
	{
		return this.brandstofPrijs;
	}
	public double getKilometerPrijs()
	{
		return this.kilometerPrijs;
	}
	public double getBorgPrijs()
	{
		return this.borgPrijs;
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

	public String getCategorie()
	{
		return this.categorie;
	}

	/**
	 * 
	 * @param categorie
	 */
	public void setCategorie(String categorie)
	{
		this.categorie = categorie;
	}

	public String getMerk()
	{
		return this.merk;
	}
	
	
	/**
	 * 
	 * @param merk
	 */
	public void setMerk(String merk)
	{
		this.merk = merk;
	}

	public String getType()
	{
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	public String getKleur()
	{
		return this.kleur;
	}

	/**
	 * 
	 * @param kleur
	 */
	public void setKleur(String kleur)
	{
		this.kleur = kleur;
	}

	public String getBeschrijving()
	{
		return this.beschrijving;
	}

	/**
	 * 
	 * @param beschrijving
	 */
	public void setBeschrijving(String beschrijving)
	{
		this.beschrijving = beschrijving;
	}

	public boolean getVerhuurbaar()
	{
		return this.verhuurbaar;
	}

	/**
	 * 
	 * @param verhuurbaar
	 */
	public void isVerhuurbaar(boolean verhuurbaar)
	{
		// TODO - implement {class}.{operation}
		this.verhuurbaar = verhuurbaar;
	}

	public void validateFields()
	{
            
                getErrors().clear();
                if(!validateRegex(this.merk,"[a-zA-Z0-9\\s-]+"))
                {
                        getErrors().add("Merk foutief of niet ingevuld.");
                        isValid = false;
                }else if(!validateRegex(this.kleur, "[a-zA-Z0-9\\s-]+")){
                    getErrors().add("kleur foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(this.brandstof, "[a-zA-Z0-9\\s-]+")){
                    getErrors().add("brandstof foutief of niet ingevuld.");
                        isValid = false;
                }
                
                else if(!validateRegex(this.type, "[a-zA-Z0-9\\s-]+")){
                    getErrors().add("type foutief of niet ingevuld.");
                        isValid = false;
                }
                
                else if(!validateRegex(this.beschrijving, "[a-zA-Z0-9\\s-]+")){
                    getErrors().add("Beschrijving foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(this.kenteken, "[a-zA-Z0-9\\s-]+")){
                    getErrors().add("Kenteken foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(Integer.toString(this.bouwJaar), "[0-9]{4}+")){
                    getErrors().add("Bouwjaar foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(Integer.toString(this.kilometerStand), "[0-9]*(,|.)[0-9]{1,}+")){
                    getErrors().add("Kilometerstand foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(this.brandstof, "[a-zA-Z0-9\\s-]+")){
                    getErrors().add("brandstof foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(Double.toString(this.dagPrijs), "[0-9]*(,|.)[0-9]{1,}+")){
                    getErrors().add("Dag prijs foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(Double.toString(this.brandstofPrijs), "[0-9]*(,|.)[0-9]{1,}+")){
                    getErrors().add("Brandstof prijs foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(Double.toString(this.kilometerPrijs), "[0-9]*(,|.)[0-9]{1,}+")){
                    getErrors().add("Kilometer prijs foutief of niet ingevuld.");
                        isValid = false;
                }
                else if(!validateRegex(Double.toString(this.borgPrijs), "[0-9]*(,|.)[0-9]{1,}+")){
                    getErrors().add("Borg prijs foutief of niet ingevuld.");
                        isValid = false;
                }
                else{
                    isValid = true;
                }
                
                
		
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