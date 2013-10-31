package leen.meij.views;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import leen.meij.*;

public class KlantView extends MasterView<ArrayList<Klant>> implements
		ListSelectionListener, ActionListener
{
	private Klant selectedKlant;

	private JButton btnWijzigen = new JButton("Wijzigen");
	private JButton btnVerwijderen = new JButton("Verwijderen");
	private JButton btnToevoegen = new JButton("Toevoegen");

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

		for (Klant klant : model)
		{
			dtm.addRow(new Object[] { klant.getKlantNummer(),
					klant.getVoornaam(), klant.getEmailadres(),
					klant.getTelefoonnummer(), });
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

		tblKlanten = new JTable(dtm, tcm)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		tblKlanten.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKlanten.getSelectionModel().addListSelectionListener(this);

		btnWijzigen.setEnabled(false);
		btnVerwijderen.setEnabled(false);

		this.pnlBotMenu.add(this.btnWijzigen);
		this.pnlBotMenu.add(this.btnVerwijderen);
		this.pnlBotMenu.add(this.btnToevoegen);

		this.pnlContent.add(this.tblKlanten.getTableHeader(), "wrap");
		this.pnlContent.add(this.tblKlanten);
	}

	protected ArrayList<Klant> getEditedModel()
	{
		return model;
	}

	public void ActionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);

		if (e.getSource() == btnToevoegen)
		{
			runTask("Klant","klantToevoegen");
		}
		else if(selectedKlant != null)
		{
			if (e.getSource() == btnVerwijderen)
			{
				runTask("Klant","klantVerwijderen", new Object[]{new Integer(selectedKlant.getKlantID())});
			}
			else if (e.getSource() == btnWijzigen)
			{
				runTask("Klant","klantWijzigen", new Object[]{new Integer(selectedKlant.getKlantID())});
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int index = tblKlanten.getSelectedRow();
		boolean inRange = index >= 0 && index < this.model.size();

		if (inRange)
		{
			this.selectedKlant = this.model.get(index);

		}
		btnVerwijderen.setEnabled(inRange);
		btnWijzigen.setEnabled(inRange);
	}

}