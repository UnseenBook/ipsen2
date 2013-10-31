
package leen.meij.views;

import java.util.ArrayList;
import javax.swing.*;
import leen.meij.Reservering;

public class ReserveringView extends MasterView<ArrayList<Reservering>>
{


	private JTable tblReserveringen;
	
	public ReserveringView(ArrayList<Reservering> model)
	{
		super(model);
		// TODO Auto-generated constructor stub
	}

	protected ArrayList<Reservering> getEditedModel()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}