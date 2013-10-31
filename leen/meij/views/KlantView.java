
package leen.meij.views;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import leen.meij.*;

public class KlantView extends MasterView<ArrayList<Klant>>
{

	private JButton btnWijzigen = new JButton("Wijzigen");


	private JTable tblKlanten;

	public KlantView(ArrayList<Klant> model)
	{
		super(model);
		
		this.setTitle("Klanten");
		
		int niks = 10;
		int jemoeder = 100;
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Klantnummer");
		dtm.addColumn("Naam");
		dtm.addColumn("Email");
		dtm.addColumn("Telefoonnummer");
		
		for(Klant klant : model)
		{
			dtm.addRow(new Object[]{ 
					klant.getKlantNummer(), 
					klant.getVoornaam() +" " + klant.getTussenvoegsel() +" "+ klant.getAchternaam(),
					klant.getEmailadres(),
					klant.getTelefoonnummer(),
			});
		}
		
		tblKlanten = new JTable(dtm);
		
		tblKlanten.setAutoscrolls(false);

		tblKlanten.setAutoCreateColumnsFromModel(true);
		
		
		
		
		
		
		this.pnlBotMenu.add(this.btnWijzigen);
		this.pnlContent.add(this.tblKlanten);
	}

	protected ArrayList<Klant> getEditedModel()
	{
		return model;
	}

}