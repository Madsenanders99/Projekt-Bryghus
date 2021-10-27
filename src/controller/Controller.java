package controller;

import model.Kategori;
import model.Kunde;
import model.Ordre;
import model.Prisliste;
import storage.Storage;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Kunde> getAllKunder() {
        return storage.getKunder();
    }

    public List<Ordre> getAllOrdre() {
        return storage.getOrdrer();
    }

    public List<Prisliste> getAllPrislister() {
        return storage.getPrislister();
    }

    public List<Kategori> getAllKategorier() {
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

        createKategori ("Øl");
        createKategori ("Whiskey");

        createKunde("Karl", "Karlsvej 30");

        Kunde kunde = new Kunde("Karl", "Karlsvej 30");

        storage.getKategorier().get(0).createProdukt("KlosterBryg");
        storage.getKategorier().get(0).createProdukt("Sweet Georgia Brown");
        storage.getKategorier().get(0).createProdukt("Extra Pilsner");

        storage.getKategorier().get(1).createProdukt("Celebration");
        storage.getKategorier().get(1).createProdukt("Blondie");
        storage.getKategorier().get(1).createProdukt("Forårsbryg");

        storage.getPrislister().get(0).createPris(storage.getKategorier().get(0).getProdukter().get(0), 70, 2);
        storage.getPrislister().get(0).createPris(storage.getKategorier().get(1).getProdukter().get(0), 70, 2);

        storage.getPrislister().get(1).createPris(storage.getKategorier().get(0).getProdukter().get(0), 36, 0);
        storage.getPrislister().get(1).createPris(storage.getKategorier().get(1).getProdukter().get(0), 36, 0);

        createOrdre(LocalDateTime.of(2021, 10, 25, 10, 0));
        createOrdre(LocalDateTime.of(2021, 10, 27, 10, 0));

        storage.getOrdrer().get(0).createOrdrelinje(2, storage.getPrislister().get(0).getPriser().get(0));
        storage.getOrdrer().get(0).createOrdrelinje(1, storage.getPrislister().get(0).getPriser().get(1));

        storage.getOrdrer().get(0).getOrdrelinjer().get(0).setRabat(12.5);

        storage.getOrdrer().get(1).createOrdrelinje(4, storage.getPrislister().get(0).getPriser().get(0));
        storage.getOrdrer().get(1).createOrdrelinje(1, storage.getPrislister().get(0).getPriser().get(1));
    }
}
