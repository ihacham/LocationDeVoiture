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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ClientRepository;
import ma.ac.ensaagadir.dao.ReservationRepository;
import ma.ac.ensaagadir.models.Client;
import ma.ac.ensaagadir.models.Reservation;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class ReservationController {

    @FXML
    private Pane pane;

    @FXML
    private TextField codeReservationInput;

    @FXML
    private TableView<Reservation> reservationTable;

    @FXML
    private TableColumn<Reservation, Long> code;

    @FXML
    private TableColumn<Reservation, String> nomClient;

    @FXML
    private TableColumn<Reservation, Long> matriculeVoiture;

    @FXML
    private TableColumn<Reservation, String> etat;

    @FXML
    private TableColumn<Reservation, LocalDate> dateReservation;

    @FXML
    private TableColumn<Reservation, LocalDate> dateDepart;

    @FXML
    private TableColumn<Reservation, LocalDate> dateRetour;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Label searchReservation;

    @FXML
    private Label clearSearchLabel;

    private ReservationRepository reservationRepository;

	private static ObservableList<Reservation> reservationObservableList;

	private ApplicationSession session;
	
	
	
	@FXML
	private void initialize() {
		reservationRepository = new ReservationRepository();
		reservationObservableList = FXCollections.observableArrayList(reservationRepository.getAllReservations());

		code.setCellValueFactory(cellData -> cellData.getValue().CodeReservation().asObject());
		nomClient.setCellValueFactory(cellData -> cellData.getValue().Client().get().nom());
		matriculeVoiture.setCellValueFactory(cellData -> cellData.getValue().Voiture().get().NumImmatriculation().asObject());
		etat.setCellValueFactory(cellData -> cellData.getValue().Etat());
		dateReservation.setCellValueFactory(cellData -> cellData.getValue().DateReservation());
		dateDepart.setCellValueFactory(cellData -> cellData.getValue().DateDepart());
		dateRetour.setCellValueFactory(cellData -> cellData.getValue().DateRetour());

		reservationTable.setItems(reservationObservableList);

		
		session = ApplicationSessionSingleton.getInstance();

		edit.setDisable(true);
		delete.setDisable(true);
	}
	
	public static ObservableList<Reservation> getReservationObservableList() {
		return reservationObservableList;
	}

	
    @FXML
    void clearSearch(MouseEvent event) {
    	 clearSearchLabel.setVisible(false);
		 searchReservation.setVisible(true);
		 codeReservationInput.clear();
		 System.out.println(reservationRepository.getAllReservations());
		 reservationObservableList.clear();
		 reservationObservableList.addAll(FXCollections.observableArrayList(reservationRepository.getAllReservations())); 
		 
    }

    @FXML
    void clicked() {
    	reservationTable.getSelectionModel().clearSelection();
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

    @FXML
    void searchReservation(KeyEvent event) {
    	clearSearchLabel.setVisible(true);
		searchReservation.setVisible(false);
		ArrayList<Reservation> reservations = reservationRepository.getReservationByCode(codeReservationInput.getText().trim());
		
		if (reservations.isEmpty()) {
			reservationObservableList.clear();

		} else {
			
			reservationObservableList.clear();
			reservationObservableList.addAll(reservations);
			
		}
    }

    @FXML
    void showAddReservation(ActionEvent event) {
    	try {
			Stage addReservationStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/AddReservation.fxml"));
			Scene addReservationScene = new Scene(root, 550, 500);
			addReservationStage.setScene(addReservationScene);
			addReservationStage.setTitle("Ajouter Nouveau Reservation");
			addReservationStage.centerOnScreen();
			addReservationStage.setAlwaysOnTop(true);
			addReservationStage.setResizable(false);
			addReservationStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
	void tableClick(MouseEvent event) {
	    Reservation reservation = reservationTable.getSelectionModel().getSelectedItem();
		session.setSelectedReservation(reservation);
		edit.setDisable(false);
		delete.setDisable(false);
    }
    @FXML
    void showDeleteReservation(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation De Suppression");
		alert.setHeaderText("Vous allez supprimer cette Reservation");
		alert.setContentText("êtes vous sûr ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				reservationRepository.deleteReservation(session.getSelectedReservation());
				reservationObservableList.remove(session.getSelectedReservation());
			} catch (SQLException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Erreur Suppression de reservation");
				error.setHeaderText("La suppression a échoué");
				error.setContentText("Ooops, there was an error!");

				error.showAndWait();
			}
		} else {
			clicked();
		}
    }


    
    @FXML
    void showEditReservation(ActionEvent event) {
    	try {
			Stage addReservationStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/EditReservation.fxml"));
			Scene addReservationScene = new Scene(root, 550, 500);
			addReservationStage.setScene(addReservationScene);
			addReservationStage.setTitle("Modifier Reservation");
			addReservationStage.centerOnScreen();
			addReservationStage.setAlwaysOnTop(true);
			addReservationStage.setResizable(false);
			addReservationStage.show();

		} catch (IOException e) {

		}
    }

}
