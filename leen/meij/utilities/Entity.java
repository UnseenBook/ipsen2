
package leen.meij.utilities;

import java.util.ArrayList;

public abstract class Entity
{

	protected boolean isValid;

	private ArrayList<String> errors = new ArrayList<String>();

	public abstract void validateFields();

	public boolean isValid()
	{
		return this.isValid;
	}

	public ArrayList<String> getErrors()
	{
		return this.errors;
	}

}