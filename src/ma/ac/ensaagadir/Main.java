package ma.ac.ensaagadir;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
			Scene scene = new Scene(root,1000,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());			
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
