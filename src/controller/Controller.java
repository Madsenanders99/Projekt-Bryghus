package controller;

import model.*;
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

    public Pris opretPris (int produktID, int klip, double pris, Prisliste prisliste) {
        if (klip != 0 && pris != 0) {
            Pris pris1 = new Pris (produktID, pris, klip);
            prisliste.addPris(pris1.getProduktID(), pris1);
            return pris1;
        }
        if (klip == 0 && pris != 0) {
            Pris pris2 = new Pris (produktID, pris);
            prisliste.addPris(pris2.getProduktID(), pris2);
            return pris2;
        }
        else {
            Pris pris3 = new Pris (produktID, klip);
            prisliste.addPris(pris3.getProduktID(), pris3);
            return pris3;
        }
    }


    public void createObjects() {

        Prisliste prisliste = new Prisliste ("Fredagsbar");

        opretPris (1, 2, 70.0, prisliste);
        opretPris (2, 2, 70.0, prisliste);
        opretPris (3, 2, 70.0, prisliste);
        opretPris (4, 2, 70.0, prisliste);
        opretPris (5, 2, 70.0, prisliste);
        opretPris (6, 2, 70.0, prisliste);

        Prisliste prisliste2 = new Prisliste ("Detailsalg");

        opretPris (1, 0, 36.0, prisliste2);
        opretPris (2, 0, 36.0, prisliste2);
        opretPris (3, 0, 36.0, prisliste2);
        opretPris (4, 0, 36.0, prisliste2);
        opretPris (5, 0, 36.0, prisliste2);
        opretPris (6, 0, 36.0, prisliste2);

        AktivPrisliste aktivPrisListe = new AktivPrisliste(null);

        Kategori kategori1 = new Kategori ("Øl");
        Kategori kategori2 = new Kategori ("Whiskey");

        kategori1.createProdukt("KlosterBryg", aktivPrisListe);
        kategori1.createProdukt("Sweet Georgia Brown", aktivPrisListe);
        kategori1.createProdukt("Extra Pilsner", aktivPrisListe);
        kategori2.createProdukt("Celebration", aktivPrisListe);
        kategori2.createProdukt("Blondie", aktivPrisListe);
        kategori2.createProdukt("Forårsbryg", aktivPrisListe);

        Kunde kunde1 = new Kunde ("Karl", "Karlsvej 30");

        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 25, 10, 0));
        ordre1.createOrdrelinje(2, kategori1.getProdukter().get(0));
        ordre1.createOrdrelinje(1, kategori2.getProdukter().get(0));
    }
}
