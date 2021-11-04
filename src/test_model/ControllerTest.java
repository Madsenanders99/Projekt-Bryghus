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
        Ordre o = controller.createOrdre(LocalDateTime.now());
        Kategori k = controller.createKategori ("Øl");
        Produkt p = new Produkt("Fustage");
        Prisliste pl = controller.createPrisliste("Detailsalg");
        Pris pris = controller.getAllPrislister().get(0).createPris(p, 300, 0);
        controller.getAllPrislister().get(0).getPriser().get(0).addKategori(k);
        controller.getAllPrislister().get(0).getPriser().get(0).setPant(30);
        controller.getAllOrdre().get(0).createOrdrelinje(pris, 1);
        controller.getAllOrdre().get(0).getUdlejning().setDatoStart(LocalDate.of(2020, 11, 4));
        controller.getAllOrdre().get(0).getUdlejning().setDatoSlut(LocalDate.of(2020, 11, 7));

        Produkt produkt2 = new Produkt ("Fustage 2");
        Ordre o2 = controller.createOrdre(LocalDateTime.now());
        Pris pris2 = controller.getAllPrislister().get(0).createPris(produkt2, 300, 0);
        controller.getAllPrislister().get(0).getPriser().get(1).addKategori(k);
        controller.getAllPrislister().get(0).getPriser().get(1).setPant(30);
        controller.getAllOrdre().get(1).createOrdrelinje(pris2, 1);

        ArrayList<Udlejning> ikkeAfleveredeUdlejninger =  new ArrayList<>();
        ikkeAfleveredeUdlejninger.add(o.getUdlejning());

        assertEquals(o.getUdlejning(), o.findIkkeAfleveredeUdlejning());

        assertNull(o2.findIkkeAfleveredeUdlejning());

        assertEquals(ikkeAfleveredeUdlejninger, controller.findIkkeAflevereUdlejninger());

    }
}