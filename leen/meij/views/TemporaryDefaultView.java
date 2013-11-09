package leen.meij.views;

public class TemporaryDefaultView extends MasterView<Object>
{

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
