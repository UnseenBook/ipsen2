
package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DateFormatter;

import leen.meij.Klant;
import leen.meij.Reservering;

public class ReserveringView extends MasterView<ArrayList<Reservering>> implements ListSelectionListener,ActionListener

{
	private JButton btnVerwijderen = new JButton("Verwijderen");
	private JButton btnAanpassen = new JButton("Aanpassen");
	private JButton btnToevoegen = new JButton("Nieuw reservering");
	private JButton btnHuurlijst = new JButton("Huurlijst");
	private JButton btnInleverlijst = new JButton("Inleverlijst");

	private Reservering selectedReservering;
	private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	
	Klant klant;

	private JTable tblReserveringen;
	
	
	public ReserveringView(ArrayList<Reservering> model)
	{
		super(model);
		this.setTitle("Reserveringen");
		
		
		tblReserveringen = createReserveringOverviewTable();
		
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
		super.actionPerformed(e);
		
		if (e.getSource() == btnInleverlijst)
		{
			runTask("Reservering","inleverlijstRaadplegen");
			
		}
		if (e.getSource() == btnHuurlijst)
		{
			runTask("Reservering","huurlijstOverzichtRaadplegen");
			
		}
		if(e.getSource() == btnToevoegen)
		{
			runTask("Reservering", "reserveringToevoegen");
			
		}
		else if (selectedReservering != null) 
		
		{
			
			if(e.getSource() == btnAanpassen)
				{
					this.setTitle("Reservering aanpassen");
					runTask("Reservering","reserveringWijzigen", new Object[]{new Integer(selectedReservering.getKlantID()) });
				}
		if(e.getSource() == btnVerwijderen)
		{
			int result = JOptionPane.showConfirmDialog(this, "Weet u zeker dat u de geselecteerde reservering wilt verwijderen?",
					"Reservering verwijderen?", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION)
			{
				runTask("Reservering", "reserveringVerwijderen", new Object[] { new Integer(selectedReservering.getKlantID()) });
			}
			
		}
		}
	}
	
	public void valueChanged(ListSelectionEvent e)
	{
		int index = tblReserveringen.getSelectedRow();
		boolean inRange = index >= 0 && index < this.model.size();

		if (inRange)
		{
			this.selectedReservering = this.model.get(index);

		}
		btnVerwijderen.setEnabled(inRange);
		btnAanpassen.setEnabled(inRange);
	}
	
	
	private JTable createReserveringOverviewTable()
	{
		DefaultTableModel dm = new DefaultTableModel();
		dm.addColumn("Klantnummer");
		dm.addColumn("Naam");
		dm.addColumn("Voertuig");
		dm.addColumn("Begin datum"); 
		dm.addColumn("Eind datum");
	
		
		for(Reservering reservering : model)
		{
			dm.addRow(new Object[]{
					reservering.getKlantID(),
					reservering.getKlant().getVolledigeNaam(),
					reservering.getVoertuig().getMerk(),
					reservering.getBeginDatum(),
					reservering.getEindDatum()
			});
		}
		
		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 100));
		tcm.addColumn(new TableColumn(1, 150));
		tcm.addColumn(new TableColumn(2, 150));
		tcm.addColumn(new TableColumn(3, 150));
		tcm.addColumn(new TableColumn(4, 150));
		tcm.getColumn(0).setHeaderValue("Klantnummer");
		tcm.getColumn(1).setHeaderValue("Naam");
		tcm.getColumn(2).setHeaderValue("Voertuig");
		tcm.getColumn(3).setHeaderValue("Begindatum");
		tcm.getColumn(4).setHeaderValue("Einddatum");
		
		JTable table = new JTable(dm,tcm)
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