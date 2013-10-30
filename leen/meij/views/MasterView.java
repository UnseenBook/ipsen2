
package leen.meij.views;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import leen.meij.utilities.*;

public abstract class MasterView<T> extends View<T>
{

	protected JPanel pnlTopMenu = new JPanel();
	protected JPanel pnlBotMenu = new JPanel();
	protected JPanel pnlContent = new JPanel();
	protected JLabel lblTitle = new JLabel();

	protected MasterView(T model)
	{
		super(model);
		
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 24));
		
		setLayout(new BorderLayout());
		add(pnlTopMenu,BorderLayout.NORTH);
		add(pnlBotMenu,BorderLayout.SOUTH);
		add(pnlContent,BorderLayout.CENTER);
		pnlContent.setLayout(new MigLayout());
		pnlContent.add(lblTitle);
		
	}
	
	public JPanel getPnlBotMenu()
	{
		return this.pnlBotMenu;
	}

	public String getTitle()
	{
		return lblTitle.getText();
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