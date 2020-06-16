package ma.ac.ensaagadir.models;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Parking {
	private LongProperty  numParking;
	private ObjectProperty<ArrayList<Voiture>> voitures;
	private LongProperty  capacite;
	private StringProperty  rue;
	private StringProperty  arrondissement;
	
	
	
	public Parking() {
		
	}
	
	public Parking(long capacite, String rue, String arrondissement) {
		this.capacite = new SimpleLongProperty(capacite);
		this.rue = new SimpleStringProperty(rue);
		this.arrondissement = new SimpleStringProperty(arrondissement);
	}
	public Long getNumParking() {
		return numParking.get();
	}
	public  LongProperty NumParking() {
		return numParking;
	}
	public void setNumParking(Long numParking) {
		this.numParking = new SimpleLongProperty(numParking);
	}
	public ArrayList<Voiture> getVoitures() {
		return voitures.get();
	}
	public  ObjectProperty<ArrayList<Voiture>> Voitures() {
		return voitures;
	}
	public void setVoitures(ArrayList<Voiture> voitures) {
		this.voitures = new SimpleObjectProperty<>(voitures);
	}
	public long getCapacite() {
		return capacite.get();
	}
	public  LongProperty Capacite() {
		return capacite;
	}
	public void setCapacite(long capacite) {
		this.capacite = new SimpleLongProperty(capacite);
	}
	public String getRue() {
		return rue.get();
	}
	public  StringProperty Rue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = new SimpleStringProperty(rue);
	}
	public String getArrondissement() {
		return arrondissement.get();
	}
	public  StringProperty Arrondissement() {
		return arrondissement;
	}
	public void setArrondissement(String arrondissement) {
		this.arrondissement = new SimpleStringProperty(arrondissement);
	}

	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parking other = (Parking) obj;
		if (numParking == null) {
			if (other.numParking != null)
				return false;
		} else if (!numParking.equals(other.numParking))
			return false;
		return true;
	}
	
	
	
}
