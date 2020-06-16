package ma.ac.ensaagadir.models;

public class Sanction {
	private long numSanction ;
	private RestituerVoiture  restitution  ;
	private int joursDeRetard;
	private double montant;
	
	
	public Sanction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sanction(long numSanction) {
		super();
		this.numSanction = numSanction;
	}
	
	
	
	public int getJoursDeRetard() {
		return joursDeRetard;
	}

	public void setJoursDeRetard(int joursDeRetard) {
		this.joursDeRetard = joursDeRetard;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public long getNumSanction() {
		return numSanction;
	}
	public void setNumSanction(long numSanction) {
		this.numSanction = numSanction;
	}
	public RestituerVoiture getRestitution() {
		return restitution;
	}
	public void setRestitution(RestituerVoiture restitution) {
		this.restitution = restitution;
	}
	
	
}

