package leen.meij.views;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

import leen.meij.Onderhoud;
import leen.meij.utilities.View;
/**
* 
* @author abetcke
* 
*/
public class OnderhoudDetailsView extends View<Onderhoud> {

    private JTextField txtLocatie = new JTextField(15);
    private JTextField txtHandeling = new JTextField(15);
    private JTextField txtBeschrijving = new JTextField(15);
    private JCheckBox cbVerhuurbaar = new JCheckBox();

    public OnderhoudDetailsView(Onderhoud model) {
        super(model);

        String wrap = "wrap";
        setLayout(new MigLayout());
        add(new JLabel("Locatie"));
        add(txtLocatie, wrap);

        add(new JLabel("Handeling"));
        add(txtHandeling, wrap);

        add(new JLabel("Beschrijving"));
        add(txtBeschrijving, wrap);

        add(new JLabel("Verhuurbaar"));
        add(cbVerhuurbaar, wrap);

        loadModelData();
    }

    /**
    * 
    * Populates swing components from the maintenance (onderhoud) model
    * 
    */
    private void loadModelData() {

        txtLocatie.setText(model.getLocatie());
        txtHandeling.setText(model.getHandeling());
        cbVerhuurbaar.setSelected(model.isVoldaan());
        txtBeschrijving.setText(model.getBeschrijving());

    }
    
    /**
    * 
    * Populates the maintenance (onderhoud) model from swing components
    * 
    */
    protected Onderhoud getEditedModel() {
        model.setLocatie(txtLocatie.getText());
        model.setHandeling(txtHandeling.getText());
        model.setVoldaan(cbVerhuurbaar.isSelected());
        model.setBeschrijving(txtBeschrijving.getText());
        return model;
    }
}