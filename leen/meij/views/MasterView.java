package leen.meij.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import leen.meij.Rechten;
import leen.meij.utilities.*;

/**
 * Provides a base view for all view that require main navigation.
 * 
 * @author Thijs
 * 
 * @param <T> A generic type parameter used for the model.
 */
public abstract class MasterView<T> extends View<T> implements ActionListener
{

	/**
	 * Panel containing the top menu.
	 */
	protected JPanel pnlTopMenu = new JPanel();
	/**
	 * Panel containing the bottom menu.
	 */
	protected JPanel pnlBotMenu = new JPanel();
	/**
	 * Panel for the main content area.
	 */
	protected JPanel pnlContent = new JPanel();
	/**
	 * Default Label in the content area.
	 */
	protected JLabel lblTitle = new JLabel("View Title (use this.setTitle in the view to change it)");

	private JButton btnKlanten = new JButton("Klanten"), btnVoertuigen = new JButton("Voertuigen"), btnReserveringen = new JButton(
			"Reserveringen"), btnManagement = new JButton("Management"), btnGebruikers = new JButton("Gebruikers"),
			btnAfsluiten = new JButton("Afsluiten");

	private JLabel lblErrorMessages = new JLabel();

	/**
	 * Initializes a new instance of the MasterView, specifying a generic model
	 * object.
	 * 
	 * @param model
	 *            The model object used in the parent class.
	 */
	protected MasterView(T model)
	{
		super(model);

		lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
		lblErrorMessages.setForeground(Color.RED);
		setLayout(new BorderLayout());

		// add components
		add(pnlTopMenu, BorderLayout.NORTH);
		add(pnlBotMenu, BorderLayout.SOUTH);
		add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new MigLayout());
		pnlContent.add(lblTitle, "wrap");
		pnlContent.add(lblErrorMessages, "pos 300px 10px");

		pnlTopMenu.setLayout(new MigLayout());
		pnlTopMenu.add(btnKlanten);
		pnlTopMenu.add(btnVoertuigen);
		pnlTopMenu.add(btnReserveringen);
		pnlTopMenu.add(btnManagement);
		pnlTopMenu.add(btnGebruikers);
		pnlTopMenu.add(btnAfsluiten);
		pnlBotMenu.setLayout(new MigLayout("right"));

		// add listeners
		btnAfsluiten.addActionListener(this);
		btnKlanten.addActionListener(this);
		btnManagement.addActionListener(this);
		btnGebruikers.addActionListener(this);
		btnReserveringen.addActionListener(this);
		btnVoertuigen.addActionListener(this);

		// set rechten
		btnAfsluiten.setVisible(true);
		btnKlanten.setVisible(Rechten.heeftRecht(Rechten.KlantenRaadplegen));
		btnManagement.setVisible(Rechten.heeftRecht(Rechten.Management));
		btnGebruikers.setVisible(Rechten.heeftRecht(Rechten.GebruikersRaadplegen));
		btnReserveringen.setVisible(Rechten.heeftRecht(Rechten.ReserveringenRaadplegen) || Rechten.heeftRecht(Rechten.Huurlijst)
				|| Rechten.heeftRecht(Rechten.Inleverlijst));
		btnVoertuigen.setVisible(Rechten.heeftRecht(Rechten.VoertuigenRaadplegen));
	}

	/**
	 * Displays a list of error messages.
	 * @param errorMessages The list of error messages to display.
	 */
	public void setErrorMessages(ArrayList<String> errorMessages)
	{
		String text = "<html>";
		for (String str : errorMessages)
		{
			text += str + "<br>";
		}
		lblErrorMessages.setText(text);

	}

	/**
	 * Gets the bottom panel.
	 * @return The bottom panel.
	 */
	public JPanel getPnlBotMenu()
	{
		return this.pnlBotMenu;
	}

	/**
	 * Gets the title
	 * @return The title.
	 */
	public String getTitle()
	{
		return lblTitle.getText();
	}

	/**
	 * Handles main menu events.
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnKlanten)
		{
			runTask("Klant", "klantOverzichtRaadplegen");
		}
		else if (e.getSource() == btnVoertuigen)
		{
			runTask("Voertuig", "voertuigOverzichtRaadplegen");
		}
		else if (e.getSource() == btnReserveringen)
		{
			runTask("Reservering", "reserveringOverzichtRaadplegen");
		}
		else if (e.getSource() == btnGebruikers)
		{
			runTask("Gebruiker", "gebruikersOverzichtRaadplegen");
		}
		else if (e.getSource() == btnManagement)
		{
			runTask("Reservering", "reserveringRapportOverzichtRaadPlegen");
		}
		else if (e.getSource() == btnAfsluiten)
		{
			System.exit(0);
		}
	}

	/**
	 * Sets the title.
	 * @param title The title.
	 */
	public void setTitle(String title)
	{
		lblTitle.setText(title);
	}

}