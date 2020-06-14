package ma.ac.ensaagadir.models;

public class Sanction {
	private long numSanction ;
	private RestituerVoiture  restitution  ;
	
	
	
	public Sanction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Sanction(long numSanction) {
		super();
		this.numSanction = numSanction;
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

