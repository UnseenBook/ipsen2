package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import leen.meij.Reservering;

public class HuurLijstView extends MasterView<ArrayList<Reservering>> implements ListSelectionListener,ActionListener {

	
	private JTable tblHuurLijst;
	
	public HuurLijstView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Huurlijst");
		
		//tabel begin
		tblHuurLijst =  createHuurLijstTable();
		
		
		
		this.pnlContent.add(this.tblHuurLijst.getTableHeader(),"wrap");
		this.pnlContent.add(this.tblHuurLijst);
		
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
	
	private JTable createHuurLijstTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Klantnummer");
		dm.addColumn("Voertuigid");
		dm.addColumn("reserveerdatum");
		dm.addColumn("einddatum");
		
		for(Reservering reservering: model)
		{
			dm.addRow(new Object[] {
					reservering.getKlantID(),
					reservering.getVoertuigID(),
					reservering.getReserveerDatum(),
					reservering.getEindDatum()
			});
		}
		
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0,100));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		
		tcm.getColumn(0).setHeaderValue("Klantnummer");
		tcm.getColumn(1).setHeaderValue("Voertuig id");
		tcm.getColumn(2).setHeaderValue("Reserveer datum");
		tcm.getColumn(3).setHeaderValue("Eind datum");
		
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
