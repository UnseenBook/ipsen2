package leen.meij.dataAccess;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import leen.meij.utilities.*;
import leen.meij.*;

/**
 * The KlantDataAccess class. Provides data access methods for Klant objects.
 * @author Thijs
 * @author Daan
 *
 */
public class KlantDataAccess extends DataAccess
{
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * Creates a Klant model object and fills it with the data from a specified ResultSet object.
	 * @param resultSet The ResultSet object.
	 * @return A Klant model object
	 * @throws SQLException 
	 */
	public Klant buildModel(ResultSet resultSet) throws SQLException
	{
		this.resultSet = resultSet;
		return buildModel();
	}

	private Klant buildModel() throws SQLException
	{
		Klant klant = new Klant();

		if (heeftKolom(resultSet, "id"))
		{
			klant.setKlantID(resultSet.getInt("id"));
		} else
		{
			klant.setKlantID(resultSet.getInt("klant_id"));
		}
		klant.setAchternaam(resultSet.getString("achternaam"));
		klant.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
		klant.setEmailadres(resultSet.getString("emailadres"));
		klant.setGeboorteDatum(resultSet.getDate("geboortedatum"));
		klant.setHuisNummer(resultSet.getString("huisnummer"));
		klant.setKlantNummer(resultSet.getInt("klantnummer"));
		klant.setKopiePaspoort(resultSet.getBytes("kopiePaspoort"));
		klant.setKopieRijbewijs(resultSet.getBytes("kopierijbewijs"));
		klant.setKvknummer(resultSet.getString("kvknummer"));
		klant.setLand(resultSet.getString("land"));
		klant.setMobielnummer(resultSet.getString("mobielnummer"));
		klant.setPostcode(resultSet.getString("postcode"));
		klant.setProvincie(resultSet.getString("provincie"));
		klant.setStraat(resultSet.getString("straat"));
		klant.setTelefoonnummer(resultSet.getString("telefoonnummer"));
		klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
		klant.setVoornaam(resultSet.getString("voornaam"));
		klant.setWoonplaats(resultSet.getString("woonplaats"));

		return klant;
	}

	private int fillStatement(Klant klant) throws SQLException
	{
		int i = 1;

		preparedStatement.setString(i++, klant.getAchternaam());
		preparedStatement.setString(i++, klant.getBedrijfsnaam());
		preparedStatement.setString(i++, klant.getEmailadres());
		if (klant.getGeboorteDatum() != null)
		{
			preparedStatement.setDate(i++, new Date(klant.getGeboorteDatum().getTime()));
		}
		else
		{
			preparedStatement.setDate(i++, null);
		}
		preparedStatement.setString(i++, klant.getHuisNummer());

		// preparedStatement.setInt(i++, klant.getKlantNummer());
		preparedStatement.setBytes(i++, klant.getKopiePaspoort());
		preparedStatement.setBytes(i++, klant.getKopieRijbewijs());
		preparedStatement.setString(i++, klant.getKvknummer());
		preparedStatement.setString(i++, klant.getLand());
		preparedStatement.setString(i++, klant.getMobielnummer());
		preparedStatement.setString(i++, klant.getPostcode());
		preparedStatement.setString(i++, klant.getProvincie());
		preparedStatement.setString(i++, klant.getStraat());
		preparedStatement.setString(i++, klant.getTelefoonnummer());
		preparedStatement.setString(i++, klant.getTussenvoegsel());

		preparedStatement.setString(i++, klant.getVoornaam());
		preparedStatement.setString(i++, klant.getWoonplaats());

		return i;
	}

	/**
	 * Gets a Klant object specified by a klant ID.
	 * @param klantID The Klant ID.
	 */
	public Klant select(int klantID)
	{

		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement("SELECT * FROM klant WHERE id = ?");

			preparedStatement.setInt(1, klantID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) { return buildModel(); }
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
	 * Gets a list of all the Klant objects.
	 * @return A list of all the Klant objects.
	 */
	public ArrayList<Klant> selectAll()
	{
		openConnection();

		try
		{
			ArrayList<Klant> klanten = new ArrayList<Klant>();
			preparedStatement = connection.prepareStatement("SELECT * FROM klant ORDER BY klantnummer");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				klanten.add(buildModel());
			}
			return klanten;
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
	 * Adds a Klant object.
	 * @param klant The added Klant object.
	 */
	public Klant add(Klant klant)
	{
		openConnection();

		try
		{

			preparedStatement = connection
					.prepareStatement("INSERT INTO klant (achternaam,bedrijfsnaam,emailadres,geboortedatum,huisnummer,kopiepaspoort,kopierijbewijs,kvknummer,land,mobielnummer,postcode,provincie,straat,telefoonnummer,tussenvoegsel,voornaam,woonplaats) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *");
			this.fillStatement(klant);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				klant = buildModel();
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

		return klant;
	}

	/**
	 * Deletes a Klant object specified by an ID.
	 * @param klantID The ID of the Klant to delete.
	 */
	public void delete(int klantID)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement("DELETE FROM klant WHERE id = ?");

			preparedStatement.setInt(1, klantID);
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


	/**
	 * Modifies an existing Klant object.
	 * @param klant The Klant object to modify.
	 * @return The modified Klant object.
	 */
	public Klant edit(Klant klant)
	{
		openConnection();

		try
		{

			preparedStatement = connection
					.prepareStatement("UPDATE klant SET achternaam = ?, bedrijfsnaam=?,emailadres=?,geboortedatum=?,huisnummer=?,kopiepaspoort=?,kopierijbewijs=?,kvknummer=?,land=?,mobielnummer=?,postcode=?,provincie=?,straat=?,telefoonnummer=?,tussenvoegsel=?,voornaam=?,woonplaats=? "
							+ "WHERE id = ? RETURNING *");
			int index = this.fillStatement(klant);
			preparedStatement.setInt(index++, klant.getKlantID());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				klant = buildModel();
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

		return klant;
	}

}