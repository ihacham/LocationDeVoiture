package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class FactureRepository {

	private Connection connection;


	public FactureRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search facture by name
	public ArrayList<Facture> getFactureByNum(String num) {
		ArrayList<Facture> factures = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from facture where numFacture like ?");
			ps.setString(1,"%"+ num+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numFacture, numContrat, dateDeFacture, montantAPaye, isPayed
				Facture facture = new Facture();
				ps.setString(1, facture.getNumFacture());
				ps.setLong(2, facture.getContrat().getNumContrat());
				ps.setDate(3, java.sql.Date.valueOf(facture.getDateDeFacture()));
				ps.setDouble(4, facture.getMontantAPaye());
				ps.setBoolean(5, facture.getIsPayed());
				factures.add(facture);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factures;
	}

	// retrieve all factures
	public ArrayList<Facture> getAllFactures() {
		ArrayList<Facture> factures = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from facture");
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numFacture, numContrat, dateDeFacture, montantAPaye, isPayed
				Facture facture = new Facture();
				ps.setString(1, facture.getNumFacture());
				ps.setLong(2, facture.getContrat().getNumContrat());
				ps.setDate(3, java.sql.Date.valueOf(facture.getDateDeFacture()));
				ps.setDouble(4, facture.getMontantAPaye());
				ps.setBoolean(5, facture.getIsPayed());
				factures.add(facture);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return factures;
	}

	// add facture
	public Facture addFacture(Facture nvFacture) throws SQLException {
		//numFacture	numContrat	dateDeFacture	montantAPaye
		PreparedStatement ps = connection.prepareStatement(
				"insert into Facture(numFacture, numContrat, dateDeFacture,	montantAPaye, isPayed) values (?,?,?,?,?)");
		ps.setString(1, nvFacture.getNumFacture());
		ps.setLong(2, nvFacture.getContrat().getNumContrat());
		ps.setDate(3, java.sql.Date.valueOf(nvFacture.getDateDeFacture()));
		ps.setDouble(4, nvFacture.getMontantAPaye());
		ps.setBoolean(5, nvFacture.getIsPayed());
		
		ps.executeUpdate();
		
		return nvFacture;
	}

	// edit facture
	public void editFacture(Facture nvFacture) throws SQLException {
		//numFacture	numContrat	dateDeFacture	montantAPaye isPayed
		PreparedStatement ps = connection.prepareStatement(
				"update Facture set numContrat=?, dateDeFacture=?, montantAPaye=?, isPayed=? where numFacture=?");
		ps.setLong(1, nvFacture.getContrat().getNumContrat());
		ps.setDate(2, java.sql.Date.valueOf(nvFacture.getDateDeFacture()));
		ps.setDouble(3, nvFacture.getMontantAPaye());
		ps.setBoolean(4, nvFacture.getIsPayed());
		
		ps.setString(5, nvFacture.getNumFacture());

		int rs = ps.executeUpdate();

	}

	// delete facture
	public void deleteFacture(Facture nvFacture) throws SQLException {
		//numFacture	numContrat	dateDeFacture	montantAPaye
		PreparedStatement ps = connection.prepareStatement("delete from Facture where numFacture=?");
		ps.setString(1, nvFacture.getNumFacture());

		int rs = ps.executeUpdate();

	}
}
