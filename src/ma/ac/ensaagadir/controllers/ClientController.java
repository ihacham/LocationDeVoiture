package ma.ac.ensaagadir.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ClientRepository;
import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class ClientController {

	@FXML
	private Pane pane;

	@FXML
	private ImageView returnback;

	@FXML
	private TextField username;

	@FXML
	private Label searchClient;

	@FXML
	private TableView<Client> clientTable;

	@FXML
	private TableColumn<Client, Long> code;

	@FXML
	private TableColumn<Client, String> nom;

	@FXML
	private TableColumn<Client, String> prenom;

	@FXML
	private TableColumn<Client, String> adresse;

	@FXML
	private TableColumn<Client, String> permis;

	@FXML
	private TableColumn<Client, String> telephone;

	@FXML
	private TableColumn<Client, Integer> age;

	private ClientRepository clientRepository;

	private static ObservableList<Client> clientObservableList;

	private ApplicationSession session;

	@FXML
	private Button edit;

	@FXML
	private Button delete;
	
	@FXML
	private Label clearSearchLabel;

	 @FXML
	 void clearSearch() {
		 clearSearchLabel.setVisible(false);
		 searchClient.setVisible(true);
		 username.clear();
		 clientObservableList.clear();
		 clientObservableList.addAll(FXCollections.observableArrayList(clientRepository.getAllClients())); 
		 
	 }
	
	@FXML
	private void initialize() {
		clientRepository = new ClientRepository();
		clientObservableList = FXCollections.observableArrayList(clientRepository.getAllClients());

		code.setCellValueFactory(cellData -> cellData.getValue().codeClient().asObject());
		nom.setCellValueFactory(cellData -> cellData.getValue().nom());
		prenom.setCellValueFactory(cellData -> cellData.getValue().prenom());
		adresse.setCellValueFactory(cellData -> cellData.getValue().adresse());
		permis.setCellValueFactory(cellData -> cellData.getValue().imageScanneeDPermis());
		telephone.setCellValueFactory(cellData -> cellData.getValue().tel());
		age.setCellValueFactory(cellData -> cellData.getValue().age().asObject());

		clientTable.setItems(clientObservableList);

		
		session = ApplicationSessionSingleton.getInstance();

		edit.setDisable(true);
		delete.setDisable(true);
	}

	public static ObservableList<Client> getClientObservableList() {
		return clientObservableList;
	}

	@FXML
	public void searchClient() {
		clearSearchLabel.setVisible(true);
		searchClient.setVisible(false);
		ArrayList<Client> clients = clientRepository.getClientByName(username.getText().trim());
		
		if (clients.isEmpty()) {
			clientObservableList.clear();

		} else {
			
			clientObservableList.clear();
			clientObservableList.addAll(clients);
			
		}
	}

	@FXML
	void showAddClient(ActionEvent event) {
		try {
			Stage addClientStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/AddClient.fxml"));
			Scene addClientScene = new Scene(root, 550, 500);
			addClientStage.setScene(addClientScene);
			addClientStage.setTitle("Ajouter Nouveau Client");
			addClientStage.centerOnScreen();
			addClientStage.setAlwaysOnTop(true);
			addClientStage.setResizable(false);
			addClientStage.show();
		} catch (IOException e) {

		}

	}

	@FXML
	void tableClick(MouseEvent event) {
		Client client = clientTable.getSelectionModel().getSelectedItem();
		if(client != null) {
			session.setSelectedClient(client);
			edit.setDisable(false);
			delete.setDisable(false);
		}

	}

	@FXML
	void showEditClient(ActionEvent event) {
		try {
			Stage addClientStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/EditClient.fxml"));
			Scene addClientScene = new Scene(root, 550, 500);
			addClientStage.setScene(addClientScene);
			addClientStage.setTitle("Modifier Client");
			addClientStage.centerOnScreen();
			addClientStage.setAlwaysOnTop(true);
			addClientStage.setResizable(false);
			addClientStage.show();

		} catch (IOException e) {

		}

	}

	@FXML
	void showDeleteClient(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation De Suppression");
		alert.setHeaderText("Vous allez supprimer ce Client");
		alert.setContentText("êtes vous sûr ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				clientRepository.deleteClient(session.getSelectedClient());
				clientObservableList.remove(session.getSelectedClient());
			} catch (SQLException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Erreur Suppression de client");
				error.setHeaderText("La suppression a échoué");
				error.setContentText("Ooops, there was an error!");

				error.showAndWait();
			}
		} else {
			clicked();
		}
	}

	

	@FXML
	private void clicked() {
		clientTable.getSelectionModel().clearSelection();
		edit.setDisable(true);
		delete.setDisable(true);
		session.setSelectedClient(null);
	}

	@FXML
	void returnBack(MouseEvent event) {
		Node DashboardSceneGraph;
		try {
			DashboardSceneGraph = (Node) FXMLLoader.load(getClass().getResource("../views/Dashboard.fxml"));
			AnchorPane mainpane = (AnchorPane) pane.getParent();
			mainpane.getChildren().setAll(DashboardSceneGraph);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
