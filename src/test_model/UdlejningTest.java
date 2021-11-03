package test_model;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UdlejningTest {

    @Test
    @Order(1)
    public void test01_findPrisMetoder() {
        Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
        Kategori kategori1 = new Kategori("Øl");
        Produkt produkt1 = new Produkt("Fustage");
        Prisliste prisliste = new Prisliste("DetailSalg");
        prisliste.createPris(produkt1, 300, 0);
        prisliste.getPriser().get(0).addKategori(kategori1);
        prisliste.getPriser().get(0).setPant(30);
        ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);

        assertEquals(270, ordre1.getUdlejning().findPrisIkkeReturneret());
        assertEquals(240, ordre1.getUdlejning().findPrisReturneret());
        assertEquals(-30, ordre1.getUdlejning().findPrisReturneretIkkeÅbnet());
    }

}
