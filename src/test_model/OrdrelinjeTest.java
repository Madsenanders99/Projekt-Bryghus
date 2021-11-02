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
public class OrdrelinjeTest {

    @Test
    @Order(1)
    public void test01_getSamletPris() {
        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 29, 10, 0));
        Kategori kategori1 = new Kategori ("Øl");
        Produkt produkt1 = new Produkt ("Klosterbryg");
        Prisliste prisliste = new Prisliste ("Fredagsbar");
        prisliste.createPris(produkt1, 70.0, 2);
        prisliste.getPriser().get(0).addKategori(kategori1);
        ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);
        ordre1.getOrdrelinjer().get(0).setRabat(20.0);

        ordre1.createOrdrelinje(kategori1.getPriser().get(0), 3);

        ordre1.createOrdrelinje(kategori1.getPriser().get(0), 2);

        ordre1.getOrdrelinjer().get(2).setRabat(18.0);

        assertEquals(56.0, ordre1.getOrdrelinjer().get(0).getSamletPris(), 0);
        assertEquals(210.0, ordre1.getOrdrelinjer().get(1).getSamletPris(), 0);
        assertEquals(114.8, ordre1.getOrdrelinjer().get(2).getSamletPris(), 0.0001);
    }
}
