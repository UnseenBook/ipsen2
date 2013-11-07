package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Klant;
import leen.meij.Reservering;
import leen.meij.utilities.DataAccess;

public class ReserveringDataAccess extends DataAccess
{

	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();
	
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private Reservering buildReserveringModel() throws SQLException
	{
		Reservering reservering = new Reservering();
		
		reservering.setReserveringID(resultSet.getInt("reservering_id"));
		reservering.setKlant(klantDataAccess.buildModel(resultSet));
		reservering.setKlantID(resultSet.getInt("klantenid"));
		reservering.setVoertuig(voertuigDataAccess.select(resultSet.getInt("voertuigenid")));
		reservering.setVoertuigID(resultSet.getInt("voertuigenid"));
		reservering.setReserveerDatum(resultSet.getDate("reserveerdatum"));
		reservering.setBeginDatum(resultSet.getDate("begindatum"));
		reservering.setEindDatum(resultSet.getDate("einddatum"));

		return reservering;
		
	}
	
	private int fillStatement(Reservering reservering) throws SQLException
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

	
	
	/**
	 * 
	 * @param reserveringID
	 */
	public Reservering select(int reserveringID)
	{
		openConnection();
		
		Reservering reservering;

		try
		{

			preparedStatement = connection.prepareStatement("SELECT R.*, K.* FROM reservering R, klant K WHERE id = ? AND R.klantid = K.id");

			preparedStatement = connection.prepareStatement("SELECT * FROM reservering WHERE id = ?");

			preparedStatement.setInt(1, reserveringID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				reservering = buildReserveringModel();
				
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

	public ArrayList<Reservering> selectAll()
	{
		ArrayList<Reservering> reservering = new ArrayList<Reservering>();
		openConnection();

		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("R.id AS reservering_id,");
		builder.append("R.klantenid,");
		builder.append("R.voertuigenid,");
		builder.append("R.voertuigenid,");
		builder.append("R.reserveerdatum,");
		builder.append("R.begindatum,");
		builder.append("R.einddatum,");
		builder.append("K.achternaam,");
		builder.append("K.bedrijfsnaam,");
		builder.append("K.emailadres,");
		builder.append("K.geboortedatum,");
		builder.append("K.huisnummer,");
		builder.append("K.id AS klant_id,");
		builder.append("K.klantnummer,");
		builder.append("K.kopiePaspoort,");
		builder.append("K.kopierijbewijs,");
		builder.append("K.kvknummer,");
		builder.append("K.land,");
		builder.append("K.mobielnummer,");
		builder.append("K.postcode,");
		builder.append("K.provincie,");
		builder.append("K.straat,");
		builder.append("K.telefoonnummer,");
		builder.append("K.tussenvoegsel,");
		builder.append("K.voornaam,");
		builder.append("K.woonplaats ");
		builder.append("FROM reservering AS R,");
		builder.append("klant AS K ");
		builder.append("WHERE R.klantenid = K.id ");
		builder.append("ORDER BY R.id");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				reservering.add(buildReserveringModel());
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
		
		try
		{

			preparedStatement = connection.prepareStatement("INSERT INTO reservering (klantenid,voertuigenid,reserveerdatum,begindatum,einddatum,kilometer,bedrag,status) VALUES (?,?,?,?,?,?,?,?) RETURNING *");
																															
																																				
			fillStatement(reservering);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				reservering = buildReserveringModel();
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
		
		try
		{

			preparedStatement = connection.prepareStatement("DELETE FROM reservering WHERE id = ?");

			preparedStatement.setInt(1, reserveringID);
			preparedStatement.execute();
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
	}


	public Reservering edit(Reservering reservering)
	{
		openConnection();
		
		try
		{
			preparedStatement = connection.prepareStatement("UPDATE reservering SET klantenid=?,voertuigenid=?,reserveerdatum=?,begindatum=?,einddatum=?,kilometer=?,bedrag=?,status=? "
					+ "WHERE id = ? RETURNING *");

			int index = this.fillStatement(reservering);
			preparedStatement.setInt(index++, reservering.getReserveringID());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				reservering = buildReserveringModel();
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

}