package ma.ac.ensaagadir.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddVoitureController {

    @FXML
    private Label title;

    @FXML
    private TextField immatriculation;

    @FXML
    private ComboBox<Long> parkingCombo;

    @FXML
    private TextField marque;

    @FXML
    private TextField type;

    @FXML
    private TextField carburant;

    @FXML
    private TextField kilometrage;

    @FXML
    private DatePicker dateMiseEnCirculation;

    @FXML
    private CheckBox disponiblite;

    @FXML
    private Button saveButton;

    @FXML
    private void initialize() {
		

	}
    
    @FXML
    void reset(ActionEvent event) {
    	
    }

    @FXML
    void saveVoiture(ActionEvent event) {

    }

}
