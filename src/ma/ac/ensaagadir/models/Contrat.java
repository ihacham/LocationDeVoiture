package ma.ac.ensaagadir.models;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Contrat {
	  private LongProperty numContrat ;
	  private ObjectProperty<Reservation>reservation  ;
	  private ObjectProperty<Facture> facture   ;
	  private ObjectProperty<LocalDate> dateContrat  ;
	  private ObjectProperty<LocalDate> dateEcheance  ;
	  private BooleanProperty isSigned;
	  
	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contrat(Reservation reservation, Facture facture, LocalDate dateContrat, LocalDate dateEcheance) {
		super();
		this.reservation = new SimpleObjectProperty<>(reservation);
		this.facture = new SimpleObjectProperty<>(facture);
		this.dateContrat = new SimpleObjectProperty<>(dateContrat);
		this.dateEcheance = new SimpleObjectProperty<>(dateEcheance);
	}
	public long getNumContrat() {
		return numContrat.get();
	}
	public  LongProperty NumContrat() {
		return numContrat;
	}
	public void setNumContrat(long numContrat) {
		this.numContrat = new SimpleLongProperty(numContrat);
	}
	
	
	public Boolean getIsSigned() {
		return isSigned.get();
	}
	public BooleanProperty IsSigned() {
		return isSigned;
	}
	public void setIsSigned(Boolean isSigned) {
		this.isSigned = new SimpleBooleanProperty(isSigned);
	}
	public Reservation getReservation() {
		return reservation.get();
	}
	public  ObjectProperty<Reservation> Reservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = new SimpleObjectProperty<>(reservation);;
	}
	public Facture getFacture() {
		return facture.get();
	}
	public  ObjectProperty<Facture> Facture() {
		return facture;
	}
	public void setFacture(Facture facture) {
		this.facture = new SimpleObjectProperty<>(facture);
	}
	public LocalDate getDateContrat() {
		return dateContrat.get();
	}
	public  ObjectProperty<LocalDate> DateContrat() {
		return dateContrat;
	}
	public void setDateContrat(LocalDate dateContrat) {
		this.dateContrat = new SimpleObjectProperty<>(dateContrat);
	}
	public LocalDate getDateEcheance() {
		return dateEcheance.get();
	}
	public ObjectProperty<LocalDate> DateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(LocalDate dateEcheance) {
		this.dateEcheance = new SimpleObjectProperty<>(dateEcheance);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrat other = (Contrat) obj;
		if (numContrat == null) {
			if (other.numContrat != null)
				return false;
		} else if (!numContrat.equals(other.numContrat))
			return false;
		return true;
	}
	  
	
}