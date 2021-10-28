package storage;

import model.Kategori;
import model.Kunde;
import model.Ordre;
import model.Prisliste;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Kunde> kunder;
    private List<Ordre> ordrer;
    private List <Prisliste> prislister;
    private List <Kategori> kategorier;

    public Storage () {
        kunder = new ArrayList<Kunde>();
        ordrer = new ArrayList<Ordre>();
        prislister = new ArrayList<Prisliste>();
        kategorier = new ArrayList<Kategori>();
    }

    public ArrayList<Kunde> getKunder() {
        return new ArrayList<Kunde>(kunder);
    }

    public void addKunde(Kunde kunde) {
        if (!kunder.contains(kunde)) {
            kunder.add(kunde);
        }
    }

    public ArrayList<Ordre> getOrdrer() {
        return new ArrayList<Ordre>(ordrer);
    }

    public void addOrdre(Ordre ordre) {
        if (!ordrer.contains(ordre)) {
            ordrer.add(ordre);
        }
    }

    public ArrayList<Prisliste> getPrislister() {
        return new ArrayList<Prisliste>(prislister);
    }

    public void addPrisliste(Prisliste prisliste) {
        if (!prislister.contains(prisliste)) {
            prislister.add(prisliste);
        }
    }

    public ArrayList<Kategori> getKategorier() {
        return new ArrayList<Kategori>(kategorier);
    }

    public void addKategori(Kategori kategori) {
        if (!kategorier.contains(kategori)) {
            kategorier.add(kategori);
        }
    }

}
