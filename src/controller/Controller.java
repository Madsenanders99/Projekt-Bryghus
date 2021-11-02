package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Controller {
    private Storage storage;
    private static Controller controller;

    private Controller() {
        storage = new Storage();
    }

    public static Controller getController() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    public static Controller getTestController() {
        return new Controller();
    }

    public ArrayList<Pris> getAllPriser() {
        return storage.getPriser();
    }

    public ArrayList<Kunde> getAllKunder() {
        return storage.getKunder();
    }

    public ArrayList<Ordre> getAllOrdre() {
        return storage.getOrdrer();
    }

    public ArrayList<Prisliste> getAllPrislister() {
        return storage.getPrislister();
    }

    public ArrayList<Kategori> getAllKategorier() {
        return storage.getKategorier();
    }

    public Kunde createKunde(String navn, String adresse) {
        Kunde k = new Kunde(navn, adresse);
        storage.addKunde(k);
        return k;
    }

    public Ordre createOrdre(LocalDateTime dato) {
        Ordre o = new Ordre(dato);
        storage.addOrdre(o);
        return o;
    }

    /**
     *
     * @param ordre
     * @param antal
     * @return
     */
    public Ordrelinje createOrdrelinje(Ordre ordre, Pris pris, int antal) {
        return ordre.createOrdrelinje(pris, antal);

    }

    public Prisliste createPrisliste(String navn) {
        Prisliste p = new Prisliste(navn);
        storage.addPrisliste(p);
        return p;
    }

    public Kategori createKategori(String navn) {
        Kategori ka = new Kategori(navn);
        storage.addKategori(ka);
        return ka;
    }

    public void createObjects() {
        createPrisliste("Fredagsbar");
        createPrisliste("Detailsalg");

        createKategori ("Flaske");
        createKategori ("Fadøl, 40 cl");
        createKategori ("Spiritus");

        createKunde("Karl", "Karlsvej 30");
        createKunde("Barl", "Barlsvej 30");

        Produkt produkt1 = new Produkt ("Klosterbryg");
        Produkt produkt2 = new Produkt ("Sweet Georgia Brown");
        Produkt produkt3 = new Produkt ("Extra Pilsner");
        Produkt produkt4 = new Produkt ("Celebration");
        Produkt produkt5 = new Produkt ("Blondie");
        Produkt produkt6 = new Produkt ("Forårsbryg");

        produkt1.setBeskrivelse("Klosterbryg er en bryg");
        produkt2.setBeskrivelse("Sweet Georgia Brown er en brown");
        produkt3.setBeskrivelse("Extra Pilsner er en pilsner med ekstra");
        produkt4.setBeskrivelse("Celebration er et ord");
        produkt5.setBeskrivelse("Blondie er blond");
        produkt6.setBeskrivelse("Forårsbryg er en bryg");


        getAllPrislister().get(0).createPris(produkt1, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt2, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt3, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt4, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt5, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt6, 70.0, 2);

        getAllPrislister().get(1).createPris(produkt1, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt2, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt3, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt4, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt5, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt6, 36.0, 0);

        getAllPrislister().get(0).getPriser().get(0).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(1).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(2).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(3).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(4).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(5).addKategori(getAllKategorier().get(0));

        getAllPrislister().get(1).getPriser().get(0).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(1).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(2).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(3).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(4).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(5).addKategori(getAllKategorier().get(0));

        Produkt produkt7 = new Produkt ("Klosterbryg");
        Produkt produkt8 = new Produkt ("Jazz Classic");
        Produkt produkt9 = new Produkt ("Extra Pilsner");

        produkt7.setBeskrivelse("Klosterbryg er en bryg");
        produkt8.setBeskrivelse("Jazz Classic er en classic");
        produkt9.setBeskrivelse("Extra Pilsner er en pilsner med ekstra");

        getAllPrislister().get(0).createPris(produkt7, 38.0, 1);
        getAllPrislister().get(0).createPris(produkt8, 38.0, 1);
        getAllPrislister().get(0).createPris(produkt9, 38.0, 1);


        getAllPrislister().get(0).getPriser().get(6).addKategori(getAllKategorier().get(1));
        getAllPrislister().get(0).getPriser().get(7).addKategori(getAllKategorier().get(1));
        getAllPrislister().get(0).getPriser().get(8).addKategori(getAllKategorier().get(1));

        Produkt produkt10 = new Produkt("Whisky45% 50 cl rør");
        Produkt produkt11 = new Produkt("Whisky 4 cl.");

        produkt10.setBeskrivelse("Whisky er en stærk drik");
        produkt11.setBeskrivelse("Lille Whisky");

        getAllPrislister().get(0).createPris(produkt10, 599, 0);
        getAllPrislister().get(0).createPris(produkt11, 50, 0);
        getAllPrislister().get(1).createPris(produkt10, 599, 0);

        getAllPrislister().get(0).getPriser().get(9).addKategori(getAllKategorier().get(2));
        getAllPrislister().get(0).getPriser().get(10).addKategori(getAllKategorier().get(2));
        getAllPrislister().get(1).getPriser().get(6).addKategori(getAllKategorier().get(2));

        createOrdre(LocalDateTime.of(2021, 10, 25, 10, 0));
        createOrdre(LocalDateTime.of(2021, 10, 27, 10, 0));

        storage.getOrdrer().get(0).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(0), 2);
        storage.getOrdrer().get(0).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(1), 1);

        storage.getOrdrer().get(0).getOrdrelinjer().get(0).setRabat(12.5);

        storage.getOrdrer().get(1).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(0), 4);
        storage.getOrdrer().get(1).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(1), 1);

        storage.getOrdrer().get(1).setBetalt(LocalDate.of(2021, 10, 25 ));

        getAllKunder().get(0).addOrdre(storage.getOrdrer().get(0));
    }
}
