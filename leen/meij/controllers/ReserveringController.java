
package leen.meij.controllers;

import leen.meij.*;
import leen.meij.dataAccess.*;
import leen.meij.utilities.*;
import leen.meij.views.*;

/**
 * 
 * @author Jovanny Martis - s1078785
 * 
 * **/
public class ReserveringController extends Controller
{
	
	private ReserveringDataAccess reserveringDataAccess = new ReserveringDataAccess();
	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
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
	 * @param reservering
	 * @return reserverinOverzichtRaadplegenTask();
	 * Before we can add a new reservation it is necessary to validate the values entered by the 
	 * user. This nullifies the possibility to get an empty/invalid record in the database. 
	 * After the required values are successfully validated, the method returns the previous view. 
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
	 * @return ReserveringDetailsView(reservering);
	 * Whenever one or more value of a placed reservation need to be made, the method 
	 * checks if the changes are made accordingly. After validating, it returns to the previous View
	 * The changes made are immediately visible.
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
	 * @param factuur
	 * @return FactuurView(factuur)
	 * This method generates an invoice based on existing data from many relations on Leenmeij's database. 
	 *  This method returns the 'FactuurView' invoice, giving users the possibility to fill the nullable row
	 *  of the factuur relation called beschrijving. After adding(editing) the invoice, the method returns the 
	 *  previous view.
	 * 
	 * **/
	
	public View factuurToevoegenTask(Factuur factuur)
	{
		factuur.validateFields();
		if(factuur.isValid())
		{
			factuur = reserveringDataAccess.add(factuur);
			return inleverlijstRaadplegenTask();
		}
		factuur.setReservering(reserveringDataAccess.select(factuur.getReserveringID()));
		factuur.getReservering().setKlant(klantDataAccess.select(factuur.getReservering().getKlantID()));
		factuur.getReservering().setVoertuig(voertuigDataAccess.select(factuur.getReservering().getVoertuigID()));
		
		return new FactuurView(factuur);
	}
	
	/**
	 * 
	 * @param reserveringID
	 * @return FactuurView(factuur);
	 * This method collects data from many relation of Leenmeij's database and show it as a collection 
	 * of all reservations. 
	 */
	public View factuurOpmakenTask(Integer reserveringID)
	{
		Factuur factuur = reserveringDataAccess.selectFactuur(reserveringID);
		if(factuur == null)
		{
			factuur = new Factuur();
			factuur.setReserveringID(reserveringID);
		}
		factuur.setReservering(reserveringDataAccess.select(factuur.getReserveringID()));
		factuur.getReservering().setKlant(klantDataAccess.select(factuur.getReservering().getKlantID()));
		factuur.getReservering().setVoertuig(voertuigDataAccess.select(factuur.getReservering().getVoertuigID()));
		
		return new FactuurView(factuur);
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