package ma.ac.ensaagadir.controllers;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ClientRepository;
import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class EditClientController implements Initializable {

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
	private Label permisFullPath;
	
	@FXML
	private Button saveButton;

	private ClientRepository clientRepository;
	
	private Client client;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clientRepository = new ClientRepository();
		client= ApplicationSessionSingleton.getInstance().getSelectedClient();
		System.out.println(client.getNom());
		nom.setText(client.getNom());
		prenom.setText(client.getPrenom());
		adresse.setText(client.getAdresse());
		telephone.setText(client.getTel());
		age.setText(String.valueOf(client.getAge()));
		permisFilename.setText(client.getImageScanneeDPermis());
	}
	
	
	
	
	@FXML
	void permisChooser(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir l'image du permis...");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png","*.jpeg","*.jpg"));
		File file = fileChooser.showOpenDialog(saveButton.getScene().getWindow());

		if ( file != null ) {
			try {
				Path filePath = Paths.get(ApplicationSessionSingleton.getInstance().getPermisImagesFolder(), file.getName());
				System.out.println("File stored in :" + filePath);
				Files.copy(file.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);
				permisFilename.setText(filePath.getFileName().toString());
				permisFullPath.setText(filePath.toString());
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void saveClient(ActionEvent event) {
		try {
			
		client.setNom(nom.getText());
		client.setPrenom(prenom.getText());
		client.setAdresse(adresse.getText());
		client.setTel(telephone.getText());
		client.setAge(Integer.valueOf(age.getText()));
		client.setImageScanneeDPermis(permisFullPath.getText());
		//Client myClient = clientRepository.addClient(nvClient);
		clientRepository.editClient(client);
		int index = ClientController.getClientObservableList().indexOf(client);
		ClientController.getClientObservableList().set(index, client);
		((Stage)nom.getScene().getWindow()).close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}


}