package ma.ac.ensaagadir.models;

import java.util.Date;

public class Facture {
	private String  numFacture ;
	private Contrat  contrat  ;
	private Date  dateDeFacture ;
	private double  montantAPaye ;
	
	
	
	public Facture() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Facture( Date dateDeFacture, double montantAPaye) {
		super();
		
		this.dateDeFacture = dateDeFacture;
		this.montantAPaye = montantAPaye;
	}
	public String getNumFacture() {
		return numFacture;
	}
	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}
	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public Date getDateDeFacture() {
		return dateDeFacture;
	}
	public void setDateDeFacture(Date dateDeFacture) {
		this.dateDeFacture = dateDeFacture;
	}
	public double getMontantAPaye() {
		return montantAPaye;
	}
	public void setMontantAPaye(double montantAPaye) {
		this.montantAPaye = montantAPaye;
	}
	
	
}
