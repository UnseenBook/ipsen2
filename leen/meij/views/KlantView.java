
package leen.meij.views;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

import leen.meij.*;

public class KlantView extends MasterView<ArrayList<Klant>>
{

	private JButton btnWijzigen = new JButton("Wijzigen");

	private JTable tblKlanten;

	public KlantView(ArrayList<Klant> model)
	{
		super(model);
		
		this.setTitle("Klanten");
		
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Klantnummer");
		dtm.addColumn("Voornaam");
		dtm.addColumn("Email");
		dtm.addColumn("Telefoonnummer");
		
		for(Klant klant : model)
		{
			dtm.addRow(new Object[]{ 
					klant.getKlantNummer(), 
					klant.getVoornaam(),
					klant.getEmailadres(),
					klant.getTelefoonnummer(),
			});
			
		}
		
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 200));
		tcm.addColumn(new TableColumn(2, 200));
		tcm.addColumn(new TableColumn(3, 200));
		tcm.getColumn(0).setHeaderValue("ID");
		tcm.getColumn(1).setHeaderValue("Voornaam");
		tcm.getColumn(2).setHeaderValue("Email adres");
		tcm.getColumn(3).setHeaderValue("Telefoonnummer");
		
		
		tblKlanten = new JTable(dtm,tcm);
		
		
		
		
		this.pnlBotMenu.add(this.btnWijzigen);
		this.pnlContent.add(this.tblKlanten.getTableHeader(),"wrap");
		this.pnlContent.add(this.tblKlanten);
	}

	protected ArrayList<Klant> getEditedModel()
	{
		return model;
	}

}