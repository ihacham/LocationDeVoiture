package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ContratRepository;
import ma.ac.ensaagadir.dao.FactureRepository;
import ma.ac.ensaagadir.dao.ReservationRepository;
import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.models.Reservation;

public class AddContratController {

	@FXML
	private Label title;

	@FXML
	private ComboBox<Long> comboReservation;

	@FXML
	private ComboBox<String> comboFacture;

	@FXML
	private DatePicker dateContrat;

	@FXML
	private DatePicker dateEcheance;

	@FXML
	private CheckBox contratSignee;

	@FXML
	private Button saveButton;
	
	private ContratRepository contratRepository;
	
	private ReservationRepository reservationRepository;
	
	private FactureRepository factureRepository;
	
	private ArrayList<Reservation> reservations;
	
	private ArrayList<Facture> factures;
	
	
	@FXML
	public void initialize() {
		factureRepository = new FactureRepository();
		reservationRepository = new ReservationRepository();
		contratRepository = new ContratRepository();
		
		factures = factureRepository.getAllFactures();
		ArrayList<String> numFactures = (ArrayList<String>) factures.stream().map(f -> f.getNumFacture()).collect(Collectors.toList());
		comboFacture.setItems(FXCollections.observableList(numFactures));
		
		
		reservations = reservationRepository.getAllReservations();
		ArrayList<Long> numReservations =  (ArrayList<Long>) reservations.stream().map(r -> r.getCodeReservation()).collect(Collectors.toList());
		comboReservation.setItems(FXCollections.observableList(numReservations));
		
		contratSignee.setSelected(true);
		dateContrat.setValue(LocalDate.now());
	}

	@FXML
	void reset(ActionEvent event) {
		comboReservation.setValue(null);
		comboFacture.setValue(null);
		dateEcheance.setValue(null);
		dateContrat.setValue(LocalDate.now());
		contratSignee.setSelected(true);
	}

	@FXML
	void saveContrat(ActionEvent event) {
		try {
    		Contrat contrat= new Contrat();
    		
    		contrat.setReservation(reservations.stream().filter(r -> r.getCodeReservation() == comboReservation.getValue()).findFirst().get());
    		contrat.setFacture(factures.stream().filter(f -> f.getNumFacture() == comboFacture.getValue()).findFirst().get());
    		contrat.setDateEcheance(dateEcheance.getValue());
    		contrat.setDateContrat(dateContrat.getValue());
    		contrat.setIsSigned(contratSignee.isSelected());
    		
    		
    		ContratController.getContratObservableList().addAll(contratRepository.addContrat(contrat));
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    		} finally {
    			((Stage)dateContrat.getScene().getWindow()).close();
    		}
    		
    }

}
