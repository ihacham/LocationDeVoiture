package ma.ac.ensaagadir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Voiture;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class ContratRepository {
	private Connection connection;

	public ContratRepository() {
		connection = SingletonConnection.getInstance();
	}

	// search Contrat by rue
	public ArrayList<Contrat> getContratByNum(String numContrat) {
		ArrayList<Contrat> Contrats = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement("select * from Contrat where numContrat like ?");
			ps.setString(1, "%" + numContrat + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numContrat	codeReservation	numFacture	dateContrat	dateEcheance

				Contrat contrat = new Contrat();
				contrat.setNumContrat(rs.getLong(1));

				contrat.setDateContrat(rs.getDate(4).toLocalDate());
				contrat.setDateEcheance(rs.getDate(5).toLocalDate());
				contrat.setIsSigned(rs.getBoolean(6));
				
				ps = connection.prepareStatement("select * from facture where numContrat = ?");
				ps.setLong(1, contrat.getNumContrat());

				ResultSet result = ps.executeQuery();

				while (result.next()) {					
					Facture facture = new Facture();
					facture.setNumFacture(result.getString(1));
					facture.getContrat().setNumContrat(result.getLong(2));
					facture.setDateDeFacture(result.getDate(3).toLocalDate());
					facture.setMontantAPaye(result.getDouble(4));
					
					contrat.setFacture(facture);
				}
				
				ps = connection.prepareStatement("select * from reservation where numContrat = ?");
				ps.setLong(1, contrat.getNumContrat());


				ResultSet result2 = ps.executeQuery();

				while (result2.next()) {					
					Reservation Reservation = new Reservation();
					Reservation.setCodeReservation(result2.getLong(1));
					Reservation.setEtat(result2.getString(2));
					Reservation.getVoiture().setNumImmatriculation(result2.getString(3));
					Reservation.getRestitution().setCodeRestitution(result2.getLong(4));
					Reservation.getContrat().setNumContrat(result2.getLong(5));
					Reservation.getClient().setCodeClient(result2.getLong(6));
					Reservation.setDateReservation(result2.getDate(7).toLocalDate());
					Reservation.setDateDepart(result2.getDate(8).toLocalDate());
					Reservation.setDateRetour(result2.getDate(9).toLocalDate());
					
					contrat.setReservation(Reservation);
				}
				
				
				

				Contrats.add(contrat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Contrats;
	}

	// retrieve all Contrats
	public ArrayList<Contrat> getAllContrats() {
		ArrayList<Contrat> Contrats = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement("select * from Contrat");
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				// numContrat	codeReservation	numFacture	dateContrat	dateEcheance

				Contrat contrat = new Contrat();
				contrat.setNumContrat(rs.getLong(1));

				contrat.setDateContrat(rs.getDate(4).toLocalDate());
				contrat.setDateEcheance(rs.getDate(5).toLocalDate());
				contrat.setIsSigned(rs.getBoolean(6));
				
				ps = connection.prepareStatement("select * from facture where numContrat = ?");
				ps.setLong(1, contrat.getNumContrat());

				ResultSet result = ps.executeQuery();

				while (result.next()) {					
					Facture facture = new Facture();
					facture.setNumFacture(result.getString(1));
					facture.getContrat().setNumContrat(result.getLong(2));
					facture.setDateDeFacture(result.getDate(3).toLocalDate());
					facture.setMontantAPaye(result.getDouble(4));
					
					contrat.setFacture(facture);
				}
				
				ps = connection.prepareStatement("select * from reservation where numContrat = ?");
				ps.setLong(1, contrat.getNumContrat());


				ResultSet result2 = ps.executeQuery();

				while (result2.next()) {					
					Reservation Reservation = new Reservation();
					Reservation.setCodeReservation(result2.getLong(1));
					Reservation.setEtat(result2.getString(2));
					Reservation.getVoiture().setNumImmatriculation(result2.getString(3));
					Reservation.getRestitution().setCodeRestitution(result2.getLong(4));
					Reservation.getContrat().setNumContrat(result2.getLong(5));
					Reservation.getClient().setCodeClient(result2.getLong(6));
					Reservation.setDateReservation(result2.getDate(7).toLocalDate());
					Reservation.setDateDepart(result2.getDate(8).toLocalDate());
					Reservation.setDateRetour(result2.getDate(9).toLocalDate());
					
					contrat.setReservation(Reservation);
				}

				Contrats.add(contrat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Contrats;
	}

	// add Contrat
	public Contrat addContrat(Contrat nvContrat) throws SQLException {

		PreparedStatement ps = connection
				.prepareStatement("insert into Contrat(codeReservation,	numFacture,	dateContrat, dateEcheance, isSigned) values (?,?,?,?,?)");
		ps.setLong(1, nvContrat.getReservation().getCodeReservation());
		ps.setString(2, nvContrat.getFacture().getNumFacture());
		ps.setDate(3, java.sql.Date.valueOf(nvContrat.getDateContrat()));
		ps.setDate(4, java.sql.Date.valueOf(nvContrat.getDateEcheance()));
		ps.setBoolean(5,nvContrat.getIsSigned());
		int rs = ps.executeUpdate();

		ps = connection.prepareStatement("SELECT LAST_INSERT_ID()");
		ResultSet result = ps.executeQuery();

		while (result.next()) {
			nvContrat.setNumContrat(result.getLong(1));
		}

		return nvContrat;
	}

	// edit Contrat
	public void editContrat(Contrat nvContrat) throws SQLException {

		PreparedStatement ps = connection
				.prepareStatement("update Contrat set codeReservation=?,	numFacture=?,	dateContrat=?, dateEcheance=?, isSigned=? where numContrat=?");
		ps.setLong(1, nvContrat.getReservation().getCodeReservation());
		ps.setString(2, nvContrat.getFacture().getNumFacture());
		ps.setDate(3, java.sql.Date.valueOf(nvContrat.getDateContrat()));
		ps.setDate(4, java.sql.Date.valueOf(nvContrat.getDateEcheance()));
		ps.setBoolean(5,nvContrat.getIsSigned());
		
		ps.setLong(6, nvContrat.getNumContrat());
		int rs = ps.executeUpdate();

	}

	// delete Contrat
	public void deleteContrat(Contrat nvContrat) throws SQLException {

		PreparedStatement ps = connection.prepareStatement("delete from Contrat where numContrat=?");
		ps.setLong(1, nvContrat.getNumContrat());

		int rs = ps.executeUpdate();

	}
}
