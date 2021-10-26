package test_model;

import model.Produkt;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProduktTest {

    @Test
    @Order(1)
    void createProdukt() {

        // Test increment id
        Produkt p1 = new Produkt("navn1", null);
        Produkt p2 = new Produkt("navn2", null);
        Produkt p3 = new Produkt("navn3", null);
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
        assertEquals(3, p3.getId());

        // Test navn
        assertEquals("navn1", p1.getNavn());
        assertEquals("navn2", p2.getNavn());
        assertEquals("navn3", p3.getNavn());

    }

    @Test
    @Order(2)
    void addProdukt() {
        Produkt p1 = new Produkt("navn1", null);
        Produkt p2 = new Produkt("navn2", null);

        p1.addProdukt(p2);
        assertEquals(p1.getProdukter().get(0), p2);


    }

    @Test
    @Order(3)
    void removeProdukt() {
        Produkt p1 = new Produkt("navn1", null);
        Produkt p2 = new Produkt("navn2", null);

        p1.addProdukt(p2);
        p1.removeProdukt(p2);
        assertEquals(0, p1.getProdukter().size());

    }
}