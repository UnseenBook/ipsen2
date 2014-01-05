
package leen.meij.utilities;

import java.util.ArrayList;

/**
 * The base class of every entity.
 * @author Thijs
 *
 */
public abstract class Entity
{

	protected boolean isValid;

	private ArrayList<String> errors = new ArrayList<String>();

	/**
	 * Validates this entity.
	 */
	public abstract void validateFields();

	/**
	 * Gets a value indicating whether this entity is valid.
	 * @return A value indicating whether this entity is valid.
	 */
	public boolean isValid()
	{
		return this.isValid;
	}

	/**
	 * Gets a list of errors that may have occurred during validation.
	 * @return A list of errors that may have occurred during validation.
	 */
	public ArrayList<String> getErrors()
	{
		return this.errors;
	}

}