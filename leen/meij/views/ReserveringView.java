
package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import leen.meij.Klant;
import leen.meij.Reservering;

public class ReserveringView extends MasterView<ArrayList<Reservering>> implements ActionListener

{
	private JButton btnVerwijderen = new JButton("Verwijderen");
	private JButton btnAanpassen = new JButton("Aanpassen");
	private JButton btnToevoegen = new JButton("Nieuw reservering");
	private JButton btnHuurlijst = new JButton("Huurlijst");
	private JButton btnInleverlijst = new JButton("Inleverlijst");
	private String title = "Reserveringen";
	Klant klant;

	private JTable tblReserveringen;
	
	public ReserveringView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle(title);
		
		tblReserveringen = createReseveringTable();

		btnAanpassen.setEnabled(false);
		btnVerwijderen.setEnabled(false);
		
		btnInleverlijst.addActionListener(this);
		btnHuurlijst.addActionListener(this);
		btnToevoegen.addActionListener(this);
		btnAanpassen.addActionListener(this);
		btnVerwijderen.addActionListener(this);
				
		this.pnlBotMenu.add(this.btnInleverlijst);
		this.pnlBotMenu.add(this.btnHuurlijst);
		this.pnlBotMenu.add(this.btnToevoegen);
		this.pnlBotMenu.add(this.btnAanpassen);
		this.pnlBotMenu.add(this.btnVerwijderen);
		this.pnlContent.add(this.tblReserveringen.getTableHeader(),"wrap");
		this.pnlContent.add(this.tblReserveringen);
		
		
		
	}

	protected ArrayList<Reservering> getEditedModel()
	{
		return model;
	
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnInleverlijst)
		{
			runTask("Resevering","inleverlijstRaadplegen");
		}
		if (e.getSource() == btnHuurlijst)
		{
			runTask("Reservering","huurlijstOverzichtRaadplegen");
		}
		if(e.getSource() == btnToevoegen)
		{
			runTask("Reservering", "reserveringToevoegen");
		}
		if(e.getSource() == btnAanpassen)
		{
			runTask("Reservering","reserveringAanpassen");
		}
		if(e.getSource() == btnVerwijderen)
		{
			runTask("Reservering", "reserveringVerwijderen");
		}
	}
	
	private JTable createReseveringTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Klantnummer");
		dm.addColumn("Naam");
		dm.addColumn("Voertuig");
		dm.addColumn("Datum"); 
		
		for(Reservering reservering : model)
		{
			dm.addRow(new Object[]{
					reservering.getKlantID(),
					reservering.getKlant(),
					reservering.getVoertuig(),
					reservering.getBeginDatum()
					//reservering.getEindDatum()
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
		
		JTable table = new JTable(dm,tcm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		
		
		return table;
	}

}