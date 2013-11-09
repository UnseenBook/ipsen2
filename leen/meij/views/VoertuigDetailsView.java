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

public class VoertuigDetailsView extends MasterView<Voertuig> implements ActionListener, ListSelectionListener {

	private Onderhoud selectedOnderhoud;
	
    private JTextField txtType = new JTextField(15);
    private JTextField txtCategorie = new JTextField(15);
    private JTextField txtMerk = new JTextField(15);
    private JTextField txtKleur = new JTextField(15);
    private JTextField txtBeschrijving = new JTextField(15);
    private JCheckBox cbVerhuurbaar= new JCheckBox();
    private JTable tblOnderhoud = new JTable();
    
    private JButton btnWijzigen = new JButton("Wijzigen");
    private JButton btnOnderhoudToevoegen = new JButton("Onderhoud Toevoegen");
    private JButton btnSave = new JButton("Opslaan");
    private JButton btnCancel = new JButton("Annuleren");

    public VoertuigDetailsView(Voertuig model) {
        super(model);

        
        if (model.getVoertuigID() == 0) {
            this.setTitle("Voertuig toevoegen");
        } else {
            this.setTitle("Voertuig aanpassen");
        }

        String gap = "gapright 20,";
        String wrap = "wrap,";
        String span2 = "spanx 2,";
        
        tblOnderhoud = createOnderhoudTable(model.getOnderhoud());
        

        pnlContent.add(new JLabel("Type"));
        pnlContent.add(txtType, wrap);

        pnlContent.add(new JLabel("Categorie"));
        pnlContent.add(txtCategorie, wrap);

        pnlContent.add(new JLabel("Merk"));
        pnlContent.add(txtMerk, wrap);

        pnlContent.add(new JLabel("Kleur"));
        pnlContent.add(txtKleur, wrap);

        pnlContent.add(new JLabel("Beschrijving"));
        pnlContent.add(txtBeschrijving, wrap);
        
        pnlContent.add(new JLabel("Verhuurbaar"));
        pnlContent.add(cbVerhuurbaar, wrap);
        
        

        this.pnlContent.add(this.tblOnderhoud.getTableHeader(), span2 + wrap);
        this.pnlContent.add(this.tblOnderhoud, span2 + wrap);
        
        if (model.getVoertuigID() != 0){
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

    protected Voertuig getEditedModel() {


        model.setType(txtType.getText());
        model.setCategorie(txtCategorie.getText());
        model.setMerk(txtMerk.getText());
        model.setKleur(txtKleur.getText());
        model.setBeschrijving(txtBeschrijving.getText());
        model.isVerhuurbaar(cbVerhuurbaar.isSelected());
        
        return this.model;
    }

    private void loadModelData() {
        //setAllThings!
        txtType.setText(model.getType());
        txtCategorie.setText(model.getCategorie());
        txtMerk.setText(model.getMerk());
        txtKleur.setText(model.getKleur());
        txtBeschrijving.setText(model.getBeschrijving());
        cbVerhuurbaar.setSelected(model.getVerhuurbaar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        
        if (e.getSource() == btnSave)
		{
			// no voertuigID? this means we know we're adding a new Klant object
			if (model.getVoertuigID() == 0)
			{
				runTask("Voertuig", "voertuigToevoegen",
						new Object[] { getEditedModel() }); 
                                
			}
			else
			{
				runTask("Voertuig", "voertuigWijzigen",
						new Object[] { getEditedModel() });
			}
		}
		else if (e.getSource() == btnCancel)
		{
			runTask("Voertuig", "voertuigOverzichtRaadplegen");
		}
		else if(e.getSource() == btnOnderhoudToevoegen)
		{
			
			OnderhoudDetailsView view = new OnderhoudDetailsView(new Onderhoud());
			
			int value = JOptionPane.showConfirmDialog(this,view,"Onderhoud toevoegen",JOptionPane.OK_CANCEL_OPTION);
			
			if(value == JOptionPane.OK_OPTION)
			{
				Onderhoud onderhoud = view.getEditedModel();
				onderhoud.setVoertuig(model);
				runTask("Voertuig","onderhoudToevoegen", new Object[]{onderhoud});
			}
			
		} else if(e.getSource() == btnWijzigen && selectedOnderhoud != null)
		{
			OnderhoudDetailsView view = new OnderhoudDetailsView(selectedOnderhoud);
			
			int value = JOptionPane.showConfirmDialog(this,view,"Onderhoud wijzigen",JOptionPane.OK_CANCEL_OPTION);
			
			if(value == JOptionPane.OK_OPTION)
			{
				Onderhoud onderhoud = view.getEditedModel();
				onderhoud.setVoertuig(model);
				runTask("Voertuig","onderhoudWijzigen", new Object[]{onderhoud});
			}
		}
    }
    
    private JTable createOnderhoudTable(ArrayList<Onderhoud> onderhoudLijst) {
        
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Nummer");
        dtm.addColumn("Beschrijving");
        dtm.addColumn("Locatie");
        dtm.addColumn("Handeling");
        dtm.addColumn("Voldaan");

        for (Onderhoud onderhoud : onderhoudLijst) {
            dtm.addRow(new Object[]{onderhoud.getOnderhoudID(),onderhoud.getBeschrijving(), onderhoud.getLocatie(), onderhoud.getHandeling(), onderhoud.isVoldaan()});
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
        tcm.getColumn(4).setHeaderValue("Voldaan");


        JTable table = new JTable(dtm, tcm) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
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
}