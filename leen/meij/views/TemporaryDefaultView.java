package leen.meij.views;

/**
 * Displays an empty MasterView.
 * @author Thijs
 *
 */
public class TemporaryDefaultView extends MasterView<Object>
{

	/**
	 * Initializes a new instance of the TemporaryDefaultView class.
	 */
	public TemporaryDefaultView()
	{
		super(new Object());
		this.setTitle("");

	}

	@Override
	protected Object getEditedModel()
	{
		return this.model;
	}

}
