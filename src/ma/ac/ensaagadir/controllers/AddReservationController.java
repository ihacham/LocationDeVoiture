package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import ma.ac.ensaagadir.dao.ClientRepository;
import ma.ac.ensaagadir.dao.ReservationRepository;
import ma.ac.ensaagadir.dao.VoitureRepository;
import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Voiture;

public class AddReservationController {

    @FXML
    private Label title;
    
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
    
    ClientRepository clientsRepository;
    
    ReservationRepository reservationRepository;
    
    VoitureRepository voitureRepository;
    
    ArrayList<Client> clients;
    
    ArrayList<Voiture> voitures;
    
    
    @FXML
	private void initialize() {
    	clientsRepository = new ClientRepository();
    	reservationRepository = new ReservationRepository();
    	voitureRepository = new VoitureRepository();
    	comboClient.setPromptText("Sélectionner votre client");
    	comboVoiture.setPromptText("Sélectionner sa mmatriculation");
    	
    	clients = clientsRepository.getAllClients();
    	ArrayList<Long> idClientList= new ArrayList<>();
    	for(Client client : clients) {
    		idClientList.add(client.getCodeClient());
    	}
    	ObservableList<Long> clients= FXCollections.observableArrayList(idClientList);
    	comboClient.getItems().addAll(clients);   
    	 
       ArrayList<String> idVoitureList= new ArrayList<>();
       voitures = voitureRepository.getAllVoitures();
    	for(Voiture voiture : voitures) {
    		idVoitureList.add(voiture.getNumImmatriculation());
    	}
    	ObservableList<String> voitures= FXCollections.observableArrayList(idVoitureList);
    	comboVoiture.getItems().addAll(voitures); 
   
    	
    	disablePastDates(dateDepart);
    	disablePastDates(dateReservation);
    	disablePastDates(dateRetour);
    	
    	dateReservation.setValue(LocalDate.now());
    	dateReservation.setDisable(true);
    }
    
   private void disablePastDates(DatePicker date) {
    	date.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
    }

    @FXML
    private Button saveButton;

    @FXML
    void reset(ActionEvent event) {
    	comboClient.setValue(null);
    	comboVoiture.setValue(null);
    	dateDepart.setValue(null);
    	dateRetour.setValue(null);
    }

    @FXML
    void saveClient(ActionEvent event) {
    	try {
    		Reservation reservation= new Reservation();
    		
    		reservation.setClient(clients.stream().filter(c -> c.getCodeClient() == comboClient.getValue()).findFirst().get());
    		reservation.setVoiture(voitures.stream().filter(v -> v.getNumImmatriculation() == comboVoiture.getValue()).findFirst().get());
    		reservation.setDateReservation(dateReservation.getValue());
    		reservation.setDateDepart(dateDepart.getValue());
    		reservation.setDateRetour(dateRetour.getValue());
    		reservation.setEtat("Non Validée");
    		
    		ReservationController.getReservationObservableList().addAll(reservationRepository.addReservation(reservation));
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		
    }
    
    

}