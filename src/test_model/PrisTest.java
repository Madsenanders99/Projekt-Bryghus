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
    public void test01_SetPant() {
        controller.createPrisliste("Detailsalg");

        Produkt produkt1 = new Produkt ("Fustage");

        controller.getAllPrislister().get(0).createPris(produkt1, 600, 0);

        controller.getAllPrislister().get(0).getPriser().get(0).setPant(30);

        assertTrue(controller.getAllPrislister().get(0).getPriser().get(0).isAfregnesVedReturnering());
        assertEquals(30, controller.getAllPrislister().get(0).getPriser().get(0).getPant());
    }
}
