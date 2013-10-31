
package leen.meij.views;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import leen.meij.Reservering;

public class ReserveringView extends MasterView<ArrayList<Reservering>>
{
	private JButton btnWijzigen = new JButton("Wijzigen");
	private JButton btnAanpassen = new JButton("Aanpassen");
	private JButton btnToevoegen = new JButton("Toevoegen");
	private JButton btnHuurlijst = new JButton("Huurlijst");
	private JButton btnInleverlijst = new JButton("Inleverlijst");
	private String title = "Reserveringen";

	private JTable tblReserveringen;
	
	public ReserveringView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle(title);
		
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Klantnummer");
		dm.addColumn("Naam");
		dm.addColumn("Voertuig");
		dm.addColumn("Datum"); //begin - eind datum
		
		for(Reservering reservering : model)
		{
			dm.addRow(new Object[]{
					reservering.getKlantID(),
					reservering.getKlant().getVoornaam(),
					reservering.getVoertuig(),
					reservering.getBeginDatum(),
					reservering.getEindDatum()
			});
		}
		
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 100));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 200));
		tcm.getColumn(0).setHeaderValue("Klantnummer");
		tcm.getColumn(1).setHeaderValue("Naam");
		tcm.getColumn(2).setHeaderValue("Voertuig");
		tcm.getColumn(3).setHeaderValue("Begin - eind datum");
		
		tblReserveringen = new JTable(dm,tcm);
		tblReserveringen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblReserveringen.setAutoCreateRowSorter(true);
		
		
		this.pnlBotMenu.add(this.btnInleverlijst);
		this.pnlBotMenu.add(this.btnHuurlijst);
		this.pnlBotMenu.add(this.btnToevoegen);
		this.pnlBotMenu.add(this.btnAanpassen);
		this.pnlBotMenu.add(this.btnWijzigen);
		this.pnlContent.add(this.tblReserveringen.getTableHeader(),"wrap");
		this.pnlContent.add(this.tblReserveringen);
		
	}

	protected ArrayList<Reservering> getEditedModel()
	{
		return model;
	
	}

}