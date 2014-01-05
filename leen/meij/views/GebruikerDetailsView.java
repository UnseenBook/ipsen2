package leen.meij.views;

import java.awt.event.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import leen.meij.*;

/**
 * Displays A single Gebruiker in a View.
 * 
 * @author Thijs
 * 
 */
public class GebruikerDetailsView extends MasterView<Gebruiker> implements ActionListener
{

	private JTextField txtPersoneelnummer = new JTextField(15);
	private JTextField txtGebruikersnaam = new JTextField(15);
	private JComboBox<Afdeling> cbAfdeling = new JComboBox<Afdeling>(Afdeling.Afdelingen);
	private JTextField txtVoornaam = new JTextField(15);
	private JTextField txtTussenvoegsel = new JTextField(15);
	private JTextField txtAchternaam = new JTextField(15);

	private JTextField txtWachtwoord = new JTextField(15);

	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	private JButton btnChangePassword = new JButton("Wachtwoord veranderen");

	/**
	 * Initializes a new instance of the GebruikerDetailsView, specifying a Gebruiker object.
	 * @param model The Gebruiker object.
	 */
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
		pnlContent.add(cbAfdeling, wrap + span2);

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

		pnlBotMenu.add(btnChangePassword);
		pnlBotMenu.add(btnSave);
		pnlBotMenu.add(btnCancel);

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnChangePassword.addActionListener(this);
		btnChangePassword.setVisible(model.getGebruikerID() != 0);
		setErrorMessages(model.getErrors());
		loadModelData();
	}

	/**
	 * Handles used interaction such as button clicks.
	 */
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
		else if (e.getSource() == btnChangePassword)
		{
			JPanel view = new JPanel();
			view.setLayout(new MigLayout());
			JTextField wachtwoord = new JTextField(15);
			view.add(new JLabel("Nieuw wachtwoord"));
			view.add(wachtwoord);

			int value = JOptionPane.showConfirmDialog(this, view, "Wachtwoord veranderen", JOptionPane.OK_CANCEL_OPTION);

			if (value == JOptionPane.OK_OPTION)
			{
				runTask("Gebruiker", "wachtwoordVeranderen", new Object[] { model.getGebruikerID(), wachtwoord.getText() });
			}
		}
	}

	/**
	 * Loads data from the model into the view.
	 */
	private void loadModelData()
	{
		txtAchternaam.setText(model.getAchternaam());

		cbAfdeling.setSelectedItem(model.getAfdeling());

		txtGebruikersnaam.setText(model.getGebruikersnaam());
		txtPersoneelnummer.setText(String.valueOf(model.getPersoneelnummer()));
		txtTussenvoegsel.setText(model.getTussenvoegsel());
		txtVoornaam.setText(model.getVoornaam());
		txtWachtwoord.setText(model.getWachtwoord());
	}

	/**
	 * Loads data from the view into the model.
	 */
	protected Gebruiker getEditedModel()
	{
		model.setAchternaam(txtAchternaam.getText());

		model.setGebruikersnaam(txtGebruikersnaam.getText());
		model.setPersoneelnummer(Integer.parseInt(txtPersoneelnummer.getText()));
		model.setTussenvoegsel(txtTussenvoegsel.getText());
		model.setVoornaam(txtVoornaam.getText());
		model.setWachtworod(txtWachtwoord.getText());

		model.setAfdeling(cbAfdeling.getItemAt(cbAfdeling.getSelectedIndex()));

		return model;
	}

}