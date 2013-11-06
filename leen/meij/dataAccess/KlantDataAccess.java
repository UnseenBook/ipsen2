package leen.meij.dataAccess;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import leen.meij.utilities.*;
import leen.meij.*;

public class KlantDataAccess extends DataAccess
{

	private Klant buildModel(ResultSet resultSet) throws SQLException
	{
		Klant klant = new Klant();
		klant.setAchternaam(resultSet.getString("achternaam"));
		klant.setBedrijfsnaam(resultSet.getString("bedrijfsnaam"));
		klant.setEmailadres(resultSet.getString("emailadres"));
		klant.setGeboorteDatum(resultSet.getDate("geboortedatum"));
		klant.setHuisNummer(resultSet.getString("huisnummer"));
		klant.setKlantID(resultSet.getInt("id"));
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

	private int fillStatement(PreparedStatement ps, Klant klant) throws SQLException
	{
		int i = 1;

		ps.setString(i++, klant.getAchternaam());
		ps.setString(i++, klant.getBedrijfsnaam());
		ps.setString(i++, klant.getEmailadres());
		if (klant.getGeboorteDatum() != null)
		{
			ps.setDate(i++, new Date(klant.getGeboorteDatum().getTime()));
		}
		else
		{
			ps.setDate(i++, null);
		}
		ps.setString(i++, klant.getHuisNummer());

		// ps.setInt(i++, klant.getKlantNummer());
		ps.setBytes(i++, klant.getKopiePaspoort());
		ps.setBytes(i++, klant.getKopieRijbewijs());
		ps.setString(i++, klant.getKvknummer());
		ps.setString(i++, klant.getLand());
		ps.setString(i++, klant.getMobielnummer());
		ps.setString(i++, klant.getPostcode());
		ps.setString(i++, klant.getProvincie());
		ps.setString(i++, klant.getStraat());
		ps.setString(i++, klant.getTelefoonnummer());
		ps.setString(i++, klant.getTussenvoegsel());

		ps.setString(i++, klant.getVoornaam());
		ps.setString(i++, klant.getWoonplaats());

		return i;
	}

	/**
	 * 
	 * @param klantID
	 */
	public Klant select(int klantID)
	{

		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{

			ps = connection.prepareStatement("SELECT * FROM klant WHERE id = ?");

			ps.setInt(1, klantID);
			resultSet = ps.executeQuery();

			if (resultSet.next()) { return buildModel(resultSet); }
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

	public ArrayList<Klant> selectAll()
	{
		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{
			ArrayList<Klant> klanten = new ArrayList<Klant>();
			ps = connection.prepareStatement("SELECT * FROM klant ORDER BY klantnummer");

			resultSet = ps.executeQuery();

			while (resultSet.next())
			{
				klanten.add(buildModel(resultSet));
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
	 * @param klant
	 */
	public Klant add(Klant klant)
	{
		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{

			ps = connection
					.prepareStatement("INSERT INTO klant (achternaam,bedrijfsnaam,emailadres,geboortedatum,huisnummer,kopiepaspoort,kopierijbewijs,kvknummer,land,mobielnummer,postcode,provincie,straat,telefoonnummer,tussenvoegsel,voornaam,woonplaats) "
							+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING *");
			this.fillStatement(ps, klant);

			resultSet = ps.executeQuery();

			if (resultSet.next())
			{
				klant = buildModel(resultSet);
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

		return klant;
	}

	/**
	 * 
	 * @param klantID
	 */
	public void delete(int klantID)
	{
		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{

			ps = connection.prepareStatement("DELETE FROM klant WHERE id = ?");

			ps.setInt(1, klantID);
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
		openConnection();

		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try
		{

			ps = connection
					.prepareStatement("UPDATE klant SET achternaam = ?, bedrijfsnaam=?,emailadres=?,geboortedatum=?,huisnummer=?,kopiepaspoort=?,kopierijbewijs=?,kvknummer=?,land=?,mobielnummer=?,postcode=?,provincie=?,straat=?,telefoonnummer=?,tussenvoegsel=?,voornaam=?,woonplaats=? "
							+ "WHERE id = ? RETURNING *");
			int index = this.fillStatement(ps, klant);
			ps.setInt(index++, klant.getKlantID());
			resultSet = ps.executeQuery();

			if (resultSet.next())
			{
				klant = buildModel(resultSet);
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

		return klant;
	}

}