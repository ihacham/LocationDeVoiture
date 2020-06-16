package ma.ac.ensaagadir.models;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Voiture {
	
	private StringProperty numImmatriculation ;
	private ObjectProperty<Parking>  parking  ;
	private StringProperty  marque ;
	private StringProperty  type ;
	private StringProperty  carburant ;
	private DoubleProperty compteurDeKM ;
	private ObjectProperty<LocalDate>  dateDeMiseEnCirculation ;
	private BooleanProperty disponibility;
	
	
	public Voiture() {
		
	}


	public Voiture(String numImmatriculation, String marque, String type, String carburant,
			double compteurDeKM, LocalDate dateDeMiseEnCirculation) {
		
		this.numImmatriculation = new SimpleStringProperty(numImmatriculation);
		this.marque = new SimpleStringProperty(marque);
		this.type = new SimpleStringProperty(type);
		this.carburant = new SimpleStringProperty(carburant);
		this.compteurDeKM = new SimpleDoubleProperty(compteurDeKM);
		this.dateDeMiseEnCirculation = new SimpleObjectProperty<>(dateDeMiseEnCirculation);
		this.disponibility = new SimpleBooleanProperty(true);
	}
	
	
	public String getNumImmatriculation() {
		return numImmatriculation.get();
	}
	public StringProperty NumImmatriculation() {
		return numImmatriculation;
	}
	public void setNumImmatriculation(String numImmatriculation) {
		this.numImmatriculation =  new SimpleStringProperty(numImmatriculation);
	}
	public Parking getParking() {
		return parking.get();
	}
	public  ObjectProperty<Parking> Parking() {
		return parking;
	}
	public void setNumParking(Parking parking) {
		this.parking = new SimpleObjectProperty<>(parking);
	}
	public String getMarque() {
		return marque.get();
	}
	public  StringProperty Marque() {
		return marque;
	}
	public void setMarque(String marque) {
		this.marque = new SimpleStringProperty(marque);
	}
	public String getType() {
		return type.get();
	}
	public  StringProperty Type() {
		return type;
	}
	public void setType(String type) {
		this.type = new SimpleStringProperty(type);
	}
	public String getCarburant() {
		return carburant.get();
	}
	public  StringProperty Carburant() {
		return carburant;
	}
	public void setCarburant(String carburant) {
		this.carburant = new SimpleStringProperty(carburant);
	}
	public double getCompteurDeKM() {
		return compteurDeKM.get();
	}
	public  DoubleProperty CompteurDeKM() {
		return compteurDeKM;
	}
	public void setCompteurDeKM(double compteurDeKM) {
		this.compteurDeKM = new SimpleDoubleProperty(compteurDeKM);
	}
	public LocalDate getDateDeMiseEnCirculation() {
		return dateDeMiseEnCirculation.get();
	}
	public  ObjectProperty<LocalDate> DateDeMiseEnCirculation() {
		return dateDeMiseEnCirculation;
	}
	public void setDateDeMiseEnCirculation(LocalDate dateDeMiseEnCirculation) {
		this.dateDeMiseEnCirculation = new SimpleObjectProperty<LocalDate>(dateDeMiseEnCirculation);
	}
	
	public Boolean getDisponibility() {
		return disponibility.getValue();
	}
	public BooleanProperty Disponibility() {
		return disponibility;
	}

	public void setDisponibility(Boolean disponibility) {
		this.disponibility = new SimpleBooleanProperty(disponibility);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voiture other = (Voiture) obj;
		if (numImmatriculation == null) {
			if (other.numImmatriculation != null)
				return false;
		} else if (!numImmatriculation.equals(other.numImmatriculation))
			return false;
		return true;
	}
	
	
}
