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

    public void removeOrdre(Ordre ordre) {
        storage.removeOrdre(ordre);
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

     public Prisliste createPrisliste(String navn, boolean tilladKlip) {
        Prisliste pl = this.createPrisliste(navn);
        pl.setTilladKlip(tilladKlip);
        return pl;
    }

    public Kategori createKategori(String navn) {
        Kategori ka = new Kategori(navn);
        storage.addKategori(ka);
        return ka;
    }

    public ArrayList<Udlejning> findIkkeAflevereUdlejninger () {
        ArrayList<Udlejning> ikkeAfleverede = new ArrayList<>();
        for (int i = 0; i < getAllOrdre().size(); i++) {
            if (getAllOrdre().get(i).findIkkeAfleveredeUdlejning() != null) {
                ikkeAfleverede.add(getAllOrdre().get(i).getUdlejning());
            }
        }
        return ikkeAfleverede;
    }

    public void createObjects() {
        // prislister oprettes
        createPrisliste("Fredagsbar", true);
        createPrisliste("Detailsalg");

        // kategorier oprettes
        createKategori ("Flaske");
        createKategori ("Fadøl, 40 cl");
        createKategori ("Spiritus");

        // kunder oprettes
        createKunde("Karl", "Karlsvej 30");
        createKunde("Barl", "Barlsvej 30");

        // produkter oprettes
        Produkt produkt1 = new Produkt ("Klosterbryg");
        Produkt produkt2 = new Produkt ("Sweet Georgia Brown");
        Produkt produkt3 = new Produkt ("Extra Pilsner");
        Produkt produkt4 = new Produkt ("Celebration");
        Produkt produkt5 = new Produkt ("Blondie");
        Produkt produkt6 = new Produkt ("Forårsbryg");

        // beskrivelser sættes
        produkt1.setBeskrivelse("Klosterbryg er en bryg");
        produkt2.setBeskrivelse("Sweet Georgia Brown er en brown");
        produkt3.setBeskrivelse("Extra Pilsner er en pilsner med ekstra");
        produkt4.setBeskrivelse("Celebration er et ord");
        produkt5.setBeskrivelse("Blondie er blond");
        produkt6.setBeskrivelse("Forårsbryg er en bryg");

        // priser oprettes på fredagsbar
        getAllPrislister().get(0).createPris(produkt1, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt2, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt3, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt4, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt5, 70.0, 2);
        getAllPrislister().get(0).createPris(produkt6, 70.0, 2);

        // prister oprettes på detailsalg
        getAllPrislister().get(1).createPris(produkt1, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt2, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt3, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt4, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt5, 36.0, 0);
        getAllPrislister().get(1).createPris(produkt6, 36.0, 0);

        // fredagsbar priser bliver tilføjet til kategorien flaske
        getAllPrislister().get(0).getPriser().get(0).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(1).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(2).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(3).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(4).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(0).getPriser().get(5).addKategori(getAllKategorier().get(0));

        // detailsalg priser bliver tilføjet til kategorien flaske
        getAllPrislister().get(1).getPriser().get(0).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(1).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(2).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(3).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(4).addKategori(getAllKategorier().get(0));
        getAllPrislister().get(1).getPriser().get(5).addKategori(getAllKategorier().get(0));

        // produkter oprettes
        Produkt produkt7 = new Produkt ("Klosterbryg");
        Produkt produkt8 = new Produkt ("Jazz Classic");
        Produkt produkt9 = new Produkt ("Extra Pilsner");
        // beskrivelser sættes
        produkt7.setBeskrivelse("Klosterbryg er en bryg");
        produkt8.setBeskrivelse("Jazz Classic er en classic");
        produkt9.setBeskrivelse("Extra Pilsner er en pilsner med ekstra");
        // priser oprettes på fredagsbar prisliste
        getAllPrislister().get(0).createPris(produkt7, 38.0, 1);
        getAllPrislister().get(0).createPris(produkt8, 38.0, 1);
        getAllPrislister().get(0).createPris(produkt9, 38.0, 1);

        // priser tilføjes til fadøl kategori
        getAllPrislister().get(0).getPriser().get(6).addKategori(getAllKategorier().get(1));
        getAllPrislister().get(0).getPriser().get(7).addKategori(getAllKategorier().get(1));
        getAllPrislister().get(0).getPriser().get(8).addKategori(getAllKategorier().get(1));

        // produkter oprettes
        Produkt produkt10 = new Produkt("Whisky45% 50 cl rør");
        Produkt produkt11 = new Produkt("Whisky 4 cl.");

        // beskrivelser sættes
        produkt10.setBeskrivelse("Whisky er en stærk drik");
        produkt11.setBeskrivelse("Lille Whisky");

        // priser bliver oprettet, Whisky 45 tilføjes til både fredagsbar og detailsalg, Whisky 4 cl tilføjes kun til fredagsbar
        getAllPrislister().get(0).createPris(produkt10, 599, 0);
        getAllPrislister().get(0).createPris(produkt11, 50, 0);
        getAllPrislister().get(1).createPris(produkt10, 599, 0);

        // prister tilføjes til spiritus kategori
        getAllPrislister().get(0).getPriser().get(9).addKategori(getAllKategorier().get(2));
        getAllPrislister().get(0).getPriser().get(10).addKategori(getAllKategorier().get(2));
        getAllPrislister().get(1).getPriser().get(6).addKategori(getAllKategorier().get(2));

        // ordre oprettes
        createOrdre(LocalDateTime.of(2021, 10, 25, 10, 0));
        createOrdre(LocalDateTime.of(2021, 10, 27, 10, 0));

        // ordrelinjer oprettes på første ordre
        storage.getOrdrer().get(0).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(0), 2);
        storage.getOrdrer().get(0).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(1), 1);

        // rabat sættes til første ordrelinje
        storage.getOrdrer().get(0).getOrdrelinjer().get(0).setRabat(12.5);

        // ordrelinjer oprettes på anden ordre
        storage.getOrdrer().get(1).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(0), 4);
        storage.getOrdrer().get(1).createOrdrelinje(storage.getKategorier().get(0).getPriser().get(1), 1);

        // anden ordre bliver sat til at være betalt
        storage.getOrdrer().get(1).setBetalt(LocalDate.of(2021, 10, 25 ));

        // kunde 1 Karl får tilføjet ordre 1 til sine ordre
        getAllKunder().get(0).addOrdre(storage.getOrdrer().get(0));

        // udlejninger bliver oprettet nedenstående

        Produkt produkt12 = new Produkt ("Fustage");

        getAllPrislister().get(1).createPris(produkt12, 600, 0);

        getAllPrislister().get(1).getPriser().get(7).setPant(30);

        createOrdre(LocalDateTime.of(2021, 11, 03, 8, 38));

        getAllOrdre().get(2).createOrdrelinje(getAllPrislister().get(1).getPriser().get(7), 1);

        Produkt produkt13 = new Produkt ("Fadølsanlæg");

        getAllPrislister().get(1).createPris(produkt13, 275, 0);

        getAllPrislister().get(1).getPriser().get(7).setAfregnesVedReturnering(false);

        createOrdre(LocalDateTime.of(2021, 11, 03, 11, 32));

        getAllOrdre().get(3).createOrdrelinje(getAllPrislister().get(1).getPriser().get(8), 1);

    }
}
