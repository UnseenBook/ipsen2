package leen.meij.views;

import java.awt.Image;
import java.awt.event.*;
import java.io.*;
import java.sql.Date;

import javax.imageio.*;
import javax.swing.*;

import leen.meij.Klant;

/**
 * 
 * @author Thijs
 * 
 */
public class KlantDetailsView extends MasterView<Klant> implements
		ActionListener
{
	private JFileChooser fcFileChooser = new JFileChooser();

	private ImageIcon iiPaspoort = new ImageIcon();
	private ImageIcon iiRijbewijs = new ImageIcon();

	private JTextField txtVoornaam = new JTextField(15);
	private JTextField txtKlantNummer = new JTextField(15);
	private JTextField txtTussenvoegsel = new JTextField(15);
	private JTextField txtAchternaam = new JTextField(15);
	private JTextField txtBedrijfsnaam = new JTextField(15);
	private JTextField txtKvkNummer = new JTextField(15);
	private JTextField txtStraat = new JTextField(15);
	private JTextField txtHuisnummer = new JTextField(5);
	private JTextField txtPostcode = new JTextField(15);
	private JTextField txtWoonplaats = new JTextField(15);
	private JTextField txtProvincie = new JTextField(15);
	private JTextField txtLand = new JTextField(15);
	private JTextField txtTelefoonnummer = new JTextField(15);
	private JTextField txtMobielnummer = new JTextField(15);
	private JTextField txtEmailadres = new JTextField(15);
	private JTextField txtGeboorteDatum = new JTextField(15);

	private JButton btnPaspoort = new JButton("Paspoort uploaden");
	private JButton btnRijbewijs = new JButton("Rijbewijs uploaden");

	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	public KlantDetailsView(Klant model)
	{
		super(model);
		// no klantID? this means we know we're adding a new Klant object
		if (model.getKlantID() == 0)
		{
			this.setTitle("Klant toevoegen");
		}
		else
		{
			this.setTitle("Klant aanpassen");
		}
		String gap = "gapright 20,";
		String wrap = "wrap,";
		String span2 = "spanx 2,";

		// row 1
		pnlContent.add(new JLabel("Voornaam"));
		pnlContent.add(txtVoornaam, gap + span2);

		pnlContent.add(new JLabel("Telefoonnummer"));
		pnlContent.add(txtTelefoonnummer, wrap + span2);

		// row 2
		pnlContent.add(new JLabel("Tussenvoegsel"));
		pnlContent.add(txtTussenvoegsel, gap + span2);

		pnlContent.add(new JLabel("Mobielenummer"));
		pnlContent.add(txtMobielnummer, wrap + span2);

		// row 3
		pnlContent.add(new JLabel("Achternaam"));
		pnlContent.add(txtAchternaam, gap + span2);

		pnlContent.add(new JLabel("Email adres"));
		pnlContent.add(txtEmailadres, wrap + span2);

		// row 4
		pnlContent.add(new JLabel("Adres"));
		pnlContent.add(txtStraat, "pushx");
		pnlContent.add(txtHuisnummer, gap + "pushx");

		pnlContent.add(new JLabel("Bedrijfsnaam"));
		pnlContent.add(txtBedrijfsnaam, wrap + span2);

		// row 5
		pnlContent.add(new JLabel("Postcode"));
		pnlContent.add(txtPostcode, gap + span2);

		pnlContent.add(new JLabel("Kvknummer"));
		pnlContent.add(txtKvkNummer, wrap + span2);

		// row 6
		pnlContent.add(new JLabel("Woonplaats"));
		pnlContent.add(txtWoonplaats, gap + span2);

		pnlContent.add(new JLabel("Provincie"));
		pnlContent.add(txtProvincie, wrap + span2);

		// row 7
		pnlContent.add(new JLabel("Geboortedatum"));
		pnlContent.add(txtGeboorteDatum, gap + span2);

		pnlContent.add(new JLabel("Land"));
		pnlContent.add(txtLand, wrap + span2);

		// row 8
		pnlContent.add(new JLabel("Paspoort"));
		pnlContent.add(btnPaspoort, span2);

		pnlContent.add(new JLabel("Rijbewijs"));
		pnlContent.add(btnRijbewijs, wrap + span2);

		// row 9 Image
		pnlContent.add(new JLabel(iiPaspoort), span2 + "w 350, h 300");
		pnlContent.add(new JLabel(iiRijbewijs), wrap + span2 + "w 350, h 300");

		pnlBotMenu.add(btnSave);
		pnlBotMenu.add(btnCancel);

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		btnPaspoort.addActionListener(this);
		btnRijbewijs.addActionListener(this);

		setErrorMessages(model.getErrors());
		loadModelData();
	}

	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);

		if (e.getSource() == btnPaspoort)
		{
			byte[] bytes = openFileAndReadBytes();
			if (bytes != null)
			{
				model.setKopiePaspoort(bytes);
				loadImages();
			}
		}
		else if (e.getSource() == btnRijbewijs)
		{
			byte[] bytes = openFileAndReadBytes();
			if (bytes != null)
			{
				model.setKopieRijbewijs(bytes);
				loadImages();
			}
		}
		else if (e.getSource() == btnSave)
		{
			// no klantID? this means we know we're adding a new Klant object
			if (model.getKlantID() == 0)
			{
				runTask("Klant", "klantToevoegen",
						new Object[] { getEditedModel() });
			}
			else
			{
				runTask("Klant", "klantWijzigen",
						new Object[] { getEditedModel() });
			}
		}
		else if (e.getSource() == btnCancel)
		{
			runTask("Klant", "klantOverzichtRaadplegen");
		}
	}

	private void loadModelData()
	{
		txtAchternaam.setText(model.getAchternaam());
		txtBedrijfsnaam.setText(model.getBedrijfsnaam());
		txtEmailadres.setText(model.getEmailadres());
		if (model.getGeboorteDatum() != null) txtGeboorteDatum.setText(model
				.getGeboorteDatum().toString());
		txtHuisnummer.setText(model.getHuisNummer());
		txtKlantNummer.setText(model.getKvknummer());
		txtKvkNummer.setText(model.getKvknummer());
		txtLand.setText(model.getLand());
		txtMobielnummer.setText(model.getMobielnummer());
		txtPostcode.setText(model.getPostcode());
		txtProvincie.setText(model.getProvincie());
		txtStraat.setText(model.getStraat());
		txtTelefoonnummer.setText(model.getTelefoonnummer());
		txtTussenvoegsel.setText(model.getTussenvoegsel());
		txtVoornaam.setText(model.getVoornaam());
		txtWoonplaats.setText(model.getWoonplaats());
		loadImages();
	}

	protected Klant getEditedModel()
	{
		model.setAchternaam(txtAchternaam.getText());
		model.setBedrijfsnaam(txtBedrijfsnaam.getText());
		model.setEmailadres(txtEmailadres.getText());
		try
		{
			model.setGeboorteDatum(Date.valueOf(txtGeboorteDatum.getText()));
		}
		catch (IllegalArgumentException ex)
		{
			model.setGeboorteDatum(null);
		}
		model.setHuisNummer(txtHuisnummer.getText());
		model.setKvknummer(txtKvkNummer.getText());
		model.setLand(txtLand.getText());
		model.setMobielnummer(txtMobielnummer.getText());
		model.setPostcode(txtPostcode.getText());
		model.setProvincie(txtProvincie.getText());
		model.setStraat(txtStraat.getText());
		model.setTelefoonnummer(txtTelefoonnummer.getText());
		model.setTussenvoegsel(txtTussenvoegsel.getText());
		model.setVoornaam(txtVoornaam.getText());
		model.setWoonplaats(txtWoonplaats.getText());
		
		return this.model;
	}

	/**
	 * Opens a file chooser then returns all the bytes from the selected file;
	 * otherwise null.
	 * 
	 * @return All the bytes from the selected file; otherwise null.
	 */
	private byte[] openFileAndReadBytes()
	{
		int value = fcFileChooser.showOpenDialog(this);
		if (value == JFileChooser.APPROVE_OPTION)
		{
			File file = fcFileChooser.getSelectedFile();
			InputStream stream = null;
			try
			{
				try
				{
					byte[] buffer = new byte[(int) file.length()];

					int offset = 0;
					int read = 0;
					stream = new FileInputStream(file);
					while (offset < buffer.length
							&& (read = stream.read(buffer, offset,
									buffer.length - offset)) >= 0)
					{
						offset += read;
					}
					if (offset >= buffer.length) return buffer;
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					if (stream != null)
					{
						stream.close();
					}
				}
			}
			catch (IOException e)
			{

			}
		}
		return null;
	}

	private void loadImages()
	{
		if (this.model == null) return;

		byte[] paspoort = this.model.getKopiePaspoort();
		if (paspoort != null)
		{
			ByteArrayInputStream stream = new ByteArrayInputStream(paspoort);
			try
			{
				iiPaspoort.setImage(ImageIO.read(stream).getScaledInstance(350,
						300, Image.SCALE_SMOOTH));
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
		byte[] rijbewijs = this.model.getKopieRijbewijs();
		if (rijbewijs != null)
		{
			ByteArrayInputStream stream = new ByteArrayInputStream(rijbewijs);
			try
			{
				iiPaspoort.setImage(ImageIO.read(stream).getScaledInstance(350,
						300, Image.SCALE_SMOOTH));
			}
			catch (IOException e)
			{
				throw new RuntimeException(e);
			}
		}
	}
}