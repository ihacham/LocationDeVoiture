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
import ma.ac.ensaagadir.dao.ContratRepository;
import ma.ac.ensaagadir.dao.ContratRepository;
import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.models.Contrat;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class ContratController {

    @FXML
    private Pane pane;

    @FXML
    private TextField codeContratInput;

    @FXML
    private TableView<Contrat> contratTable;

    @FXML
    private TableColumn<Contrat, Long> numeroContrat;

    @FXML
    private TableColumn<Contrat, Long> codeReservation;

    @FXML
    private TableColumn<Contrat, String> numeroFacture;

    @FXML
    private TableColumn<Contrat, LocalDate> dateContrat;

    @FXML
    private TableColumn<Contrat, LocalDate> dateEcheance;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private Label searchContrat;

    @FXML
    private Label clearSearchLabel;
    
    private ContratRepository contratRepository;
    
    private static ObservableList<Contrat> contratObservableList;

	private ApplicationSession session;
    

	
	@FXML
	private void initialize() {
		contratRepository = new ContratRepository();
		contratObservableList = FXCollections.observableArrayList(contratRepository.getAllContrats());

		
		
		numeroContrat.setCellValueFactory(cellData -> cellData.getValue().NumContrat().asObject());
		codeReservation.setCellValueFactory(cellData -> cellData.getValue().Reservation().get().CodeReservation().asObject());
		numeroFacture.setCellValueFactory(cellData -> cellData.getValue().Facture().get().NumFacture());
		dateContrat.setCellValueFactory(cellData -> cellData.getValue().DateContrat());
		dateEcheance.setCellValueFactory(cellData -> cellData.getValue().DateEcheance());
		

		contratTable.setItems(contratObservableList);

		
		session = ApplicationSessionSingleton.getInstance();

		edit.setDisable(true);
		delete.setDisable(true);
	}

	public static ObservableList<Contrat> getContratObservableList() {
		return contratObservableList;
	}
	
	
	
    @FXML
    void clearSearch(MouseEvent event) {
    	 clearSearchLabel.setVisible(false);
		 searchContrat.setVisible(true);
		 codeContratInput.clear();
		 contratObservableList.clear();
		 contratObservableList.addAll(FXCollections.observableArrayList(contratRepository.getAllContrats())); 
    }

    @FXML
	public void searchContrat() {
		clearSearchLabel.setVisible(true);
		searchContrat.setVisible(false);
		ArrayList<Contrat> contrats = contratRepository.getContratByNum(codeContratInput.getText().trim());
		
		if (contrats.isEmpty()) {
			contratObservableList.clear();

		} else {
			
			contratObservableList.clear();
			contratObservableList.addAll(contrats);
			
		}
	}

	@FXML
	void showAddContrat(ActionEvent event) {
		try {
			Stage addContratStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/AddContrat.fxml"));
			Scene addContratScene = new Scene(root, 550, 500);
			addContratStage.setScene(addContratScene);
			addContratStage.setTitle("Ajouter Nouveau Contrat");
			addContratStage.centerOnScreen();
			addContratStage.setAlwaysOnTop(true);
			addContratStage.setResizable(false);
			addContratStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void tableClick(MouseEvent event) {
		Contrat contrat = contratTable.getSelectionModel().getSelectedItem();
		if(contrat != null) {
			session.setSelectedContrat(contrat);
			edit.setDisable(false);
			delete.setDisable(false);
		}

	}

	@FXML
	void showEditContrat(ActionEvent event) {
		try {
			Stage addContratStage = new Stage();
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/EditContrat.fxml"));
			Scene addContratScene = new Scene(root, 550, 500);
			addContratStage.setScene(addContratScene);
			addContratStage.setTitle("Modifier Contrat");
			addContratStage.centerOnScreen();
			addContratStage.setAlwaysOnTop(true);
			addContratStage.setResizable(false);
			addContratStage.show();

		} catch (IOException e) {

		}

	}

	@FXML
	void showDeleteContrat(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation De Suppression");
		alert.setHeaderText("Vous allez supprimer cette Contrat");
		alert.setContentText("êtes vous sûr ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				contratRepository.deleteContrat(session.getSelectedContrat());
				contratObservableList.remove(session.getSelectedContrat());
			} catch (SQLException e) {
				Alert error = new Alert(AlertType.ERROR);
				error.setTitle("Erreur Suppression de contrat");
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
		contratTable.getSelectionModel().clearSelection();
		edit.setDisable(true);
		delete.setDisable(true);
		session.setSelectedContrat(null);
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
