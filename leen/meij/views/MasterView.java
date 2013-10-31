package leen.meij.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import leen.meij.utilities.*;

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
	protected JLabel lblTitle = new JLabel("View Title");

	private JButton btnKlanten = new JButton("Klanten"),
			btnVoertuigen = new JButton("Voertuigen"),
			btnReserveringen = new JButton("Reserveringen"),
			btnManagement = new JButton("Management"),
			btnAfsluiten = new JButton("Afsluiten");

	protected MasterView(T model)
	{
		super(model);

		lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));

		setLayout(new BorderLayout());
		add(pnlTopMenu, BorderLayout.NORTH);
		add(pnlBotMenu, BorderLayout.SOUTH);
		add(pnlContent, BorderLayout.CENTER);
		pnlContent.setLayout(new MigLayout());
		pnlContent.add(lblTitle,"wrap");
		
		pnlTopMenu.setLayout(new MigLayout());
		pnlTopMenu.add(btnKlanten);
		pnlTopMenu.add(btnVoertuigen);
		pnlTopMenu.add(btnReserveringen);
		pnlTopMenu.add(btnManagement);
		pnlTopMenu.add(btnAfsluiten);
		
		
		pnlBotMenu.setLayout(new MigLayout("right"));
	}

	public JPanel getPnlBotMenu()
	{
		return this.pnlBotMenu;
	}

	public String getTitle()
	{
		return lblTitle.getText();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnKlanten)
		{

		}
		else if (e.getSource() == btnVoertuigen)
		{

		}
		else if (e.getSource() == btnReserveringen)
		{
			
		}
		else if (e.getSource() == btnManagement)
		{

		}
		else if (e.getSource() == btnAfsluiten)
		{

		}
	}

	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		lblTitle.setText(title);
	}

}