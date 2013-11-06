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

	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Reservering reservering = null;
	
	private Reservering buildReserveringModel(ResultSet resultSet) throws SQLException
	{
		reservering = new Reservering();
		
		reservering.setReserveringID(resultSet.getInt("id"));
		reservering.setKlant(klantDataAccess.select(resultSet.getInt("klantenid")));
		reservering.setKlantID(resultSet.getInt("klantenid"));
		reservering.setVoertuig(voertuigDataAccess.select(resultSet.getInt("voertuigenid")));
		reservering.setVoertuigID(resultSet.getInt("voertuigenid"));
		reservering.setReserveerDatum(resultSet.getDate("reserveerdatum"));
		reservering.setBeginDatum(resultSet.getDate("begindatum"));
		reservering.setEindDatum(resultSet.getDate("einddatum"));
//		reservering.setKilometer(resultSet.getInt("kilometer"));
//		reservering.setBedrag(resultSet.getDouble("bedrag"));
		
		return reservering;
		
	}
	
	private void fillStatement(PreparedStatement preparedStatement, Reservering reservering) throws SQLException
	{
		preparedStatement.setInt(1, reservering.getKlant().getKlantID());
		preparedStatement.setInt(2, reservering.getVoertuig().getVoertuigID());
		preparedStatement.setDate(3,  new java.sql.Date(reservering.getBeginDatum().getTime()));
		preparedStatement.setDate(4, new java.sql.Date(reservering.getEindDatum().getTime()));
		preparedStatement.setInt(5, reservering.getKilometer());
		preparedStatement.setDouble(6, reservering.getBedrag());
		preparedStatement.setString(7, "Alles goed");

	}

	
	
	/**
	 * 
	 * @param reserveringID
	 */
	public Reservering select(int reserveringID)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement("SELECT * FROM reservering WHERE id = ?");

			preparedStatement.setInt(1, reserveringID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				reservering = buildReserveringModel(resultSet);
				
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
			if (preparedStatement != null) try
			{
				preparedStatement.close();
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
		ArrayList<Reservering> reservering = new ArrayList<Reservering>();
		openConnection();

		try
		{
			preparedStatement = connection.prepareStatement("SELECT * FROM reservering ORDER BY id");

			resultSet = preparedStatement.executeQuery();

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
			if (preparedStatement != null) try
			{
				preparedStatement.close();
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
	public Reservering add(Reservering reservering)
	{
//		tempData.add(reservering);
		openConnection();
		try
		{
			preparedStatement = connection.prepareStatement("INSERT INTO reservering (klantenid,voertuigenid,begindatum,einddatum,kilometer,bedrag,status) VALUES (?,?,?,?,?,?,?) RETURNING *");
																																															
																																				
			fillStatement(preparedStatement,reservering);

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
	public void delete(int reserveringID)
	{
		openConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{

			ps = connection.prepareStatement("DELETE FROM reservering WHERE id = ?");

			ps.setInt(1, reserveringID);
			ps.execute();
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
	}

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