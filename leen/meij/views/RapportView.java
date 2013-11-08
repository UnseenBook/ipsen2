
package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.Voertuig;
import leen.meij.dataAccess.KlantDataAccess;
import leen.meij.dataAccess.ReserveringDataAccess;
import leen.meij.dataAccess.VoertuigDataAccess;


public class RapportView extends MasterView<ArrayList<Reservering>> implements ActionListener,ListSelectionListener
{
	private JTable tblManagement;
	private JComboBox cbKlant = new JComboBox();
	private JComboBox cbVoertuig = new JComboBox();
	private JComboBox cbSelectie = new JComboBox();
	private JComboBox cbReservering = new JComboBox();
	private ArrayList<Klant> klantLijst;
	private ArrayList<Voertuig> voertuigLijst;
	private ArrayList<Reservering> reserveringLijst;
	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	private ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();
	
	public RapportView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Management overzicht");
		this.klantLijst = klantDataAccess.selectAll();
		this.voertuigLijst = voertuigDataAccess.selectAll();
		this.reserveringLijst = reserveringDataAccess.selectAll();
		String gapTop = "gaptop 10, ";
		String wrap = "wrap,";
		String span2 = "spanx 2,";

		//+----------+
		//| Optional |
		//+----------+
	/**
		//Resevering combobox vullen
			for(int i = 0; i < model.size();i++)
			{
				cbReservering.addItem(model.get(i).getKlant().getKlantID());
			}
		//Voertuig combobox vullen
			for(int i = 0; i <voertuigLijst.size(); i++)
			{
				cbVoertuig.addItem(model.get(i).getVoertuig().getVoertuigID());
			}
	**/		
		//Default combobox 
			String []select = {"Voertuigen", "Reserveringen", "Gebruikers"}; 
			for(int i = 0; i < select.length; i++)
			{
				cbSelectie.addItem(select[i]);
			}
	
		//Default tabel aangeven
		tblManagement = createVoertuigManagementTable();
		
		//Tabel veranderen door selectie
		pnlContent.add(new JLabel("Overzicht selecteren"), wrap + span2);
		cbSelectie.addActionListener(this);
		pnlContent.add(cbSelectie, wrap  + gapTop + span2);
				
		//Tabel toevoegen aan paneel
		pnlContent.add(tblManagement.getTableHeader(),wrap + gapTop + span2);
		pnlContent.add(tblManagement);
		
		
		
		/**
		//Tabel veranderen naar reservering overzicht
		pnlContent.add(new JLabel("Resevering selectie"));
		pnlContent.add(cbReservering, wrap + span2);
		
		//Tabel veranderen naar voertuig overzicht
		pnlContent.add(new JLabel("Voertuig selectie"));
		pnlContent.add(cbVoertuig, wrap + span2);
		**/
		

	}

	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if(cbSelectie.getSelectedItem().toString() == "Voertuigen")
		{
			tblManagement = createVoertuigManagementTable();
		}
		else if(cbSelectie.getSelectedItem().toString() == "Reserveringen")
		{
			tblManagement = createReseveringManagementTable();
		}
		else if(cbSelectie.getSelectedItem().toString() == "Gebruikers")
		{
			tblManagement = createGebruikerManagementTable();
		}
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		
	}
	protected ArrayList<Reservering> getEditedModel()
	{
		return model;
	}
	
	public JTable createVoertuigManagementTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("id");
		dm.addColumn("categorie");
		dm.addColumn("type");
		dm.addColumn("merk");
		dm.addColumn("verhuurbaar");
		
		for(Voertuig voertuig : voertuigLijst )
		{
			dm.addRow(new Object[]{
				voertuig.getVoertuigID(),
				voertuig.getCategorie(),
				voertuig.getType(),
				voertuig.getMerk(),
				voertuig.getVerhuurbaar()
			});
		}
				
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		tcm.addColumn(new TableColumn(4, 150));
		tcm.getColumn(0).setHeaderValue("Voertuig#");
		tcm.getColumn(1).setHeaderValue("Categorie");
		tcm.getColumn(2).setHeaderValue("Type");
		tcm.getColumn(3).setHeaderValue("Merk");
		tcm.getColumn(4).setHeaderValue("Verhuurbaar");
		
		JTable table = new JTable(dm,tcm)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		return table;
	}
	public JTable createGebruikerManagementTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("id");
		dm.addColumn("categorie");
		dm.addColumn("type");
		dm.addColumn("merk");
		dm.addColumn("verhuurbaar");
		
		for(Voertuig voertuig : voertuigLijst )
		{
			dm.addRow(new Object[]{
				voertuig.getVoertuigID(),
				voertuig.getCategorie(),
				voertuig.getType(),
				voertuig.getMerk(),
				voertuig.getVerhuurbaar()
			});
		}
				
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		tcm.addColumn(new TableColumn(4, 150));
		tcm.getColumn(0).setHeaderValue("Voertuig#");
		tcm.getColumn(1).setHeaderValue("Categorie");
		tcm.getColumn(2).setHeaderValue("Type");
		tcm.getColumn(3).setHeaderValue("Merk");
		tcm.getColumn(4).setHeaderValue("Verhuurbaar");
		
		JTable table = new JTable(dm,tcm)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		return table;
	}
	
	public JTable createReseveringManagementTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("id");
		dm.addColumn("categorie");
		dm.addColumn("type");
		dm.addColumn("merk");
		dm.addColumn("verhuurbaar");
		
		for(Voertuig voertuig : voertuigLijst )
		{
			dm.addRow(new Object[]{
				voertuig.getVoertuigID(),
				voertuig.getCategorie(),
				voertuig.getType(),
				voertuig.getMerk(),
				voertuig.getVerhuurbaar()
			});
		}
				
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		tcm.addColumn(new TableColumn(4, 150));
		tcm.getColumn(0).setHeaderValue("Voertuig#");
		tcm.getColumn(1).setHeaderValue("Categorie");
		tcm.getColumn(2).setHeaderValue("Type");
		tcm.getColumn(3).setHeaderValue("Merk");
		tcm.getColumn(4).setHeaderValue("Verhuurbaar");
		
		JTable table = new JTable(dm,tcm)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		return table;
	}
}