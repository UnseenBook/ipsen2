
package leen.meij.views;
import javax.swing.*;

import leen.meij.Reservering;

public class ReververingDetailsView extends MasterView<Reservering>
{


	private JComboBox cbKlant;

	private JComboBox cbVoertuig;

	private JTextField txtBeginDatum;

	private JTextField txtEindDatum;

	private JTextField txtKilometer;

	private JTextField txtBedrag;

	private JTextField attribute;

	public ReververingDetailsView(Reservering model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * @param model
	 */
	protected void initialize(Reservering model)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	protected Reservering retrieveModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}