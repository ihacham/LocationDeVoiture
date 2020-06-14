package ma.ac.ensaagadir.models;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Client {

	private LongProperty codeClient;           
	private StringProperty  nom;                
	private StringProperty  prenom;            
	private StringProperty  adresse;                      
	private StringProperty  imageScanneeDPermis;
	private StringProperty  tel;        
	private IntegerProperty  age;
	private ArrayList<Reservation> reservations;
	
	
	public Client() {
	
	}

	

	public Client(Long codeClient, String nom, String prenom, String adresse,
			String imageScanneeDPermis, String tel, Integer age) {
		this.codeClient = new SimpleLongProperty(codeClient);
		this.nom = new SimpleStringProperty(nom);
		this.prenom =  new SimpleStringProperty(prenom);
		this.adresse =  new SimpleStringProperty(adresse);
		this.imageScanneeDPermis =  new SimpleStringProperty(imageScanneeDPermis);
		this.tel =  new SimpleStringProperty(tel);
		this.age =  new SimpleIntegerProperty(age);
	}



	public Long getCodeClient() {
		return codeClient.get();
	}


	public void setCodeClient(Long codeClient) {
		this.codeClient = new SimpleLongProperty(codeClient);
	}

	public LongProperty codeClient() {
		return codeClient;
	}
	

	public String getNom() {
		return nom.get();
	}


	public void setNom(String nom) {
		this.nom = new SimpleStringProperty(nom);
	}


	public StringProperty nom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom.get();
	}


	public void setPrenom(String prenom) {
		this.prenom =new SimpleStringProperty(prenom);
	}

	public StringProperty prenom() {
		return prenom;
	}
	
	public String getAdresse() {
		return adresse.get();
	}


	public void setAdresse(String adresse) {
		this.adresse =new SimpleStringProperty(adresse);
	}

	public StringProperty adresse() {
		return adresse;
	}
	
	public String getImageScanneeDPermis() {
		return imageScanneeDPermis.get();
	}


	public void setImageScanneeDPermis(String imageScanneeDPermis) {
		this.imageScanneeDPermis = new SimpleStringProperty(imageScanneeDPermis);
	}

	public StringProperty imageScanneeDPermis() {
		return imageScanneeDPermis;
	}
	
	public String getTel() {
		return tel.get();
	}


	public void setTel(String tel) {
		this.tel = new SimpleStringProperty(tel);
	}

	public StringProperty tel() {
		return tel;
	}
	
	public Integer getAge() {
		return age.get();
	}


	public void setAge(Integer age) {
		this.age = new SimpleIntegerProperty(age);
	}

	public IntegerProperty age() {
		return age;
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (codeClient == null) {
			if (other.codeClient != null)
				return false;
		} else if (!codeClient.equals(other.codeClient))
			return false;
		return true;
	}

	
	
	
	
}
