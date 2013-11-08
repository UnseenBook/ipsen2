package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Factuur;
import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.utilities.DataAccess;

public class ReserveringDataAccess extends DataAccess
{

	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private Reservering reservering = null;
	private Factuur factuur = null;
	
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

		
		return reservering;
		
	}
	
	private int fillStatement(PreparedStatement preparedStatement, Reservering reservering) throws SQLException
	{
		int i = 1;
		
	
		preparedStatement.setInt(i++, reservering.getKlant().getKlantID());
		preparedStatement.setInt(i++, reservering.getVoertuig().getVoertuigID());
		preparedStatement.setDate(i++, new java.sql.Date(reservering.getReserveerDatum().getTime()));
		preparedStatement.setDate(i++,  new java.sql.Date(reservering.getBeginDatum().getTime()));
		preparedStatement.setDate(i++, new java.sql.Date(reservering.getEindDatum().getTime()));
		preparedStatement.setInt(i++, reservering.getKilometer());
		preparedStatement.setDouble(i++, reservering.getBedrag());
		preparedStatement.setString(i++, reservering.getStatus());

		return i;
	}
	
	
	private Factuur buildFactuurModel(ResultSet resultSet) throws SQLException
	{
		factuur = new Factuur();
		
		factuur.setReserveringID(resultSet.getInt("id"));
		factuur.setFactuurID(resultSet.getInt("id"));
		factuur.setFactuurnummer(resultSet.getInt("factuurnummer"));
		factuur.setDatum(resultSet.getString("datum"));
		factuur.setReden(resultSet.getString("reden"));
		
		return factuur;
		
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
	
	
	//temporary Factuur select
	public Factuur selectFactuur(int reserveringID)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement("SELECT * FROM factuur WHERE id = ?");

			preparedStatement.setInt(1, reserveringID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				factuur = buildFactuurModel(resultSet);
				
				return factuur;
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

		openConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try
		{

			ps = connection.prepareStatement("INSERT INTO reservering (klantenid,voertuigenid,reserveerdatum,begindatum,einddatum,kilometer,bedrag,status) VALUES (?,?,?,?,?,?,?,?) RETURNING *");
																															
																																				
			fillStatement(ps,reservering);
			resultSet = ps.executeQuery();

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
			if (ps != null) try
			{
				ps.close();
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


	public Reservering edit(Reservering reservering)
	{
		openConnection();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		
		try
		{
			ps = connection.prepareStatement("UPDATE reservering SET klantenid=?,voertuigenid=?,reserveerdatum=?,begindatum=?,einddatum=?,kilometer=?,bedrag=?,status=? "
					+ "WHERE id = ? RETURNING *");

			int index = this.fillStatement(ps, reservering);
			ps.setInt(index++, reservering.getReserveringID());
			resultSet = ps.executeQuery();
			if(resultSet.next())
			{
				reservering = buildReserveringModel(resultSet);
			}
		}

		
		catch(SQLException sqle)
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
		return reservering;
	}

}