package ma.ac.ensaagadir.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Sanction {
	private LongProperty numSanction ;
	private ObjectProperty<RestituerVoiture>  restitution  ;
	private IntegerProperty joursDeRetard;
	private DoubleProperty montant;
	
	
	public Sanction() {
		
	}
	
	public int getJoursDeRetard() {
		return joursDeRetard.get();
	}
	public IntegerProperty JoursDeRetard() {
		return joursDeRetard;
	}
	public void setJoursDeRetard(int joursDeRetard) {
		this.joursDeRetard = new SimpleIntegerProperty(joursDeRetard);
	}

	public double getMontant() {
		return montant.get();
	}
	public DoubleProperty Montant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = new SimpleDoubleProperty(montant);
	}

	public long getNumSanction() {
		return numSanction.get();
	}
	public LongProperty NumSanction() {
		return numSanction;
	}
	public void setNumSanction(long numSanction) {
		this.numSanction = new SimpleLongProperty(numSanction);
	}
	public RestituerVoiture getRestitution() {
		return restitution.get();
	}
	public ObjectProperty<RestituerVoiture> Restitution() {
		return restitution;
	}
	public void setRestitution(RestituerVoiture restitution) {
		this.restitution = new SimpleObjectProperty<>(restitution);
	}

	public void setNumSanction(LongProperty numSanction) {
		this.numSanction = numSanction;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sanction other = (Sanction) obj;
		if (numSanction == null) {
			if (other.numSanction != null)
				return false;
		} else if (!numSanction.equals(other.numSanction))
			return false;
		return true;
	}
	
	
}

