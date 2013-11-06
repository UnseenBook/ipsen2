
package leen.meij.views;

import java.util.ArrayList;
import javax.swing.*;
import leen.meij.Reservering;


public class RapportView extends MasterView<ArrayList<Reservering>>
{

	public RapportView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Management");
	}

	protected ArrayList<Reservering> getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}