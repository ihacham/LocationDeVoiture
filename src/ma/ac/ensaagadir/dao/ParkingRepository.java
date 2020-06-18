package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ma.ac.ensaagadir.models.Parking;
import ma.ac.ensaagadir.models.Voiture;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class ParkingRepository {
	private Connection connection;

	public ParkingRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search Parking by rue
	public ArrayList<Parking> getParkingByRue(String rue) {
		ArrayList<Parking> Parkings = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement("select * from Parking where rue like ?");
			ps.setString(1, "%" + rue + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numParking numImmatriculation capacite rue arrondissement
				Parking parking = new Parking();
				parking.setNumParking(rs.getLong(1));

				ps = connection.prepareStatement("select * from Voiture where numParking = ?");
				ps.setLong(1, parking.getNumParking());

				ResultSet result = ps.executeQuery();

				ArrayList<Voiture> voitures = new ArrayList<>();

				while (result.next()) {
					Voiture voiture = new Voiture();
					voiture.setNumImmatriculation(result.getString(1));
					voiture.getParking().setNumParking(result.getLong(2));
					voiture.setMarque(result.getString(3));
					voiture.setType(result.getString(4));
					voiture.setCarburant(result.getString(5));
					voiture.setCompteurDeKM(result.getDouble(6));
					voiture.setDateDeMiseEnCirculation(result.getDate(7).toLocalDate());
					voiture.setDisponibility(result.getBoolean(8));
					voitures.add(voiture);
				}
				parking.setVoitures(voitures);
				parking.setCapacite(rs.getLong(3));
				parking.setRue(rs.getString(4));
				parking.setArrondissement(rs.getString(5));

				Parkings.add(parking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Parkings;
	}

	// retrieve all Parkings
	public ArrayList<Parking> getAllParkings() {
		ArrayList<Parking> Parkings = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement("select * from Parking");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numParking numImmatriculation capacite rue arrondissement
				Parking parking = new Parking();
				parking.setNumParking(rs.getLong(1));

				ps = connection.prepareStatement("select * from Voiture where numParking = ?");
				ps.setLong(1, parking.getNumParking());

				ResultSet result = ps.executeQuery();

				ArrayList<Voiture> voitures = new ArrayList<>();

				while (result.next()) {
					Voiture voiture = new Voiture();
					voiture.setNumImmatriculation(result.getString(1));
					voiture.setNumParking(parking);
					voiture.setMarque(result.getString(3));
					voiture.setType(result.getString(4));
					voiture.setCarburant(result.getString(5));
					voiture.setCompteurDeKM(result.getDouble(6));
					voiture.setDateDeMiseEnCirculation(result.getDate(7).toLocalDate());
					voiture.setDisponibility(result.getBoolean(8));
					voitures.add(voiture);
				}
				parking.setVoitures(voitures);
				parking.setCapacite(rs.getLong(3));
				parking.setRue(rs.getString(4));
				parking.setArrondissement(rs.getString(5));

				Parkings.add(parking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Parkings;
	}

	// add Parking
	public Parking addParking(Parking nvParking) throws SQLException {

		PreparedStatement ps = connection
				.prepareStatement("insert into Parking(capacite, rue, arrondissement) values (?,?,?)");
		ps.setLong(1, nvParking.getCapacite());
		ps.setString(2, nvParking.getRue());
		ps.setString(3, nvParking.getArrondissement());

		int rs = ps.executeUpdate();

		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();

		while (result.next()) {
			nvParking.setNumParking(result.getLong(1));
		}

		return nvParking;
	}

	// edit Parking
	public void editParking(Parking nvParking) throws SQLException {

		PreparedStatement ps = connection
				.prepareStatement("update Parking set capacite=?, rue=?, arrondissement=? where numParking=?");
		ps.setLong(1, nvParking.getCapacite());
		ps.setString(2, nvParking.getRue());
		ps.setString(3, nvParking.getArrondissement());

		ps.setLong(4, nvParking.getNumParking());
		int rs = ps.executeUpdate();

	}

	// delete Parking
	public void deleteParking(Parking nvParking) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Parking where numParking=?");
		ps.setLong(1, nvParking.getNumParking());

		int rs = ps.executeUpdate();

	}
}
