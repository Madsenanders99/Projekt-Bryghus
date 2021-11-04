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
    public void test01_getSamletPrisPrOrdrelinje() {
        Ordre ordre1 = new Ordre (LocalDateTime.of(2021, 10, 29, 10, 0));
        Kategori kategori1 = new Kategori ("Øl");
        Produkt produkt1 = new Produkt ("Klosterbryg");
        Prisliste prisliste = new Prisliste ("Fredagsbar");
        Pris pris1 = prisliste.createPris(produkt1, 70.0, 2);
        pris1.addKategori(kategori1);
        Ordrelinje ol1 = ordre1.createOrdrelinje(pris1, 1);
        ol1.setRabat(20.0);

        Ordrelinje ol2 = ordre1.createOrdrelinje(pris1, 3);

        Ordrelinje ol3 = ordre1.createOrdrelinje(pris1, 2);

        ol3.setRabat(18.0);

        assertEquals(56.0,  ol1.getSamletPris(), 0);
        assertEquals(210.0, ol2.getSamletPris(), 0);
        assertEquals(114.8, ol3.getSamletPris(), 01);
    }
    
}
