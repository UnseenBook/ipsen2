
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
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import leen.meij.Gebruiker;
import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.Voertuig;
import leen.meij.dataAccess.GebruikerDataAccess;
import leen.meij.dataAccess.KlantDataAccess;
import leen.meij.dataAccess.ReserveringDataAccess;
import leen.meij.dataAccess.VoertuigDataAccess;


public class RapportView extends MasterView<ArrayList<Reservering>> implements ActionListener,ListSelectionListener
{
	private JTable tblManagementVoertuig;
	private JTable tblManagementReservering;
	private JTable tblManagementGebruikers;
	private JComboBox cbKlant = new JComboBox();
	private JComboBox cbVoertuig = new JComboBox();
	private JComboBox cbSelectie = new JComboBox();
	private JComboBox cbReservering = new JComboBox();
	private ArrayList<Klant> klantLijst;
	private ArrayList<Voertuig> voertuigLijst;
	private ArrayList<Gebruiker> gebruikerLijst;
	private ArrayList<Reservering> reserveringLijst;
	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	private GebruikerDataAccess gebruikerDataAccess = new GebruikerDataAccess();
	private ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();
	private String gapTop = "gaptop 10, ";
	private String wrap = "wrap,";
	private String span2 = "spanx 2,";
	
	public RapportView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Management overzicht");
		this.klantLijst = klantDataAccess.selectAll();
		this.voertuigLijst = voertuigDataAccess.selectAll();
		this.gebruikerLijst = gebruikerDataAccess.selectAll();
		this.reserveringLijst = reserveringDataAccess.selectAll();
	
		//Default combobox 
		String []select = {"Voertuigen", "Reserveringen", "Gebruikers"}; 
			for(int i = 0; i < select.length; i++)
			{
				cbSelectie.addItem(select[i]);
			}
				
			tblManagementVoertuig = createVoertuigManagementTable();
			tblManagementGebruikers = createGebruikerManagementTable();
			tblManagementReservering = createReserveringManagementTable();
			
		//Tabel veranderen door selectie
		pnlContent.add(new JLabel("Overzicht selecteren"), wrap + span2);
		cbSelectie.addActionListener(this);
		pnlContent.add(cbSelectie, wrap  + gapTop + span2);
				
		//Tabel toevoegen aan paneel
		pnlContent.add(tblManagementVoertuig.getTableHeader(),wrap + gapTop + span2);
		pnlContent.add(tblManagementVoertuig);

	}
	
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if(cbSelectie.getSelectedItem().toString() == "Voertuigen")
		{
			this.setTitle("Management - Voertuigen overzicht");
			pnlContent.remove(tblManagementReservering);
			pnlContent.remove(tblManagementGebruikers);
			pnlContent.remove(tblManagementReservering.getTableHeader());
			pnlContent.remove(tblManagementGebruikers.getTableHeader());
			pnlContent.add(tblManagementVoertuig.getTableHeader(),wrap + gapTop + span2);
			pnlContent.add(tblManagementVoertuig);
			pnlContent.revalidate();
			pnlContent.repaint();
		}
		else if(cbSelectie.getSelectedItem().toString() == "Reserveringen")
		{
			this.setTitle("Management - Reserveringen overzicht");
			pnlContent.remove(tblManagementVoertuig);
			pnlContent.remove(tblManagementGebruikers);
			pnlContent.remove(tblManagementVoertuig.getTableHeader());
			pnlContent.remove(tblManagementGebruikers.getTableHeader());
			pnlContent.add(tblManagementReservering.getTableHeader(),wrap + gapTop + span2);
			pnlContent.add(tblManagementReservering);
			pnlContent.revalidate();
			pnlContent.repaint();
	
		}
		else if(cbSelectie.getSelectedItem().toString() == "Gebruikers")
		{
			this.setTitle("Management - Gebruikers overzicht");
			pnlContent.remove(tblManagementVoertuig);
			pnlContent.remove(tblManagementReservering);
			pnlContent.remove(tblManagementReservering.getTableHeader());
			pnlContent.remove(tblManagementVoertuig.getTableHeader());
			pnlContent.add(tblManagementGebruikers.getTableHeader(),wrap + gapTop + span2);
			pnlContent.add(tblManagementGebruikers);
			pnlContent.revalidate();
			pnlContent.repaint();
		}
		else
		{
			this.setTitle("Management overzicht");
		}
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		
	}
	protected ArrayList<Reservering> getEditedModel()
	{
		return model;
	}
	public JTable createReserveringManagementTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("id");
		dm.addColumn("geplaatstdoor");
		dm.addColumn("reserveerdatum");
		dm.addColumn("klantnaam");
		dm.addColumn("categorie");
		dm.addColumn("status");
		
		for(Reservering reservering : reserveringLijst )
		{
			dm.addRow(new Object[]{
				reservering.getReserveringID(),
				reservering.getGebruiker(),
				reservering.getReserveerDatum(),
				reservering.getKlant().getVolledigeNaam(),
				reservering.getVoertuig().getCategorie(),
				reservering.getStatus()
			});
		}
				
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		tcm.addColumn(new TableColumn(4, 150));
		tcm.addColumn(new TableColumn(5, 150));
		tcm.getColumn(0).setHeaderValue("Reservering#");
		tcm.getColumn(1).setHeaderValue("geplaatstdoor");
		tcm.getColumn(2).setHeaderValue("reserveerdatum");
		tcm.getColumn(3).setHeaderValue("klantnaam");
		tcm.getColumn(4).setHeaderValue("categorie");
		tcm.getColumn(5).setHeaderValue("status");
		
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
		dm.addColumn("persnummer");
		dm.addColumn("gebruikersnaam");
		dm.addColumn("wachtwoord");
		
		
		for(Gebruiker gebruiker: gebruikerLijst )
		{
			dm.addRow(new Object[]{
					gebruiker.getGebruikerID(),
					gebruiker.getPersoneelnummer(),
					gebruiker.getGebruikersnaam(),
					gebruiker.getWachtworod(),
					
			});
		}
		TableColumnModel tcm = new DefaultTableColumnModel();			
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		tcm.getColumn(0).setHeaderValue("id");
		tcm.getColumn(1).setHeaderValue("persnummer");
		tcm.getColumn(2).setHeaderValue("gebruikersnaam");
		tcm.getColumn(3).setHeaderValue("wachtwoord");
		
		
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