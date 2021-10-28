package controller;

import model.*;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    private Storage storage;
    private static Controller controller;

    private Controller() {
        storage = new Storage();
    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    public static Controller getTestController() {
        return new Controller();
    }

    public ArrayList<Pris> getAllPriser() {
        return storage.getPriser();
    }

    public ArrayList<Kunde> getAllKunder() {
        return storage.getKunder();
    }

    public ArrayList<Ordre> getAllOrdre() {
        return storage.getOrdrer();
    }

    public ArrayList<Prisliste> getAllPrislister() {
        return storage.getPrislister();
    }

    public ArrayList<Kategori> getAllKategorier() {
        return storage.getKategorier();
    }

    public Kunde createKunde(String navn, String adresse) {
        Kunde k = new Kunde(navn, adresse);
        storage.addKunde(k);
        return k;
    }

    public Ordre createOrdre(LocalDateTime dato) {
        Ordre o = new Ordre(dato);
        storage.addOrdre(o);
        return o;
    }

    public Prisliste createPrisliste(String navn) {
        Prisliste p = new Prisliste(navn);
        storage.addPrisliste(p);
        return p;
    }

    public Kategori createKategori(String navn) {
        Kategori ka = new Kategori(navn);
        storage.addKategori(ka);
        return ka;
    }

    public void createObjects() {
        createPrisliste("Fredagsbar");
        createPrisliste("Detailsalg");
        createPrisliste("test");

        createKategori ("Flaske");
        createKategori ("Fadøl, 40 cl");
        createKategori ("Spiritus");

        createKunde("Karl", "Karlsvej 30");

        Kunde kunde1 = new Kunde("Karl", "Karlsvej 30");
        Kunde kunde2 = new Kunde ("Barl", "Barlsvej 30");

        storage.getKategorier().get(0).createProdukt("KlosterBryg");
        storage.getKategorier().get(0).createProdukt("Sweet Georgia Brown");
        storage.getKategorier().get(0).createProdukt("Extra Pilsner");
        storage.getKategorier().get(0).createProdukt("Celebration");
        storage.getKategorier().get(0).createProdukt("Blondie");
        storage.getKategorier().get(0).createProdukt("Forårsbryg");

        storage.getKategorier().get(0).getProdukter().get(0).setBeskrivelse("Klosterbryg er en bryg");
        storage.getKategorier().get(0).getProdukter().get(1).setBeskrivelse("Sweet Georgia Brown er en brown");
        storage.getKategorier().get(0).getProdukter().get(2).setBeskrivelse("Extra Pilsner er en pilsner med ekstra");
        storage.getKategorier().get(0).getProdukter().get(3).setBeskrivelse("Celebration er et ord");
        storage.getKategorier().get(0).getProdukter().get(4).setBeskrivelse("Blondie er blond");
        storage.getKategorier().get(0).getProdukter().get(5).setBeskrivelse("Forårsbryg er en bryg");

        storage.getKategorier().get(1).createProdukt("Klosterbryg");
        storage.getKategorier().get(1).createProdukt("Jazz Classic");
        storage.getKategorier().get(1).createProdukt("Extra Pilsner");

        storage.getKategorier().get(1).getProdukter().get(0).setBeskrivelse("Klosterbryg er en bryg");
        storage.getKategorier().get(1).getProdukter().get(1).setBeskrivelse("Jazz Classic er en classic");
        storage.getKategorier().get(1).getProdukter().get(2).setBeskrivelse("Extra Pilsner er en pilsner med ekstra");

        storage.getKategorier().get(2).createProdukt("Whisky45% 50 cl rør");
        storage.getKategorier().get(2).createProdukt("Whisky 4 cl.");

        storage.getKategorier().get(2).getProdukter().get(0).setBeskrivelse("Whisky er en stærk drik");
        storage.getKategorier().get(2).getProdukter().get(1).setBeskrivelse("Lille Whisky");

        storage.getPrislister().get(0).createPris(storage.getKategorier().get(0).getProdukter().get(0), 70, 2);
        storage.getPrislister().get(0).createPris(storage.getKategorier().get(0).getProdukter().get(1), 70, 2);

        storage.getPrislister().get(1).createPris(storage.getKategorier().get(0).getProdukter().get(0), 36, 0);
        storage.getPrislister().get(1).createPris(storage.getKategorier().get(0).getProdukter().get(1), 36, 0);

        storage.getPrislister().get(0).createPris(storage.getKategorier().get(1).getProdukter().get(0), 38, 1);
        storage.getPrislister().get(0).createPris(storage.getKategorier().get(1).getProdukter().get(1), 38, 1);

        createOrdre(LocalDateTime.of(2021, 10, 25, 10, 0));
        createOrdre(LocalDateTime.of(2021, 10, 27, 10, 0));

        storage.getOrdrer().get(0).createOrdrelinje(2, storage.getPrislister().get(0).getPriser().get(0));
        storage.getOrdrer().get(0).createOrdrelinje(1, storage.getPrislister().get(0).getPriser().get(1));

        storage.getOrdrer().get(0).getOrdrelinjer().get(0).setRabat(12.5);

        storage.getOrdrer().get(1).createOrdrelinje(4, storage.getPrislister().get(0).getPriser().get(0));
        storage.getOrdrer().get(1).createOrdrelinje(1, storage.getPrislister().get(0).getPriser().get(1));

        kunde1.addOrdre(storage.getOrdrer().get(0));
    }
}
