package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Ordre {

	private int id;
	private LocalDateTime dato;
	private double totalPris;
	private LocalDate betalt;
	private Kunde kunde;
	private ArrayList<Ordrelinje> ordrelinjer = new ArrayList<>();
	
}