package controller;

import storage.Storage;
import model.Pris;
import model.Prisliste;

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

    public Pris opretPris (int produktID, int klip, double pris, Prisliste prisliste) {
        if (klip != 0 && pris != 0) {
            Pris pris1 = new Pris (produktID, pris, klip);
            prisliste.addPris(pris1.getProduktID(), pris1);
            return pris1;
        }
        if (klip == 0 && pris != 0) {
            Pris pris2 = new Pris (produktID, pris);
            prisliste.addPris(pris2.getProduktID(), pris2);
            return pris2;
        }
        else {
            Pris pris3 = new Pris (produktID, klip);
            prisliste.addPris(pris3.getProduktID(), pris3);
            return pris3;
        }
    }


    public void createObjects() {

    }
}
