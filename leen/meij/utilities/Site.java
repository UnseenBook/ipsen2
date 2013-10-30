
package leen.meij.utilities;

import java.awt.BorderLayout;
import java.lang.reflect.Method;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import leen.meij.Gebruiker;
import leen.meij.views.LoginView;

public class Site extends JFrame
{

	private static Site instance;

	private View currentView;

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
		this.setLayout(new BorderLayout());
		
		// initial view
		currentView = new LoginView(new Gebruiker());
		this.add(currentView);
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
			
			this.removeAll();
			this.setVisible(false);
			this.add(this.currentView);
			this.setVisible(true);
			
			
		}
	}



}