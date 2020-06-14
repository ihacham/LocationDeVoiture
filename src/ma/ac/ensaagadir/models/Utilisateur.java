package ma.ac.ensaagadir.models;

public class Utilisateur {

	private long idUtilisateur;
	private String   nom;
	private String   prenom;
	private String   tel;
	private int   age;
	private String   mail;
	private String   identifiant;
	private String   motDePasse;
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Utilisateur( String nom, String prenom, String tel, int age, String mail,
			String identifiant, String motDePasse) {
		
		
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.age = age;
		this.mail = mail;
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
	}
	
	public long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	
	
}

