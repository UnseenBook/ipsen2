package leen.meij.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
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
	
	private JDateChooser calBeginDatum = new JDateChooser();
	private JDateChooser calEindDatum = new JDateChooser();

	private JComboBox cbKlant = new JComboBox();
	private JComboBox cbVoertuig = new JComboBox();
	private JFormattedTextField txtBeginDatum = new JFormattedTextField();
	private JFormattedTextField txtEindDatum = new JFormattedTextField();
	private JTextField txtKilometer = new JTextField(15);
	private JTextField txtBedrag = new JTextField(15);
	private JTextField attribute = new JTextField(15); 
	private ArrayList<Klant> klantLijst;
	private ArrayList<Voertuig> voertuigLijst;
	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	public ReserveringDetailsView(Reservering model)
	{
		super(model);
		this.klantLijst = klantDataAccess.selectAll();
		this.voertuigLijst = voertuigDataAccess.selectAll();

		if(model.getReserveringID() == 0)
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
		txtBeginDatum.setColumns(15);
		txtEindDatum.setColumns(15);

		//Combobox Klant vullen
		for(Klant klant: klantLijst)
		{
			if (model.getKlant() != null && model.getKlant().getKlantID() == klant.getKlantID())
			{
				cbKlant.addItem(model.getKlant());
			} else
			{
				cbKlant.addItem(klant);
			}
		}
		
		//Combobox Voertuig vullen
		for(Voertuig voertuig: voertuigLijst)
		{
			if (model.getVoertuig() != null && model.getVoertuig().getVoertuigID() == voertuig.getVoertuigID())
			{
				cbVoertuig.addItem(model.getVoertuig());
			} else
			{
				cbVoertuig.addItem(voertuig);
			}
		}
		
		//row 1
		pnlContent.add(new JLabel("Klant"));
		pnlContent.add(cbKlant, gapRight + span2);
		
		//row 2
		pnlContent.add(new JLabel("Voertuig"));
		pnlContent.add(cbVoertuig, wrap + span2);
		
		pnlContent.add(new JLabel("Begin datum"));
		//pnlContent.add(txtBeginDatum,wrap +  gapTop + span2);
		pnlContent.add(calBeginDatum,wrap +  gapTop + span2);
		
		
		
		//row 3
		pnlContent.add(new JLabel("Eind datum"));
		pnlContent.add(calEindDatum,wrap +   gapTop + span2);
		//pnlContent.add(txtEindDatum,wrap +   gapTop + span2);
		
		
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
		setErrorMessages(model.getErrors());
	    loadModelData();
		
		
	}

	
	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
		if(e.getSource() == btnCancel)
		{
			runTask("Reservering", "reserveringOverzichtRaadplegen");
		}
		else if(e.getSource() == btnSave)
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
		cbKlant.setSelectedItem(model.getKlant());
		cbVoertuig.setSelectedItem(model.getVoertuig());
		calBeginDatum.setDate(model.getBeginDatum());
		calEindDatum.setDate(model.getEindDatum());
		txtKilometer.setText(Integer.toString(model.getKilometer()));
	}
	
	protected Reservering getEditedModel()
	{
		model.setKlant((Klant)cbKlant.getSelectedItem());
		model.setVoertuig((Voertuig) cbVoertuig.getSelectedItem());
		model.setBeginDatum(calBeginDatum.getDate());
		model.setEindDatum(calEindDatum.getDate());
		model.setKilometer(Integer.parseInt(txtKilometer.getText()));
		return this.model;
	}
	


}