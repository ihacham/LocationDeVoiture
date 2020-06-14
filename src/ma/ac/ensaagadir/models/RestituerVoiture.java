package ma.ac.ensaagadir.models;

public class RestituerVoiture {
	private long  codeRestitution ;
	private Sanction  sanction ;
	private Reservation  reservation  ;
	
	
	public RestituerVoiture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestituerVoiture(long codeRestitution) {
		super();
		this.codeRestitution = codeRestitution;
	}
	
	public long getCodeRestitution() {
		return codeRestitution;
	}
	public void setCodeRestitution(long codeRestitution) {
		this.codeRestitution = codeRestitution;
	}
	public Sanction getSanction() {
		return sanction;
	}
	public void setSanction(Sanction sanction) {
		this.sanction = sanction;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setCodeReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	
	
}
