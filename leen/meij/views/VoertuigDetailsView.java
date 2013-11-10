package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import leen.meij.Onderhoud;

import leen.meij.Voertuig;
import leen.meij.utilities.View;

public class VoertuigDetailsView extends MasterView<Voertuig> implements ActionListener, ListSelectionListener
{

	private Onderhoud selectedOnderhoud;

	private JTextField txtType = new JTextField(15);
	private JTextField txtCategorie = new JTextField(15);
	private JTextField txtMerk = new JTextField(15);
	private JTextField txtKleur = new JTextField(15);
	private JTextField txtBeschrijving = new JTextField(15);
	private JCheckBox cbVerhuurbaar = new JCheckBox();
	private JTable tblOnderhoud = new JTable();

	private JTextField txtKenteken = new JTextField(15);
	private JTextField txtBouwjaar = new JTextField(15);
	private JTextField txtKilometerstand = new JTextField(15);
	private JTextField txtBrandstof = new JTextField(15);
	private JCheckBox cbAirco = new JCheckBox();
	private JCheckBox cbStation = new JCheckBox();
	private JTextField txtDagprijs = new JTextField(15);
	private JTextField txtBrandstofprijs = new JTextField(15);
	private JTextField txtKilometerprijs = new JTextField(15);
	private JTextField txtBorgprijs = new JTextField(15);

	private JButton btnWijzigen = new JButton("Wijzigen");
	private JButton btnOnderhoudToevoegen = new JButton("Onderhoud Toevoegen");
	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	public VoertuigDetailsView(Voertuig model)
	{
		super(model);

		if (model.getVoertuigID() == 0)
		{
			this.setTitle("Voertuig toevoegen");
		}
		else
		{
			this.setTitle("Voertuig aanpassen");
		}

		String gap = "gapright 20,";
		String wrap = "wrap,";
		String span2 = "spanx 2,";

		tblOnderhoud = createOnderhoudTable(model.getOnderhoud());

		pnlContent.add(new JLabel("Type"));
		pnlContent.add(txtType, gap);

		pnlContent.add(new JLabel("Categorie"));
		pnlContent.add(txtCategorie, wrap);

		pnlContent.add(new JLabel("Merk"));
		pnlContent.add(txtMerk, gap);

		pnlContent.add(new JLabel("Kleur"));
		pnlContent.add(txtKleur, wrap);

		pnlContent.add(new JLabel("Beschrijving"));
		pnlContent.add(txtBeschrijving, gap);

		pnlContent.add(new JLabel("Verhuurbaar"));
		pnlContent.add(cbVerhuurbaar, wrap);

		pnlContent.add(new JLabel("Kenteken"));
		pnlContent.add(txtKenteken, gap);

		pnlContent.add(new JLabel("Bouwjaar"));
		pnlContent.add(txtBouwjaar, wrap);

		pnlContent.add(new JLabel("Kilometerstand"));
		pnlContent.add(txtKilometerstand, gap);

		pnlContent.add(new JLabel("Brandstof"));
		pnlContent.add(txtBrandstof, wrap);

		pnlContent.add(new JLabel("Airco"));
		pnlContent.add(cbAirco, gap);

		pnlContent.add(new JLabel("Station"));
		pnlContent.add(cbStation, wrap);

		pnlContent.add(new JLabel("Dagprijs"));
		pnlContent.add(txtDagprijs, gap);

		pnlContent.add(new JLabel("Brandstofprijs"));
		pnlContent.add(txtBrandstofprijs, wrap);

		pnlContent.add(new JLabel("Kilometerprijs"));
		pnlContent.add(txtKilometerprijs, gap);

		pnlContent.add(new JLabel("Borgprijs"));
		pnlContent.add(txtBorgprijs, wrap);

		this.pnlContent.add(this.tblOnderhoud.getTableHeader(), "spanx 4, wrap");
		this.pnlContent.add(this.tblOnderhoud, "spanx 4, wrap");

		if (model.getVoertuigID() != 0)
		{
			pnlBotMenu.add(btnWijzigen);
			pnlBotMenu.add(btnOnderhoudToevoegen);
		}
		pnlBotMenu.add(btnSave);
		pnlBotMenu.add(btnCancel);

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnOnderhoudToevoegen.addActionListener(this);
		btnWijzigen.addActionListener(this);

		setErrorMessages(model.getErrors());
		loadModelData();
	}

	protected Voertuig getEditedModel()
	{

		model.setType(txtType.getText());
		model.setCategorie(txtCategorie.getText());
		model.setMerk(txtMerk.getText());
		model.setKleur(txtKleur.getText());
		model.setBeschrijving(txtBeschrijving.getText());
		model.isVerhuurbaar(cbVerhuurbaar.isSelected());

		model.setAirco(cbAirco.isSelected());
		model.setBorgPrijs(toInt(txtBorgprijs.getText()));
		model.setBouwJaar(toInt(txtBouwjaar.getText()));
		model.setBrandstof(txtBrandstof.getText());
		model.setBrandstofPrijs(toDouble(txtBrandstofprijs.getText()));
		model.setDagPrijs(toDouble(txtDagprijs.getText()));
		model.setKenteken(txtKenteken.getText());
		model.setKilometerPrijs(toDouble(txtKilometerprijs.getText()));
		model.setKilometerStand(toInt(txtKilometerstand.getText()));
		model.setStation(cbStation.isSelected());

		return this.model;
	}

	private void loadModelData()
	{
		// setAllThings!
		txtType.setText(model.getType());
		txtCategorie.setText(model.getCategorie());
		txtMerk.setText(model.getMerk());
		txtKleur.setText(model.getKleur());
		txtBeschrijving.setText(model.getBeschrijving());
		cbVerhuurbaar.setSelected(model.getVerhuurbaar());

		
		cbAirco.setSelected(model.getAirco());
		txtBorgprijs.setText(String.valueOf(model.getBorgPrijs()));
		txtBouwjaar.setText(String.valueOf(model.getBouwJaar()));
		txtBrandstof.setText(model.getBrandstof());
		txtBrandstofprijs.setText(String.valueOf(model.getBrandstofPrijs()));
		txtDagprijs.setText(String.valueOf(model.getDagPrijs()));
		txtKenteken.setText(model.getKenteken());
		txtKilometerprijs.setText(String.valueOf(model.getKilometerPrijs()));
		txtKilometerstand.setText(String.valueOf(model.getKilometerStand()));
		cbStation.setSelected(model.getStation());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);

		if (e.getSource() == btnSave)
		{
			// no voertuigID? this means we know we're adding a new Klant object
			if (model.getVoertuigID() == 0)
			{
				runTask("Voertuig", "voertuigToevoegen", new Object[] { getEditedModel() });

			}
			else
			{
				runTask("Voertuig", "voertuigWijzigen", new Object[] { getEditedModel() });
			}
		}
		else if (e.getSource() == btnCancel)
		{
			runTask("Voertuig", "voertuigOverzichtRaadplegen");
		}
		else if (e.getSource() == btnOnderhoudToevoegen)
		{

			OnderhoudDetailsView view = new OnderhoudDetailsView(new Onderhoud());

			int value = JOptionPane.showConfirmDialog(this, view, "Onderhoud toevoegen", JOptionPane.OK_CANCEL_OPTION);

			if (value == JOptionPane.OK_OPTION)
			{
				Onderhoud onderhoud = view.getEditedModel();
				onderhoud.setVoertuig(model);
				runTask("Voertuig", "onderhoudToevoegen", new Object[] { onderhoud });
			}

		}
		else if (e.getSource() == btnWijzigen && selectedOnderhoud != null)
		{
			OnderhoudDetailsView view = new OnderhoudDetailsView(selectedOnderhoud);

			int value = JOptionPane.showConfirmDialog(this, view, "Onderhoud wijzigen", JOptionPane.OK_CANCEL_OPTION);

			if (value == JOptionPane.OK_OPTION)
			{
				Onderhoud onderhoud = view.getEditedModel();
				onderhoud.setVoertuig(model);
				runTask("Voertuig", "onderhoudWijzigen", new Object[] { onderhoud });
			}
		}
	}

	private JTable createOnderhoudTable(ArrayList<Onderhoud> onderhoudLijst)
	{

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.addColumn("Nummer");
		dtm.addColumn("Beschrijving");
		dtm.addColumn("Locatie");
		dtm.addColumn("Handeling");
		dtm.addColumn("Verhuurbaar");

		for (Onderhoud onderhoud : onderhoudLijst)
		{
			dtm.addRow(new Object[] { onderhoud.getOnderhoudID(), onderhoud.getBeschrijving(), onderhoud.getLocatie(),
					onderhoud.getHandeling(), onderhoud.isVoldaan() });
		}

		TableColumnModel tcm = new DefaultTableColumnModel();
		tcm.addColumn(new TableColumn(0, 50));
		tcm.addColumn(new TableColumn(1, 200));
		tcm.addColumn(new TableColumn(2, 200));
		tcm.addColumn(new TableColumn(3, 200));
		tcm.addColumn(new TableColumn(4, 200));

		tcm.getColumn(0).setHeaderValue("Nummer");
		tcm.getColumn(1).setHeaderValue("Beschrijving");
		tcm.getColumn(2).setHeaderValue("Locatie");
		tcm.getColumn(3).setHeaderValue("Handeling");
		tcm.getColumn(4).setHeaderValue("Verhuurbaar");

		JTable table = new JTable(dtm, tcm)
		{
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column)
			{
				return false;
			};
		};
		table.getSelectionModel().addListSelectionListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // let only
		// one
		// row
		// be
		// selected

		return table;
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	{
		int index = tblOnderhoud.getSelectedRow();
		boolean inRange = index >= 0 && index < this.model.getOnderhoud().size();

		if (inRange)
		{
			this.selectedOnderhoud = this.model.getOnderhoud().get(index);

		}
		btnWijzigen.setEnabled(inRange);
	}


	/**
	 * Parses a string to an int, when it fails it returns the default int value.
	 * @param string The string to parse.
	 * @return The parsed int value.
	 */
	private int toInt(String string)
	{
		try
		{
			return Integer.parseInt(string);
		}
		catch (NumberFormatException ex)
		{
			return 0;
		}
	}
	
	/**
	 * Parses a string to a double, when it fails it returns the default double value.
	 * @param string The string to parse.
	 * @return The parsed double value.
	 */
	private double toDouble(String string)
	{
		try
		{
			return Double.parseDouble(string);
		}
		catch (NumberFormatException ex)
		{
			return 0;
		}
	}
}