
package leen.meij.controllers;

import leen.meij.*;
import leen.meij.dataAccess.*;
import leen.meij.utilities.*;
import leen.meij.views.*;

/**
 * 
 * @author Jovanny
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