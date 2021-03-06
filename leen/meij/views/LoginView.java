
package leen.meij.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.*;

import leen.meij.Gebruiker;
import leen.meij.utilities.*;

/**
 * Displays a login form in a view.
 * @author Thijs
 *
 */
public class LoginView extends View<Gebruiker> implements ActionListener
{


	private JLabel lblGebruikersnaam = new JLabel("Gebruikersnaam");
	private JLabel lblWachtwoord= new JLabel("Wachtwoord");

	private JTextField txtGebruikersnaam = new JTextField(20);
	private JPasswordField txtWachtwoord = new JPasswordField(20);

	private JButton btnLogin = new JButton("Login");
	private JButton btnExit = new JButton("Exit");
	
	/**
	 * Initializes a new instance of the LoginView class, specifying a Gebruiker object.
	 * @param model The Gebruiker object.
	 */
	public LoginView(Gebruiker model)
	{
		super(model);
		
		setLayout(new MigLayout("center"));
		
		add(lblGebruikersnaam);
		add(txtGebruikersnaam,"wrap");
		
		add(lblWachtwoord);
		add(txtWachtwoord,"wrap");
		
		add(btnLogin);
		add(btnExit);
		
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);
		
		txtGebruikersnaam.setText(model.getGebruikersnaam());
	}

	protected Gebruiker getEditedModel()
	{
		return this.model;
	}

	/**
	 * Handles user actions such as button clicks.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnLogin)
		{
			runTask("Gebruiker","inloggen",new Object[]{
					txtGebruikersnaam.getText(),
					new String(txtWachtwoord.getPassword())
			});
		} 
		else if(e.getSource() == btnExit)
		{
			System.exit(0);
		}
		
	}

}