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
    public void test01_findPrisIkkeReturneret() {
        Ordre ordre1 = new Ordre(LocalDateTime.now());
        Kategori kategori1 = new Kategori("Øl");
        Produkt produkt1 = new Produkt("Fustage");
        Prisliste prisliste = new Prisliste("DetailSalg");
        Pris pris1 = prisliste.createPris(produkt1, 300, 0);
        pris1.addKategori(kategori1);
        pris1.setPant(30);
        ordre1.createOrdrelinje(pris1, 1);

        assertEquals(270, ordre1.getUdlejning().findPrisIkkeReturneret());

    }

    @Test
    @Order(2)
    public void test02_findPrisReturneret() {
        Ordre ordre1 = new Ordre(LocalDateTime.now());
        Kategori kategori1 = new Kategori("Øl");
        Produkt produkt1 = new Produkt("Fustage");
        Prisliste prisliste = new Prisliste("DetailSalg");
        Pris pris1 = prisliste.createPris(produkt1, 300, 0);
        pris1.addKategori(kategori1);
        pris1.setPant(30);
        ordre1.createOrdrelinje(pris1, 1);

        assertEquals(240, ordre1.getUdlejning().findPrisReturneret());
    }

    @Test
    @Order(3)
    public void test03_findPrisReturneretIkkeÅbnet() {
        Ordre ordre1 = new Ordre(LocalDateTime.now());
        Kategori kategori1 = new Kategori("Øl");
        Produkt produkt1 = new Produkt("Fustage");
        Prisliste prisliste = new Prisliste("DetailSalg");
        Pris pris1 = prisliste.createPris(produkt1, 300, 0);
        pris1.addKategori(kategori1);
        pris1.setPant(30);
        ordre1.createOrdrelinje(pris1, 1);

        assertEquals(-30, ordre1.getUdlejning().findPrisReturneretIkkeÅbnet());
    }
}
