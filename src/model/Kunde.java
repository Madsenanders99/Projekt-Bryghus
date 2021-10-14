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
	private ArrayList<Ordre> ordrer = new ArrayList<>();
	
	
	public Kunde(String navn, String adresse) {
		
	}
	
}
