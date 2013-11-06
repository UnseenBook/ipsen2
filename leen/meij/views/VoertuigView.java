package leen.meij.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import leen.meij.Voertuig;

public class VoertuigView extends MasterView<ArrayList<Voertuig>> implements ListSelectionListener, ActionListener {

    private Voertuig selectedVoertuig;
    private JButton btnWijzigen = new JButton("Wijzigen");
    private JButton btnVerwijderen = new JButton("Verwijderen");
    private JButton btnToevoegen = new JButton("Toevoegen");
    private JTable tblVoertuigen;

    public VoertuigView(ArrayList<Voertuig> model) {
        super(model);
        // TODO Auto-generated constructor stub
        this.setTitle("Voertuigen");

        // content panel

        tblVoertuigen = createVoertuigTable();
        this.pnlContent.add(this.tblVoertuigen.getTableHeader(), "wrap");
        this.pnlContent.add(this.tblVoertuigen);

        // bottom menu panel

        btnWijzigen.setEnabled(false);
        btnVerwijderen.setEnabled(false);

        this.pnlBotMenu.add(this.btnWijzigen);
        this.pnlBotMenu.add(this.btnVerwijderen);
        this.pnlBotMenu.add(this.btnToevoegen);

        btnWijzigen.addActionListener(this);
        btnVerwijderen.addActionListener(this);
        btnToevoegen.addActionListener(this);
    }

    protected ArrayList<Voertuig> getEditedModel() {
        return model;
    }

    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == btnToevoegen) {
            runTask("Voertuig", "voertuigToevoegen");
        } else if (selectedVoertuig != null) // only enable this buttons if a Klant
        // is selected
        {
            if (e.getSource() == btnVerwijderen) {
                int result = JOptionPane.showConfirmDialog(this, "Weet u zeker dat u de geselecteerde voertuig wilt verwijderen?",
                        "Voertuig verwijderen?", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    runTask("Voertuig", "voertuigVerwijderen", new Object[]{new Integer(selectedVoertuig.getVoertuigID())});
                }
            } else if (e.getSource() == btnWijzigen) {
                runTask("Voertuig", "voertuigWijzigen", new Object[]{new Integer(selectedVoertuig.getVoertuigID())});
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent lse) {
        int index = tblVoertuigen.getSelectedRow();
        boolean inRange = index >= 0 && index < this.model.size();

        if (inRange) {
            this.selectedVoertuig = this.model.get(index);

        }
        btnVerwijderen.setEnabled(inRange);
        btnWijzigen.setEnabled(inRange);
    }

    private JTable createVoertuigTable() {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Nummer");
        dtm.addColumn("Type");
        dtm.addColumn("Categorie");
        dtm.addColumn("Merk");
        dtm.addColumn("Kleur");
        dtm.addColumn("Beschrijving");
        dtm.addColumn("Verhuurbaar");

        for (Voertuig voertuig : model) {
            dtm.addRow(new Object[]{voertuig.getVoertuigID(), voertuig.getType(), voertuig.getCategorie(), voertuig.getMerk(), voertuig.getKleur(), voertuig.getBeschrijving(), voertuig.getVerhuurbaar()});

        }

        TableColumnModel tcm = new DefaultTableColumnModel();
        tcm.addColumn(new TableColumn(0, 50));
        tcm.addColumn(new TableColumn(1, 200));
        tcm.addColumn(new TableColumn(2, 200));
        tcm.addColumn(new TableColumn(3, 200));
        tcm.addColumn(new TableColumn(4, 200));
        tcm.addColumn(new TableColumn(5, 200));
        tcm.addColumn(new TableColumn(6, 200));

        tcm.getColumn(0).setHeaderValue("Nummer");
        tcm.getColumn(1).setHeaderValue("Type");
        tcm.getColumn(2).setHeaderValue("Categorie");
        tcm.getColumn(3).setHeaderValue("Merk");
        tcm.getColumn(4).setHeaderValue("Kleur");
        tcm.getColumn(5).setHeaderValue("Beschrijving");
        tcm.getColumn(6).setHeaderValue("Verhuurbaar");


        JTable table = new JTable(dtm, tcm) {
            private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        ;
        };
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // let only
        // one
        // row
        // be
        // selected
        table.getSelectionModel().addListSelectionListener(this);
        return table;
    }
}