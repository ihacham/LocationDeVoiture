package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class ReservationRepository {

	private Connection connection;


	public ReservationRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search Reservation by codeReservation
	public ArrayList<Reservation> getReservationByName(String codeReservation) {
		ArrayList<Reservation> Reservations = new ArrayList<>();
		 
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from Reservation where codeReservation like ?");
			ps.setString(1,"%"+ codeReservation+"%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// codeReservation	etat	numImmatriculation	codeRestitution	numContrat	codeClient	dateReservation	dateDepart	dateRetour
				Reservation Reservation = new Reservation();
				Reservation.setCodeReservation(rs.getLong(1));
				Reservation.setEtat(rs.getString(2));
				Reservation.getVoiture().setNumImmatriculation(rs.getLong(3));
				Reservation.getRestitution().setCodeRestitution(rs.getLong(4));
				Reservation.getContrat().setNumContrat(rs.getLong(5));
				Reservation.getClient().setCodeClient(rs.getLong(6));
				Reservation.setDateReservation(rs.getDate(7).toLocalDate());
				Reservation.setDateDepart(rs.getDate(8).toLocalDate());
				Reservation.setDateRetour(rs.getDate(9).toLocalDate());
				Reservations.add(Reservation);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Reservations;
	}

	// retrieve all Reservations
	public ArrayList<Reservation> getAllReservations() {
		ArrayList<Reservation> Reservations = new ArrayList<>();
		try {
			
			PreparedStatement ps = connection.prepareStatement("select * from Reservation");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// codeReservation nom prenom adresse imageScanneeDPermis tel age
				Reservation Reservation = new Reservation();
				Reservation.setCodeReservation(rs.getLong(1));
				Reservation.setEtat(rs.getString(2));
				Reservation.getVoiture().setNumImmatriculation(rs.getLong(3));
				Reservation.getRestitution().setCodeRestitution(rs.getLong(4));
				Reservation.getContrat().setNumContrat(rs.getLong(5));
				Reservation.getClient().setCodeClient(rs.getLong(6));
				Reservation.setDateReservation(rs.getDate(7).toLocalDate());
				Reservation.setDateDepart(rs.getDate(8).toLocalDate());
				Reservation.setDateRetour(rs.getDate(9).toLocalDate());
				
				ps = connection.prepareStatement("select * from Client where codeClient = ?");
				Client client = Reservation.getClient();
				ps.setLong(1, client.getCodeClient());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					client.setNom(result.getString(2));
					client.setPrenom(result.getString(3));
					client.setAdresse(result.getString(4));
					client.setImageScanneeDPermis(result.getString(5));
					client.setTel(result.getString(6));
					client.setAge(result.getInt(7));
				}
				
				Reservations.add(Reservation);
				
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Reservations;
	}

	// add Reservation
	public Reservation addReservation(Reservation nvReservation) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(
				"insert into Reservation(etat,	numImmatriculation,	codeRestitution,	numContrat,	codeClient,	dateReservation,	dateDepart,	dateRetour) values (?,?,?,?,?,?,?,?)");
		ps.setString(1, nvReservation.getEtat());
		ps.setLong(2, nvReservation.getVoiture().getNumImmatriculation());
		ps.setLong(3, nvReservation.getRestitution().getCodeRestitution());
		ps.setLong(4, nvReservation.getContrat().getNumContrat());
		ps.setLong(5, nvReservation.getClient().getCodeClient());
		ps.setDate(6, java.sql.Date.valueOf(nvReservation.getDateReservation()));
		ps.setDate(7, java.sql.Date.valueOf(nvReservation.getDateDepart()));
		ps.setDate(8, java.sql.Date.valueOf(nvReservation.getDateRetour()));
		int rs = ps.executeUpdate();

		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			nvReservation.setCodeReservation(result.getLong(1));
		}
		
		return nvReservation;
	}

/*	// edit Reservation
	public void editReservation(Reservation nvReservation) throws SQLException {
		
		
		PreparedStatement ps = connection.prepareStatement(
				"update Reservation set nom=?, prenom=?,	adresse=?,imageScanneeDPermis=?	,tel=?,age=? where codeReservation=?");
		ps.setString(1, nvReservation.getNom());
		ps.setString(2, nvReservation.getPrenom());
		ps.setString(3, nvReservation.getAdresse());
		ps.setString(4, nvReservation.getImageScanneeDPermis());
		ps.setString(5, nvReservation.getTel());
		ps.setInt(6, nvReservation.getAge());
		ps.setLong(7, nvReservation.getCodeReservation());

		int rs = ps.executeUpdate();

	}
*/
	// delete Reservation
	public void deleteReservation(Reservation nvReservation) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Reservation where codeReservation=?");
		ps.setLong(1, nvReservation.getCodeReservation());

		int rs = ps.executeUpdate();

	}
}
