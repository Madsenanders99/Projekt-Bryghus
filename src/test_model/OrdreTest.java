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
class OrdreTest {
	Controller controller = Controller.getController();

	// TC1
	@Test
	@Order(1)
	public void test01_getIDMedOrdreTjekIDOptælling() {

		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		Ordre ordre2 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));

		assertEquals(1, ordre1.getId());
		assertEquals(2, ordre2.getId());
		assertNotEquals(1, ordre2.getId());
	}

	// TC1
	@Test
	@Order(2)
	public void test02_getKundeOgsetKunde() {
		Kunde kunde1 = new Kunde("Carl", "Carlsvej 30");
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));

		ordre1.setKunde(kunde1);

		assertEquals(kunde1, ordre1.getKunde());

	}

	// TC1
	@Test
	@Order(3)
	public void test03_createOrdrelinjerGetOrdrelinjerNotNull() {
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		Kategori kategori1 = new Kategori("Øl");
		Produkt produkt1 = new Produkt("Klosterbryg");
		Prisliste prisliste = new Prisliste("Fredagsbar");
		prisliste.createPris(produkt1, 70.0, 2);
		prisliste.getPriser().get(0).addKategori(kategori1);
		ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);
		ordre1.getOrdrelinjer().get(0).setRabat(20.0);

		assertNotNull(ordre1.getOrdrelinjer().get(0));
	}

	// TC2
	@Test
	@Order(4)
	public void test03_createOrdrelinjerFåAntalAfProdukterPåOrdrelinjer() {
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		Kategori kategori1 = new Kategori("Øl");
		Produkt produkt1 = new Produkt("Klosterbryg");
		Prisliste prisliste = new Prisliste("Fredagsbar");
		prisliste.createPris(produkt1, 70.0, 2);
		prisliste.getPriser().get(0).addKategori(kategori1);
		ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);
		ordre1.getOrdrelinjer().get(0).setRabat(20.0);

		assertEquals(1, ordre1.getOrdrelinjer().get(0).getAntal());

	}

	// TC3
	@Test
	@Order(5)
	public void test03_createOrdrelinjerGetPriserPrisLigMedGetOrdrelinjerPris() {
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		Kategori kategori1 = new Kategori("Øl");
		Produkt produkt1 = new Produkt("Klosterbryg");
		Prisliste prisliste = new Prisliste("Fredagsbar");
		prisliste.createPris(produkt1, 70.0, 2);
		prisliste.getPriser().get(0).addKategori(kategori1);
		ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);
		ordre1.getOrdrelinjer().get(0).setRabat(20.0);

		assertEquals(kategori1.getPriser().get(0), ordre1.getOrdrelinjer().get(0).getPris());

	}

	// TC4
	@Test
	@Order(6)
	public void test03_createOrdrelinjerFåRabat() {
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		Kategori kategori1 = new Kategori("Øl");
		Produkt produkt1 = new Produkt("Klosterbryg");
		Prisliste prisliste = new Prisliste("Fredagsbar");
		prisliste.createPris(produkt1, 70.0, 2);
		prisliste.getPriser().get(0).addKategori(kategori1);
		ordre1.createOrdrelinje(kategori1.getPriser().get(0), 1);
		ordre1.getOrdrelinjer().get(0).setRabat(20.0);

		assertEquals(0.8, ordre1.getOrdrelinjer().get(0).getRegnbarRabat(), 0);
	}

	@Test
	@Order(7)
	public void test04_getDatoPåOrdre() {
		LocalDateTime Dato = LocalDateTime.of(2021, 10, 29, 10, 0);
		Ordre ordre1 = new Ordre(Dato);

		assertEquals(Dato, ordre1.getDato());
	}

	// TC1
	@Order(8)
	public void test05_getOgSetBetaltBetalt() {
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		LocalDate betaltDato = LocalDate.of(2021, 10, 29);
		ordre1.setBetalt(betaltDato);
		assertEquals(betaltDato, ordre1.getBetalt());
	}

	// TC2
	@Order(9)
	public void test05_getOgSetBetaltIkkeBetalt() {
		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 29, 10, 0));
		LocalDate betaltDato = (null);
		ordre1.setBetalt(betaltDato);
		assertNotEquals(betaltDato, ordre1.getBetalt());
	}

	@Test
	@Order(10)
	public void test06_findTotalPrisUdenRabat() {
		Prisliste prisliste = new Prisliste("Fredagsbar");

		Kategori kategori1 = new Kategori("Øl");
		Kategori kategori2 = new Kategori("Whiskey");

		Produkt produkt1 = new Produkt("Klosterbryg");
		Produkt produkt2 = new Produkt("Celebration");

		prisliste.createPris(produkt1, 70.0, 2);
		prisliste.createPris(produkt2, 70.0, 2);

		prisliste.getPriser().get(0).addKategori(kategori1);
		prisliste.getPriser().get(1).addKategori(kategori2);

		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 25, 10, 0));
		ordre1.createOrdrelinje(kategori1.getPriser().get(0), 2);
		ordre1.createOrdrelinje(kategori2.getPriser().get(0), 1);

		assertEquals(210, ordre1.findTotalPris(), 0);
	}

	@Test
	@Order(11)
	public void test06_findTotalPrisMedRabat() {
		Prisliste prisliste = new Prisliste("Fredagsbar");

		Kategori kategori1 = new Kategori("Øl");
		Kategori kategori2 = new Kategori("Whiskey");

		Produkt produkt1 = new Produkt("Klosterbryg");
		Produkt produkt2 = new Produkt("Celebration");

		prisliste.createPris(produkt1, 70.0, 2);
		prisliste.createPris(produkt2, 70.0, 2);

		prisliste.getPriser().get(0).addKategori(kategori1);
		prisliste.getPriser().get(1).addKategori(kategori2);

		Ordre ordre1 = new Ordre(LocalDateTime.of(2021, 10, 25, 10, 0));
		ordre1.createOrdrelinje(kategori1.getPriser().get(0), 2);
		ordre1.createOrdrelinje(kategori2.getPriser().get(0), 1);
		ordre1.getOrdrelinjer().get(0).setRabat(12.5);

		assertEquals(192.5, ordre1.findTotalPris(), 0);
	}
}
