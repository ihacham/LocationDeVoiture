package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Parking;
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
				voiture.setNumImmatriculation(rs.getString(1));				
				//voiture.getParking().setNumParking(rs.getLong(2));
				voiture.setMarque(rs.getString(3));
				voiture.setType(rs.getString(4));
				voiture.setCarburant(rs.getString(5));
				voiture.setCompteurDeKM(rs.getDouble(6));
				voiture.setDateDeMiseEnCirculation(rs.getDate(7).toLocalDate());
				voiture.setDisponibility(rs.getBoolean(8));
				
				
				ps = connection.prepareStatement("select * from parking where numParking = ?");
				ps.setLong(1,rs.getLong(2));
				ResultSet result = ps.executeQuery();
				
				while(result.next()) {
					Parking parking = new Parking();
					parking.setNumParking(result.getLong(1));
					parking.setCapacite(result.getLong(3));
					parking.setRue(result.getString(4));
					parking.setArrondissement(result.getString(5));
					voiture.setNumParking(parking);
				}
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
				voiture.setNumImmatriculation(rs.getString(1));		
				voiture.setMarque(rs.getString(3));
				voiture.setType(rs.getString(4));
				voiture.setCarburant(rs.getString(5));
				voiture.setCompteurDeKM(rs.getDouble(6));
				voiture.setDateDeMiseEnCirculation(rs.getDate(7).toLocalDate());
				voiture.setDisponibility(rs.getBoolean(8));
				
				ps = connection.prepareStatement("select * from parking where numParking = ?");
				ps.setLong(1,rs.getLong(2));
				ResultSet result = ps.executeQuery();
				
				while(result.next()) {
					Parking parking = new Parking();
					parking.setNumParking(result.getLong(1));
					parking.setCapacite(result.getLong(3));
					parking.setRue(result.getString(4));
					parking.setArrondissement(result.getString(5));
					voiture.setNumParking(parking);
				}
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
				"insert into Voiture(numImmatriculation,numParking, marque, type, carburant, compteurDeKM, dateDeMiseEnCirculation, disponible) values (?,?,?,?,?,?,?,?)");
		ps.setString(1, nvVoiture.getNumImmatriculation());
		ps.setLong(2, nvVoiture.getParking().getNumParking());
		ps.setString(3, nvVoiture.getMarque());
		ps.setString(4, nvVoiture.getType());
		ps.setString(5, nvVoiture.getCarburant());
		ps.setDouble(6, nvVoiture.getCompteurDeKM());
		ps.setDate(7, java.sql.Date.valueOf(nvVoiture.getDateDeMiseEnCirculation()));
		ps.setBoolean(8, nvVoiture.getDisponibility());
		
		int rs = ps.executeUpdate();

		
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
		ps.setBoolean(7, nvVoiture.getDisponibility());

		ps.setString(8, nvVoiture.getNumImmatriculation());
		int rs = ps.executeUpdate();

	}

	// delete voiture
	public void deleteVoiture(Voiture nvVoiture) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Voiture where numImmatriculation=?");
		ps.setString(1, nvVoiture.getNumImmatriculation());

		int rs = ps.executeUpdate();

	}
}
