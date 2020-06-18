package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.FactureRepository;
import ma.ac.ensaagadir.models.Facture;

public class AddFactureController {

    @FXML
    private Label title;

    @FXML
    private TextField montantAPaye;

    @FXML
    private DatePicker dateFacture;

    @FXML
    private CheckBox factureReglee;

    @FXML
    private Button saveButton;

    private FactureRepository factureRepository;
    
    @FXML
    private void initialize() {
    	factureRepository = new FactureRepository();
	}
    
    @FXML
    void reset(ActionEvent event) {
    	montantAPaye.setText("");
    	dateFacture.setValue(null);
    	factureReglee.setSelected(false);
    	
    }

    @FXML
    void saveFacture(ActionEvent event) {
    	Facture facture = new Facture();
    	facture.setMontantAPaye(Double.valueOf(montantAPaye.getText()));
    	facture.setIsPayed(factureReglee.isSelected());
    	facture.setDateDeFacture(dateFacture.getValue());
    	
    	try {
			FactureController.getFactureObservableList().addAll(factureRepository.addFacture(facture));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			((Stage)dateFacture.getScene().getWindow()).close();
		}
    }

}
