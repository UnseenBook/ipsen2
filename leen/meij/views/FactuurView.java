package leen.meij.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import leen.meij.Factuur;
import leen.meij.Reservering;

import com.toedter.calendar.JDateChooser;

public class FactuurView extends MasterView<Reservering> implements ListSelectionListener,ActionListener{
	
	private JButton btnOpslaan = new JButton("Opslaan");
	private JButton btnAnnuleren = new JButton("Annuleren");
	private Factuur factuur; //This is temporary 
	
	//invoice components
	private JTextField reserveringenid = new JTextField(15);
	private JTextField voertuigenid = new JTextField(15);
	private JTextField bedrag = new JTextField(15);
	private JDateChooser datum = new JDateChooser(); 
	private JTextField reden = new JTextField(15);
	
	
	public FactuurView(Reservering model) {
		super(model);
		setTitle("Factuur opmaken");
		
		//textfield alignment
		String gapRight = "gapright 20,";
		String gapTop = "gaptop 10, ";
		String wrap = "wrap,";
		String span2 = "spanx 2,";
		
		//jdatechooser w,h
		datum.setPreferredSize(new Dimension(125,20));
		
		//begin invoice
		
		//row 1
		pnlContent.add(new JLabel("Reservering kenmerk"));
		pnlContent.add(reserveringenid,wrap + gapTop + span2);
		reserveringenid.setToolTipText("Reserverig kenmerk");
		
		//row 2
		pnlContent.add(new JLabel("Voertuig"));
		pnlContent.add(voertuigenid,wrap + gapTop + span2);
		voertuigenid.setToolTipText("Voertuig id");
		
		//row 3
		pnlContent.add(new JLabel("Bedrag"));
		pnlContent.add(bedrag, wrap + gapTop + span2);
		bedrag.setToolTipText("Totaal bedrag");
		
		//row 4
		pnlContent.add(new JLabel("Datum"));
		pnlContent.add(datum, wrap + gapTop + span2);
		datum.setToolTipText("Datum");
		
		//row 5
		pnlContent.add(new JLabel("Reden"));
		pnlContent.add(reden, wrap + gapTop + span2);
		reden.setToolTipText("Opmerking");
		
			
		//buttons
		btnAnnuleren.setToolTipText("Terug naar overzicht");
		btnAnnuleren.addActionListener(this);
		btnOpslaan.setToolTipText("Factuur opslaan");
		btnOpslaan.addActionListener(this);
		

		pnlBotMenu.add(this.btnOpslaan);
		pnlBotMenu.add(this.btnAnnuleren);
		
		setErrorMessages(model.getErrors());
	}

	public void actionPerformed(ActionEvent e)
	{
		super.actionPerformed(e);
	
		if(e.getSource() == btnOpslaan)
		{
			if(model.getReserveringID() == 0)
			{
				
			}
			
		}
		else if(e.getSource() == btnAnnuleren)
		{
			runTask("Reservering", "inleverlijstRaadplegen");
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Reservering getEditedModel() {
		// TODO Auto-generated method stub
		return null;
	}

}