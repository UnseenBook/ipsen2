package leen.meij.views;

import java.awt.event.*;
import javax.swing.*;
import leen.meij.*;

public class GebruikerDetailsView extends MasterView<Gebruiker> implements ActionListener
{

	private JTextField txtPersoneelnummer = new JTextField(15);
	private JTextField txtGebruikersnaam = new JTextField(15);
	private JTextField txtAfdeling = new JTextField(15);
	private JTextField txtVoornaam = new JTextField(15);
	private JTextField txtTussenvoegsel = new JTextField(15);
	private JTextField txtAchternaam = new JTextField(15);

	private JTextField txtWachtwoord = new JTextField(15);

	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	public GebruikerDetailsView(Gebruiker model)
	{
		super(model);

		// no GebruikerID? this means we know we're adding a new Gebruiker
		// object
		if (model.getGebruikerID() == 0)
		{
			this.setTitle("Gebruiker toevoegen");
		}
		else
		{
			this.setTitle("Gebruiker aanpassen");

		}

		String gap = "gapright 20,";
		String wrap = "wrap,";
		String span2 = "spanx 2,";

		// row 1
		pnlContent.add(new JLabel("Voornaam"));
		pnlContent.add(txtVoornaam, gap + span2);

		pnlContent.add(new JLabel("Personeelnummer"));
		pnlContent.add(txtPersoneelnummer, wrap + span2);

		// row 2
		pnlContent.add(new JLabel("Tussenvoegsel"));
		pnlContent.add(txtTussenvoegsel, gap + span2);

		pnlContent.add(new JLabel("Afdeling"));
		pnlContent.add(txtAfdeling, wrap + span2);

		// row 3
		pnlContent.add(new JLabel("Achternaam"));
		pnlContent.add(txtAchternaam, gap + span2);

		pnlContent.add(new JLabel("Gebruikersnaam"));
		pnlContent.add(txtGebruikersnaam, wrap + span2);

		// row 4
		if (model.getGebruikerID() == 0)
		{
			pnlContent.add(new JLabel("Wachtwoord"));
			pnlContent.add(txtWachtwoord, gap + span2);
		}
		pnlBotMenu.add(btnSave);
		pnlBotMenu.add(btnCancel);

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);

		setErrorMessages(model.getErrors());
		loadModelData();
	}

	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);

		if (e.getSource() == btnSave)
		{
			// no GebruikerID? this means we know we're adding a new Gebruiker
			// object
			if (model.getGebruikerID() == 0)
			{
				runTask("Gebruiker", "gebruikerToevoegen", new Object[] { getEditedModel() });
			}
			else
			{
				runTask("Gebruiker", "gebruikerWijzigen", new Object[] { getEditedModel() });
			}
		}
		else if (e.getSource() == btnCancel)
		{
			runTask("Gebruiker", "gebruikersOverzichtRaadplegen");
		}
	}

	private void loadModelData()
	{
		txtAchternaam.setText(model.getAchternaam());
		txtAfdeling.setText(model.getAfdeling());
		txtGebruikersnaam.setText(model.getGebruikersnaam());
		txtPersoneelnummer.setText(String.valueOf(model.getPersoneelnummer()));
		txtTussenvoegsel.setText(model.getTussenvoegsel());
		txtVoornaam.setText(model.getVoornaam());

	}

	protected Gebruiker getEditedModel()
	{
		model.setAchternaam(txtAchternaam.getText());
		model.setAfdeling(txtAfdeling.getText());
		model.setGebruikersnaam(txtGebruikersnaam.getText());
		model.setPersoneelnummer(Integer.parseInt(txtPersoneelnummer.getText()));
		model.setTussenvoegsel(txtTussenvoegsel.getText());
		model.setAchternaam(txtVoornaam.getText());
		model.setVoornaam(txtVoornaam.getText());
		model.setWachtworod(txtWachtwoord.getText());

		return model;
	}

}