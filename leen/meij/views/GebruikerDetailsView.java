
package leen.meij.views;

import javax.swing.*;

import leen.meij.*;



public class GebruikerDetailsView extends MasterView<Gebruiker>
{


	private JTextField txtPersoneelnummer;

	private JTextField txtGebruikersnaam;

	private JTextField txtWachtworod;

	private JTextField txtAfdeling;

	private JTextField txtVoornaam;

	private JTextField txtTussenvoegsel;

	private JTextField txtAchternaam;
	
	public GebruikerDetailsView(Gebruiker model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param model
	 */
	protected void initialize(Gebruiker model)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	protected Gebruiker getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}