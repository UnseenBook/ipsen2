
package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Gebruiker;
import leen.meij.utilities.DataAccess;

public class GebruikerDataAccess extends DataAccess
{
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet                 = null;
	private Gebruiker gebruiker                 = null;


	private Gebruiker buildModel(ResultSet resultSet)
	{
		gebruiker = new Gebruiker();
		try
		{
			gebruiker.setGebruikerID(resultSet.getInt("id"));
			gebruiker.setPersoneelnummer(resultSet.getInt("personeelnummer"));
			gebruiker.setGebruikersnaam(resultSet.getString("gebruikersnaam")); 
			gebruiker.setWachtworod(resultSet.getString("wachtwoord"));
			gebruiker.setAfdeling("afdeling placeholder");///////////////////////////////      Hardcoded afdeling
			gebruiker.setVoornaam(resultSet.getString("voornaam"));
			gebruiker.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
			gebruiker.setAchternaam(resultSet.getString("achternaam"));
		} catch (SQLException negeer) {}
		return gebruiker;
	}

	private void fillStatement(PreparedStatement preparedStatement, Gebruiker gebruiker)
	{
		try
		{
		preparedStatement.setInt(1,gebruiker.getPersoneelnummer());
		preparedStatement.setString(2,gebruiker.getGebruikersnaam());
		preparedStatement.setString(3,gebruiker.getWachtworod());
		preparedStatement.setString(4,gebruiker.getVoornaam());
		preparedStatement.setString(5,gebruiker.getTussenvoegsel());
		preparedStatement.setString(6,gebruiker.getAchternaam());
		preparedStatement.setInt(7,1);///////////////////////////////      Hardcoded afdeling

		} catch (SQLException negeer) {}
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public Gebruiker select(int gebruikerID)
	{
		openConnection();
		try 
		{
			preparedStatement = connection.prepareStatement("SELECT * FROM gebruiker WHERE id = ?");
			preparedStatement.setInt(1, gebruikerID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				gebruiker = buildModel(resultSet);
			}
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} finally {
			if (resultSet != null) try { resultSet.close(); } catch (SQLException negeer) {}
			if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException negeer) {}
			closeConnection();
		}

		return gebruiker;
	}

	/**
	 * 
	 * @param gebruikersNaam
	 * @param wachtwoord
	 */
	public Gebruiker select(String gebruikersNaam, String wachtwoord)
	{
		openConnection();
		try 
		{
			preparedStatement = connection.prepareStatement("SELECT * FROM gebruiker WHERE gebruikersnaam = ? AND wachtwoord = ?");
			preparedStatement.setString(1, gebruikersNaam);
			preparedStatement.setString(2, wachtwoord);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				gebruiker = buildModel(resultSet);
			}
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} finally {
			if (resultSet != null) try { resultSet.close(); } catch (SQLException negeer) {}
			if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException negeer) {}
			closeConnection();
		}
		
		return gebruiker;
	}

	public ArrayList<Gebruiker> selectAll()
	{
		ArrayList<Gebruiker> gebruikerList = new ArrayList<Gebruiker>();
		openConnection();
		try
		{
			preparedStatement = connection.prepareStatement("SELECT * FROM gebruiker");
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				gebruikerList.add(buildModel(resultSet));
			}
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} finally {
			if (resultSet != null) try { resultSet.close(); } catch (SQLException negeer) {}
			if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException negeer) {}
			closeConnection();
		}
		return gebruikerList;
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public Gebruiker add(Gebruiker gebruiker)
	{
		openConnection();
		try
		{
			preparedStatement = connection.prepareStatement("INSERT INTO gebruiker (personeelnummer,gebruikersnaam,wachtwoord,voornaam,tussenvoegsel,achternaam,afdelingid) VALUES (?,?,?,?,?,?,1) RETURNING *");///////////////////////////////      Hardcoded afdeling
			fillStatement(preparedStatement, gebruiker);
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				gebruiker = buildModel(resultSet);
			}
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} finally {
			if (resultSet != null) try { resultSet.close(); } catch (SQLException negeer) {}
			if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException negeer) {}
			closeConnection();
		}

		return gebruiker;
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public void delete(int gebruikerID)
	{
		// TODO - implement {class}.{operation}
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param gebruiker
	 */
	public Gebruiker edit(Gebruiker gebruiker)
	{
		openConnection();
		try
		{
			preparedStatement = connection.prepareStatement("UPDATE gebruiker SET personeelnummer = ?, gebruikersnaam = ?, wachtwoord = ?, voornaam = ?, tussenvoegsel = ?, achternaam = ?, afdelingid = ? WHERE id = ? RETURNING *");///////////////////////////////      Hardcoded afdeling
			fillStatement(preparedStatement, gebruiker);
			preparedStatement.setInt(8,gebruiker.getGebruikerID());
			resultSet = preparedStatement.executeQuery();

			if(resultSet.next())
			{
				gebruiker = buildModel(resultSet);
			}
		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		} finally {
			if (resultSet != null) try { resultSet.close(); } catch (SQLException negeer) {}
			if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException negeer) {}
			closeConnection();
		}

		return gebruiker;
	}

}