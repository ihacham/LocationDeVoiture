package ma.ac.ensaagadir.controllers;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WelcomeController {

    @FXML
    private AnchorPane mainpane;

    @FXML
    private ImageView logoSopra;

    @FXML
    private Label kitlabel3;

    @FXML
    private Label idClient;

    @FXML
    private Label idReservation;

    @FXML
    private Label idContrat;

    @FXML
    private Label idFacture;

    @FXML
    private Label idVoiture;

    @FXML
    private Label idParking;

    @FXML
    private Label idSanction;

    @FXML
    private Label idSanction1;

    @FXML
    private Label idSanction11;

    @FXML
    private Label viewTitle;
    
    @FXML
    private ImageView logoutImg;

    @FXML
    private HBox controlPane;

    @FXML
    private ImageView minimize;

    @FXML
    private ImageView exit;

    @FXML
    void exit(MouseEvent event) {

    }

    @FXML
    void loadViewToMainPane(MouseEvent event) {
    	String vienName = ((Label)(event.getSource())).getText().replace("é", "e");
    	String viewPath = "../views/" + vienName + ".fxml";
    	viewTitle.setText("Gestion " + vienName);
    	try {
			Node root = (Node) FXMLLoader.load(getClass().getResource(viewPath));
			mainpane.getChildren().setAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void logout(MouseEvent event) throws IOException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation De Déconnexion");
		alert.setHeaderText("Vous allez se déconnecter");
		alert.setContentText("êtes vous sûr ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Stage stage = (Stage) mainpane.getScene().getWindow();
			Pane root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
			Scene scene = new Scene(root,1000,600);
			stage.setScene(scene);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());			
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
			
		}
    }

    @FXML
    void minimize(MouseEvent event) {

    }

    @FXML
    void moving(MouseEvent event) {

    }

    @FXML
    void premoving(MouseEvent event) {

    }

}
