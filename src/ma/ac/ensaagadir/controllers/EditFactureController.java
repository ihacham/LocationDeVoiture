package ma.ac.ensaagadir.controllers;

import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ContratRepository;
import ma.ac.ensaagadir.dao.FactureRepository;
import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class EditFactureController {

    @FXML
    private Label title;

    @FXML
    private TextField montantAPaye;
    
    @FXML
    private ComboBox<Long> comboContrat;

    @FXML
    private DatePicker dateFacture;

    @FXML
    private CheckBox factureReglee;

    @FXML
    private Button saveButton;

    private FactureRepository factureRepository;
    private ContratRepository contratRepository;
    
    private Facture facture;
    
    private ArrayList<Contrat> contrats;
    
    
    @FXML
    private void initialize() {
    	factureRepository = new FactureRepository();
    	contratRepository = new ContratRepository();
    	facture = ApplicationSessionSingleton.getInstance().getSelectedFacture();
    	
    	contrats = contratRepository.getAllContrats();
    	ArrayList<Long> numContrats =  (ArrayList<Long>) contrats.stream().map(c -> c.getNumContrat()).collect(Collectors.toList());
    	comboContrat.setItems(FXCollections.observableList(numContrats));
		
		
    	montantAPaye.setText(String.valueOf(facture.getMontantAPaye()));
    	factureReglee.setSelected(facture.getIsPayed());
    	dateFacture.setValue(facture.getDateDeFacture());
	}
    
  

    @FXML
    void saveFacture(ActionEvent event) {
    	
    	facture.setMontantAPaye(Double.valueOf(montantAPaye.getText()));
    	facture.setIsPayed(factureReglee.isSelected());
    	facture.setDateDeFacture(dateFacture.getValue());
    	facture.setContrat(contrats.stream().filter(c -> c.getNumContrat() == comboContrat.getValue()).findFirst().orElse(new Contrat()));
    	
    	try {
    		factureRepository.editFacture(facture);
    		int index = FactureController.getFactureObservableList().indexOf(facture);
    		FactureController.getFactureObservableList().set(index, facture);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			((Stage)dateFacture.getScene().getWindow()).close();
		}
    }

}
