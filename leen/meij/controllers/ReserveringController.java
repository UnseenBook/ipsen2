
package leen.meij.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import leen.meij.dataAccess.ReserveringDataAccess;
import leen.meij.utilities.*;
import leen.meij.views.ReserveringView;
import leen.meij.views.ReserveringDetailsView;
import leen.meij.*;

/**
 * 
 * @author Jovanny
 * 
 * **/
public class ReserveringController extends Controller
{
	
	private ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();

	public View reserveringOverzichtRaadplegenTask()
	{
		ArrayList<Reservering> reserveringen = new ArrayList<Reservering>();
				
		reserveringen.add(new Reservering(1,null,null, null, null));
		reserveringen.add(new Reservering(2,null,null, null, null));
		reserveringen.add(new Reservering(3,null,null, null, null));
		reserveringen.add(new Reservering(4,null,null, null, null));
		return new ReserveringView(reserveringen);
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
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	public View inleverlijstRaadplegenTask()
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

}