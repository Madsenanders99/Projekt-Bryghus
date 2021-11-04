package test_model;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PrisTest {
    Controller controller = Controller.getController();

    @Test
    @Order(1)
    public void test01_SetPantStørreEndNul() {
        Prisliste pl = controller.createPrisliste("Detailsalg");

        Produkt produkt1 = new Produkt ("Fustage");

        Pris pris = pl.createPris(produkt1, 600, 0);

        pris.setPant(30);

        assertTrue(pris.isAfregnesVedReturnering());
        assertEquals(30, pris.getPant());
    }
    
    @Test
    @Order(2)
    public void test01_SetPantMindreEndNul() {
        Prisliste pl = controller.createPrisliste("Detailsalg");

        Produkt produkt1 = new Produkt ("Fustage");

        Pris pris = pl.createPris(produkt1, 600, 0);

        pris.setPant(0);

        assertFalse(pris.isAfregnesVedReturnering());
        assertEquals(0, pris.getPant());
    }
}