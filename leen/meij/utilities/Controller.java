
package leen.meij.utilities;

public abstract class Controller
{

	private View currentView;

	protected Controller()
	{
		
	}

	public View getCurrentView()
	{
		return this.currentView;
	}

	/**
	 * 
	 * @param currentView
	 */
	public void setCurrentView(View currentView)
	{
		this.currentView = currentView;
	}

}