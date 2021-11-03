package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
    private WindowNumPad windowNumPad;
    private WindowOptions windowOptions;
    private Button btnBack;
    private Button btnAfregningBetal;
    private Prisliste aktivPrisliste;

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
        this.stage.setMinWidth(1100); // 1000
        this.stage.setMinHeight(400);
        this.stage.setWidth(1300);
        this.stage.setHeight(700);
        //this.stage.setFullScreen(true);

        this.initSceneStart();
        this.windowOptions = new WindowOptions("", this.stage);


        // -------------------------
        // TEST
        //ArrayList<Prisliste> prislister = controller.getAllPrislister();
        //initSceneSalg(prislister.get(0));
        //this.betalAfregningAction();
        //this.koebProdukt(null);

        // --------------------------

    }

    private void forceWindowRefresh()
    {
        this.stage.getScene().getWindow().setWidth(this.stage.getScene().getWindow().getWidth() + 0.001);
    }

    private void initSceneStart()
    {
        // Reset
        this.ordre = null;
        this.aktivPrisliste = null;
        this.panesSalgLeft.clear();

        // Set-up scenePrisliste
        this.initScenePrisliste();
        this.stage.setScene(this.scenePrisliste);
        this.stage.show();
        this.forceWindowRefresh();
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
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(100);
        pane.getColumnConstraints().addAll(col1);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row1.setVgrow(Priority.NEVER);
        row2.setVgrow(Priority.ALWAYS);
        pane.getRowConstraints().addAll(row1, row2);
        
        // --- Options button ---
        Button btnOpt = new Button();
        //btnOpt.setId(String.valueOf(i));
        pane.add(btnOpt, 0, 0);
        GridPane.setHalignment(btnOpt, HPos.RIGHT);
        btnOpt.getStyleClass().add("btnOptions");
        btnOpt.setOnAction(event -> this.optionsAction());
        

        // --- Prisliste buttons ---
        // pane.add(element, at col, at ro, extending columns, extending rows)
        GridPane panePrislisteBtns = new GridPane();
        pane.add(panePrislisteBtns, 0, 1);
        panePrislisteBtns.setGridLinesVisible(false);
        panePrislisteBtns.setPadding(new Insets(30, 30, 30, 30));
        panePrislisteBtns.setHgap(10);
        panePrislisteBtns.setVgap(40);
        panePrislisteBtns.setAlignment(Pos.CENTER);
        panePrislisteBtns.getStyleClass().add("panePrislisteBtns");


        // Buttons
        int btnMinWidth = 200;
        for (int i = 0; i < prislister.size(); i++) {
            Button btnPl = new Button(prislister.get(i).getNavn());
            btnPl.setId(String.valueOf(i));
            panePrislisteBtns.add(btnPl, 0, i);
            btnPl.getStyleClass().add("btnPrisliste");
            // Event listeners
            //btnPl.setOnAction(this::selectPrislisteAction);
            int tmp = i;
            btnPl.setOnAction(event -> this.selectPrislisteAction(prislister.get(tmp)));
        }
    }

    private void optionsAction()
    {
        this.windowOptions.showAndWait();
    }

    /**
     *
     *
     */
    private void selectPrislisteAction(Prisliste prisliste)
    {
        this.aktivPrisliste = prisliste;
        this.ordre = this.controller.createOrdre(LocalDateTime.now());
        this.initSceneSalg();
        this.stage.setScene(this.sceneSalg);
    }

    // --- Scene: Salg--------------------------------------------------------------------------------------------------

    /**
     *
     */
    private void initSceneSalg()
    {
        this.paneSalg = new GridPane();
        this.sceneSalg = new Scene(paneSalg);
        this.sceneSalg.getStylesheets().add("gui/sceneSalg.css");
        this.paneSalg.setGridLinesVisible(false);
        this.paneSalg.setPadding(new Insets(20));
        this.paneSalg.setHgap(10);
        this.paneSalg.setVgap(10);
        this.paneSalg.getStyleClass().add("paneSalg");
        // pane.add(element, at col, at ro, extending columns, extending rows)

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        this.paneSalg.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        this.paneSalg.getRowConstraints().addAll(row1, row2);


        // --- PaneLeft ---
        this.lblHeadlinePaneLeft = new Label();
        GridPane.setHalignment(this.lblHeadlinePaneLeft, HPos.CENTER);
        // Back button
        this.btnBack = new Button("<<<");
        btnBack.setDisable(true);
        btnBack.getStyleClass().add("btnBack");
        this.paneSalg.add(btnBack, 0, 0);
        btnBack.setOnAction(event -> this.prevPaneSalgLeft());
        lblHeadlinePaneLeft.getStyleClass().add("lblHeadlinePaneLeft");
        this.paneSalg.add(lblHeadlinePaneLeft, 0, 0);
        // Kategorier
        this.setPaneSalgLeft(this.createKategorierPane());


        // --- PaneRight ---
        // Ordre
        Label lblOrdre = new Label("Ordre");
        GridPane.setHalignment(lblOrdre, HPos.CENTER);
        lblOrdre.getStyleClass().add("lblOrdre");
        this.paneSalg.add(lblOrdre, 1, 0);
        this.updateOrdrePane(); // Opret og vis ordrePane.
    }

    private void updateOrdrePane()
    {
        if (this.ordre != null) {
            if (this.paneOrdre != null) {
                // Remove currently displayed ordre pane
                this.paneSalg.getChildren().remove(this.paneOrdre);
            }
            // Create new ordre pane and add to paneSalg.
            this.paneOrdre = this.createOrdrePane();
            this.paneSalg.add(this.paneOrdre, 1, 1);

            // Opdater betal knap
            if (this.ordre.getOrdrelinjer().size() == 0) {
                this.btnAfregningBetal.setDisable(true);
            }
            else {
                this.btnAfregningBetal.setDisable(false);
            }

        }
    }

    private void setPaneSalgLeft(GridPane pane)
    {
        if (this.panesSalgLeft.size() != 0) {
            // Fjern nuværende panesSalgLeft fra vindue.
            this.paneSalg.getChildren().remove(this.panesSalgLeft.get(this.panesSalgLeft.size() - 1));
        }

        this.panesSalgLeft.add(pane);
        this.paneSalg.add(pane, 0, 1);
        this.lblHeadlinePaneLeft.setText(pane.getId());

        if (this.panesSalgLeft.size() > 1) {
            this.btnBack.setDisable(false);
        }

         forceWindowRefresh();


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

        if (this.panesSalgLeft.size() == 1) {
            this.btnBack.setDisable(true);
        }

         forceWindowRefresh();
    }

    // --- Kategorier --------------------------------------------------------------------------------------------------

    /**
     *
     */
    private GridPane createKategorierPane()
    {
        // -------------------------
        // Opret array med kategorier der indeholder produkter fra sendt prisliste
        ArrayList<Kategori> aktiveKategorier = new ArrayList<>();
        for (Pris pris : this.aktivPrisliste.getPriser()) {
            for (Kategori tmpKategori : pris.getKategorier()) {
                if (!aktiveKategorier.contains(tmpKategori)) {
                    aktiveKategorier.add(tmpKategori);
                }
            }
        }
        // -----------------------

        GridPane paneKat = new GridPane();
        paneKat.setId("Vælg kategori");
        paneKat.setGridLinesVisible(false);
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
            btn.setOnAction(event -> this.selectKategoriAction(aktiveKategorier.get(tmp)));
        }

        return paneKat;
    }

    /**
     *
     */
    private void selectKategoriAction(Kategori kat)
    {
        this.setPaneSalgLeft(this.createProdukterPane(kat));
    }



    // --- Produkt -----------------------------------------------------------------------------------------------------

    /**
     *
     */
    private GridPane createProdukterPane(Kategori kat)
    {
        ArrayList<Pris> aktuellePriser = new ArrayList<>();
        for (Pris katPris : kat.getPriser()) {
            for (Pris pris : this.aktivPrisliste.getPriser()) {
                if (katPris == pris) {
                    aktuellePriser.add(pris);
                }
            }
        }


        GridPane paneProdukter = new GridPane();
        paneProdukter.setId("Vælg produkt");
        paneProdukter.setGridLinesVisible(false);
        paneProdukter.setPadding(new Insets(20));
        paneProdukter.setHgap(10);
        paneProdukter.setVgap(10);
        paneProdukter.getStyleClass().add("paneProdukter");

        for (int i = 0; i < aktuellePriser.size(); i++) {
            Pris pris = aktuellePriser.get(i);
            Button btn = new Button();
            btn.setText(pris.getProdukt().getNavn());
            btn.setId(String.valueOf(pris.getProdukt().getId()));
            btn.getStyleClass().add("btnProdukt");
            btn.setOnAction(event -> this.koebProdukt(pris));
            int elmsPrRow = 4;
            paneProdukter.add(btn, i % elmsPrRow, i / elmsPrRow);
        }

        return paneProdukter;
    }


    private void koebProdukt(Pris pris)
    {
        WindowNumPad numPad = new WindowNumPad("Angiv antal", this.stage);
        numPad.showAndWait();
        if (numPad.getOkAction() && numPad.getValue() > 0) {
            // Tjek om produkt i forvejen er tilføjet ordren.
            boolean found = false;
            int i = 0;
            while (i < this.ordre.getOrdrelinjer().size() && !found) {
                if (this.ordre.getOrdrelinjer().get(i).getPris() == pris) {
                    found = true;
                }
                else {
                    i++;
                }
            }

            if (found) {
                // Opdatér eksisterende ordrelinje
                int oldAntal = this.ordre.getOrdrelinjer().get(i).getAntal();
                this.ordre.getOrdrelinjer().get(i).setAntal(oldAntal + numPad.getValue());
            }
            else {
                // Opret ny ordrelinje
                this.ordre.createOrdrelinje(pris, numPad.getValue());
            }
            this.updateOrdrePane();
        }

    }


    // --- Ordre -------------------------------------------------------------------------------------------------------

    private GridPane createOrdrePane()
    {
        GridPane paneOrdre = new GridPane();
        paneOrdre.setGridLinesVisible(false);
        paneOrdre.setPadding(new Insets(20));
        paneOrdre.setHgap(10);
        paneOrdre.setVgap(10);
        paneOrdre.getStyleClass().add("paneOrdre");

        RowConstraints rowVgrowNever = new RowConstraints();
        RowConstraints rowVgrowAlways = new RowConstraints();
        rowVgrowNever.setVgrow(Priority.NEVER);
        rowVgrowAlways.setVgrow(Priority.ALWAYS);
        paneOrdre.getRowConstraints().addAll(rowVgrowNever, rowVgrowAlways, rowVgrowNever);

        // pane.add(element, at col, at ro, extending columns, extending rows)
        int tmpCol = 0;
        int tmpRow = 0;
        int ordreRow = 0;


        // --- Legend ---
        GridPane paneOrdreLegend = new GridPane();
        paneOrdreLegend.setGridLinesVisible(false);
        paneOrdreLegend.setPadding(new Insets(0, 24, 0, 0));
        paneOrdreLegend.setHgap(10);
        paneOrdreLegend.setVgap(10);
        paneOrdreLegend.getStyleClass().add("paneOrdreLegend");
        paneOrdre.add(paneOrdreLegend, 0, ordreRow++);

        Label lblLegendProduktnavn = new Label("Produkt");
        paneOrdreLegend.add(lblLegendProduktnavn, tmpCol++, tmpRow);
        lblLegendProduktnavn.getStyleClass().add("lblLegendProduktnavn");

        Label lblLegendAntal = new Label("Antal");
        paneOrdreLegend.add(lblLegendAntal, tmpCol++, tmpRow);
        lblLegendAntal.getStyleClass().add("lblLegendAntal");

        Label lblLegendStkPris = new Label("Stk. pris");
        paneOrdreLegend.add(lblLegendStkPris, tmpCol++, tmpRow);
        lblLegendStkPris.getStyleClass().add("lblLegendStkPris");

        if (this.aktivPrisliste.getTilladKlip()) {
            Label lblLegendStkKlip = new Label("Stk. klip");
            paneOrdreLegend.add(lblLegendStkKlip, tmpCol++, tmpRow);
            lblLegendStkKlip.getStyleClass().add("lblLegendStkKlip");
        }

        Label lblLegendRabat = new Label("Rabat %");
        paneOrdreLegend.add(lblLegendRabat, tmpCol++, tmpRow);
        //GridPane.setHalignment(lblLegendRabat, HPos.RIGHT);
        lblLegendRabat.getStyleClass().add("lblLegendRabat");

        Label lblLegendSamletPris = new Label("Pris");
        paneOrdreLegend.add(lblLegendSamletPris, tmpCol++, tmpRow);
        //GridPane.setHalignment(lblLegendSamletPris, HPos.RIGHT);
        lblLegendSamletPris.getStyleClass().add("lblLegendSamletPris");


        // --- Ordrelinjer ---
        GridPane paneOrdrelinjerContainer = new GridPane();
        paneOrdrelinjerContainer.setGridLinesVisible(false);
        paneOrdrelinjerContainer.setPadding(new Insets(0));
        paneOrdrelinjerContainer.setHgap(0);
        paneOrdrelinjerContainer.setVgap(0);
        paneOrdrelinjerContainer.getStyleClass().add("paneOrdrelinjerContainer");
        paneOrdrelinjerContainer.getRowConstraints().addAll(rowVgrowAlways);
        paneOrdre.add(paneOrdrelinjerContainer, 0, ordreRow++);



        if (this.ordre.getOrdrelinjer().size() > 0) {
        ScrollPane sp = new ScrollPane();
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        sp.getStyleClass().add("scrollPaneOrdrelinjer");
        paneOrdrelinjerContainer.add(sp, 0, 0);

            GridPane paneOrdrelinjer = new GridPane();
            paneOrdrelinjer.setGridLinesVisible(false);
            paneOrdrelinjer.setPadding(new Insets(0));
            paneOrdrelinjer.setHgap(10);
            paneOrdrelinjer.setVgap(10);
            paneOrdrelinjer.getStyleClass().add("paneOrdrelinjer");
            sp.setContent(paneOrdrelinjer);

            // Ordrelinjer
            tmpRow = 0;
            for (Ordrelinje ol : this.ordre.getOrdrelinjer()) {
                tmpCol = 0;

                Label lblProduktnavn = new Label(ol.getPris().getProdukt().getNavn());
                paneOrdrelinjer.add(lblProduktnavn, tmpCol++, tmpRow);
                lblProduktnavn.getStyleClass().add("lblProduktnavn");

                TextField txtfAntal = new TextField(String.valueOf(ol.getAntal()));
                paneOrdrelinjer.add(txtfAntal, tmpCol++, tmpRow);
                txtfAntal.setEditable(false);
                txtfAntal.getStyleClass().add("txtfAntal");
                txtfAntal.setOnAction(event -> this.redigerAntalAction(ol)); // On enter key
                txtfAntal.setOnMouseClicked(event -> this.redigerAntalAction(ol));

                Label lblStkPris = new Label(String.valueOf(ol.getPris().getPris()));
                paneOrdrelinjer.add(lblStkPris, tmpCol++, tmpRow);
                lblStkPris.getStyleClass().add("lblStkPris");

                TextField txtfRabat = new TextField(String.valueOf(Math.round(ol.getRabat() * 100) / 100.0));
                paneOrdrelinjer.add(txtfRabat, tmpCol++, tmpRow);
                txtfRabat.setEditable(false);
                txtfRabat.getStyleClass().add("txtfRabat");
                txtfRabat.setOnAction(event -> this.redigerRabatAction(ol)); // On enter key
                txtfRabat.setOnMouseClicked(event -> this.redigerRabatAction(ol));

                Label lblSamletPris = new Label(String.valueOf(Math.round(ol.getSamletPris() * 100) / 100.0));
                paneOrdrelinjer.add(lblSamletPris, tmpCol++, tmpRow);
                GridPane.setHalignment(lblSamletPris, HPos.RIGHT);
                lblSamletPris.getStyleClass().add("lblSamletPris");

                tmpRow++;
            }
        }

        // --- Total ---
        GridPane paneTotal = new GridPane();
        paneTotal.setGridLinesVisible(false);
        paneTotal.setPadding(new Insets(0, 24, 0, 0));
        paneTotal.setHgap(10);
        paneTotal.setVgap(10);
        paneTotal.getStyleClass().add("paneTotal");
        paneOrdre.add(paneTotal, 0, ordreRow++);

        tmpCol = 0;
        tmpRow = 0;

        Label lblTotal = new Label("Total Kr.");
        paneTotal.add(lblTotal, tmpCol++, tmpRow);
        lblTotal.getStyleClass().add("lblTotal");

        Label lblTotalValue = new Label(String.valueOf(Math.round(this.ordre.findTotalPris() * 100) / 100.0));
        paneTotal.add(lblTotalValue, tmpCol++, tmpRow);
        lblTotalValue.getStyleClass().add("lblTotalValue");

        // --- Afregning ---
        GridPane paneAfregning = new GridPane();
        paneAfregning.setGridLinesVisible(false);
        paneAfregning.setPadding(new Insets(10, 0, 10, 0));
        paneAfregning.setHgap(20);
        paneAfregning.setVgap(10);
        paneAfregning.setAlignment(Pos.CENTER);
        paneAfregning.getStyleClass().add("paneAfregning");
        paneOrdre.add(paneAfregning, 0, ordreRow++);

        tmpCol = 0;
        tmpRow = 0;

        this.btnAfregningBetal = new Button("Betal");
        this.btnAfregningBetal.getStyleClass().add("btnAfregningBetal");
        paneAfregning.add(this.btnAfregningBetal, tmpCol++, tmpRow);
        this.btnAfregningBetal.setOnAction(event -> this.betalAfregningAction());

        Button btnAfregningAnnuller = new Button("Annuller");
        btnAfregningAnnuller.getStyleClass().add("btnAfregningAnnuller");
        paneAfregning.add(btnAfregningAnnuller, tmpCol++, tmpRow);
        btnAfregningAnnuller.setOnAction(event -> this.annullerAfregningAction());

        return paneOrdre;
    }

    private void redigerAntalAction(Ordrelinje ol)
    {
        WindowNumPad numPad = new WindowNumPad("Rediger antal", this.stage);
        numPad.setValue(ol.getAntal());
        numPad.showAndWait();
        if (numPad.getOkAction() && numPad.getValue() > 0) {
            ol.setAntal(numPad.getValue());
            this.updateOrdrePane();
        }
    }

    private void redigerRabatAction(Ordrelinje ol)
    {
        WindowNumPad numPad = new WindowNumPad("Rediger rabat", this.stage);
        numPad.setValue((int) ol.getRabat());
        numPad.showAndWait();
        if (numPad.getOkAction() && numPad.getValue() > 0) {
            ol.setRabat((double) numPad.getValue());
            this.updateOrdrePane();
        }
    }

    private void annullerAfregningAction()
    {
        if (this.ordre != null && this.ordre.getOrdrelinjer().size() != 0) {
            ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType btnCancel = new ButtonType("Annuller", ButtonBar.ButtonData.CANCEL_CLOSE);
            String txt = "Igangværende ordre slettes.";
            Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk, btnCancel);
            alert.setHeaderText("Afbryd ordre og gå til startskærm?");
            alert.setTitle("Advarsel");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == btnOk) {
                // Go to start screen
                this.initSceneStart();
            }
        }
        else {
            // Go to start screen
            this.initSceneStart();
        }

    }

    private void betalAfregningAction()
    {
       this.stage.setScene(this.initSceneBetaling());
    }


    // --- Scene: Betaling----------------------------------------------------------------------------------------------

    /**
     *
     */
    private Scene initSceneBetaling()
    {
        GridPane paneMaster = new GridPane();
        Scene sceneBetaling = new Scene(paneMaster);
        sceneBetaling.getStylesheets().add("gui/sceneBetaling.css");
        paneMaster.setGridLinesVisible(true);
        paneMaster.setPadding(new Insets(20));
        paneMaster.setHgap(10);
        paneMaster.setVgap(10);
        paneMaster.setAlignment(Pos.CENTER);
        paneMaster.getStyleClass().add("paneMaster");
        // pane.add(element, at col, at ro, extending columns, extending rows)

//        ColumnConstraints col1 = new ColumnConstraints();
//        col1.setPercentWidth(60);
//        ColumnConstraints col2 = new ColumnConstraints();
//        col2.setPercentWidth(40);
//        paneMaster.getColumnConstraints().addAll(col1, col2);

//        RowConstraints row1 = new RowConstraints();
//        RowConstraints row2 = new RowConstraints();
//        row2.setVgrow(Priority.ALWAYS);
//        this.paneSalg.getRowConstraints().addAll(row1, row2);

        int masterRow = 0;
        int tmpCol = 0;
        int tmpRow = 0;

        // --- Betalingsmetoder ---


        Label lblBetalingsmetoder = new Label("Betalingsmetoder:");
        lblBetalingsmetoder.getStyleClass().add("lblBetalingsmetoder");
        paneMaster.add(lblBetalingsmetoder, 0, masterRow++);

        // Metode knapper
        GridPane paneBetalingsmetoder = new GridPane();
        paneBetalingsmetoder.setGridLinesVisible(true);
        paneBetalingsmetoder.setPadding(new Insets(0, 0, 30, 0));
        paneBetalingsmetoder.setHgap(10);
        paneBetalingsmetoder.setVgap(10);
        paneBetalingsmetoder.setAlignment(Pos.CENTER);
        paneBetalingsmetoder.getStyleClass().add("paneBetalingsmetoder");
        paneMaster.add(paneBetalingsmetoder, 0, masterRow++);

        String[] metoder = {"Kontant", "Dankort", "MobilePay", "Klippekort", "Faktura"};

        for (int i = 0; i < metoder.length; i++) {
            Button btnMetode = new Button(metoder[i]);
            btnMetode.getStyleClass().add("btnMetode");
            paneBetalingsmetoder.add(btnMetode, tmpCol++, tmpRow);
        }

        // --- Afbryd ---
        Button btnAfbryd = new Button("Afbryd");
        btnAfbryd.getStyleClass().add("btnAfbryd");
        GridPane.setHalignment(btnAfbryd, HPos.CENTER);
        paneMaster.add(btnAfbryd, 0, masterRow++);
        btnAfbryd.setOnAction(event -> this.afbrydBetalingAction());

       return sceneBetaling;
    }

    private void afbrydBetalingAction()
    {
        ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType btnCancel = new ButtonType("Annuller", ButtonBar.ButtonData.CANCEL_CLOSE);
        String txt = "Igangværende ordre bibeholdes.";
        Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk, btnCancel);
        alert.setHeaderText("Afbryd betaling?");
        alert.setTitle("Advarsel");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == btnOk) {
            this.stage.setScene(this.sceneSalg);
            forceWindowRefresh();
        }
    }


}