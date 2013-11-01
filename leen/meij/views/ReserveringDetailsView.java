package leen.meij.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.Voertuig;
import leen.meij.dataAccess.KlantDataAccess;
import leen.meij.dataAccess.VoertuigDataAccess;

public class ReserveringDetailsView extends MasterView<Reservering> implements ActionListener
{


	private JComboBox cbKlant = new JComboBox();
	private JComboBox cbVoertuig = new JComboBox();
	private JTextField txtBeginDatum = new JTextField(15);
	private JTextField txtEindDatum = new JTextField(15);
	private JTextField txtKilometer = new JTextField(15);
	private JTextField txtBedrag = new JTextField(15);
	private JTextField attribute = new JTextField(15); 
	private ArrayList<Klant> tempList;
	private ArrayList<Voertuig> tempListvoertuig;
	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	public ReserveringDetailsView(Reservering model)
	{
		super(model);
		this.tempList = klantDataAccess.selectAll();
		if(model.getKlantID() == 0)
		{
			this.setTitle("Reservering toevoegen");
		}
		else
		{
			this.setTitle("Klant aanpassen");
		}
		String gapRight = "gapright 20,";
		String gapTop = "gaptop 10, ";
		String wrap = "wrap,";
		String span2 = "spanx 2,";
		
		//temporary list
		for(Klant klant: tempList)
		{
			cbKlant.addItem(klant.getVolledigeNaam());
		}
		
		//temporary list 2
	//	for(Voertuig voertuig: tempListvoertuig)
		//{
			//cbVoertuig.addItem(voertuig.getMerk());
//		}
		
		//row 1
		pnlContent.add(new JLabel("Klant"));
		pnlContent.add(cbKlant, gapRight + span2);
		
		//row 2
		pnlContent.add(new JLabel("Voertuig"));
		pnlContent.add(cbVoertuig, wrap + span2);
		
		pnlContent.add(new JLabel("Begin datum"));
		pnlContent.add(txtBeginDatum,wrap +  gapTop + span2);
		
		
		//row 3
		pnlContent.add(new JLabel("Eind datum"));
		pnlContent.add(txtEindDatum,wrap +   gapTop + span2);
		
		//row 4
		pnlContent.add(new JLabel("Kilometers"));
		pnlContent.add(txtKilometer,wrap +  gapTop + span2);
		
		//row 5
		pnlContent.add(new JLabel("Bedrag"));
		pnlContent.add(txtBedrag, wrap + gapTop + span2);
		
		

		btnSave.addActionListener(this);
		btnCancel.addActionListener(this);
		pnlBotMenu.add(btnSave);
		pnlBotMenu.add(btnCancel);
		loadModelData();
		
		
	}

	protected Reservering getEditedModel()
	{
		return this.model;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if(e.getSource() == btnCancel)
		{
			runTask("Reservering", "reserveringOverzichtRaadplegen");
		}
		if(e.getSource() == btnSave)
		{
			if (model.getKlantID() == 0)
			{
				runTask("Reservering", "reserveringToevoegen",
						new Object[] { getEditedModel() });
			}
			else
			{
				runTask("Reservering", "reserveringWijzigen",
						new Object[] { getEditedModel() });
			}
		}
		
	}
	
	private void loadModelData()
	{

		cbKlant.setSelectedItem(model.getKlant().getVolledigeNaam());
		//cbVoertuig.setSelectedItem(model.getVoertuig().getMerk());
		txtKilometer.setText(Integer.toString(model.getKilometer()) + " KM");
		
	}
	


}