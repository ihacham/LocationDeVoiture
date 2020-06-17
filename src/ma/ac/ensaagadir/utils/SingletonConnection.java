package ma.ac.ensaagadir.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
	
	  //URL de connexion
	  private String url = "jdbc:mysql://localhost:3308/locationdevoiture?characterEncoding=utf8";
	  //Nom du user
	  private String user = "root";
	  //Mot de passe de l'utilisateur
	  private String passwd = "";
	  //Objet Connection
	  private static Connection connect;
	  
	  // bloc d'initialisation statique : appel une seule fois
	  static {
		// Chargement du Driver de DataBase MySQL
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	  }
	  
	  //Constructeur privé
	  private SingletonConnection(){
	    try {
	      connect = DriverManager.getConnection(url, user, passwd);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	   
	  //Méthode qui va nous retourner notre instance et la créer si elle n'existe pas
	   public static Connection getInstance(){
	    if(connect == null){
	      new SingletonConnection();
	    }
	    return connect;   
	  }   
	
}
