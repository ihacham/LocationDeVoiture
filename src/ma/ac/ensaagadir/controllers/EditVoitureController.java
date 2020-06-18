package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ParkingRepository;
import ma.ac.ensaagadir.dao.ReservationRepository;
import ma.ac.ensaagadir.dao.VoitureRepository;
import ma.ac.ensaagadir.models.Parking;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Voiture;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class EditVoitureController {

	@FXML
    private TextField immatriculation;

    @FXML
    private ComboBox<Long> parkingCombo;
    
    @FXML
    private ComboBox<String> carburantCombo;

    @FXML
    private TextField marque;

    @FXML
    private TextField type;

    @FXML
    private TextField kilometrage;

    @FXML
    private DatePicker dateMiseEnCirculation;

    @FXML
    private CheckBox disponiblite;
    

    private ParkingRepository parkingRepository; 
    private VoitureRepository voitureRepository;
    
    private ArrayList<Parking> parkings;
    private Voiture voiture;
    
    @FXML
    private void initialize() {
    	parkingRepository = new ParkingRepository();
        voitureRepository = new VoitureRepository();
        
        voiture = ApplicationSessionSingleton.getInstance().getSelectedVoiture();
        System.out.println(voiture.getNumImmatriculation());
        parkings = parkingRepository.getAllParkings();
    	ArrayList<Long> idParkingList= new ArrayList<>();
    	for(Parking parking : parkings) {
    		idParkingList.add(parking.getNumParking());
    	}
    	ObservableList<Long> parkings= FXCollections.observableArrayList(idParkingList);
    	parkingCombo.getItems().addAll(parkings);
    	
    	carburantCombo.setItems(FXCollections.observableArrayList("Diesel", "Essence", "Carburant gazeux"));
        
        
        immatriculation.setText(voiture.getNumImmatriculation());
        immatriculation.setDisable(true);
        parkingCombo.setValue(voiture.getParking().getNumParking());
        carburantCombo.setValue(voiture.getCarburant());
        marque.setText(voiture.getMarque());
        type.setText(voiture.getType());
        kilometrage.setText(String.valueOf(voiture.getCompteurDeKM()));
        dateMiseEnCirculation.setValue(voiture.getDateDeMiseEnCirculation());
        disponiblite.setSelected(voiture.getDisponibility());
	}
    
    
    @FXML
    void saveVoiture(ActionEvent event) {
    	try {
    		
    		voiture.setNumParking(parkings.stream().filter(p -> p.getNumParking() == parkingCombo.getValue()).findFirst().get());
    		voiture.setCarburant(carburantCombo.getValue());
    		voiture.setType(type.getText());
    		voiture.setCompteurDeKM(Double.valueOf(kilometrage.getText()));
    		voiture.setDisponibility(disponiblite.isSelected());
    		voiture.setMarque(marque.getText());
    		voiture.setNumImmatriculation(immatriculation.getText());
    		voiture.setDateDeMiseEnCirculation(dateMiseEnCirculation.getValue());
    		
    		
    		voitureRepository.editVoiture(voiture);
    		int index = VoitureController.getVoitureObservableList().indexOf(voiture);
    		VoitureController.getVoitureObservableList().set(index, voiture);
    		((Stage)type.getScene().getWindow()).close();
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    }

}
