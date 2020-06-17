package ma.ac.ensaagadir.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.VoitureRepository;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.models.Voiture;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class VoitureController {

	@FXML
	private Pane pane;

	@FXML
	private TextField codeVoitureInput;

	@FXML
	private TableView<Voiture> voitureTable;

	@FXML
	private TableColumn<Voiture, String> immatriculation;

	@FXML
	private TableColumn<Voiture, String> marque;

	@FXML
	private TableColumn<Voiture, String> type;

	@FXML
	private TableColumn<Voiture, String> carburant;

	@FXML
	private TableColumn<Voiture, Long> numParking;

	@FXML
	private TableColumn<Voiture, Double> kilometrage;

	@FXML
	private TableColumn<Voiture, LocalDate> dateDeMiseEnCirculation;

	@FXML
	private TableColumn<Voiture, Boolean> disponibilite;

	@FXML
	private Button edit;

	@FXML
	private Button delete;

	@FXML
	private Label searchVoiture;

	@FXML
	private Label clearSearchLabel;

	private VoitureRepository voitureRepository;

	private static ObservableList<Voiture> voitureObservableList;

	private ApplicationSession session;

	@FXML
	private void initialize() {
		voitureRepository = new VoitureRepository();

		voitureObservableList = FXCollections.observableArrayList(voitureRepository.getAllVoitures());

		immatriculation.setCellValueFactory(cellData -> cellData.getValue().NumImmatriculation());
		marque.setCellValueFactory(cellData -> cellData.getValue().Marque());
		type.setCellValueFactory(cellData -> cellData.getValue().Type());
		carburant.setCellValueFactory(cellData -> cellData.getValue().Carburant());
		numParking.setCellValueFactory(cellData -> cellData.getValue().Parking().get().NumParking().asObject());
		kilometrage.setCellValueFactory(cellData -> cellData.getValue().CompteurDeKM().asObject());
		dateDeMiseEnCirculation.setCellValueFactory(cellData -> cellData.getValue().DateDeMiseEnCirculation());
		disponibilite.setCellValueFactory(cellData -> cellData.getValue().Disponibility());

		voitureTable.setItems(voitureObservableList);

		session = ApplicationSessionSingleton.getInstance();
		
		edit.setDisable(true);
		delete.setDisable(true);
	}

	public static ObservableList<Voiture> getVoitureObservableList() {
		return voitureObservableList;
	}

	@FXML
	void clearSearch(MouseEvent event) {
		clearSearchLabel.setVisible(false);
		searchVoiture.setVisible(true);
		codeVoitureInput.clear();
		System.out.println(voitureRepository.getAllVoitures());
		voitureObservableList.clear();
		voitureObservableList.addAll(FXCollections.observableArrayList(voitureRepository.getAllVoitures()));
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

	@FXML
	void searchVoiture(KeyEvent event) {
		clearSearchLabel.setVisible(true);
		searchVoiture.setVisible(false);
		ArrayList<Voiture> voitures = voitureRepository.getVoitureByMarque(codeVoitureInput.getText().trim());

		if (voitures.isEmpty()) {
			voitureObservableList.clear();

		} else {

			voitureObservableList.clear();
			voitureObservableList.addAll(voitures);

		}
	}

	@FXML
	void showAddVoiture(ActionEvent event) {
		try {
			Stage addVoitureStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/AddVoiture.fxml"));
			Scene addVoitureScene = new Scene(root, 550, 550);
			addVoitureStage.setScene(addVoitureScene);
			addVoitureStage.setTitle("Ajouter Nouveau Voiture");
			addVoitureStage.centerOnScreen();
			addVoitureStage.setAlwaysOnTop(true);
			addVoitureStage.setResizable(false);
			addVoitureStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void showDeleteVoiture(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation De Suppression");
		alert.setHeaderText("Vous allez supprimer cette Voiture");
		alert.setContentText("êtes vous sûr ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				voitureRepository.deleteVoiture(session.getSelectedVoiture());
				voitureObservableList.remove(session.getSelectedReservation());
			} catch (SQLException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Erreur Suppression de Voiture");
				error.setHeaderText("La suppression a échoué");
				error.setContentText("Ooops, there was an error!");

				error.showAndWait();
			}
		} else {
			clicked();
		}
	}

	@FXML
	void clicked() {
		voitureTable.getSelectionModel().clearSelection();
		edit.setDisable(true);
		delete.setDisable(true);
		session.setSelectedVoiture(null);
	}

	@FXML
	void showEditVoiture(ActionEvent event) {
		try {
			Stage editVoitureStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/EditVoiture.fxml"));
			Scene editVoitureScene = new Scene(root, 550, 550);
			editVoitureStage.setScene(editVoitureScene);
			editVoitureStage.setTitle("Modifier Voiture");
			editVoitureStage.centerOnScreen();
			editVoitureStage.setAlwaysOnTop(true);
			editVoitureStage.setResizable(false);
			editVoitureStage.show();

		} catch (IOException e) {

		}
	}

	@FXML
	void tableClick(MouseEvent event) {
		Voiture voiture = voitureTable.getSelectionModel().getSelectedItem();
	    if(voiture != null) {
	    	session.setSelectedVoiture(voiture);
			edit.setDisable(false);
			delete.setDisable(false);
	    }
	}

}
