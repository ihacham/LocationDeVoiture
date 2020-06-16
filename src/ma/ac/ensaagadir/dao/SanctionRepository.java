package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Sanction;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class SanctionRepository {
	private Connection connection;


	public SanctionRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search Sanction by codeSanction
	public ArrayList<Sanction> getSanctionByCode(String codeSanction) {
		ArrayList<Sanction> Sanctions = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from Sanction where numSanction like ?");
			ps.setString(1,"%"+ codeSanction+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//numSanction	codeRestitution	joursDeRetard	montant
				Sanction Sanction = new Sanction();
				Sanction.setNumSanction(rs.getLong(1));
				Sanction.setJoursDeRetard(rs.getInt(2));
				Sanction.setMontant(rs.getDouble(3));
				
				Sanctions.add(Sanction);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Sanctions;
	}

	// retrieve all Sanctions
	public ArrayList<Sanction> getAllSanctions() {
		ArrayList<Sanction> Sanctions = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from Sanction");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//numSanction	codeRestitution	joursDeRetard	montant
				Sanction Sanction = new Sanction();
				Sanction.setNumSanction(rs.getLong(1));
				Sanction.setJoursDeRetard(rs.getInt(2));
				Sanction.setMontant(rs.getDouble(3));
				
				Sanctions.add(Sanction);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Sanctions;
	}

	// add Sanction
	public Sanction addSanction(Sanction nvSanction) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(
				"insert into Sanction(joursDeRetard, montant) values (?,?)");
		ps.setInt(1, nvSanction.getJoursDeRetard());
		ps.setDouble(2, nvSanction.getMontant());
		
		int rs = ps.executeUpdate();

		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			nvSanction.setNumSanction(result.getLong(1));
		}
		
		return nvSanction;
	}

	// edit Sanction
	public void editSanction(Sanction nvSanction) throws SQLException {
		
		
		PreparedStatement ps = connection.prepareStatement(
				"update Sanction set joursDeRetard=?, montant=? where numSanction=?");
		ps.setInt(1, nvSanction.getJoursDeRetard());
		ps.setDouble(2, nvSanction.getMontant());
		
		ps.setLong(3, nvSanction.getNumSanction());
		int rs = ps.executeUpdate();

	}

	// delete Sanction
	public void deleteSanction(Sanction nvSanction) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Sanction where numSanction=?");
		ps.setLong(1, nvSanction.getNumSanction());

		int rs = ps.executeUpdate();

	}
}
