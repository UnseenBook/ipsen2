
package leen.meij.controllers;

import leen.meij.Factuur;
import leen.meij.Reservering;
import leen.meij.dataAccess.ReserveringDataAccess;
import leen.meij.utilities.Controller;
import leen.meij.utilities.View;
import leen.meij.views.FactuurView;
import leen.meij.views.HuurLijstView;
import leen.meij.views.InleverLijstView;
import leen.meij.views.RapportView;
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
	
	public View reserveringRapportOverzichtRaadPlegenTask()
	{
		return new RapportView(reserveringDataAccess.selectAll());
	}
	

	public View reserveringToevoegenTask()
	{
		return new ReserveringDetailsView(new Reservering());
	}

	/**
	 * 
	 * @param reservering.
	 */
	public View reserveringToevoegenTask(Reservering reservering)
	{
		
		reservering.validateFields();
		
		if(reservering.isValid())
		{
			reservering = reserveringDataAccess.add(reservering);
			return reserveringOverzichtRaadplegenTask();
		}
		return new ReserveringDetailsView(reservering);
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public View reserveringWijzigenTask(Integer reserveringID)
	{
		Reservering reservering = reserveringDataAccess.select(reserveringID);
		return new ReserveringDetailsView(reservering);
	}

	/**
	 * 
	 * @param reservering
	 */
	public View reserveringWijzigenTask(Reservering reservering)
	{
		reservering.validateFields();
		if(reservering.isValid())
		{
			reservering = reserveringDataAccess.edit(reservering);
			
			return reserveringOverzichtRaadplegenTask();
		}
		
		return new ReserveringDetailsView(reservering);
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public View reserveringVerwijderenTask(Integer reserveringID)
	{
		reserveringDataAccess.delete(reserveringID);
		
		return reserveringOverzichtRaadplegenTask();
	}

	/**
	 * 
	 * @param reserveringID
	 */
	public View factuurOpmakenTask(Integer reserveringID)
	{
		Factuur factuur = reserveringDataAccess.selectFactuur(reserveringID);
		if (factuur != null)
		{
			return new FactuurView(factuur);
		}
		return new FactuurView(new Factuur());
	}
	
	
	

	public View huurlijstOverzichtRaadplegenTask()
	{
		return new HuurLijstView(reserveringDataAccess.selectAll());
	}

	public View inleverlijstRaadplegenTask()
	{	
		return new InleverLijstView(reserveringDataAccess.selectAll());
	}

}