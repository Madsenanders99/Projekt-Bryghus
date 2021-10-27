package controller;

import model.Kategori;
import model.Kunde;
import model.Ordre;
import model.Prisliste;
import storage.Storage;

import java.time.LocalDateTime;

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

    public void createObjects() {
        Prisliste prisliste = new Prisliste ("Fredagsbar");
        Prisliste prisliste2 = new Prisliste ("Detailsalg");

        Kategori kategori1 = new Kategori ("Øl");
        Kategori kategori2 = new Kategori ("Whiskey");

        Kunde kunde = new Kunde("Karl", "Karlsvej 30");

        kategori1.createProdukt("KlosterBryg");
        kategori1.createProdukt("Sweet Georgia Brown");
        kategori1.createProdukt("Extra Pilsner");

        kategori2.createProdukt("Celebration");
        kategori2.createProdukt("Blondie");
        kategori2.createProdukt("Forårsbryg");

        prisliste.createPris(kategori1.getProdukter().get(0), 70, 2);
        prisliste.createPris(kategori2.getProdukter().get(0), 70, 2);

        prisliste2.createPris(kategori1.getProdukter().get(0), 36.0, 0);
        prisliste2.createPris(kategori2.getProdukter().get(0), 36.0, 0);


        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 25, 10, 0));

        ordre1.createOrdrelinje(2, prisliste.getPriser().get(0));
        ordre1.createOrdrelinje(1, prisliste.getPriser().get(1));

        ordre1.getOrdrelinjer().get(0).setRabat(12.5);

        Ordre ordre2 = new Ordre (LocalDateTime.of(2021, 10, 27, 10, 0));

        ordre2.createOrdrelinje(4, prisliste.getPriser().get(0));
        ordre2.createOrdrelinje(1, prisliste.getPriser().get(1));
    }
}
