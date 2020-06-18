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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ma.ac.ensaagadir.dao.ContratRepository;
import ma.ac.ensaagadir.dao.FactureRepository;
import ma.ac.ensaagadir.models.Facture;
import ma.ac.ensaagadir.utils.ApplicationSession;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;

public class FactureController {

	@FXML
	private TextField codeFactureInput;

	@FXML
	private TableView<Facture> factureTable;

	@FXML
	private TableColumn<Facture, Long> numeroFacture;

	@FXML
	private TableColumn<Facture, Long> numeroContrat;

	@FXML
	private TableColumn<Facture, LocalDate> dateFacture;

	@FXML
	private TableColumn<Facture, Double> montantAPaye;

	@FXML
	private TableColumn<Facture, Boolean> etat;

	@FXML
	private Button edit;

	@FXML
	private Button delete;

	@FXML
	private Label searchFacture;

	@FXML
	private Label clearSearchLabel;

	private FactureRepository factureRepository;

	private ContratRepository contratRepository;

	private static ObservableList<Facture> factureObservableList;

	private ApplicationSession session;

	@FXML
	private void initialize() {
		factureRepository = new FactureRepository();
		factureObservableList = FXCollections.observableArrayList(factureRepository.getAllFactures());
		numeroFacture.setCellValueFactory(cellData -> cellData.getValue().NumFacture().asObject());
		numeroContrat.setCellValueFactory(cellData -> {
			if(cellData.getValue().Contrat() !=null && cellData.getValue().Contrat().get().NumContrat() != null)
				return cellData.getValue().Contrat().get().NumContrat().asObject();
			return null;
		});
		dateFacture.setCellValueFactory(cellData -> cellData.getValue().DateDeFacture());
		montantAPaye.setCellValueFactory(cellData -> cellData.getValue().MontantAPaye().asObject());
		etat.setCellValueFactory(cellData -> cellData.getValue().IsPayed());

		factureTable.setItems(factureObservableList);

		session = ApplicationSessionSingleton.getInstance();

		edit.setDisable(true);
		delete.setDisable(true);
	}

	public static ObservableList<Facture> getFactureObservableList() {
		return factureObservableList;
	}

	@FXML
	void clearSearch(MouseEvent event) {
		 clearSearchLabel.setVisible(false);
		 searchFacture.setVisible(true);
		 codeFactureInput.clear();
		 factureObservableList.clear();
		 factureObservableList.addAll(FXCollections.observableArrayList(factureRepository.getAllFactures())); 
	}

	@FXML
	void returnBack(MouseEvent event) {

	}

	 @FXML
		public void searchFacture() {
			clearSearchLabel.setVisible(true);
			searchFacture.setVisible(false);
			ArrayList<Facture> factures = null;
			if(!codeFactureInput.getText().trim().isEmpty())
				factures = factureRepository.getFactureByNum(Long.valueOf(codeFactureInput.getText().trim()));
			
			if (factures == null || factures.isEmpty()) {
				factureObservableList.clear();

			} else {
				
				factureObservableList.clear();
				factureObservableList.addAll(factures);
				
			}
		}

		@FXML
		void showAddFacture(ActionEvent event) {
			try {
				Stage addFactureStage = new Stage();
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/AddFacture.fxml"));
				Scene addFactureScene = new Scene(root, 550, 500);
				addFactureStage.setScene(addFactureScene);
				addFactureStage.setTitle("Ajouter Nouveau Facture");
				addFactureStage.centerOnScreen();
				addFactureStage.setAlwaysOnTop(true);
				addFactureStage.setResizable(false);
				addFactureStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@FXML
		void tableClick(MouseEvent event) {
			Facture facture = factureTable.getSelectionModel().getSelectedItem();
			if(facture != null) {
				session.setSelectedFacture(facture);
				edit.setDisable(false);
				delete.setDisable(false);
			}

		}

		@FXML
		void showEditFacture(ActionEvent event) {
			try {
				Stage addFactureStage = new Stage();
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("../views/EditFacture.fxml"));
				Scene addFactureScene = new Scene(root, 550, 500);
				addFactureStage.setScene(addFactureScene);
				addFactureStage.setTitle("Modifier Facture");
				addFactureStage.centerOnScreen();
				addFactureStage.setAlwaysOnTop(true);
				addFactureStage.setResizable(false);
				addFactureStage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		@FXML
		void showDeleteFacture(ActionEvent event) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation De Suppression");
			alert.setHeaderText("Vous allez supprimer cette Facture");
			alert.setContentText("êtes vous sûr ?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				try {
					factureRepository.deleteFacture(session.getSelectedFacture());
					factureObservableList.remove(session.getSelectedFacture());
				} catch (SQLException e) {
					Alert error = new Alert(AlertType.ERROR);
					error.setTitle("Erreur Suppression de facture");
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
			factureTable.getSelectionModel().clearSelection();
			edit.setDisable(true);
			delete.setDisable(true);
			session.setSelectedFacture(null);
		}

		
}
