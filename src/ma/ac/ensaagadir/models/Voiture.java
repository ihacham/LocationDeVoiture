package ma.ac.ensaagadir.models;

import java.util.Date;

public class Voiture {
	
	private long numImmatriculation ;
	private Parking  parking  ;
	private String  marque ;
	private String  type ;
	private String  carburant ;
	private double compteurDeKM ;
	private Date  dateDeMiseEnCirculation ;
	
	
	
	public Voiture() {
		
		// TODO Auto-generated constructor stub
	}


	public Voiture(long numImmatriculation, String marque, String type, String carburant,
			double compteurDeKM, Date dateDeMiseEnCirculation) {
		
		this.numImmatriculation = numImmatriculation;
		this.marque = marque;
		this.type = type;
		this.carburant = carburant;
		this.compteurDeKM = compteurDeKM;
		this.dateDeMiseEnCirculation = dateDeMiseEnCirculation;
	}
	
	
	public long getNumImmatriculation() {
		return numImmatriculation;
	}
	public void setNumImmatriculation(long numImmatriculation) {
		this.numImmatriculation = numImmatriculation;
	}
	public Parking getParking() {
		return parking;
	}
	public void setNumParking(Parking parking) {
		this.parking = parking;
	}
	public String getMarque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = marque;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCarburant() {
		return carburant;
	}
	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}
	public double getCompteurDeKM() {
		return compteurDeKM;
	}
	public void setCompteurDeKM(double compteurDeKM) {
		this.compteurDeKM = compteurDeKM;
	}
	public Date getDateDeMiseEnCirculation() {
		return dateDeMiseEnCirculation;
	}
	public void setDateDeMiseEnCirculation(Date dateDeMiseEnCirculation) {
		this.dateDeMiseEnCirculation = dateDeMiseEnCirculation;
	}
	
	
}
