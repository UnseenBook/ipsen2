package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import leen.meij.Reservering;

public class InleverLijstView extends MasterView<ArrayList<Reservering>> implements ListSelectionListener,ActionListener  {

	
	public InleverLijstView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Inleverlijst");
	}
	
	public void actionPerformed(ActionEvent e)
	{
	
		super.actionPerformed(e);
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		
	}
	

	protected ArrayList<Reservering> getEditedModel() {
		
		return model;
	}

}
