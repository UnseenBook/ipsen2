package leen.meij.dataAccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leen.meij.Afdeling;
import leen.meij.Gebruiker;
import leen.meij.utilities.DataAccess;

/**
 * The GebruikerDataAccess class. Provides data access methods for Gebruiker objects.
 * @author Daan
 * @author Thijs
 *
 */

public class GebruikerDataAccess extends DataAccess
{
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * Creates a Afdeling model and fills it with the data from a ResultSet object. The object is returned to be pu in the Gebruiker model.
	 * @param afdelingResultSet
	 * @return
	 * @throws SQLException
	 */
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

	/**
	 * Creates a Gebruiker model and fills it with data fram a ResultSet object. It also checks if the ResultSet has data of the Afdeling model and if there is, it will place the Afdeling model in the Gebruiker model.
	 * @return The Gebruiker object filled with the found data
	 * @throws SQLException When an SQLException occurs it will be thrown in the method where this method is called
	 */
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

	/**
	 * When making an edit in a database record or add a database record, this method will fill the prepared statement with the model data. 
	 * @param gebruiker The model that will be placed in the database
	 * @param metWachtwoord True when the password has to be updated, otherwise false
	 * @return number of items added in the prepared statement
	 * @throws SQLException When an SQLException occurs it will be thrown in the method where this method is called
	 */
	private int fillStatement(Gebruiker gebruiker, boolean metWachtwoord) throws SQLException
	{
		int i = 1;
		preparedStatement.setInt(i++, gebruiker.getPersoneelnummer());
		preparedStatement.setString(i++, gebruiker.getGebruikersnaam());
		
		if(metWachtwoord)
			preparedStatement.setString(i++, gebruiker.getWachtwoord());
		
		preparedStatement.setString(i++, gebruiker.getVoornaam());
		preparedStatement.setString(i++, gebruiker.getTussenvoegsel());
		preparedStatement.setString(i++, gebruiker.getAchternaam());
		preparedStatement.setInt(i++, gebruiker.getAfdeling().getAfdelingID());

		return i;

	}

	/**
	 * Finds a specific Gebruiker in the database based on the id of the Gebruiker and returns the model of the found Gebruiker or NULL if the Gebruiker is not found
	 * @param gebruikerID The id of the selected Gebruiker
	 * @return The found Gebruiker object
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
	 * Finds a specific Gebruiker object in the database based on the username and password of the Gebruiker and returns the model of the found Gebruiker or NULL if the Gebruiker is not found
	 * @param gebruikersNaam The username of the Gebruiker object
	 * @param wachtwoord The password of the Gebruiker object
	 * @return The found Gebruiker object
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

	/**
	 * Finds all the Gebruiker objects in the database
	 * @return A list of all the Gebruiker objects
	 */
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
	 * Will add the Gebruiker object to the database
	 * @param gebruiker The object inserted into the database
	 * @return The added Gebruiker object
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


			fillStatement(gebruiker,true);
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
	 * Deletes a Gebruiker object from the database
	 * @param gebruikerID The Gebruiker object's id to be deleted
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
	 * Edits a Gebruiker object in the database
	 * @param gebruiker The Gebruiker object to edit
	 * @return The edited Gebruiker object
	 */
	public Gebruiker edit(Gebruiker gebruiker)
	{
		openConnection();

		Gebruiker tempGebruiker;

		try
		{
			preparedStatement = connection

					.prepareStatement("UPDATE gebruiker SET personeelnummer = ?, gebruikersnaam = ?, voornaam = ?, tussenvoegsel = ?, achternaam = ?, afdelingid = ? WHERE id = ? RETURNING *");// /////////////////////////////

																																																// Hardcoded
																																																// afdeling
			int index = fillStatement(gebruiker,false);
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

	/**
	 * Edits a Gebruiker object his password
	 * @param gebruikerID The id of the Gebruiker object
	 * @param wachtwoord The new password of the Gebruiker object
	 * @return NULL
	 */
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