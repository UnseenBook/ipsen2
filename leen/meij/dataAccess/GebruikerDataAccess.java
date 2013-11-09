package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Afdeling;
import leen.meij.Gebruiker;
import leen.meij.utilities.DataAccess;

public class GebruikerDataAccess extends DataAccess
{
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private Afdeling buildAfdelingModel(ResultSet afdelingResultSet) throws SQLException
	{
		Afdeling afdeling = new Afdeling();

		afdeling.setAfdelingID(afdelingResultSet.getInt("afdeling_id"));
		afdeling.setAfdelingNaam(afdelingResultSet.getString("afdelingnaam"));
		afdeling.setRechten(afdelingResultSet.getInt("rechten"));

		return afdeling;
	}

	public Gebruiker buildGebruikerModel(ResultSet resultSet) throws SQLException
	{
		this.resultSet = resultSet;
		return buildGebruikerModel();
	}

	private Gebruiker buildGebruikerModel() throws SQLException
	{
		Gebruiker gebruiker = new Gebruiker();

		if (heeftKolom(resultSet, "gebruiker_id"))
		{
			gebruiker.setGebruikerID(resultSet.getInt("gebruiker_id"));
		} else 
		{
			gebruiker.setGebruikerID(resultSet.getInt("id"));
		}
		gebruiker.setPersoneelnummer(resultSet.getInt("personeelnummer"));
		gebruiker.setGebruikersnaam(resultSet.getString("gebruikersnaam"));
		gebruiker.setWachtworod(resultSet.getString("wachtwoord"));
		if (heeftKolom(resultSet, "afdeling_id"))
		{
			gebruiker.setAfdelingID(resultSet.getInt("afdeling_id"));
			gebruiker.setAfdeling(buildAfdelingModel(resultSet));
		} else
		{
			gebruiker.setAfdelingID(0);
			gebruiker.setAfdeling(null);
		}
		gebruiker.setVoornaam(resultSet.getString("voornaam"));
		gebruiker.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
		gebruiker.setAchternaam(resultSet.getString("achternaam"));

		return gebruiker;
	}

	private int fillStatement(Gebruiker gebruiker) throws SQLException
	{

		int i = 1;
		preparedStatement.setInt(i++, gebruiker.getPersoneelnummer());
		preparedStatement.setString(i++, gebruiker.getGebruikersnaam());
		preparedStatement.setString(i++, gebruiker.getWachtwoord());
		preparedStatement.setString(i++, gebruiker.getVoornaam());
		preparedStatement.setString(i++, gebruiker.getTussenvoegsel());
		preparedStatement.setString(i++, gebruiker.getAchternaam());
		preparedStatement.setInt(i++, gebruiker.getAfdeling().getAfdelingID());

		return i;

	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public Gebruiker select(int gebruikerID)
	{
		openConnection();
		
		Gebruiker gebruiker = null;

		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("A.id AS afdeling_id, ");
		builder.append("afdelingnaam, ");
		builder.append("rechten, ");
		builder.append("G.id AS gebruiker_id, ");
		builder.append("personeelnummer, ");
		builder.append("gebruikersnaam, ");
		builder.append("wachtwoord, ");
		builder.append("voornaam, ");
		builder.append("tussenvoegsel, ");
		builder.append("achternaam ");
		builder.append("FROM gebruiker AS G,");
		builder.append("afdeling AS A ");
		builder.append("WHERE G.id = ? ");
		builder.append("AND G.afdelingid = A.id");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setInt(1, gebruikerID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				gebruiker = buildGebruikerModel();
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
		
		Gebruiker gebruiker = null;

		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("A.id AS afdeling_id, ");
		builder.append("afdelingnaam, ");
		builder.append("rechten, ");
		builder.append("G.id AS gebruiker_id, ");
		builder.append("personeelnummer, ");
		builder.append("gebruikersnaam, ");
		builder.append("wachtwoord, ");
		builder.append("voornaam, ");
		builder.append("tussenvoegsel, ");
		builder.append("achternaam ");
		builder.append("FROM gebruiker AS G,");
		builder.append("afdeling AS A ");
		builder.append("WHERE gebruikersnaam = ? AND wachtwoord = MD5(?) ");
		builder.append("AND G.afdelingid = A.id");
		
		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());

			preparedStatement.setString(1, gebruikersNaam);
			preparedStatement.setString(2, wachtwoord);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				gebruiker = buildGebruikerModel();
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

		return gebruiker;
	}

	public ArrayList<Gebruiker> selectAll()
	{
		ArrayList<Gebruiker> gebruikerList = new ArrayList<Gebruiker>();
		openConnection();

		StringBuilder builder = new StringBuilder("SELECT ");
		builder.append("A.id AS afdeling_id, ");
		builder.append("afdelingnaam, ");
		builder.append("rechten, ");
		builder.append("G.id AS gebruiker_id, ");
		builder.append("personeelnummer, ");
		builder.append("gebruikersnaam, ");
		builder.append("wachtwoord, ");
		builder.append("voornaam, ");
		builder.append("tussenvoegsel, ");
		builder.append("achternaam ");
		builder.append("FROM gebruiker AS G,");
		builder.append("afdeling AS A ");
		builder.append("WHERE G.afdelingid = A.id");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				gebruikerList.add(buildGebruikerModel());
			}
			return gebruikerList;
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
	 * @param gebruiker
	 */
	public Gebruiker add(Gebruiker gebruiker)
	{
		openConnection();

		Gebruiker tempGebruiker;

		StringBuilder builder = new StringBuilder("INSERT INTO gebruiker (");
		builder.append("personeelnummer,");
		builder.append("gebruikersnaam,");
		builder.append("wachtwoord,");
		builder.append("voornaam,");
		builder.append("tussenvoegsel,");
		builder.append("achternaam,");
		builder.append("afdelingid) ");
		builder.append("VALUES (?,?,MD5(?),?,?,?,?) ");
		builder.append("RETURNING *");		

		try
		{

			preparedStatement = connection.prepareStatement(builder.toString());


			fillStatement(gebruiker);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				tempGebruiker = buildGebruikerModel();
				if (tempGebruiker.getAfdeling() == null)
				{
					tempGebruiker.setAfdelingID(gebruiker.getAfdelingID());
					tempGebruiker.setAfdeling(gebruiker.getAfdeling());
				}
				gebruiker = tempGebruiker;
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
 
		return gebruiker; 
	}

	/**
	 * 
	 * @param gebruikerID
	 */
	public void delete(int gebruikerID)
	{
		openConnection();
		try
		{
			preparedStatement = connection.prepareStatement("DELETE FROM gebruiker WHERE id=?");
 
			preparedStatement.setInt(1, gebruikerID);
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
	 * 
	 * @param gebruiker
	 */
	public Gebruiker edit(Gebruiker gebruiker)
	{
		openConnection();

		Gebruiker tempGebruiker;

		try
		{
			preparedStatement = connection

					.prepareStatement("UPDATE gebruiker SET personeelnummer = ?, gebruikersnaam = ?, wachtwoord = MD5(?), voornaam = ?, tussenvoegsel = ?, achternaam = ?, afdelingid = ? WHERE id = ? RETURNING *");// /////////////////////////////

																																																// Hardcoded
																																																// afdeling
			int index = fillStatement(gebruiker);
			preparedStatement.setInt(index, gebruiker.getGebruikerID());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				tempGebruiker = buildGebruikerModel();
				if (tempGebruiker.getAfdeling() == null)
				{
					tempGebruiker.setAfdelingID(gebruiker.getAfdelingID());
					tempGebruiker.setAfdeling(gebruiker.getAfdeling());
				}
				gebruiker = tempGebruiker;
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

		return gebruiker;
	}

	public Gebruiker editWachtwoord(int gebruikerID, String wachtwoord)
	{
		openConnection();

		Gebruiker tempGebruiker;

		try
		{
			preparedStatement = connection

					.prepareStatement("UPDATE gebruiker SET wachtwoord = MD5(?) WHERE id = ?");// /////////////////////////////

																																																// Hardcoded
																																																// afdeling
			
			preparedStatement.setString(1, wachtwoord);
			preparedStatement.setInt(2, gebruikerID);
			preparedStatement.execute();
			
			return select(gebruikerID);

		
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