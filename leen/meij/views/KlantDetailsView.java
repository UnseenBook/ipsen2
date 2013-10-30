
package leen.meij.views;
import javax.swing.*;

import leen.meij.Klant;

public class KlantDetailsView extends MasterView<Klant>
{



	private JTextField txtVoornaam;

	private JTextField txtKlantNummer;

	private JTextField txtTussenvoegsel;

	private JTextField txtAchternaam;

	private JTextField txtBedrijfsnaam;

	private JTextField txtKvkNummer;

	private JTextField txtStraat;

	private JTextField txtHuisnummer;

	private JTextField txtPostcode;

	private JTextField txtWoonplaats;

	private JTextField txtProvincie;

	private JTextField txtLand;

	private JTextField txtTelefoonnummer;

	private JTextField txtMobielnummer;

	private JTextField txtEmailadres;

	private JTextField txtGeboorteDatum;
	
	public KlantDetailsView(Klant model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param model
	 */
	protected void initialize(Klant model)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	protected Klant getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}