package ma.ac.ensaagadir.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ma.ac.ensaagadir.utils.SingletonConnection;

public class LoginController {
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Label incorrectedLogin;
	
	private Connection connection;
	
	
	@FXML
	private void initialize() {
		connection = SingletonConnection.getInstance();
	}
	
	public void connect() {
		int count = 0;
		String name, code, query;
		
		query = "select * from utilisateur where identifiant = ? and motDePasse = ?";
		
		name=username.getText();
		code=password.getText();
		
		incorrectedLogin.setText("");
		
		
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, code);
			
			ResultSet rs =ps.executeQuery();
			
			while (rs.next()) {
			    ++count;
			}

			if (count == 0) {
				incorrectedLogin.setText("Utilisateur ou mot de passe erronée");
			} else if (count == 1) {
				Stage myStage = (Stage) username.getScene().getWindow();
				try {
					Pane root = FXMLLoader.load(getClass().getResource("../views/Welcome.fxml"));
					Scene dashboardScene = new Scene(root,1200,670);
					dashboardScene.getStylesheets().add(getClass().getResource("../dashboard.css").toExternalForm());
					myStage.setScene(dashboardScene);
					Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
					myStage.setX((primScreenBounds.getWidth() - myStage.getWidth()) / 2);
					myStage.setY((primScreenBounds.getHeight() - myStage.getHeight()) / 2);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				incorrectedLogin.setText("Erreur de connexion.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
