package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import leen.meij.Reservering;

public class InleverLijstView extends MasterView<ArrayList<Reservering>> implements ListSelectionListener,ActionListener  {

	
	private JTable tblInleverLijst;
	private String reserveerdatumformatted;
	private String einddatumformatted;
	private JButton btnFactuurOpmaken = new JButton("Factuur opmaken");
	private Reservering selectedReservering;
	
	
	
	public InleverLijstView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Inleverlijst");
		
		//begin table
		tblInleverLijst = createInleverLijstTable();
		
		//begin pnlBot buttons
		btnFactuurOpmaken.setEnabled(false);
	
		btnFactuurOpmaken.addActionListener(this);
		
		this.pnlBotMenu.add(this.btnFactuurOpmaken);
		this.pnlContent.add(this.tblInleverLijst.getTableHeader(),"wrap");
		this.pnlContent.add(this.tblInleverLijst);
	}
	
	public void actionPerformed(ActionEvent e)
	{
	
		super.actionPerformed(e);
		if(selectedReservering != null)
		{
			if(e.getSource() == btnFactuurOpmaken)
			{
				this.setTitle("Factuur opmaken");
				runTask("Reservering", "factuurOpmaken", new Object []{new Integer (selectedReservering.getReserveringID()) });
			}
		}

	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		int index = tblInleverLijst.getSelectedRow();
		boolean inRange = index >= 0 && index < this.model.size();

		if (inRange)
		{
			this.selectedReservering = this.model.get(index);

		}
		btnFactuurOpmaken.setEnabled(inRange);

	}
	

	protected ArrayList<Reservering> getEditedModel() {
		
		return model;
	}
	
	private JTable createInleverLijstTable()
	{
		
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Klantnummer");
		dm.addColumn("Klant naam");
		dm.addColumn("Voertuig");
		dm.addColumn("Reserveer datum");
		dm.addColumn("eind datum");
		dm.addColumn("ReserveringID");
		dm.addColumn("Gereserveerd door");
		
		for(Reservering reservering: model)
		{
			einddatumformatted = String.format("%1$td-%1$tm-%1$tY", reservering.getEindDatum());
			reserveerdatumformatted = String.format("%1$td-%1$tm-%1$tY", reservering.getReserveerDatum());
			
			
			dm.addRow(new Object[] {
					("KL" + reservering.getKlantID()),
					reservering.getKlant().getVoornaam(),
					reservering.getVoertuig().getMerk(),
				//	reservering.getVoertuig().getKenteken
					reserveerdatumformatted,
					einddatumformatted,
					("ID: " + reservering.getReserveringID()),
					reservering.getGebruiker()
					
					
			});
		}
		
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0,60));
		tcm.addColumn(new TableColumn(1, 100));
		tcm.addColumn(new TableColumn(2, 100));
		tcm.addColumn(new TableColumn(3,100));
		tcm.addColumn(new TableColumn(4,80));
		tcm.addColumn(new TableColumn(5,50));
		tcm.addColumn(new TableColumn(6,150));
		//tcm.addColumn(new TableColumn(7, 150));


		
		tcm.getColumn(0).setHeaderValue("Klant#");
		tcm.getColumn(1).setHeaderValue("Klant naam");
		tcm.getColumn(2).setHeaderValue("Voertuig merk");
		//tcm.getColumn(i++).setHeaderValue("Voertuig kenteken");
		tcm.getColumn(3).setHeaderValue("Reserveer datum");
		tcm.getColumn(4).setHeaderValue("Eind datum");
		tcm.getColumn(5).setHeaderValue("Reserveer#");
		tcm.getColumn(6).setHeaderValue("Gereserveerd door");
		
		
		JTable table = new JTable(dm, tcm)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		//table.setAutoCreateRowSorter(false);
		
		
		return table;
		
		
	}

}
