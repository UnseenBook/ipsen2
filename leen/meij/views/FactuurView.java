package leen.meij.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import leen.meij.Factuur;
import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.dataAccess.ReserveringDataAccess;

import com.toedter.calendar.JDateChooser;

/**
 * Displays a Factuur.
 * @author Thijs
 *
 */
public class FactuurView extends MasterView<Factuur> implements ListSelectionListener,ActionListener
{
	
	private JButton btnOpslaan = new JButton("Opslaan");
	private JButton btnAnnuleren = new JButton("Annuleren");
	private Factuur factuur; //This is temporary 
	
	//invoice components
	private JTextField txtklantnaam = new JTextField(15);
	private JTextField txtklantadres = new JTextField(15);
	private JTextField txtklantpostcode = new JTextField(6);
	private JTextField txtklantplaats = new JTextField(8);
	//bedrijf[leenmeij]

	private JTextField txtbedrijfemail = new JTextField(10);
	private JTextField txtreserveringenid = new JTextField(8);
	private JTextField txtvoertuigenid = new JTextField(8);
	private JTextField txtbedrag = new JTextField(8);
	private JScrollPane scrollreden;
	private JDateChooser calFactuurdatum = new JDateChooser(); 
	private JTextArea txtreden = new JTextArea(5,15);
	private JLabel invoiceTitle = new JLabel("Leenmeij");
	private ArrayList<Reservering> reserveringLijst;
	private ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();
	
	
	/**
	 * Initializes a new instance of the FactuurView, specifying a Factuur object.
	 * @param model The Factuur object to display.
	 */
	public FactuurView(Factuur model) {
		super(model);
		this.reserveringLijst = reserveringDataAccess.selectAll();
		setTitle("Factuur opmaken");
		
		//textfield alignment
		String gapRight = "gapright 20,";
		String gapLeft0 = "gapLeft 1,";
		String gapLeft = "gapleft 140,";
		String gapLeft2 = "gapleft 20,";
		String gapTop = "gaptop 5, ";


		String gapTop2 = "gaptop 30, ";
		String wrap = "wrap 12,";
		String span2 = "spanx 12,";
		String span3 = "spanx 5,";
		String span4 = "spanx 2,";
	
		//jdatechooser w,h
		calFactuurdatum.setPreferredSize(new Dimension(125,20));
		
		//begin invoice
		
		//factuur kop-links
		pnlContent.add(new JLabel("Klant: "));
		pnlContent.add(txtklantnaam, gapTop + span2);
		invoiceTitle.getFont().deriveFont(18.0f);
		pnlContent.add(invoiceTitle, wrap +gapLeft + span3 + wrap);
		Font font = new Font("Arial", Font.BOLD, 40);
		invoiceTitle.setFont(font);

	
		pnlContent.add(new JLabel ("Adres: "));
		pnlContent.add(txtklantadres, wrap +span2);


		pnlContent.add(new JLabel("postcode"));
		pnlContent.add(txtklantpostcode, span3);
		pnlContent.add(txtklantplaats, span2 + wrap);
		
		pnlContent.add(new JLabel("Reden"));
		txtreden.setBorder(BorderFactory.createTitledBorder("Omschrijving"));
		scrollreden = new JScrollPane(txtreden,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pnlContent.add(scrollreden,wrap+ gapTop2 + span2);
		txtreden.setMaximumSize(new Dimension(195,100));
		txtreden.setWrapStyleWord(true);
		txtreden.setLineWrap(true);
		txtreden.setToolTipText("Opmerking");
	
		
		
		//factuur kop-rechts
		
		pnlContent.add(new JLabel("E-mail"), span2 + "pos 515px 180px, ");
		pnlContent.add(txtbedrijfemail, span2 + "pos 600px 180px, ");
				
		pnlContent.add(new JLabel("Kenmerk"), span2 + "pos 515px 210px, ");
		pnlContent.add(txtreserveringenid,span2 + "pos 600px 210px, ");
		txtreserveringenid.setToolTipText("Reserverig kenmerk");
		txtreserveringenid.setEditable(false);

		pnlContent.add(new JLabel("Voertuig"),span2 + "pos 515px 240px, ");
		pnlContent.add(txtvoertuigenid,span2 + "pos 600px 240px, ");
		txtvoertuigenid.setToolTipText("Voertuig id");
		

		pnlContent.add(new JLabel("Bedrag"),span2 + "pos 515px 270px, ");
		pnlContent.add(txtbedrag, span2 + "pos 600px 270px, ");
		txtbedrag.setToolTipText("Totaal bedrag");

		pnlContent.add(new JLabel("Datum"),span2 + "pos 515px 300px, ");
		pnlContent.add(calFactuurdatum, span2 + "pos 600px 300px, ");
		calFactuurdatum.setToolTipText("Factuur datum");
		
	
//		//row 5
//	
//		
			
		txtbedrijfemail.setEditable(false);
		txtklantadres.setEditable(false);
		txtklantnaam.setEditable(false);
		txtklantplaats.setEditable(false);
		txtklantpostcode.setEditable(false);
		txtreserveringenid.setEditable(false);
		txtvoertuigenid.setEditable(false);
		
		
		//buttons
		btnAnnuleren.setToolTipText("Terug naar overzicht");
		btnAnnuleren.addActionListener(this);
		btnOpslaan.setToolTipText("Factuur opslaan");
		btnOpslaan.addActionListener(this);
		

		pnlBotMenu.add(this.btnOpslaan);
		pnlBotMenu.add(this.btnAnnuleren);
		
		setErrorMessages(model.getErrors());
		loadModelData();
	}
	
	/**
	 * Handles user interaction.
	 */
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
	
		if(e.getSource() == btnOpslaan)
		{
			runTask("Reservering", "factuurToevoegen",	new Object[]{ getEditedModel() });
		}
		else if(e.getSource() == btnAnnuleren)
		{
			runTask("Reservering", "inleverlijstRaadplegen");
		}
	}

	/**
	 * Empty.
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		
	}

	/**
	 * Loads model data into the view.
	 */
	private void loadModelData()
	{
		// model data that is saved/edited
		calFactuurdatum.setDate(model.getDatum());
		txtreden.setText(model.getReden());
		txtbedrag.setText(String.valueOf(model.getBedrag()));
		
		Klant klant = model.getReservering().getKlant();
		
		txtbedrijfemail.setText(klant.getEmailadres());
		txtklantadres.setText(klant.getStraat() + " " + klant.getHuisNummer());
		txtklantnaam.setText(klant.getVolledigeNaam());
		txtklantplaats.setText(klant.getWoonplaats());
		txtklantpostcode.setText(klant.getPostcode());

		
		txtvoertuigenid.setText(model.getReservering().getVoertuig().toString());
		
		/*
		//txtmedewerkernaam.setText(model.getGebruiker().getGebruikersnaam());
		txtreserveringenid.setText(
				"V" + // voertuig v
				Integer.toString(model.getVoertuigID()) +
				"R" + //reservering r
				Integer.toString(model.getReserveringID()) + 
				"K" + //klant k
				Integer.toString(model.getKlantID())
				);
		txtvoertuigenid.setText
			(
				"ID" +
				Integer.toString(model.getVoertuigID()) +
				model.getVoertuig().getMerk()
			);
		txtbedrag.setText("\u20ac "+ Double.toString(model.getBedrag()));
		calFactuurdatum.setDate(model.getEindDatum()); 
		*/
		
	}

	/**
	 * Loads view data into the model.
	 */
	@Override
	protected Factuur getEditedModel() {
		
		// model data that is saved/edited
		model.setDatum(calFactuurdatum.getDate());
		model.setReden(txtreden.getText());
		model.setBedrag(Double.valueOf(txtbedrag.getText()));
		
		
		return this.model;
	}

}
