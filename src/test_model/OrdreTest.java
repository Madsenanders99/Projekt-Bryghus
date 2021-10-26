package test_model;


import controller.Controller;
import model.AktivPrisliste;
import model.Kategori;
import model.Ordre;
import model.Prisliste;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrdreTest {
    Controller controller = Controller.getController();
    @Test
    public void test_findTotalPris() {
        AktivPrisliste aktivPrisListe = new AktivPrisliste(null);

        Prisliste prisliste = new Prisliste ("Fredagsbar");

        Prisliste prisliste2 = new Prisliste ("Detailsalg");

        aktivPrisListe.setPrisliste(prisliste);

        Kategori kategori1 = new Kategori ("Øl");
        Kategori kategori2 = new Kategori ("Whiskey");

        kategori1.createProdukt("KlosterBryg", aktivPrisListe);
        kategori1.createProdukt("Sweet Georgia Brown", aktivPrisListe);
        kategori1.createProdukt("Extra Pilsner", aktivPrisListe);
        kategori2.createProdukt("Celebration", aktivPrisListe);
        kategori2.createProdukt("Blondie", aktivPrisListe);
        kategori2.createProdukt("Forårsbryg", aktivPrisListe);

        controller.opretPris (1, 0, 36.0, prisliste2);
        controller.opretPris (2, 0, 36.0, prisliste2);
        controller.opretPris (3, 0, 36.0, prisliste2);
        controller.opretPris (4, 0, 36.0, prisliste2);
        controller.opretPris (5, 0, 36.0, prisliste2);
        controller.opretPris (6, 0, 36.0, prisliste2);

        controller.opretPris (1, 2, 70.0, prisliste);
        controller.opretPris (2, 2, 70.0, prisliste);
        controller.opretPris (3, 2, 70.0, prisliste);
        controller.opretPris (4, 2, 70.0, prisliste);
        controller.opretPris (5, 2, 70.0, prisliste);
        controller.opretPris (6, 2, 70.0, prisliste);


        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 25, 10, 0));
        ordre1.createOrdrelinje(2, kategori1.getProdukter().get(0));
        ordre1.createOrdrelinje(1, kategori2.getProdukter().get(0));
        ordre1.getOrdrelinjer().get(0).setRabat(12.5);

        ordre1.findTotalPris();
        assertEquals(192.5, ordre1.getTotalPris(), 0);
    }
}
