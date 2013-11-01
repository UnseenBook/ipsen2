package leen.meij.utilities;

import leen.meij.Gebruiker;

/**
 * The base class of every Controller.
 * 
 * @author Thijs
 * 
 */
public abstract class Controller
{

	private View currentView;

	/**
	 * Initializes a new instance of the Controller class.
	 */
	protected Controller()
	{

	}

	/**
	 * Gets the current active view.
	 * 
	 * @return
	 */
	public View getCurrentView()
	{
		return this.currentView;
	}

	/**
	 * Sets the current active view.
	 * 
	 * @param currentView
	 */
	public void setCurrentView(View currentView)
	{
		this.currentView = currentView;
	}

	public Gebruiker getGebruiker()
	{
		return Site.getInstance().getGebruiker();
	}

}