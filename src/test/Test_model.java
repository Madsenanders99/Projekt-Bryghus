package test;

import controller.Controller;
import model.AktivPrisliste;
import model.Kategori;
import org.junit.Test;

import static org.junit.Assert.*;

public class Test_model {
    Controller controller = Controller.getController();

    @Test
    public void testIDIncrement() {
        AktivPrisliste aktivPrisListe = new AktivPrisliste(null);

        Kategori kategori1 = new Kategori ("Øl");

        kategori1.createProdukt("KlosterBryg", aktivPrisListe);
        kategori1.createProdukt("Sweet Georgia Brown", aktivPrisListe);
        kategori1.createProdukt("Extra Pilsner", aktivPrisListe);

        assertEquals(1, kategori1.getProdukter().get(0).getId());
        assertEquals(2, kategori1.getProdukter().get(1).getId());
        assertEquals(3, kategori1.getProdukter().get(2).getId());
    }
}
