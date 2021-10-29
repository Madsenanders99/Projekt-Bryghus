package test_model;


import controller.Controller;
import model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrdreTest {
    Controller controller = Controller.getController();
    @Test
    @Order(1)
    public void test01_getID() {

        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 29, 10, 0));


        assertEquals(1, ordre1.getId());
    }

    @Test
    @Order(2)
    public void test02_getKundeOgsetKunde() {
        Kunde kunde1 = new Kunde ("Carl", "Carlsvej 30");
        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 29, 10, 0));

        ordre1.setKunde(kunde1);

        assertEquals(kunde1, ordre1.getKunde());

    }

    @Test
    @Order(3)
    public void test03_createOrdrelinjer() {
        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 29, 10, 0));
        Kategori kategori1 = new Kategori ("Øl");
        Produkt produkt1 = new Produkt ("Klosterbryg");
        Prisliste prisliste = new Prisliste ("Fredagsbar");
        prisliste.createPris(produkt1, 70.0, 2);
        prisliste.getPriser().get(0).addKategori(kategori1);
        ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);

        assertNotNull(ordre1.getOrdrelinjer().get(0));
    }

    @Test
    @Order(4)
    public void test04_getDato() {
        LocalDateTime Dato = LocalDateTime.of(2021, 10, 29, 10, 0);
        Ordre ordre1 = new Ordre (Dato);

        assertEquals(Dato, ordre1.getDato());
    }

    @Order(5)
    public void test05_getOgSetBetalt() {
        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 29, 10, 0));
        LocalDate betaltDato = LocalDate.of(2021, 10, 29);
        ordre1.setBetalt(betaltDato);
        assertEquals(betaltDato, ordre1.getBetalt());
    }

    @Test
    @Order(6)
    public void test06_findTotalPris() {
        Prisliste prisliste = new Prisliste ("Fredagsbar");

        Kategori kategori1 = new Kategori ("Øl");
        Kategori kategori2 = new Kategori ("Whiskey");

        Produkt produkt1 = new Produkt ("Klosterbryg");
        Produkt produkt2 = new Produkt ("Celebration");

        prisliste.createPris(produkt1, 70.0, 2);
        prisliste.createPris(produkt2, 70.0, 2);

        prisliste.getPriser().get(0).addKategori(kategori1);
        prisliste.getPriser().get(1).addKategori(kategori2);


        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 25, 10, 0));
        ordre1.createOrdrelinje(kategori1.getPriser().get(0), 2);
        ordre1.createOrdrelinje(kategori2.getPriser().get(0), 1);
        ordre1.getOrdrelinjer().get(0).setRabat(12.5);


        assertEquals(192.5, ordre1.findTotalPris(), 0);
    }
}
