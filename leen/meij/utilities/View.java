
package leen.meij.utilities;

import javax.swing.JPanel;

public abstract class View<T> extends JPanel
{

	protected T model;

	/**
	 * Runs a specific task using specified arguments.
	 * @param controller The name of the Controller without the 'Controller' part.
	 * @param task The name of the Task without the 'Task' part.
	 * @param args Arguments passed to the Task.
	 */
	protected void runTask(String controller, String task, Object[] args)
	{
		Site.getInstance().runTask(
				"leen.meij.controllers." + controller + "Controller",
				task + "Task", 
				args);
	}

	/**
	 * Runs a specific task without arguments.
	 * @param controller The name of the Controller without the 'Controller' part.
	 * @param task The name of the Task without the 'Task' part.
	 */
	protected void runTask(String controller, String task)
	{
		this.runTask(controller, task, new Object[0]);
	}

	/**
	 * Initializes a new instance of the View class, specifying a Model object.
	 * @param model The model used in this View.
	 */
	protected View(T model)
	{
		this.model = model;
		this.initialize(model);
	}

	/**
	 * Initializes the data in the model in controls; such as JTextFields.
	 * @param model The model used by the View.
	 */
	protected abstract void initialize(T model);

	/**
	 * Retrieves the model from the controls in the View.
	 * @return
	 */
	protected abstract T retrieveModel();

}