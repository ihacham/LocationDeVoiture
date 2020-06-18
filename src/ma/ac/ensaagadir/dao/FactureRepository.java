package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class FactureRepository {

	private Connection connection;


	public FactureRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search facture by name
	public ArrayList<Facture> getFactureByNum(Long num) {
		ArrayList<Facture> factures = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from facture where numFacture like ?");
			ps.setString(1,"%"+ num+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numFacture, numContrat, dateDeFacture, montantAPaye, isPayed
				Facture facture = new Facture();
				facture.setNumFacture(rs.getLong(1));
				facture.setDateDeFacture(rs.getDate(3).toLocalDate());
				facture.setMontantAPaye(rs.getDouble(4));
				facture.setIsPayed(rs.getBoolean(5));
				
				/*
				ps = connection.prepareStatement("select * from contrat where numFacture=?");
				ps.setLong(1, facture.getNumFacture());
				ResultSet result = ps.executeQuery();
				
				while(result.next()) {
					//numContrat	codeReservation	numFacture	dateContrat	dateEcheance	isSigned
					Contrat contrat = new Contrat();
					contrat.setNumContrat(result.getLong(1));
					contrat.setDateContrat(result.getDate(4).toLocalDate());
					contrat.setDateEcheance(result.getDate(5).toLocalDate());
					contrat.setIsSigned(result.getBoolean(6));
					facture.setContrat(contrat);
				}
				*/
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
				facture.setNumFacture(rs.getLong(1));
				facture.setDateDeFacture(rs.getDate(3).toLocalDate());
				facture.setMontantAPaye(rs.getDouble(4));
				facture.setIsPayed(rs.getBoolean(5));
				/*
				ps = connection.prepareStatement("select * from contrat where numFacture=?");
				ps.setLong(1, facture.getNumFacture());
				ResultSet result = ps.executeQuery();
				
				while(result.next()) {
					//numContrat	codeReservation	numFacture	dateContrat	dateEcheance	isSigned
					Contrat contrat = new Contrat();
					contrat.setNumContrat(result.getLong(1));
					contrat.setDateContrat(result.getDate(4).toLocalDate());
					contrat.setDateEcheance(result.getDate(5).toLocalDate());
					contrat.setIsSigned(result.getBoolean(6));
					facture.setContrat(contrat);
				}
				*/
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
				"insert into Facture(dateDeFacture,	montantAPaye, isPayed) values (?,?,?)");
		
		
		ps.setDate(1, java.sql.Date.valueOf(nvFacture.getDateDeFacture()));
		ps.setDouble(2, nvFacture.getMontantAPaye());
		ps.setBoolean(3, nvFacture.getIsPayed());
		
		ps.executeUpdate();
		
		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			nvFacture.setNumFacture(result.getLong(1));
		}
		
		return nvFacture;
	}

	// edit facture
	public void editFacture(Facture nvFacture) throws SQLException {
		//numFacture	numContrat	dateDeFacture	montantAPaye isPayed
		PreparedStatement ps = connection.prepareStatement(
				"update Facture set dateDeFacture=?, montantAPaye=?, isPayed=? where numFacture=?");
		/*if(nvFacture.getContrat() != null)
			ps.setLong(1, nvFacture.getContrat().getNumContrat());
		else
			ps.setNull(1, java.sql.Types.BIGINT);*/
		ps.setDate(1, java.sql.Date.valueOf(nvFacture.getDateDeFacture()));
		ps.setDouble(2, nvFacture.getMontantAPaye());
		ps.setBoolean(3, nvFacture.getIsPayed());
		
		ps.setLong(4, nvFacture.getNumFacture());

		int rs = ps.executeUpdate();

	}

	// delete facture
	public void deleteFacture(Facture nvFacture) throws SQLException {
		//numFacture	numContrat	dateDeFacture	montantAPaye
		PreparedStatement ps = connection.prepareStatement("delete from Facture where numFacture=?");
		ps.setLong(1, nvFacture.getNumFacture());

		int rs = ps.executeUpdate();

	}
}
