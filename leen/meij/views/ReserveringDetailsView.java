
package leen.meij.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import leen.meij.Reservering;

public class ReserveringDetailsView extends MasterView<Reservering> implements ActionListener
{


	private JComboBox cbKlant = new JComboBox();
	private JComboBox cbVoertuig = new JComboBox();
	private JTextField txtBeginDatum = new JTextField(15);
	private JTextField txtEindDatum = new JTextField(15);
	private JTextField txtKilometer = new JTextField(15);
	private JTextField txtBedrag = new JTextField(15);
	private JTextField attribute = new JTextField(15); 
	private JButton btnSave = new JButton("Opslaan");
	private JButton btnCancel = new JButton("Annuleren");

	public ReserveringDetailsView(Reservering model)
	{
		super(model);
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
		
		
		
	}

	protected Reservering getEditedModel()
	{
		return this.model;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == btnCancel)
		{
			runTask("Reservering", "reserveringOverzichtRaadplegen");
		}
		if(e.getSource() == btnSave)
		{
			//action
		}
		
	}

}