
package leen.meij.views;

import javax.swing.*;
import leen.meij.Voertuig;


public class VoertuigDetailsView extends MasterView<Voertuig>
{


	private JTextField txtCategorie;

	private JTextField txtMerk;

	private JTextField txtType;

	private JTextField txtKleur;

	private JTextField txtBeschrijving;

	public VoertuigDetailsView(Voertuig model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	protected Voertuig getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}