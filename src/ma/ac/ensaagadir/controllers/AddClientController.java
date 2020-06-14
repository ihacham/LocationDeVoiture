package ma.ac.ensaagadir.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ClientRepository;
import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class AddClientController implements Initializable {

	@FXML
	private TextField nom;

	@FXML
	private TextField prenom;

	@FXML
	private TextField adresse;

	@FXML
	private TextField telephone;

	@FXML
	private TextField age;
	
	@FXML
	private Label permisFilename;
	
	@FXML
	private Label title;
	
	@FXML
	private Button saveButton;

	private ClientRepository clientRepository;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientRepository = new ClientRepository();
	}
	
	
	
	
	@FXML
	void permisChooser(ActionEvent event) {
		
	}

	@FXML
	void saveClient(ActionEvent event) {
		try {
		Client nvClient= new Client();
		nvClient.setNom(nom.getText());
		nvClient.setPrenom(prenom.getText());
		nvClient.setAdresse(adresse.getText());
		nvClient.setTel(telephone.getText());
		nvClient.setAge(Integer.valueOf(age.getText()));
		nvClient.setImageScanneeDPermis(permisFilename.getText());
		//Client myClient = clientRepository.addClient(nvClient);
		ClientController.getClientObservableList().addAll(clientRepository.addClient(nvClient));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@FXML
	void reset(ActionEvent event) {
//		Stage window =(Stage) nom.getScene().getWindow();
//		window.close();
		nom.setText("");
		prenom.setText("");
		adresse.setText("");
		telephone.setText("");
		age.setText("");
		permisFilename.setText("");
		
		
	}

}