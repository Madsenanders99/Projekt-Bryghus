package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;


public class MainApp extends Application
{
    private Controller controller;
    private Stage stage;
    private Scene scenePrisliste;
    private Scene sceneSalg;
    private GridPane paneSalg;
    private Label lblHeadlinePaneLeft;
    private ArrayList<GridPane> panesSalgLeft = new ArrayList<>();
    private GridPane paneOrdre;
    private Ordre ordre;


    @Override
    public void init() {
        //Controller.init();
        this.controller = Controller.getController();
        this.controller.createObjects();
    }

    @Override
    public void start(Stage stage) {

        // Set-up stage
        this.stage = stage;
        this.stage.setTitle("Aarhus Bryghus");
        this.stage.setResizable(true);
        this.stage.setMinWidth(1000); // 1000
        this.stage.setMinHeight(400);
        this.stage.setWidth(1300);
        this.stage.setHeight(700);
        //this.stage.setFullScreen(true);

        this.initSceneStart();


        // -------------------------
        //this.initSceneSalg();
        //this.stage.setScene(this.sceneSalg);
        // --------------------------

        //this.deltagereWindow = new DeltagereWindow("", this.stage);
        //this.udflugterWindow = new UdflugterWindow("", this.stage);
        //this.hotellerWindow = new HotellerWindow("", this.stage);
    }

    private void initSceneStart()
    {
        // Reset
        this.ordre = null;
        this.panesSalgLeft.clear();

        // Set-up scenePrisliste
        this.initScenePrisliste();
        this.stage.setScene(this.scenePrisliste);
        this.stage.show();

        // Tving opdatering af GridPane i GUI.
        this.stage.getScene().getWindow().setWidth(this.stage.getScene().getWindow().getWidth() + 0.001);
    }

    // --- Scene: Prisliste --------------------------------------------------------------------------------------------

    /**
     *
     */
    private void initScenePrisliste()
    {
        // -------------------------------
        // Hent prislister fra controller
        ArrayList<Prisliste> prislister = controller.getAllPrislister();
        // -------------------------------

        GridPane pane = new GridPane();
        this.scenePrisliste = new Scene(pane);
        this.scenePrisliste.getStylesheets().add("gui/scenePrisliste.css");
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // pane.add(element, at col, at ro, extending columns, extending rows)
        // Prisliste buttons gridpane
        GridPane panePrislisteBtns = new GridPane();
        pane.add(panePrislisteBtns, 0, 0);
        panePrislisteBtns.setGridLinesVisible(false);
        panePrislisteBtns.setPadding(new Insets(30, 30, 30, 30));
        panePrislisteBtns.setHgap(10);
        panePrislisteBtns.setVgap(40);
        //panePrislisteBtns.setPrefHeight(500);
        //GridPane.setHalignment(panePrislisteBtns, HPos.CENTER);
        pane.setAlignment(Pos.CENTER);


        // Buttons
        int btnMinWidth = 200;
        for (int i = 0; i < prislister.size(); i++) {
            Button btn = new Button(prislister.get(i).getNavn());
            btn.setId(String.valueOf(i));
            panePrislisteBtns.add(btn, 0, i);
            btn.getStyleClass().add("btnPrisliste");
            // Event listeners
            //btn.setOnAction(this::selectPrislisteAction);
            int tmp = i;
            btn.setOnAction(event -> this.selectPrislisteAction(prislister.get(tmp)));
        }
    }

    /**
     *
     *
     */
    private void selectPrislisteAction(Prisliste prisliste)
    {
        this.ordre = this.controller.createOrdre(LocalDateTime.now());
        this.initSceneSalg(prisliste);
        this.stage.setScene(this.sceneSalg);
        // Tving opdatering af GridPane i GUI.
        this.stage.getScene().getWindow().setWidth(this.stage.getScene().getWindow().getWidth() + 0.001);


    }

    // --- Scene: Salg--------------------------------------------------------------------------------------------------

    /**
     *
     */
    private void initSceneSalg(Prisliste prisliste)
    {
        this.paneSalg = new GridPane();
        this.sceneSalg = new Scene(paneSalg);
        this.sceneSalg.getStylesheets().add("gui/sceneSalg.css");
        this.paneSalg.setGridLinesVisible(true);
        this.paneSalg.setPadding(new Insets(20));
        this.paneSalg.setHgap(10);
        this.paneSalg.setVgap(10);
        this.paneSalg.getStyleClass().add("paneSalg");
        // pane.add(element, at col, at ro, extending columns, extending rows)

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(60);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40);
        this.paneSalg.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        this.paneSalg.getRowConstraints().addAll(row1, row2);


        // --- PaneLeft ---
        this.lblHeadlinePaneLeft = new Label();
        GridPane.setHalignment(this.lblHeadlinePaneLeft, HPos.CENTER);
        // Back button
        Button btn = new Button("<<<");
        btn.getStyleClass().add("btnBack");
        this.paneSalg.add(btn, 0, 0);
        btn.setOnAction(event -> this.prevPaneSalgLeft());
        lblHeadlinePaneLeft.getStyleClass().add("lblHeadlinePaneLeft");
        this.paneSalg.add(lblHeadlinePaneLeft, 0, 0);
        // Kategorier
        this.setPaneSalgLeft(this.createKategorierPane(prisliste));


        // --- PaneRight ---
        // Ordre
        Label lblOrdre = new Label("Ordre");
        lblOrdre.getStyleClass().add("lblOrdre");
        this.paneSalg.add(lblOrdre, 1, 0);
        this.updateOrdrePane();
    }

    private void updateOrdrePane()
    {
        if (this.paneOrdre != null) {
            // Remove currently displayed ordre pane
            this.paneSalg.getChildren().remove(this.paneOrdre);
        }
        // Create new ordre pane and add to paneSalg.
        this.paneOrdre = this.createOrdrePane();
        this.paneSalg.add(this.paneOrdre, 1, 1);
    }

    private void setPaneSalgLeft(GridPane pane)
    {
        if (this.panesSalgLeft.size() != 0) {
            this.paneSalg.getChildren().remove(this.panesSalgLeft.get(this.panesSalgLeft.size() - 1));
        }
        this.panesSalgLeft.add(pane);
        this.paneSalg.add(pane, 0, 1);
        this.lblHeadlinePaneLeft.setText(pane.getId());

    }

    private void prevPaneSalgLeft()
    {
        if (this.panesSalgLeft.size() > 1) {
            // Remove current displayed pane from window.
            this.paneSalg.getChildren().remove(this.panesSalgLeft.get(this.panesSalgLeft.size() - 1));
            // Remove current displayed pane from ArrayList.
            this.panesSalgLeft.remove(this.panesSalgLeft.size() - 1);
            // Add previous displayed pane to window.
            GridPane panePrev = this.panesSalgLeft.get(this.panesSalgLeft.size() - 1);
            this.paneSalg.add(panePrev, 0, 1);
            this.lblHeadlinePaneLeft.setText(panePrev.getId());
        }
        else if (this.ordre != null) {
            ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType("Annuller", ButtonBar.ButtonData.CANCEL_CLOSE);
            String txt = "Igangværende ordre slettes.";
            Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk, btnCancel);
            alert.setHeaderText("Afbryd ordre og gå til startskærm?");
            alert.setTitle("Advarsel");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == btnOk) {
                // --- Go to start screen ---
                this.initSceneStart();
            }
        }
        else {
            // --- Go to start screen ---
            this.initSceneStart();
        }
    }

    // --- Kategorier --------------------------------------------------------------------------------------------------

    /**
     *
     */
    private GridPane createKategorierPane(Prisliste prisliste)
    {
        // -------------------------
        // Opret array med kategorier der indeholder produkter fra sendt prisliste
        ArrayList<Kategori> aktiveKategorier = new ArrayList<>();
        for (Pris pris : prisliste.getPriser()) {
            for (Kategori tmpKategori : pris.getKategorier()) {
                if (!aktiveKategorier.contains(tmpKategori)) {
                    aktiveKategorier.add(tmpKategori);
                }
            }
        }
        // -----------------------

        GridPane paneKat = new GridPane();
        paneKat.setId("Vælg kategori");
        paneKat.setGridLinesVisible(true);
        paneKat.setPadding(new Insets(20));
        paneKat.setHgap(10);
        paneKat.setVgap(10);
        paneKat.getStyleClass().add("paneKat");

        for (int i = 0; i < aktiveKategorier.size(); i++) {
            Button btn = new Button(aktiveKategorier.get(i).getNavn());
            btn.setId(String.valueOf(i));
            int btnsPrRow = 4;
            paneKat.add(btn, i % btnsPrRow, i / btnsPrRow);
            btn.getStyleClass().add("btnKat");
            int tmp = i;
            btn.setOnAction(event -> this.selectKategoriAction(aktiveKategorier.get(tmp), prisliste));
        }

        return paneKat;
    }

    /**
     *
     *
     */
    private void selectKategoriAction(Kategori kat, Prisliste pl)
    {
        this.setPaneSalgLeft(this.createProdukterPane(kat, pl));
    }



    // --- Produkt -----------------------------------------------------------------------------------------------------

    /**
     *
     *
     *
     */
    private GridPane createProdukterPane(Kategori kat, Prisliste pl)
    {
        ArrayList<Pris> aktuellePriser = new ArrayList<>();
        for (Pris katPris : kat.getPriser()) {
            for (Pris pris : pl.getPriser()) {
                if (katPris == pris) {
                    aktuellePriser.add(pris);
                }
            }
        }


        GridPane paneProdukter = new GridPane();
        paneProdukter.setId("Vælg produkt");
        paneProdukter.setGridLinesVisible(true);
        paneProdukter.setPadding(new Insets(20));
        paneProdukter.setHgap(10);
        paneProdukter.setVgap(10);
        paneProdukter.getStyleClass().add("paneProdukter");

        for (int i = 0; i < aktuellePriser.size(); i++) {
            GridPane paneProdSelect = new GridPane();
            paneProdSelect.setGridLinesVisible(true);
            paneProdSelect.setPadding(new Insets(0));
            paneProdSelect.setHgap(10);
            paneProdSelect.setVgap(10);
            paneProdSelect.getStyleClass().add("paneProdSelect");

            Pris pris = aktuellePriser.get(i);
            Button btn = new Button();
            btn.setText(pris.getProdukt().getNavn());
            btn.setId(String.valueOf(pris.getProdukt().getId()));
            btn.getStyleClass().add("btnProdukt");
            btn.setOnAction(event -> this.koebProdukt(pris));
            int elmsPrRow = 4;
            paneProdSelect.add(btn, 0, 0);
            paneProdukter.add(paneProdSelect, i % elmsPrRow, i / elmsPrRow);
        }

        return paneProdukter;
    }


    private void koebProdukt(Pris pris)
    {
        System.out.println("Køb produkttttt");
        this.ordre.createOrdrelinje(pris, 1);
        this.updateOrdrePane();
    }


    // --- Ordre -------------------------------------------------------------------------------------------------------

    private GridPane createOrdrePane()
    {
        // --------------------------------------------------------
        // Temp ordrelinjer
//        ArrayList<tmpOrdrelinje> ordrelinjer = new ArrayList<>();
//
//        tmpOrdrelinje ol1 = new tmpOrdrelinje();
//        ordrelinjer.add(ol1);
//        ol1.setNavn("IPA");
//        ol1.setAntal(1);
//        ol1.setStkPris(68.50);
//        ol1.setRabat(0);

//        tmpOrdrelinje ol2 = new tmpOrdrelinje();
//        ordrelinjer.add(ol2);
//        ol2.setNavn("Hvedeøl");
//        ol2.setAntal(1);
//        ol2.setStkPris(55.99);
//        ol2.setRabat(10);
//
//        tmpOrdrelinje ol3 = new tmpOrdrelinje();
//        ordrelinjer.add(ol3);
//        ol3.setNavn("Sort Silke");
//        ol3.setAntal(3);
//        ol3.setStkPris(49.25);
//        ol3.setRabat(20);
        // ------------------------------------------------------

        GridPane paneOrdre = new GridPane();
        paneOrdre.setGridLinesVisible(true);
        paneOrdre.setPadding(new Insets(20));
        paneOrdre.setHgap(10);
        paneOrdre.setVgap(10);
        paneOrdre.getStyleClass().add("paneOrdre");

        // pane.add(element, at col, at ro, extending columns, extending rows)
        int col = -1;
        int row = 0;

        // Legend
        Label lblLegendProduktnavn = new Label("Produkt");
        paneOrdre.add(lblLegendProduktnavn, ++col, row);
        lblLegendProduktnavn.getStyleClass().add("lblLegendProduktnavn");

        Label lblLegendAntal = new Label("Antal");
        paneOrdre.add(lblLegendAntal, ++col, row);
        GridPane.setHalignment(lblLegendAntal, HPos.RIGHT);
        lblLegendAntal.getStyleClass().add("lblLegendAntal");

        Label lblLegendStkPris = new Label("Stk. pris");
        paneOrdre.add(lblLegendStkPris, ++col, row);
        GridPane.setHalignment(lblLegendStkPris, HPos.RIGHT);
        lblLegendStkPris.getStyleClass().add("lblLegendStkPris");

        Label lblLegendRabat = new Label("Rabat");
        paneOrdre.add(lblLegendRabat, ++col, row);
        GridPane.setHalignment(lblLegendRabat, HPos.RIGHT);
        lblLegendRabat.getStyleClass().add("lblLegendRabat");

        Label lblLegendSamletPris = new Label("Pris");
        paneOrdre.add(lblLegendSamletPris, ++col, row);
        GridPane.setHalignment(lblLegendSamletPris, HPos.RIGHT);
        lblLegendSamletPris.getStyleClass().add("lblLegendSamletPris");

        // Ordrelinjer
        for (Ordrelinje ol : this.ordre.getOrdrelinjer()) {
            col = -1;
            row++;

            Label lblProduktnavn = new Label(ol.getPris().getProdukt().getNavn());
            paneOrdre.add(lblProduktnavn, ++col, row);
            lblProduktnavn.getStyleClass().add("lblProduktnavn");

            TextField txtfAntal = new TextField(String.valueOf(ol.getAntal()));
            paneOrdre.add(txtfAntal, ++col, row);
            txtfAntal.getStyleClass().add("txtfAntal");

            TextField txtfStkPris = new TextField(String.valueOf(ol.getPris().getPris()));
            paneOrdre.add(txtfStkPris, ++col, row);
            txtfStkPris.getStyleClass().add("txtfStkPris");

            TextField txtfRabat = new TextField(String.valueOf(ol.getRabat()) + "%");
            paneOrdre.add(txtfRabat, ++col, row);
            txtfRabat.getStyleClass().add("txtfRabat");

            TextField txtfSamletPris = new TextField(String.valueOf(ol.getSamletPris()));
            paneOrdre.add(txtfSamletPris, ++col, row);
            txtfSamletPris.getStyleClass().add("txtfSamletPris");
        }

        return paneOrdre;
    }




}