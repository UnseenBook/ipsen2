
package leen.meij.views;

import javax.swing.*;
import leen.meij.Onderhoud;

public class OnderhoudDetailsView extends MasterView<Onderhoud>
{

	private JTextField txtLocatie;

	private JTextField txtHandeling;

	private JCheckBox cbVerhuurbaar;

	public OnderhoudDetailsView(Onderhoud model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	protected Onderhoud getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}