package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import leen.meij.utilities.*;
import leen.meij.*;

public class ReserveringDataAccess extends DataAccess
{

//	@SuppressWarnings("deprecation")
//	protected static ArrayList<Reservering> tempData = new ArrayList<Reservering>(Arrays.asList(new Reservering[] {
//			new Reservering(1, new Klant(10, "Thijs"), new Voertuig(1, "Volkswagen"), new Date("08/16/2013"), new Date("08/20/2013")),
//			new Reservering(2, new Klant(11, "Daan"), new Voertuig(2, "Toyota"), new Date("08/16/2013"), new Date("08/22/2013")),
//			new Reservering(3, new Klant(12, "Jovanny"), new Voertuig(3, "Chevrolet"), new Date("10/09/2013"), new Date("10/17/2013")),
//			new Reservering(4, new Klant(13, "Angelo"), new Voertuig(4, "Ford"), new Date("10/18/2013"), new Date("10/31/2013")) }));

	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Reservering reservering = null;
	
	private Reservering buildReserveringModel(ResultSet resultSet) throws SQLException
	{
		reservering = new Reservering();
		
		
		reservering.setKlant(klantDataAccess.select(resultSet.getInt("klantenid")));
		reservering.setKlantID(resultSet.getInt("klantenid"));
		reservering.setVoertuig(voertuigDataAccess.select(resultSet.getInt("voertuigid")));
		reservering.setVoertuigID(resultSet.getInt("voertuigid"));
		reservering.setBeginDatum(resultSet.getDate("begindatum"));
		reservering.setEindDatum(resultSet.getDate("einddatum"));
		reservering.setKilometer(resultSet.getInt("kilometer"));
		reservering.setBedrag(resultSet.getDouble("bedrag"));
		
		return reservering;
		
	}
	
	private void fillStatement(PreparedStatement preparedStatement, Klant klant, Reservering reservering, Voertuig voertuig) throws SQLException
	{
		preparedStatement.setString(1, klant.getVolledigeNaam());
		preparedStatement.setString(2, voertuig.getMerk());
		//preparedStatement.setDate(3,  reservering.getBeginDatum());
		//preparedStatement.setDate(4, reservering.getEindDatum());
		preparedStatement.setDouble(5, reservering.getBedrag());
		preparedStatement.setInt(6, reservering.getKilometer());

	}

	
	
	/**
	 * 
	 * @param reserveringID
	 */
	public Reservering select(int reserveringID)
	{
		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{

			ps = connection.prepareStatement("SELECT * FROM reservering WHERE id = ?");

			ps.setInt(1, reserveringID);
			resultSet = ps.executeQuery();

			if (resultSet.next())
			{
				Reservering reservering = buildReserveringModel(resultSet);
				
				return reservering;
			}
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			if (resultSet != null) try
			{
				resultSet.close();
			}
			catch (SQLException negeer)
			{
			}
			if (ps != null) try
			{
				ps.close();
			}
			catch (SQLException negeer)
			{
			}
			closeConnection();
		}

		return null;
	}

//	public ArrayList<Reservering> selectAll()
//	{
//		return tempData;
//	}
//	
	public ArrayList<Reservering> selectAll()
	{
	
		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{
			ArrayList<Reservering> reservering = new ArrayList<Reservering>();
			ps = connection.prepareStatement("SELECT * FROM reservering ORDER BY id");

			resultSet = ps.executeQuery();

			while (resultSet.next())
			{
				reservering.add(buildReserveringModel(resultSet));
			}
			return reservering;
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			if (resultSet != null) try
			{
				resultSet.close();
			}
			catch (SQLException negeer)
			{
			}
			if (ps != null) try
			{
				ps.close();
			}
			catch (SQLException negeer)
			{
			}
			closeConnection();
		}

		return null;
	}

	/**
	 * 
	 * @param reservering
	 */
	public Reservering add(Klant klant, Reservering reservering, Voertuig voertuig)
	{
//		tempData.add(reservering);
		openConnection();
		try
		{
			preparedStatement = connection
					.prepareStatement("INSERT INTO reservering (klantid,voertuigid,begindatum,einddatum,kilometer,bedrag) VALUES (?,?,?,?,?,?,1) RETURNING *");// /////////////////////////////
																																															// Hardcoded
																																															// afdeling
			fillStatement(preparedStatement, klant, reservering, voertuig);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				reservering = buildReserveringModel(resultSet);
			}
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			if (resultSet != null) try
			{
				resultSet.close();
			}
			catch (SQLException negeer)
			{
			}
			if (preparedStatement != null) try
			{
				preparedStatement.close();
			}
			catch (SQLException negeer)
			{
			}
			closeConnection();
		}
		return reservering;

	
	}

	/**
	 * 
	 * @param klantID
	 */
//	public void delete(int reserveringID)
//	{
//		Reservering toRemove = null;
//		for (Reservering reservering : tempData)
//		{
//			if (reservering.getKlantID() == reserveringID)
//			{
//				toRemove = reservering;
//			}
//		}
//		if (toRemove != null)
//		{
//			tempData.remove(toRemove);
//		}
//	}

	/**
	 * 
	 * @param klant
	 */
	public Klant edit(Klant klant)
	{
		return klant;
	}

	public Reservering edit(Reservering reservering)
	{
		return reservering;
	}

}