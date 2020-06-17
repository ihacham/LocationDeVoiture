package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ParkingRepository;
import ma.ac.ensaagadir.dao.VoitureRepository;
import ma.ac.ensaagadir.models.Parking;
import ma.ac.ensaagadir.models.Voiture;

public class AddVoitureController {

    @FXML
    private Label title;

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

    @FXML
    private Button saveButton;

    private VoitureRepository voitureRepository;
    
    private ParkingRepository parkingRepository;
    
    private ArrayList<Parking> parkings;
    
    @FXML
    private void initialize() {
    	voitureRepository = new VoitureRepository();
    	parkingRepository = new ParkingRepository();
    	carburantCombo.setItems(FXCollections.observableArrayList("Diesel", "Essence", "Carburant gazeux"));
    	disponiblite.setSelected(true);
    	disponiblite.setDisable(true);
    	parkings = parkingRepository.getAllParkings();
    	ArrayList<Long> idParkingList= new ArrayList<>();
    	for(Parking parking : parkings) {
    		idParkingList.add(parking.getNumParking());
    	}
    	ObservableList<Long> parkings= FXCollections.observableArrayList(idParkingList);
    	parkingCombo.getItems().addAll(parkings);
    	
    	dateMiseEnCirculation.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) > 0 );
            }
        });
	}
    
    @FXML
    void reset(ActionEvent event) {
    	carburantCombo.setValue(null);
    	kilometrage.setText("");
    	dateMiseEnCirculation.setValue(null);
    	disponiblite.setSelected(true);
    	immatriculation.setText("");
    	marque.setText("");
    	type.setText("");
    	parkingCombo.setValue(null);
    }

    @FXML
    void saveVoiture(ActionEvent event) {
    	try {
    		Voiture voiture= new Voiture();
    		
    		voiture.setNumParking(parkings.stream().filter(p -> p.getNumParking() == parkingCombo.getValue()).findFirst().get());
    		voiture.setCarburant(carburantCombo.getValue());
    		voiture.setType(type.getText());
    		voiture.setCompteurDeKM(Double.valueOf(kilometrage.getText()));
    		voiture.setDisponibility(disponiblite.isSelected());
    		voiture.setMarque(marque.getText());
    		voiture.setNumImmatriculation(immatriculation.getText());
    		voiture.setDateDeMiseEnCirculation(dateMiseEnCirculation.getValue());
    		
    		VoitureController.getVoitureObservableList().addAll(voitureRepository.addVoiture(voiture));
    		
    		} catch (SQLException e) {
    			e.printStackTrace();
    			
    		} finally {
    			((Stage)type.getScene().getWindow()).close();
    		}
    		
    }
    

}
