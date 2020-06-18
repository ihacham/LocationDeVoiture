package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ReservationRepository;
import ma.ac.ensaagadir.dao.VoitureRepository;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Voiture;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class EditReservationController {

	@FXML
	private Label title;

	@FXML
	private Button saveButton;

	@FXML
	private ComboBox<Long> comboClient;

	@FXML
	private ComboBox<String> comboVoiture;

	@FXML
	private DatePicker dateReservation;

	@FXML
	private DatePicker dateDepart;

	@FXML
	private DatePicker dateRetour;

	@FXML
	private ComboBox<String> etat;

	private ReservationRepository reservationRepository;
	private VoitureRepository voitureRepository;
	ArrayList<Voiture> voitures;
	private Reservation reservation;

	@FXML
	private void initialize() {
		reservationRepository = new ReservationRepository();
		voitureRepository = new VoitureRepository();
		voitures = voitureRepository.getAllVoitures();
		comboVoiture.setItems(FXCollections.observableArrayList(
				voitures.stream().map(v -> v.getNumImmatriculation()).collect(Collectors.toList())));
		etat.setItems(FXCollections.observableArrayList("Validée", "Non Validée", "Annulée"));

		reservation = ApplicationSessionSingleton.getInstance().getSelectedReservation();

		comboClient.setValue(reservation.getClient().getCodeClient());
		comboClient.setDisable(true);
		comboVoiture.setValue(reservation.getVoiture().getNumImmatriculation());
		dateReservation.setValue(reservation.getDateReservation());
		dateDepart.setValue(reservation.getDateDepart());
		dateRetour.setValue(reservation.getDateRetour());
		etat.setValue(reservation.getEtat());
	}

	@FXML
	void saveClient(ActionEvent event) {
		try {

			reservation.setVoiture(voitures.stream().filter(v -> v.getNumImmatriculation().equals(comboVoiture.getValue()))
					.findFirst().get());
			reservation.setDateReservation(dateReservation.getValue());
			reservation.setDateDepart(dateDepart.getValue());
			reservation.setDateRetour(dateRetour.getValue());
			reservation.setEtat(etat.getValue());

			reservationRepository.editReservation(reservation);
			int index = ReservationController.getReservationObservableList().indexOf(reservation);
			ReservationController.getReservationObservableList().set(index, reservation);
			((Stage) etat.getScene().getWindow()).close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
