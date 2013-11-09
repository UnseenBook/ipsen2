package leen.meij.dataAccess;

import java.sql.*;
import java.util.*;

import leen.meij.utilities.*;
import leen.meij.*;

public class VoertuigDataAccess extends DataAccess
{
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public Voertuig buildVoertuigModel(ResultSet resultSet) throws SQLException
	{
		this.resultSet = resultSet;
		return buildVoertuigModel();
	}

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
		voertuig.setBeschrijving(resultSet.getString("categorie"));
		voertuig.setCategorie(resultSet.getString("merk"));
		voertuig.setKleur(resultSet.getString("type"));
		voertuig.setMerk(resultSet.getString("kleur"));
		voertuig.setType(resultSet.getString("beschrijving"));
		voertuig.isVerhuurbaar(resultSet.getBoolean("verhuurbaar"));

		return voertuig;
	}

	private int fillVoertuigStatement(Voertuig voertuig) throws SQLException
	{
		int i = 1;
		preparedStatement.setString(i++, voertuig.getBeschrijving());
		preparedStatement.setString(i++, voertuig.getCategorie());
		preparedStatement.setString(i++, voertuig.getKleur());
		preparedStatement.setString(i++, voertuig.getMerk());
		preparedStatement.setString(i++, voertuig.getType());
		preparedStatement.setBoolean(i++, voertuig.getVerhuurbaar());

		return i;
	}

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
	 * 
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
	 * 
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
		builder.append("verhuurbaar) ");
		builder.append("VALUES (?,?,?,?,?,?) ");
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
	 * 
	 * @param klantID
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
	 * 
	 * @param voertuig
	 */
	public Voertuig edit(Voertuig voertuig)
	{
		openConnection();

		Voertuig edited = null;
		try
		{

			preparedStatement = connection.prepareStatement("UPDATE voertuig SET categorie=?,merk=?,type=?,kleur=?,beschrijving=?,verhuurbaar=? "
					+ "WHERE id=? RETURNING *");
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
	 * 
	 * @param onderhoud
	 */
	public Onderhoud addOnderhoud(Onderhoud onderhoud)
	{
		openConnection();

		ResultSet onderhoudResultSet = null;

		try
		{

			preparedStatement = connection.prepareStatement("INSERT INTO onderhoud (beschrijving,handeling,locatie,voldaan,klantenid,voertuigenid) "
					+ "VALUES (?,?,?,?,?,?) RETURNING *");
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

}