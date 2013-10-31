
package leen.meij.views;

import java.util.ArrayList;
import javax.swing.*;
import leen.meij.Voertuig;


public class VoertuigView extends MasterView<ArrayList<Voertuig>>
{

	public VoertuigView(ArrayList<Voertuig> model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	private JTable tblVoertuigen;

	protected ArrayList<Voertuig> getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}