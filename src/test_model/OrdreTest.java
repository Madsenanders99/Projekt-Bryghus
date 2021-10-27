package test_model;


import controller.Controller;
import model.Kategori;
import model.Ordre;
import model.Prisliste;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrdreTest {
    Controller controller = Controller.getController();
    @Test
    public void test_findTotalPris() {
        Prisliste prisliste = new Prisliste ("Fredagsbar");

        Kategori kategori1 = new Kategori ("Øl");
        Kategori kategori2 = new Kategori ("Whiskey");

        kategori1.createProdukt("KlosterBryg");
        kategori1.createProdukt("Sweet Georgia Brown");
        kategori1.createProdukt("Extra Pilsner");
        kategori2.createProdukt("Celebration");
        kategori2.createProdukt("Blondie");
        kategori2.createProdukt("Forårsbryg");

        prisliste.createPris(kategori1.getProdukter().get(0), 70, 2);
        prisliste.createPris(kategori2.getProdukter().get(0), 70, 2);




        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 25, 10, 0));
        ordre1.createOrdrelinje(2, prisliste.getPriser().get(0));
        ordre1.createOrdrelinje(1, prisliste.getPriser().get(1));
        ordre1.getOrdrelinjer().get(0).setRabat(12.5);

        ordre1.findTotalPris();
        assertEquals(192.5, ordre1.getTotalPris(), 0);
    }
}
