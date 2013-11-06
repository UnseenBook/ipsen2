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

public class InleverLijstView extends MasterView<ArrayList<Reservering>> implements ListSelectionListener,ActionListener  {

	
	private JTable tblInleverLijst;
	
	
	public InleverLijstView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Inleverlijst");
		
		//begin table
		tblInleverLijst = createInleverLijstTable();
		
		
		this.pnlContent.add(this.tblInleverLijst.getTableHeader(),"wrap");
		this.pnlContent.add(this.tblInleverLijst);
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
	
	private JTable createInleverLijstTable()
	{
		
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Klantnummer");
		dm.addColumn("Klantnaam");
		dm.addColumn("einddatum");
		
		for(Reservering reservering: model)
		{
			dm.addRow(new Object[] {
					reservering.getKlantID(),
					reservering.getVoertuigID(),
					reservering.getEindDatum()
					
			});
		}
		
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0,100));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));

		
		tcm.getColumn(0).setHeaderValue("Klantnummer");
		tcm.getColumn(1).setHeaderValue("Voertuig id");
		tcm.getColumn(2).setHeaderValue("Eind datum");
		
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
