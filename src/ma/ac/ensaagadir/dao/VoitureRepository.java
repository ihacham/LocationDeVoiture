package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ma.ac.ensaagadir.models.Voiture;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class VoitureRepository {
	private Connection connection;


	public VoitureRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search voiture by marque
	public ArrayList<Voiture> getVoitureByMarque(String marque) {
		ArrayList<Voiture> voitures = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from voiture where marque like ?");
			ps.setString(1,"%"+ marque+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numImmatriculation numParking marque type carburant compteurDeKM dateDeMiseEnCirculation disponible
				Voiture voiture = new Voiture();
				voiture.setNumImmatriculation(rs.getLong(1));				
				voiture.getParking().setNumParking(rs.getLong(2));
				voiture.setMarque(rs.getString(3));
				voiture.setType(rs.getString(4));
				voiture.setCarburant(rs.getString(5));
				voiture.setCompteurDeKM(rs.getDouble(6));
				voiture.setDateDeMiseEnCirculation(rs.getDate(7).toLocalDate());
				voiture.setDisponibility(rs.getBoolean(8));
				voitures.add(voiture);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return voitures;
	}

	// retrieve all voitures
	public ArrayList<Voiture> getAllVoitures() {
		ArrayList<Voiture> voitures = new ArrayList<>();
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from voiture");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numImmatriculation numParking marque type carburant compteurDeKM dateDeMiseEnCirculation disponible
				Voiture voiture = new Voiture();
				voiture.setNumImmatriculation(rs.getLong(1));				
				voiture.getParking().setNumParking(rs.getLong(2));
				voiture.setMarque(rs.getString(3));
				voiture.setType(rs.getString(4));
				voiture.setCarburant(rs.getString(5));
				voiture.setCompteurDeKM(rs.getDouble(6));
				voiture.setDateDeMiseEnCirculation(rs.getDate(7).toLocalDate());
				voiture.setDisponibility(rs.getBoolean(8));
				voitures.add(voiture);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return voitures;
	}

	// add voiture
	public Voiture addVoiture(Voiture nvVoiture) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(
				"insert into Voiture(numParking, marque, type, carburant, compteurDeKM, dateDeMiseEnCirculation, disponible) values (?,?,?,?,?,?,?)");
		ps.setLong(1, nvVoiture.getParking().getNumParking());
		ps.setString(2, nvVoiture.getMarque());
		ps.setString(3, nvVoiture.getType());
		ps.setString(4, nvVoiture.getCarburant());
		ps.setDouble(5, nvVoiture.getCompteurDeKM());
		ps.setDate(6, java.sql.Date.valueOf(nvVoiture.getDateDeMiseEnCirculation()));
		ps.setBoolean(6, nvVoiture.getDisponibility());
		
		int rs = ps.executeUpdate();

		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			nvVoiture.setNumImmatriculation(result.getLong(1));
		}
		
		return nvVoiture;
	}

	// edit voiture
	public void editVoiture(Voiture nvVoiture) throws SQLException {
		
		//numImmatriculation numParking marque type carburant compteurDeKM dateDeMiseEnCirculation disponible
		PreparedStatement ps = connection.prepareStatement(
				"update Voiture set numParking=?, marque=?,	type=?, carburant=?, compteurDeKM=?, dateDeMiseEnCirculation=?, disponible=? where numImmatriculation=?");
		ps.setLong(1, nvVoiture.getParking().getNumParking());
		ps.setString(2, nvVoiture.getMarque());
		ps.setString(3, nvVoiture.getType());
		ps.setString(4, nvVoiture.getCarburant());
		ps.setDouble(5, nvVoiture.getCompteurDeKM());
		ps.setDate(6, java.sql.Date.valueOf(nvVoiture.getDateDeMiseEnCirculation()));
		ps.setBoolean(6, nvVoiture.getDisponibility());

		ps.setLong(7, nvVoiture.getNumImmatriculation());
		int rs = ps.executeUpdate();

	}

	// delete voiture
	public void deleteVoiture(Voiture nvVoiture) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Voiture where numImmatriculation=?");
		ps.setLong(1, nvVoiture.getNumImmatriculation());

		int rs = ps.executeUpdate();

	}
}
