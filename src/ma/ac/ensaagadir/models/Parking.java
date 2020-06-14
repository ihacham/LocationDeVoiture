package ma.ac.ensaagadir.models;

public class Parking {
	private String  numParking ;
	private Voiture immatriculation  ;
	private long  capacite  ;
	private String  rue ;
	private String  arrondissement  ;
	
	
	public Parking() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Parking( long capacite, String rue, String arrondissement) {
		super();
		this.capacite = capacite;
		this.rue = rue;
		this.arrondissement = arrondissement;
	}
	public String getNumParking() {
		return numParking;
	}
	public void setNumParking(String numParking) {
		this.numParking = numParking;
	}
	public Voiture getNumImmatriculation() {
		return immatriculation;
	}
	public void setNumImmatriculation(Voiture immatriculation) {
		this.immatriculation = immatriculation;
	}
	public long getCapacite() {
		return capacite;
	}
	public void setCapacite(long capacite) {
		this.capacite = capacite;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getArrondissement() {
		return arrondissement;
	}
	public void setArrondissement(String arrondissement) {
		this.arrondissement = arrondissement;
	}
	
	
}
