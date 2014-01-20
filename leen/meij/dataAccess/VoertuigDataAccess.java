package leen.meij.dataAccess;

import java.sql.*;
import java.util.*;

import leen.meij.utilities.*;
import leen.meij.*;
/**
 * 
 * @author abetcke
 * @author Daan
 * @author Thijs
 * 
 */
public class VoertuigDataAccess extends DataAccess
{
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

        /**
        * 
        * Sets local variable specified by resultSet
        * @return Voertuig @see	buildVoertuigModel
        * @throws SQLException
        */
	public Voertuig buildVoertuigModel(ResultSet resultSet) throws SQLException
	{
		this.resultSet = resultSet;
		return buildVoertuigModel();
	}

        /**
        * 
        * Populates the vehicle (voertuig) model from the db query result
        * @return Voertuig
        * @throws SQLException
        */
	private Voertuig buildVoertuigModel() throws SQLException
	{
		Voertuig voertuig = new Voertuig();

		if (heeftKolom(resultSet, "id"))
		{
			voertuig.setVoertuigID(resultSet.getInt("id"));
		} else
		{
			voertuig.setVoertuigID(resultSet.getInt("voertuig_id"));
		}
		voertuig.setCategorie(resultSet.getString("categorie"));
		voertuig.setMerk(resultSet.getString("merk"));
		voertuig.setType(resultSet.getString("type"));
		voertuig.setKleur(resultSet.getString("kleur"));
		voertuig.setBeschrijving(resultSet.getString("beschrijving"));
		voertuig.isVerhuurbaar(resultSet.getBoolean("verhuurbaar"));
		voertuig.setKenteken(resultSet.getString("kenteken"));
		voertuig.setBouwJaar(resultSet.getInt("bouwjaar"));
		voertuig.setKilometerStand(resultSet.getInt("kilometerstand"));
		voertuig.setBrandstof(resultSet.getString("brandstof"));
		voertuig.setAirco(resultSet.getBoolean("airco"));
		voertuig.setStation(resultSet.getBoolean("station"));
		voertuig.setDagPrijs(resultSet.getDouble("dagprijs"));
		voertuig.setBrandstofPrijs(resultSet.getDouble("brandstofprijs"));
		voertuig.setKilometerPrijs(resultSet.getDouble("kilometerprijs"));
		voertuig.setBorgPrijs(resultSet.getDouble("borgprijs"));

		return voertuig;
	}

        /**
        * 
        * Populates the attributes for preparedstatement from the vehicle (voertuig) model
        * @return int
        * @param voertuig
        * @throws SQLException
        */
	private int fillVoertuigStatement(Voertuig voertuig) throws SQLException
	{
		int i = 1;
		preparedStatement.setString(i++, voertuig.getCategorie());
		preparedStatement.setString(i++, voertuig.getMerk());
		preparedStatement.setString(i++, voertuig.getType());
		preparedStatement.setString(i++, voertuig.getKleur());
		preparedStatement.setString(i++, voertuig.getBeschrijving());
		preparedStatement.setBoolean(i++, voertuig.getVerhuurbaar());
		preparedStatement.setString(i++, voertuig.getKenteken());
		preparedStatement.setInt(i++, voertuig.getBouwJaar());
		preparedStatement.setInt(i++, voertuig.getKilometerStand());
		preparedStatement.setString(i++, voertuig.getBrandstof());
		preparedStatement.setBoolean(i++, voertuig.getAirco());
		preparedStatement.setBoolean(i++, voertuig.getStation());
		preparedStatement.setDouble(i++, voertuig.getDagPrijs());
		preparedStatement.setDouble(i++, voertuig.getBrandstofPrijs());
		preparedStatement.setDouble(i++, voertuig.getKilometerPrijs());
		preparedStatement.setDouble(i++, voertuig.getBorgPrijs());

		return i;
	}
        
        /**
        * 
        * Populates the maintenance (onderhoud) model from the db query result
        * @return Onderhoud
        * @param onderhoudResultSet
        * @throws SQLException
        */
	private Onderhoud buildOnderhoudModel(ResultSet onderhoudResultSet) throws SQLException
	{
		Onderhoud onderhoud = new Onderhoud();
		onderhoud.setHandeling(onderhoudResultSet.getString("handeling"));
		onderhoud.setBeschrijving(onderhoudResultSet.getString("beschrijving"));
		onderhoud.setLocatie(onderhoudResultSet.getString("locatie"));
		onderhoud.setOnderhoudID(onderhoudResultSet.getInt("id"));
		onderhoud.setVoldaan(onderhoudResultSet.getBoolean("voldaan"));

		return onderhoud;
	}

        /**
        * 
        * Populates the attributes for preparedstatement from the maintenance (onderhoud) model
        * @return int
        * @param onderhoud
        * @throws SQLException
        */
	private int fillOnderhoudStatement(Onderhoud onderhoud) throws SQLException
	{
		int i = 1;
		preparedStatement.setString(i++, onderhoud.getBeschrijving());
		preparedStatement.setString(i++, onderhoud.getHandeling());
		preparedStatement.setString(i++, onderhoud.getLocatie());
		preparedStatement.setBoolean(i++, onderhoud.isVoldaan());

		return i;
	}

	/**
	 * selection of a specific vehicle (voertuig) from the database, specified by ID
         * @return Voertuig
	 * @param voertuigID
	 */
	public Voertuig select(int voertuigID)
	{
		openConnection();

		try
		{

			preparedStatement = connection.prepareStatement("SELECT * FROM voertuig WHERE id = ?");

			preparedStatement.setInt(1, voertuigID);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				Voertuig voertuig = buildVoertuigModel();
				voertuig.setOnderhoud(getOnderhoud(voertuigID)); //hierin wordt resultSet gesloten.TODO: onderhoud toevoegen in select query 

				return voertuig;
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

        /**
	 * selection all vehicles (voertuig) from the database, in a arraylist of vehicle (voertuig) models
	 * @return ArrayList<Voertuig>
	 */
	public ArrayList<Voertuig> selectAll()
	{
		openConnection();
		
		Voertuig voertuig;

		try
		{
			ArrayList<Voertuig> voertuigen = new ArrayList<Voertuig>();
			preparedStatement = connection.prepareStatement("SELECT * FROM voertuig");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next())
			{
				voertuig = buildVoertuigModel();
				voertuig.setOnderhoud(getOnderhoud(voertuig.getVoertuigID()));
				voertuigen.add(voertuig);
			}
			return voertuigen;
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
	 * Adds a vehicle (voertuig) to the database, specified by the model
         * @return Voertuig
	 * @param voertuig
	 */
	public Voertuig add(Voertuig voertuig)
	{
		openConnection();

		Voertuig addedVoertuig = null;

		StringBuilder builder = new StringBuilder("INSERT INTO voertuig (");

		builder.append("categorie,");
		builder.append("merk,");
		builder.append("type,");
		builder.append("kleur,");
		builder.append("beschrijving,");
		builder.append("verhuurbaar, ");
		builder.append("kenteken, ");
		builder.append("bouwjaar, ");
		builder.append("kilometerstand, ");
		builder.append("brandstof, ");
		builder.append("airco, ");
		builder.append("station, ");
		builder.append("dagprijs, ");
		builder.append("brandstofprijs, ");
		builder.append("kilometerprijs, ");
		builder.append("borgprijs) ");
		builder.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		builder.append("RETURNING *");

		try
		{
			preparedStatement = connection.prepareStatement(builder.toString());
			this.fillVoertuigStatement(voertuig);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				addedVoertuig = buildVoertuigModel();

				for (Onderhoud onderhoud : voertuig.getOnderhoud())
				{
					onderhoud.setVoertuig(addedVoertuig);
					addedVoertuig.getOnderhoud().add(addOnderhoud(onderhoud));

				}
				return addedVoertuig;
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

		return addedVoertuig;
	}

	/**
	 * Deletes a specific vehicle (voertuig) from the database, specified by ID 
	 * @param voertuigID
	 */
	public void delete(int voertuigID)
	{
		openConnection();

		Voertuig addedVoertuig = null;

		try
		{

			preparedStatement = connection.prepareStatement("BEGIN TRANSACTION;" + "DELETE FROM onderhoud WHERE voertuigenid = ?;"
					+ "DELETE FROM voertuig WHERE id = ?;" + "COMMIT;");
			preparedStatement.setInt(1, voertuigID);
			preparedStatement.setInt(2, voertuigID);

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
	 * Updates the vehicle (voertuig)from the database, specified by the vehicle model 
         * @return Voertuig
	 * @param voertuig
	 */
	public Voertuig edit(Voertuig voertuig)
	{
		openConnection();

		Voertuig edited = null;

		StringBuilder builder = new StringBuilder("UPDATE voertuig SET ");

		builder.append("categorie=?, ");
		builder.append("merk=?, ");
		builder.append("type=?, ");
		builder.append("kleur=?, ");
		builder.append("beschrijving=?, ");
		builder.append("verhuurbaar=?,  ");
		builder.append("kenteken=?, ");
		builder.append("bouwjaar=?, ");
		builder.append("kilometerstand=?, ");
		builder.append("brandstof=?, ");
		builder.append("airco=?, ");
		builder.append("station=?, ");
		builder.append("dagprijs=?, ");
		builder.append("brandstofprijs=?, ");
		builder.append("kilometerprijs=?, ");
		builder.append("borgprijs=? ");
		builder.append("WHERE id=?  ");
		builder.append("RETURNING * ");

		try
		{

			preparedStatement = connection.prepareStatement(builder.toString());
			int index = this.fillVoertuigStatement(voertuig);
			preparedStatement.setInt(index++, voertuig.getVoertuigID());
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
			{
				voertuig = buildVoertuigModel();

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

		return voertuig;
	}

	/**
	 * Adds a maintenance item (onderhoud) to the database, specified by the model
         * @return Onderhoud
	 * @param onderhoud
	 */
	public Onderhoud addOnderhoud(Onderhoud onderhoud)
	{
		openConnection();

		ResultSet onderhoudResultSet = null;

		StringBuilder builder = new StringBuilder("INSERT INTO onderhoud (");

		builder.append("beschrijving,");
		builder.append("handeling,");
		builder.append("locatie,");
		builder.append("voldaan,");
		builder.append("klantenid,");
		builder.append("voertuigenid) ");
		builder.append("VALUES (?,?,?,?,?,?) ");
		builder.append("RETURNING *");

		try
		{

			preparedStatement = connection.prepareStatement(builder.toString());
			int index = this.fillOnderhoudStatement(onderhoud);
			preparedStatement.setObject(index++, onderhoud.getKlantID());
			preparedStatement.setInt(index++, onderhoud.getVoertuig().getVoertuigID());
			onderhoudResultSet = preparedStatement.executeQuery();

			if (onderhoudResultSet.next())
			{
				onderhoud = buildOnderhoudModel(onderhoudResultSet);
				onderhoud.setVoertuig(new Voertuig());
				onderhoud.getVoertuig().setVoertuigID(onderhoudResultSet.getInt("voertuigenid"));

			}
			
			if(!onderhoud.isVoldaan())
			{			
				preparedStatement = connection.prepareStatement("UPDATE voertuig SET verhuurbaar=FALSE WHERE id=?");
			
				preparedStatement.setInt(1, onderhoud.getVoertuig().getVoertuigID());
				preparedStatement.execute();
			}
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			if (onderhoudResultSet != null) try
			{
				onderhoudResultSet.close();
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

		return onderhoud;
	}

        
        /**
	 * selection of all maintenance items (Onderhoud) from the database by vehicleId, in a arraylist of maintenance items (Onderhoud) models.
	 * @return ArrayList<Onderhoud>
	 */
	private ArrayList<Onderhoud> getOnderhoud(int voertuigID)
	{
		openConnection();
		ArrayList<Onderhoud> onderhouden = new ArrayList<Onderhoud>();
		ResultSet onderhoudResultSet = null;

		try
		{

			preparedStatement = connection.prepareStatement("SELECT * FROM onderhoud WHERE voertuigenid=?");

			preparedStatement.setInt(1, voertuigID);

			onderhoudResultSet = preparedStatement.executeQuery();

			while (onderhoudResultSet.next())
			{
				Onderhoud onderhoud = buildOnderhoudModel(onderhoudResultSet);
				onderhouden.add(onderhoud);
			}
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			if (onderhoudResultSet != null) try
			{
				onderhoudResultSet.close();
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

		return onderhouden;
	}

        /**
	 * Updates the maintenance item (onderhoud) from the database, specified by the onderhoud model 
         * @return Onderhoud
	 * @param voertuig
	 */
	public Onderhoud editOnderhoud(Onderhoud onderhoud)
	{
		openConnection();

		ResultSet onderhoudResultSet = null;

		try
		{
			preparedStatement = connection.prepareStatement("UPDATE onderhoud SET beschrijving=?,handeling=?,locatie=?,voldaan=?,klantenid=? "
					+ " WHERE id = ? " +
					"RETURNING *");
			
			int index = this.fillOnderhoudStatement(onderhoud);
			preparedStatement.setObject(index++, onderhoud.getKlantID());
			preparedStatement.setInt(index++, onderhoud.getOnderhoudID());

			
			onderhoudResultSet = preparedStatement.executeQuery();

			if (onderhoudResultSet.next())
			{
				onderhoud = buildOnderhoudModel(onderhoudResultSet);
				onderhoud.setVoertuig(new Voertuig());
				onderhoud.getVoertuig().setVoertuigID(onderhoudResultSet.getInt("voertuigenid"));

			}
			if(!onderhoud.isVoldaan())
			{			
				preparedStatement = connection.prepareStatement("UPDATE voertuig SET verhuurbaar=FALSE WHERE id=?");
			
				preparedStatement.setInt(1, onderhoud.getVoertuig().getVoertuigID());
				preparedStatement.execute();
			}
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
		finally
		{
			if (onderhoudResultSet != null) try
			{
				onderhoudResultSet.close();
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

		return onderhoud;
	}

}