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

public class Facture {
	private LongProperty numFacture;
	private ObjectProperty<Contrat> contrat;
	private ObjectProperty<LocalDate> dateDeFacture;
	private DoubleProperty montantAPaye;
	private BooleanProperty isPayed;
	
	
	
	public Facture() {
		super();
		//contrat = new SimpleObjectProperty<>(new Contrat());
	}
	public Facture( LocalDate dateDeFacture, double montantAPaye) {
		super();
		
		this.dateDeFacture = new SimpleObjectProperty<>(dateDeFacture);
		this.montantAPaye = new SimpleDoubleProperty(montantAPaye);
	}
	
	
	public Boolean getIsPayed() {
		return isPayed.get();
	}
	public BooleanProperty IsPayed() {
		return isPayed;
	}
	public void setIsPayed(Boolean isPayed) {
		this.isPayed = new SimpleBooleanProperty(isPayed);
	}
	public Long getNumFacture() {
		return numFacture.get();
	}
	public LongProperty NumFacture() {
		return numFacture;
	}
	public void setNumFacture(Long numFacture) {
		this.numFacture = new SimpleLongProperty(numFacture);
	}
	public Contrat getContrat() {
		return contrat.get();
	}
	public ObjectProperty<Contrat> Contrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = new SimpleObjectProperty<>(contrat);
	}
	public LocalDate getDateDeFacture() {
		return dateDeFacture.get();
	}
	public ObjectProperty<LocalDate> DateDeFacture() {
		return dateDeFacture;
	}
	public void setDateDeFacture(LocalDate dateDeFacture) {
		this.dateDeFacture = new SimpleObjectProperty<>(dateDeFacture);
	}
	public double getMontantAPaye() {
		return montantAPaye.get();
	}
	public DoubleProperty MontantAPaye() {
		return montantAPaye;
	}
	public void setMontantAPaye(double montantAPaye) {
		this.montantAPaye = new SimpleDoubleProperty(montantAPaye);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		if (numFacture == null) {
			if (other.numFacture != null)
				return false;
		} else if (!numFacture.equals(other.numFacture))
			return false;
		return true;
	}
	
	
}
