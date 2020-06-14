package ma.ac.ensaagadir.models;

import java.util.Date;

public class Reservation {
	private  long  codeReservation ;
	private  Voiture  immatriculation ;
	private  RestituerVoiture  restitution ;
	private  Contrat  contrat;
	private  Date  dateReservation  ;
	private  Date  dateDepart  ;
	private  Date  dateRetour   ;
	private Client client;
	
	
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reservation( Date dateReservation, Date dateDepart, Date dateRetour) {
		this.dateReservation = dateReservation;
		this.dateDepart = dateDepart;
		this.dateRetour = dateRetour;
	}
	public long getCodeReservation() {
		return codeReservation;
	}
	public void setCodeReservation(long codeReservation) {
		this.codeReservation = codeReservation;
	}
	public Voiture getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(Voiture immatriculation) {
		this.immatriculation = immatriculation;
	}
	public RestituerVoiture getRestitution() {
		return restitution;
	}
	public void setRestitution(RestituerVoiture restitution) {
		this.restitution = restitution;
	}
	public Contrat getContrat() {
		return contrat;
	}
	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
	public Date getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	
	
	
}
