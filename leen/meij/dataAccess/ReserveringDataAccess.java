package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Factuur;
import leen.meij.Reservering;
import leen.meij.utilities.DataAccess;
/**
 * @author Jovanny Martis - s1078785
 * @author Daan
 * @author Thijs
 * @category Data
 * This class connects with Leenmeij's database to retrieve data. The queried data are
 * returned whenever requested.
 * 
 * **/
public class ReserveringDataAccess extends DataAccess
{

	private KlantDataAccess klantDataAccess = new KlantDataAccess();
	private VoertuigDataAccess voertuigDataAccess = new VoertuigDataAccess();

	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	/***
	 * <b>Description: </b> Build the model to be populated by using the ResultSet. If the requested values are found, 
	 * then it will get this value and store it into the reservering object. This is why such majestic method
	 * like the one here down below cannot be called anything else than buildReserveringModel.
	 * 
	 * @throws SQLException
	 * @return {@link Reservering}
	 *
	 * 
	 * */

	private Reservering buildReserveringModel() throws SQLException
	{
		Reservering reservering = new Reservering();

		if (heeftKolom(resultSet, "id"))
		{
			reservering.setReserveringID(resultSet.getInt("id"));
		}
		else
		{
			reservering.setReserveringID(resultSet.getInt("reservering_id"));
		}

		if (heeftKolom(resultSet, "klant_id"))
		{
			reservering.setKlant(klantDataAccess.buildModel(resultSet));
		}
		else
		{
			reservering.setKlant(null);
		}

		if (heeftKolom(resultSet, "voertuig_id"))
		{
			reservering.setVoertuig(voertuigDataAccess.buildVoertuigModel(resultSet));
		}
		else
		{
			reservering.setVoertuig(null);
		}

		reservering.setKlantID(resultSet.getInt("klantenid"));
		reservering.setVoertuigID(resultSet.getInt("voertuigenid"));
		reservering.setReserveerDatum(resultSet.getDate("reserveerdatum"));
		reservering.setBeginDatum(resultSet.getDate("begindatum"));
		reservering.setEindDatum(resultSet.getDate("einddatum"));

		return reservering;
	}

	/***
	 *  
	 * <b>Description: </b>Okay, Before something can be build, we first need to fill the database. Otherwise there will be nothing
	 * to query from the database meaning no meaningful data to show on our fancy views. We use prepared statement
	 * to fill our database. Why? because! And no, the data yet to be filled is unknown. 
	 * @throws SQLException 
	 * @return {@link Integer}
	 
	 * 
	 * 
	 * */
	private int fillStatement(Reservering reservering) throws SQLException
	{
		int i = 1;

		preparedStatement.setInt(i++, reservering.getKlant().getKlantID());
		preparedStatement.setInt(i++, reservering.getVoertuig().getVoertuigID());
		preparedStatement.setDate(i++, new java.sql.Date(reservering.getReserveerDatum().getTime()));
		preparedStatement.setDate(i++, new java.sql.Date(reservering.getBeginDatum().getTime()));
		preparedStatement.setDate(i++, new java.sql.Date(reservering.getEindDatum().getTime()));
		preparedStatement.setInt(i++, reservering.getKilometer());
		preparedStatement.setDouble(i++, reservering.getBedrag());
		preparedStatement.setString(i++, reservering.getStatus());

		return i;
	}

	/**
	 * <b>Description: </b>Build the model to be populated by using the ResultSet. If the requested values are found, 
	 * then it will get this value and store it into the factuur object.
	 * <br>
	 * <br>
	 * @throws SQLException
	 * @return {@link Factuur}
	 * 
	 *
	 * 
	 * */
	private Factuur buildFactuurModel() throws SQLException
	{
		Factuur factuur = new Factuur();

		if (heeftKolom(resultSet, "id"))
		{
			factuur.setFactuurID(resultSet.getInt("id"));
		}
		else
		{
			factuur.setFactuurID(resultSet.getInt("factuur_id"));
		}

		if (heeftKolom(resultSet, "reservering_id"))
		{
			factuur.setReserveringID(resultSet.getInt("reservering_id"));
		}
		else
		{
			System.out.println("Fout id");
		}
		if (heeftKolom(resultSet, "factuurnummer"))
		{
			factuur.setFactuurnummer(resultSet.getInt("factuurnummer"));
		}
		else
		{
			factuur.setFactuurnummer(0);
			System.out.println("Fout factuurnummer");
		}
		factuur.setDatum(resultSet.getDate("datum"));
		factuur.setBedrag(resultSet.getDouble("bedrag"));

		if (heeftKolom(resultSet, "reden"))
		{
			factuur.setReden(resultSet.getString("reden"));
		}
		else
		{
			factuur.setReden("");
		}

		return factuur;

	}

	/**
	 * <b>Description: </b> This method serve to fill the factuur relation. 
	 * It get the values from the Factuur object
	 * @throws SQLException
	 * @return {@link Integer}
	 * **/
	private int fillStatement(Factuur factuur, boolean setReserveringid) throws SQLException
	{
		int i = 1;
		if (setReserveringid)
		{
			preparedStatement.setInt(i++, factuur.getReserveringID());
		}
		preparedStatement.setInt(i++, factuur.getFactuurnummer());
		preparedStatement.setDouble(i++, factuur.getBedrag());
		if (factuur.getDatum() != null)
		{
			preparedStatement.setDate(i++, new java.sql.Date(factuur.getDatum().getTime()));
		}
		else
		{
			preparedStatement.setDate(i++, new java.sql.Date(new java.util.Date().getTime()));
		}
		preparedStatement.setString(i++, (factuur.getReden() == null ? "" : factuur.getReden()));

		return i;
	}

	/**
	 * <b>Description: </b> This method connects with the database and select the row that the user have chosen
	 * to either edit or delete.
	 * @param reserveringID
	 * @return {@link Integer}
	 */
	public Reservering select(int reserveringID)
	{
		openConnection();

		Reservering reservering;

		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("R.id AS reservering_id,");
		builder.append("klantenid,");
		builder.append("voertuigenid,");
		builder.append("reserveerdatum,");
		builder.append("begindatum,");
		builder.append("einddatum,");
		builder.append("achternaam,");
		builder.append("bedrijfsnaam,");
		builder.append("emailadres,");
		builder.append("geboortedatum,");
		builder.append("huisnummer,");
		builder.append("K.id AS klant_id,");
		builder.append("klantnummer,");
		builder.append("kopiePaspoort,");
		builder.append("kopierijbewijs,");
		builder.append("kvknummer,");
		builder.append("land,");
		builder.append("mobielnummer,");
		builder.append("postcode,");
		builder.append("provincie,");
		builder.append("straat,");
		builder.append("telefoonnummer,");
		builder.append("tussenvoegsel,");
		builder.append("voornaam,");
		builder.append("woonplaats, ");
		builder.append("V.id AS voertuig_id, ");
		builder.append("categorie,");
		builder.append("merk,");
		builder.append("type,");
		builder.append("kleur,");
		builder.append("beschrijving,");
		builder.append("verhuurbaar, ");
		builder.append("kenteken, ");
		builder.append("bouwJaar, ");
		builder.append("kilometerStand, ");
		builder.append("brandstof, ");
		builder.append("airco, ");
		builder.append("station, ");
		builder.append("dagPrijs, ");
		builder.append("brandstofPrijs, ");
		builder.append("kilometerPrijs, ");
		builder.append("borgPrijs ");
		builder.append("FROM reservering AS R,");
		builder.append("klant AS K, ");
		builder.append("voertuig AS V ");
		builder.append("WHERE klantenid = K.id ");
		builder.append("AND voertuigenid = V.id ");
		builder.append("AND R.id = ? ");
		builder.append("ORDER BY R.id");

		try
		{

			preparedStatement = connection.prepareStatement(builder.toString());

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

	/***
	 * <b>Description: </b>This magnificent method queries the database to retrieve the necessary values
	 * to edit or delete one ore more invoice records.
	 * 
	 *  @param reserveringID
	 *  @return <code>null</code>
	 *  
	 * 
	 * ***/
	public Factuur selectFactuur(int reserveringID)
	{
		openConnection();

		Factuur factuur;

		try
		{

			StringBuilder builder = new StringBuilder("SELECT ");

			builder.append("F.id AS factuur_id, ");
			builder.append("R.id AS reservering_id, ");
			builder.append("F.factuurnummer, ");
			builder.append("F.datum, ");
			builder.append("F.bedrag, ");
			builder.append("F.reden ");
			builder.append("FROM factuur AS F, ");
			builder.append("reservering AS R ");
			builder.append("WHERE F.reserveringenid = ?");

			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setInt(1, reserveringID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				factuur = buildFactuurModel();

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

	/****
	 * 
	 * <b>Description: </b>This method retrieves all the required records from the database to display data
	 * on the reserveringView table. 
	 * @return <code>null</code>
	 * */
	public ArrayList<Reservering> selectAll()
	{
		ArrayList<Reservering> reservering = new ArrayList<Reservering>();
		openConnection();

		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("R.id AS reservering_id,");
		builder.append("klantenid,");
		builder.append("voertuigenid,");
		builder.append("reserveerdatum,");
		builder.append("begindatum,");
		builder.append("einddatum,");
		builder.append("achternaam,");
		builder.append("bedrijfsnaam,");
		builder.append("emailadres,");
		builder.append("geboortedatum,");
		builder.append("huisnummer,");
		builder.append("K.id AS klant_id,");
		builder.append("klantnummer,");
		builder.append("kopiePaspoort,");
		builder.append("kopierijbewijs,");
		builder.append("kvknummer,");
		builder.append("land,");
		builder.append("mobielnummer,");
		builder.append("postcode,");
		builder.append("provincie,");
		builder.append("straat,");
		builder.append("telefoonnummer,");
		builder.append("tussenvoegsel,");
		builder.append("voornaam,");
		builder.append("woonplaats, ");
		builder.append("V.id AS voertuig_id, ");
		builder.append("categorie,");
		builder.append("merk,");
		builder.append("type,");
		builder.append("kleur,");
		builder.append("beschrijving,");
		builder.append("verhuurbaar, ");
		builder.append("kenteken, ");
		builder.append("bouwJaar, ");
		builder.append("kilometerStand, ");
		builder.append("brandstof, ");
		builder.append("airco, ");
		builder.append("station, ");
		builder.append("dagPrijs, ");
		builder.append("brandstofPrijs, ");
		builder.append("kilometerPrijs, ");
		builder.append("borgPrijs ");
		builder.append("FROM reservering AS R,");
		builder.append("klant AS K, ");
		builder.append("voertuig AS V ");
		builder.append("WHERE klantenid = K.id ");
		builder.append("AND voertuigenid = V.id ");
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
	 * <b>Description: </b>This method is used to add a new record to the reservering relation on the database.
	 * Data are stored on the database by using the aforementioned buildReserveringModel method. The SQL statement
	 * is stored on a StringBuilder called builder. The SQL query execution occurs within a try-catch block. This is needed
	 * because there is a greater probability that the query execution will fail. 
	 * We sure want to be there to catch the errors :-)  
	 * @param reservering
	 * @return {@link Reservering}
	 */
	public Reservering add(Reservering reservering)
	{

		openConnection();

		Reservering tempReservering;

		StringBuilder builder = new StringBuilder("INSERT INTO reservering ( ");

		builder.append("klantenid, ");
		builder.append("voertuigenid, ");
		builder.append("reserveerdatum, ");
		builder.append("begindatum, ");
		builder.append("einddatum, ");
		builder.append("kilometer, ");
		builder.append("bedrag, ");
		builder.append("status) ");
		builder.append("VALUES (?,?,?,?,?,?,?,?) ");
		builder.append("RETURNING *");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());

			fillStatement(reservering);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				tempReservering = buildReserveringModel();
				if (tempReservering.getKlant() == null && tempReservering.getVoertuig() == null)
				{
					tempReservering.setKlant(reservering.getKlant());
					tempReservering.setVoertuig(reservering.getVoertuig());
				}
				else if (tempReservering.getKlant() == null ^ tempReservering.getVoertuig() == null)
				{
					System.out.println("Het gaat anders dan je dacht Daan");
				}
				reservering = tempReservering;
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
	
	/******
	 * <b>Description: </b>This method is used to add a new record to the factuur relation on the database.
	 * Data are stored on the database by using the aforementioned buildFactuurModell method. The SQL statement
	 * is stored on a StringBuilder called builder. The SQL query execution occurs within a try-catch block. This is needed
	 * because there is a greater probability that the query execution will fail. 
	 * We sure want to be there to catch the errors :-)  
	 *  @param factuur
	 *  @return {@link Factuur}
	 * */
	public Factuur add(Factuur factuur)
	{

		openConnection();

		Factuur tempFactuur;

		try
		{
			if (factuur.getFactuurID() == 0)
			{
				StringBuilder builder = new StringBuilder("INSERT INTO factuur ( ");

				builder.append("reserveringenid, ");
				builder.append("factuurnummer, ");
				builder.append("bedrag, ");
				builder.append("datum, ");
				builder.append("reden) ");
				builder.append("VALUES (?,?,?,?,?) ");
				builder.append("RETURNING *");

				preparedStatement = connection.prepareStatement(builder.toString());

				fillStatement(factuur, true);
			}
			else
			{
				StringBuilder builder = new StringBuilder("UPDATE factuur SET ");

				builder.append("factuurnummer = ?, ");
				builder.append("bedrag = ?, ");
				builder.append("datum = ?, ");
				builder.append("reden = ? ");
				builder.append("WHERE reserveringenid = ? ");
				builder.append("RETURNING *");

				preparedStatement = connection.prepareStatement(builder.toString());

				int index = fillStatement(factuur, false);
				preparedStatement.setInt(index++, factuur.getReserveringID());
			}
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				int reserveringenid = resultSet.getInt("reserveringenid");
				factuur = selectFactuur(reserveringenid);

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
		return factuur;

	}

	/**
	 * <b>Description: </b>This method is used to delete a existing record from the reservering relation of Leenmeij's database.
	 * The method takes an integer as parameter which will be used to delete the selected record.
	 * @param reserveringID
	 * 
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
	/***
	 * <b>Description: </b>This method is used to edit a existing record from the reservering relation of Leenmeij's database.
	 * The method takes the selected reservering object as parameter which will be used to edit the selected record.
	 * @param reservering
	 * @return {@link Reservering}
	 * */
	public Reservering edit(Reservering reservering)
	{
		openConnection();

		Reservering tempReservering;

		StringBuilder builder = new StringBuilder("UPDATE reservering SET ");

		builder.append("klantenid=?,");
		builder.append("voertuigenid=?,");
		builder.append("reserveerdatum=?,");
		builder.append("begindatum=?,");
		builder.append("einddatum=?,");
		builder.append("kilometer=?,");
		builder.append("bedrag=?,");
		builder.append("status=? ");
		builder.append("WHERE id = ? ");
		builder.append("RETURNING *");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());

			int index = this.fillStatement(reservering);
			preparedStatement.setInt(index++, reservering.getReserveringID());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				tempReservering = buildReserveringModel();
				if (tempReservering.getKlant() == null && tempReservering.getVoertuig() == null)
				{
					tempReservering.setKlant(reservering.getKlant());
					tempReservering.setVoertuig(reservering.getVoertuig());
				}
				else if (tempReservering.getKlant() == null ^ tempReservering.getVoertuig() == null)
				{
					System.out.println("Het gaat anders dan je dacht Daan");
				}
				reservering = tempReservering;
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
	 * <b>Description: </b>This method is used to select dates of reservations by using 
	 * and integer as parameter.
	 * @param voertuigID
	 * @return <code>null</code>
	 * **/
	public ArrayList<Reservering> selectDatumByVoertuigID(int voertuigID)
	{
		ArrayList<Reservering> reservering = new ArrayList<Reservering>();
		openConnection();
		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("id,");
		builder.append("klantenid,");
		builder.append("voertuigenid,");
		builder.append("reserveerdatum,");
		builder.append("begindatum,");
		builder.append("einddatum ");
		builder.append("FROM reservering ");
		builder.append("WHERE voertuigenid = ? ");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setInt(1, voertuigID);
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
}