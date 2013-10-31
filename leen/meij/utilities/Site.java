
package leen.meij.utilities;

import java.awt.BorderLayout;
import java.lang.reflect.Method;
import java.util.*;

import javax.swing.*;

import leen.meij.*;

import leen.meij.views.*;

public class Site extends JFrame
{

	private static Site instance;

	private View currentView;
	private Gebruiker user;
	private JPanel panel;
	
	public static Site getInstance()
	{
		if(Site.instance == null)
		{
			Site.instance = new Site();
		}
		return Site.instance;
	}

	/**
	 * Initializes a new instance of the Site class.
	 */
	private Site()
	{
		this.setSize(800, 600);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("LeenMij Office Management System");
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		// default initial view
		currentView = new LoginView(new Gebruiker());
		panel.add(currentView);
		
		this.add(panel);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args)
	{
		Site.getInstance();
	}
	
	/**
	 * 
	 * @param controller
	 * @param task
	 * @param args
	 */
	public void runTask(String controllerName, String taskName, Object[] args)
	{
		try
		{
			Class<?> c = Class.forName(controllerName);
			Class<?>[] parameterTypes = new Class<?>[args.length];
			
			for(int i = 0; i < args.length;i++)
			{
				parameterTypes[i] = args[i].getClass();
			}
			
			// create controller
			Controller controller = (Controller)c.newInstance();
			controller.setCurrentView(this.currentView);
			
			// run the task
			Method task = c.getMethod(taskName, parameterTypes);
			Object view = task.invoke(controller,args);
			
			// change the view accordingly
			changeView((View)view);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param newView
	 */
	private void changeView(View newView)
	{
		if(this.currentView != newView)
		{
			this.currentView = newView;
			
			panel.removeAll();
			panel.setVisible(false);
			panel.add(this.currentView);
			panel.setVisible(true);
			
			
		}
	}

	public Gebruiker getUser()
	{
		return user;
	}

	public void setUser(Gebruiker user)
	{
		this.user = user;
	}



}