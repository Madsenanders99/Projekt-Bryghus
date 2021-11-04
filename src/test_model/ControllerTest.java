package test_model;

import controller.Controller;
import model.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ControllerTest {
    Controller controller = Controller.getController();

    @Test
    @Order(1)
    public void test01_findIkkeAflevereUdlejninger() {
        controller.createOrdre(LocalDateTime.of(2021, 10, 25, 10, 0));
        controller.createKategori ("Øl");
        Produkt produkt1 = new Produkt("Fustage");
        controller.createPrisliste("Detailsalg");
        controller.getAllPrislister().get(0).createPris(produkt1, 300, 0);
        controller.getAllPrislister().get(0).getPriser().get(0).addKategori(controller.getAllKategorier().get(0));
        controller.getAllPrislister().get(0).getPriser().get(0).setPant(30);
        controller.getAllOrdre().get(0).createOrdrelinje(controller.getAllKategorier().get(0).getPriser().get(0), 1);
        controller.getAllOrdre().get(0).getUdlejning().setDatoStart(LocalDate.of(2020, 11, 4));
        controller.getAllOrdre().get(0).getUdlejning().setDatoSlut(LocalDate.of(2020, 11, 7));

        Produkt produkt2 = new Produkt ("Fustage 2");
        controller.createOrdre(LocalDateTime.of(2021, 10, 27, 10, 0));
        controller.getAllPrislister().get(0).createPris(produkt2, 300, 0);
        controller.getAllPrislister().get(0).getPriser().get(1).addKategori(controller.getAllKategorier().get(0));
        controller.getAllPrislister().get(0).getPriser().get(1).setPant(30);
        controller.getAllOrdre().get(1).createOrdrelinje(controller.getAllKategorier().get(0).getPriser().get(1), 1);

        ArrayList<Udlejning> ikkeAfleveredeUdlejninger =  new ArrayList<>();
        ikkeAfleveredeUdlejninger.add(controller.getAllOrdre().get(0).getUdlejning());

        assertEquals(controller.getAllOrdre().get(0).getUdlejning(), controller.getAllOrdre().get(0).findIkkeAfleveredeUdlejning());

        assertNull(controller.getAllOrdre().get(1).findIkkeAfleveredeUdlejning());

        assertEquals(ikkeAfleveredeUdlejninger, controller.findIkkeAflevereUdlejninger());

    }
}
