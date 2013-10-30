
package leen.meij.views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.*;

import leen.meij.Gebruiker;
import leen.meij.utilities.*;

public class LoginView extends View<Gebruiker> implements ActionListener
{


	private JLabel lblGebruikersnaam = new JLabel("Gebruikersnaam");
	private JLabel lblWachtwoord= new JLabel("Wachtwoord");

	private JTextField txtGebruikersnaam = new JTextField(20);
	private JPasswordField txtWachtwoord = new JPasswordField(20);

	private JButton btnLogin = new JButton("Login");
	private JButton btnExit = new JButton("Exit");
	
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
	}

	/**
	 * 
	 * @param model
	 */
	protected void initialize(Gebruiker model)
	{
		
	}

	protected Gebruiker retrieveModel()
	{
		return this.model;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnLogin)
		{
			runTask("Gebruiker","inloggen",new Object[]{
					txtGebruikersnaam.getText(),
					txtWachtwoord.getPassword().toString()
			});
		} 
		else if(e.getSource() == btnExit)
		{
			System.exit(0);
		}
		
	}

}