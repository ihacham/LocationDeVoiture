package ma.ac.ensaagadir;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ma.ac.ensaagadir.utils.ApplicationSessionSingleton;


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
	
	@Override
	public void init() throws Exception {
		// Creating Application Folder
		String appFolder = ApplicationSessionSingleton.getInstance().getAppFolder();
		File appFolderFile = new File(appFolder);
		File permisFolderFile = new File(ApplicationSessionSingleton.getInstance().getPermisImagesFolder());
		if (!appFolderFile.exists()){
			appFolderFile.mkdir();
	    }
		
		if (!permisFolderFile.exists()){
			permisFolderFile.mkdir();
	        
	    }
				
			
		
	}
}
