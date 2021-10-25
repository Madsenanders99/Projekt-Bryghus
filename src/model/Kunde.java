package model;

import java.util.ArrayList;

public class Kunde {

	private int id;
	private String cvr;
	private String firmanavn;
	private String navn;
	private String adresse;
	private String tlf;
	private String mail;
	private final ArrayList<Ordre> ordrer = new ArrayList<>();
	
	
	public Kunde(String navn, String adresse) {
		
	}
	public String getNavn() {
		return navn;
	}
	public String getAdresse() {
		return adresse;
	}
	// 0..* Til Ordre association
	public ArrayList<Ordre> getOrdre() {
		return new ArrayList<>(ordrer);
	}
	public void addOrdre(Ordre ordre) {
		if (!ordrer.contains(ordre)) {
			ordrer.add(ordre);
			ordre.setKunde(this);
		}
	}
	public void removeOrdre (Ordre ordre) {
		if (ordrer.contains(ordre)) {
			ordrer.remove(ordre);
			ordre.setKunde(null);
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCvr(String cvr) {
		this.cvr = cvr;
	}

	public void setFirmanavn(String firmanavn) {
		this.firmanavn = firmanavn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public String getCvr() {
		return cvr;
	}

	public String getFirmanavn() {
		return firmanavn;
	}

	public String getTlf() {
		return tlf;
	}

	public String getMail() {
		return mail;
	}

	public ArrayList<Ordre> getOrdrer() {
		return ordrer;
	}
}
