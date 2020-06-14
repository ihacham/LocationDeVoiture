package ma.ac.ensaagadir.models;

import java.util.Date;

public class Contrat {
	  private long numContrat ;
	  private Reservation reservation  ;
	  private Facture facture   ;
	  private Date dateContrat  ;
	  private Date dateEcheance  ;
	  
	public Contrat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Contrat(Reservation reservation, Facture facture, Date dateContrat, Date dateEcheance) {
		super();
		this.reservation = reservation;
		this.facture = facture;
		this.dateContrat = dateContrat;
		this.dateEcheance = dateEcheance;
	}
	public long getNumContrat() {
		return numContrat;
	}
	public void setNumContrat(long numContrat) {
		this.numContrat = numContrat;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Facture getFacture() {
		return facture;
	}
	public void setFacture(Facture facture) {
		this.facture = facture;
	}
	public Date getDateContrat() {
		return dateContrat;
	}
	public void setDateContrat(Date dateContrat) {
		this.dateContrat = dateContrat;
	}
	public Date getDateEcheance() {
		return dateEcheance;
	}
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	  
	  
}