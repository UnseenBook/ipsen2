package leen.meij.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.Voertuig;
import leen.meij.dataAccess.KlantDataAccess;
import leen.meij.dataAccess.VoertuigDataAccess;

import com.toedter.calendar.JDateChooser;

public class ReserveringDetailsView extends MasterView<Reservering> implements ActionListener
{
	private JDateChooser dcReserveerDatum = new JDateChooser();
	private JDateChooser dcBeginDatum = new JDateChooser();
	private JDateChooser dcEindDatum = new JDateChooser();

	private JComboBox cbKlant = new JComboBox();
	private JComboBox cbVoertuig = new JComboBox();

	private JTextField txtKilometer = new JTextField(15);
	private JTextField txtBedrag = new JTextField(15);
	private ArrayList<Klant> klantLijst;
	private ArrayList<Voertuig> voertuigLijst;
	private JTextField txtStatus = new JTextField(15);
	private JTextField attribute = new JTextField(12);
	private ArrayList<Klant> tempList;
	private ArrayList<Voertuig> tempListvoertuig;

	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();

	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	private JButton btnBerekenBedrag = new JButton("Bereken bedrag");
	
	public ReserveringDetailsView(Reservering model)
	{
		super(model);
		this.klantLijst = klantDataAccess.selectAll();
		this.voertuigLijst = voertuigDataAccess.selectAll();

		if (model.getReserveringID() == 0)
		{
			this.setTitle("Reservering toevoegen");
		}
		else
		{
			this.setTitle("Reservering aanpassen");
		}
		String gapRight = "gapright 20,";
		String gapTop = "gaptop 10, ";
		String wrap = "wrap,";
		String span2 = "spanx 2,";
		txtBedrag.setColumns(15);
		txtKilometer.setColumns(15);
		dcReserveerDatum.setPreferredSize(new Dimension(125, 20));
		dcBeginDatum.setPreferredSize(new Dimension(125, 20));
		dcEindDatum.setPreferredSize(new Dimension(125, 20));

		// Combobox Klant vullen
		for (Klant klant : klantLijst)
		{
			if (model.getKlant() != null && model.getKlant().getKlantID() == klant.getKlantID())
			{
				cbKlant.addItem(model.getKlant());
			}
			else
			{
				cbKlant.addItem(klant);
			}
		}

		// Combobox Voertuig vullen
		for (Voertuig voertuig : voertuigLijst)
		{
			if (voertuig.getVerhuurbaar())
			{
				if (model.getVoertuig() != null && model.getVoertuig().getVoertuigID() == voertuig.getVoertuigID())
				{
					cbVoertuig.addItem(model.getVoertuig());
				}
				else
				{
					cbVoertuig.addItem(voertuig);
				}
			}
		}

		// row 1
		pnlContent.add(new JLabel("Klant"));
		pnlContent.add(cbKlant, gapRight + span2);

		// row 2
		pnlContent.add(new JLabel("Voertuig"));
		pnlContent.add(cbVoertuig, wrap + span2);

		//
		pnlContent.add(new JLabel("Reserveer datum"));
		pnlContent.add(dcReserveerDatum, wrap + gapTop + span2);

		pnlContent.add(new JLabel("Begin datum"));
		// pnlContent.add(txtBeginDatum,wrap + gapTop + span2);
		pnlContent.add(dcBeginDatum, wrap + gapTop + span2);

		// row 3
		pnlContent.add(new JLabel("Eind datum"));
		pnlContent.add(dcEindDatum, wrap + gapTop + span2);
		// pnlContent.add(txtEindDatum,wrap + gapTop + span2);

		// row 4
		pnlContent.add(new JLabel("Kilometers"));
		pnlContent.add(txtKilometer, wrap + gapTop + span2);

		// row 5
		pnlContent.add(new JLabel("Bedrag"));
		pnlContent.add(txtBedrag, gapTop + span2);
		pnlContent.add(btnBerekenBedrag, wrap );

		// row 6
		pnlContent.add(new JLabel("Status"));
		pnlContent.add(txtStatus, wrap + gapTop + span2);

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnBerekenBedrag.addActionListener(this);
		
		

		pnlBotMenu.add(btnSave);
		pnlBotMenu.add(btnCancel);
		setErrorMessages(model.getErrors());
		loadModelData();

	}

	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if (e.getSource() == btnCancel)
		{
			runTask("Reservering", "reserveringOverzichtRaadplegen");
		}
		else if (e.getSource() == btnSave)
		{
			if (model.getKlantID() == 0)
			{
				runTask("Reservering", "reserveringToevoegen", new Object[] { getEditedModel() });
			}
			else
			{
				runTask("Reservering", "reserveringWijzigen", new Object[] { getEditedModel() });
			}
		}
		else if (e.getSource() == btnBerekenBedrag)
		{
			if (dcEindDatum.getDate() != null && dcBeginDatum.getDate() != null && ((Voertuig) cbVoertuig.getSelectedItem()) != null)
			{
				Voertuig voertuig = (Voertuig) cbVoertuig.getSelectedItem();
				if (voertuig != null)
				{
					long millilPerDay = 24L * 60L * 60L * 1000L;
					long aantalDagen = (dcEindDatum.getDate().getTime() - dcBeginDatum.getDate().getTime()) / millilPerDay + 1;

					double bedrag = voertuig.getDagPrijs() * aantalDagen;
					txtBedrag.setText(String.valueOf(bedrag));
				}
			}
		}

	}

	private void loadModelData()
	{
		cbKlant.setSelectedItem(model.getKlant());
		cbVoertuig.setSelectedItem(model.getVoertuig());
		dcReserveerDatum.setDate(model.getReserveerDatum());
		dcBeginDatum.setDate(model.getBeginDatum());
		dcEindDatum.setDate(model.getEindDatum());
		txtKilometer.setText(Integer.toString(model.getKilometer()));
		txtBedrag.setText(Double.toString(model.getBedrag()));
		txtStatus.setText(model.getStatus());
	}

	protected Reservering getEditedModel()
	{

		model.setKlant((Klant) cbKlant.getSelectedItem());
		model.setVoertuig((Voertuig) cbVoertuig.getSelectedItem());
		model.setReserveerDatum(dcReserveerDatum.getDate());
		model.setBeginDatum(dcBeginDatum.getDate());
		model.setEindDatum(dcEindDatum.getDate());
		model.setKilometer(Integer.parseInt(txtKilometer.getText()));
		model.setBedrag(Double.parseDouble(txtBedrag.getText()));
		model.setStatus(txtStatus.getText());

		return this.model;
	}

}