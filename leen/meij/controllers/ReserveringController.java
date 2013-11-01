
package leen.meij.controllers;

import leen.meij.Reservering;
import leen.meij.dataAccess.ReserveringDataAccess;
import leen.meij.utilities.Controller;
import leen.meij.utilities.View;
import leen.meij.views.ReserveringDetailsView;
import leen.meij.views.ReserveringView;

/**
 * 
 * @author Jovanny
 * 
 * **/
public class ReserveringController extends Controller
{
	
	private ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();
	/**
	 * Shows the reserveringen overzicht View.
	 */
	public View reserveringOverzichtRaadplegenTask()
	{
		
		return new ReserveringView(reserveringDataAccess.selectAll());
	}

	public View reserveringToevoegenTask()
	{
		return new ReserveringDetailsView(new Reservering());
	}

	/**
	 * 
	 * @param reservering
	 */
	public View reserveringToevoegenTask(Reservering reservering)
	{
		
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public View reserveringWijzigenTask(Integer reserveringID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reservering
	 */
	public View reserveringWijzigenTask(Reservering reservering)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public View reserveringVerwijderenTask(Integer reserveringID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public View factuurOpmakenTask(Integer reserveringID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	public View huurlijstOverzichtRaadplegenTask()
	{
		return new ReserveringView(reserveringDataAccess.selectAll());
	}

	public View inleverlijstRaadplegenTask()
	{	
		return new ReserveringView(reserveringDataAccess.selectAll());
	}

}