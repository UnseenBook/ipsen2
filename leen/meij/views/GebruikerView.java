package leen.meij.views;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import leen.meij.*;

public class GebruikerView extends MasterView<ArrayList<Gebruiker>> implements ListSelectionListener, ActionListener
{
	private Gebruiker selectedGebruiker;

	private JButton btnWijzigen = new JButton("Wijzigen");
	private JButton btnVerwijderen = new JButton("Verwijderen");
	private JButton btnToevoegen = new JButton("Toevoegen");

	private JTable tblGebruikers;

	public GebruikerView(ArrayList<Gebruiker> model)
	{
		super(model);

		// content panel

		tblGebruikers = createGebruikerTable();
		this.pnlContent.add(this.tblGebruikers.getTableHeader(), "wrap");
		this.pnlContent.add(this.tblGebruikers);

		// bottom menu panel

		btnWijzigen.setEnabled(false);
		btnVerwijderen.setEnabled(false);

		this.pnlBotMenu.add(this.btnWijzigen);
		this.pnlBotMenu.add(this.btnVerwijderen);
		this.pnlBotMenu.add(this.btnToevoegen);

		btnWijzigen.addActionListener(this);
		btnVerwijderen.addActionListener(this);
		btnToevoegen.addActionListener(this);
	}

	protected ArrayList<Gebruiker> getEditedModel()
	{
		return model;
	}



	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if (e.getSource() == btnToevoegen)
		{
			runTask("Gebruiker", "gebruikerToevoegen");
		}
		else if (selectedGebruiker != null)
		{
			if (e.getSource() == btnVerwijderen)
			{
				int result = JOptionPane.showConfirmDialog(this, "Weet u zeker dat u de geselecteerde Gebruiker wilt verwijderen?",
						"Gebruiker verwijderen?", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION)
				{
					runTask("Gebruiker", "gebruikerVerwijderen", new Object[] { new Integer(selectedGebruiker.getGebruikerID()) });
				}
			}
			else if (e.getSource() == btnWijzigen)
			{
				runTask("Gebruiker", "gebruikerWijzigen", new Object[] { new Integer(selectedGebruiker.getGebruikerID()) });
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int index = tblGebruikers.getSelectedRow();
		boolean inRange = index >= 0 && index < this.model.size();

		if (inRange)
		{
			this.selectedGebruiker = this.model.get(index);

		}
		btnVerwijderen.setEnabled(inRange);
		btnWijzigen.setEnabled(inRange);

	}
	
	private JTable createGebruikerTable()
	{
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Personeelnummer");
		dtm.addColumn("Gebruikersnaam");
		dtm.addColumn("Afdeling");
		dtm.addColumn("Naam");

		for (Gebruiker gebruiker : model)
		{
			dtm.addRow(new Object[] { gebruiker.getPersoneelnummer(), gebruiker.getGebruikersnaam(), gebruiker.getAfdeling(),
					gebruiker.getVoornaam() });
		}

		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 200));
		tcm.addColumn(new TableColumn(2, 200));
		tcm.addColumn(new TableColumn(3, 200));

		tcm.getColumn(0).setHeaderValue("Personeelnummer");
		tcm.getColumn(1).setHeaderValue("Gebruikersnaam");
		tcm.getColumn(2).setHeaderValue("Afdeling");
		tcm.getColumn(3).setHeaderValue("Naam");

		JTable table = new JTable(dtm, tcm)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // let only
																		// one
																		// row
																		// be
																		// selected
		table.getSelectionModel().addListSelectionListener(this);
		return table;
	}
}