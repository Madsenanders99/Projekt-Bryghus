package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

import java.time.LocalDate;
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
    private WindowOptions windowOptions;
    private Button btnBack;
    private Button btnAfregningBetal;
    private Prisliste aktivPrisliste;
    private Label lblKlipValue;
    private Label lblResterendeValue;
    private TextField txtfKassebeloebValue;
    private ToggleGroup tgMetode;

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
        this.stage.setWidth(1200);
        this.stage.setHeight(600);
        //this.stage.setFullScreen(true);

        this.initSceneStart();
        this.windowOptions = new WindowOptions("", this.stage);


        // -------------------------
        // TEST
//        ArrayList<Prisliste> prislister = this.controller.getAllPrislister();
//        selectPrislisteAction(prislister.get(0));
//        this.ordre = this.controller.getAllOrdre().get(0);
//        this.betalAfregningAction();
//
//        this.paaBeloebetAction();
//        this.udfoerBetalingAction();
        // --------------------------

    }

    private void forceWindowRefresh()
    {
        this.stage.getScene().getWindow().setWidth(this.stage.getScene().getWindow().getWidth() + 0.001);
    }

    private void initSceneStart()
    {
        // Reset
        this.aktivPrisliste = null;
        this.panesSalgLeft.clear();
        if (this.ordre != null) {
            this.controller.removeOrdre(this.ordre);
            this.ordre = null;
        }

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
        col1.setPercentWidth(45);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(55);
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

         if (this.aktivPrisliste.getTilladKlip()) {
            Label lblLegendSamletKlip = new Label("Klip");
            paneOrdreLegend.add(lblLegendSamletKlip, tmpCol++, tmpRow);
            lblLegendSamletKlip.getStyleClass().add("lblLegendSamletKlip");
        }

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
                String tmpText;

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

                if (this.aktivPrisliste.getTilladKlip()) {
                    tmpText = String.valueOf(ol.getPris().getKlip());
                    if (ol.getPris().getKlip() == 0) tmpText = "-";
                    Label lblStkKlip = new Label(tmpText);
                    paneOrdrelinjer.add(lblStkKlip, tmpCol++, tmpRow);
                    lblStkKlip.getStyleClass().add("lblStkKlip");
                }

                TextField txtfRabat = new TextField(String.valueOf(Math.round(ol.getRabat() * 100) / 100.0));
                paneOrdrelinjer.add(txtfRabat, tmpCol++, tmpRow);
                txtfRabat.setEditable(false);
                txtfRabat.getStyleClass().add("txtfRabat");
                txtfRabat.setOnAction(event -> this.redigerRabatAction(ol)); // On enter key
                txtfRabat.setOnMouseClicked(event -> this.redigerRabatAction(ol));

                if (this.aktivPrisliste.getTilladKlip()) {
                    tmpText = String.valueOf(ol.getSamletKlip());
                    if (ol.getSamletKlip() == 0) tmpText = "-";
                    Label lblSamletKlip = new Label(String.valueOf(tmpText));
                    paneOrdrelinjer.add(lblSamletKlip, tmpCol++, tmpRow);
                    lblSamletKlip.getStyleClass().add("lblSamletKlip");
                }

                Label lblSamletPris = new Label(String.valueOf(Math.round(ol.getSamletPris() * 100) / 100.0));
                paneOrdrelinjer.add(lblSamletPris, tmpCol++, tmpRow);
                //GridPane.setHalignment(lblSamletPris, HPos.RIGHT);
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

        // Kr.
        Label lblTotalKr = new Label("Total kr.");
        paneTotal.add(lblTotalKr, tmpCol++, tmpRow);
        lblTotalKr.getStyleClass().add("lblTotalKr");

        Label lblTotalKrValue = new Label(this.roundDouble2String(this.ordre.findTotalPris()));
        paneTotal.add(lblTotalKrValue, tmpCol++, tmpRow);
        lblTotalKrValue.getStyleClass().add("lblTotalKrValue");

        tmpCol = 0;
        tmpRow++;

        // Klip
        if (this.aktivPrisliste.getTilladKlip()) {
            Label lblTotalKlip = new Label("Heraf kan betales med " + this.ordre.findTotalKlip() + " klip kr.");
            paneTotal.add(lblTotalKlip, tmpCol++, tmpRow);
            lblTotalKlip.getStyleClass().add("lblTotalKlip");

            Label lblTotalKlipValue = new Label(this.roundDouble2String(this.ordre.findTotalKlipKrVærdi()));
            paneTotal.add(lblTotalKlipValue, tmpCol++, tmpRow);
            lblTotalKlipValue.getStyleClass().add("lblTotalKlipValue");
        }



        // --- Afregning ---
        GridPane paneAfregning = new GridPane();
        paneAfregning.setGridLinesVisible(false);
        paneAfregning.setPadding(new Insets(10, 0, 0, 0));
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
        if (numPad.getOkAction()) {
            if (numPad.getValue() >= 0 && numPad.getValue() <= 100) {
                ol.setRabat((double) numPad.getValue());
                this.updateOrdrePane();
            }
            else {
                ButtonType btnOk = new ButtonType("Yep", ButtonBar.ButtonData.OK_DONE);
                String txt = "";
                Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk);
                alert.setHeaderText("Rabtat skal være et heltal mellem 0 og 100 (inkl.)");
                alert.setTitle("Ugyldigt input");
                Optional<ButtonType> result = alert.showAndWait();
            }
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
       this.forceWindowRefresh();
    }


    // --- Scene: Betaling----------------------------------------------------------------------------------------------

    /**
     *
     */
    private Scene initSceneBetaling()
    {
        GridPane paneMaster = new GridPane();
        HBox hboxWrapper = new HBox(paneMaster);
        hboxWrapper.setPadding(new Insets(20));
        hboxWrapper.setAlignment(Pos.CENTER);
        hboxWrapper.setFillHeight(false);
        hboxWrapper.getStyleClass().add("hboxWrapper");

        Scene sceneBetaling = new Scene(hboxWrapper);
        sceneBetaling.getStylesheets().add("gui/sceneBetaling.css");
        paneMaster.setGridLinesVisible(false);
        paneMaster.setPadding(new Insets(20));
        paneMaster.setHgap(0);
        paneMaster.setVgap(20);
        paneMaster.getStyleClass().add("paneMaster");

        ColumnConstraints colGrowAlways = new ColumnConstraints();
        colGrowAlways.setHgrow(Priority.ALWAYS);
        ColumnConstraints colGrowNever = new ColumnConstraints();
        colGrowNever.setHgrow(Priority.NEVER);

        int masterRow = 0;
        int tmpCol = 0;
        int tmpRow = 0;

        // --- Total ---
        GridPane paneTotal = new GridPane();
        paneTotal.setGridLinesVisible(false);
        paneTotal.setPadding(new Insets(0));
        paneTotal.getStyleClass().add("paneTotal");
        paneTotal.getColumnConstraints().addAll(colGrowAlways);
        paneMaster.add(paneTotal, 0, masterRow++);

        Label lblTotal = new Label("Total kr.");
        lblTotal.getStyleClass().add("lblTotal");
        paneTotal.add(lblTotal, tmpCol++, tmpRow);

        Label lblTotalValue = new Label(this.roundDouble2String(this.ordre.findTotalPris()));
        lblTotalValue.getStyleClass().add("lblTotalValue");
        paneTotal.add(lblTotalValue, tmpCol++, tmpRow);

        // --- Afregning ---
        GridPane paneAfregning = new GridPane();
        paneAfregning.setGridLinesVisible(false);
        paneAfregning.setPadding(new Insets(10));
        paneAfregning.setHgap(10);
        paneAfregning.setVgap(0);
        paneAfregning.setAlignment(Pos.BASELINE_LEFT);
        paneAfregning.getColumnConstraints().addAll(colGrowAlways, colGrowNever, colGrowNever);
        paneAfregning.getStyleClass().add("paneAfregning");
        paneMaster.add(paneAfregning, 0, masterRow++);

        tmpCol = 0;
        tmpRow = 0;

        Label lblAfregning = new Label("Afregning");
        lblAfregning.getStyleClass().add("lblAfregning");
        paneAfregning.add(lblAfregning, tmpCol, tmpRow++);

        // Klip
        Label lblKlip = new Label("Klip:");
        lblKlip.getStyleClass().add("lblKlip");
        GridPane.setHalignment(lblKlip, HPos.RIGHT);
        paneAfregning.add(lblKlip, tmpCol++, tmpRow);

        ComboBox cboxKlip = new ComboBox();
        cboxKlip.getStyleClass().add("cboxKlip");
        GridPane.setHalignment(cboxKlip, HPos.RIGHT);
        paneAfregning.add(cboxKlip, tmpCol++, tmpRow);
        for (int i = 0; i <= this.ordre.findTotalKlip(); i++) {
            cboxKlip.getItems().add(i);
        }
        cboxKlip.setValue(0);
        cboxKlip.setOnAction(event -> this.updateAfregning((Integer) cboxKlip.getValue()));


        this.lblKlipValue = new Label(this.roundDouble2String(0.0));
        this.lblKlipValue.getStyleClass().add("lblKlipValue");
        paneAfregning.add(this.lblKlipValue, tmpCol++, tmpRow);

        // Resterende kr.
        tmpCol = 0;
        tmpRow++;

        Label lblResterende = new Label("Kroner:");
        GridPane.setHalignment(lblResterende, HPos.RIGHT);
        lblResterende.getStyleClass().add("lblResterende");
        paneAfregning.add(lblResterende, tmpCol++, tmpRow);


        this.lblResterendeValue = new Label(this.roundDouble2String(this.ordre.findTotalPris()));
        this.lblResterendeValue.getStyleClass().add("lblResterendeValue");
        paneAfregning.add(this.lblResterendeValue, ++tmpCol, tmpRow);

        // --- Kassebeløb ---
        GridPane paneKasse = new GridPane();
        paneKasse.setGridLinesVisible(false);
        paneKasse.setPadding(new Insets(10));
        paneKasse.setHgap(10);
        paneKasse.setVgap(0);
        paneKasse.setAlignment(Pos.BASELINE_LEFT);
        paneKasse.getColumnConstraints().addAll(colGrowAlways, colGrowNever);
        paneKasse.getStyleClass().add("paneKasse");
        paneMaster.add(paneKasse, 0, masterRow++);

        tmpCol = 0;
        tmpRow = 0;

        Label lblKassebeloeb = new Label("Kasse ind kr.:");
        GridPane.setHalignment(lblKassebeloeb, HPos.RIGHT);
        lblKassebeloeb.getStyleClass().add("lblKassebeloeb");
        paneKasse.add(lblKassebeloeb, tmpCol++, tmpRow);

        Button btnPaaBeloebet = new Button("På beløbet");
        btnPaaBeloebet.getStyleClass().add("btnPaaBeloebet");
        paneKasse.add(btnPaaBeloebet, tmpCol++, tmpRow);
        btnPaaBeloebet.setOnAction(event -> this.paaBeloebetAction());

        this.txtfKassebeloebValue = new TextField();
        this.txtfKassebeloebValue.getStyleClass().add("txtfKassebeloebValue");
        paneKasse.add(this.txtfKassebeloebValue, tmpCol++, tmpRow);

        // --- Betalingsmetoder ---
        GridPane paneBetalingsmetoder = new GridPane();
        paneBetalingsmetoder.setGridLinesVisible(false);
        paneBetalingsmetoder.setPadding(new Insets(0));
        paneBetalingsmetoder.setHgap(10);
        paneBetalingsmetoder.setVgap(0);
        paneBetalingsmetoder.setAlignment(Pos.CENTER);
        paneBetalingsmetoder.getStyleClass().add("paneBetalingsmetoder");
        paneMaster.add(paneBetalingsmetoder, 0, masterRow++);

        tmpCol = 0;
        tmpRow = 0;
        String[] metoder = {"Dankort", "MobilePay", "Kontant", "Faktura"};

        this.tgMetode = new ToggleGroup();

        for (int i = 0; i < metoder.length; i++) {
            RadioButton rbMetode = new RadioButton(metoder[i]);
            rbMetode.setToggleGroup(this.tgMetode);
            rbMetode.getStyleClass().add("rbMetode");
            if (i == 0) rbMetode.setSelected(true);
            paneBetalingsmetoder.add(rbMetode, tmpCol++, tmpRow);
        }


        // --- Udfør / Afbryd ---
        GridPane paneUdfoerBetaling = new GridPane();
        paneUdfoerBetaling.setGridLinesVisible(false);
        paneUdfoerBetaling.setPadding(new Insets(0, 0, 20, 0));
        paneUdfoerBetaling.setHgap(40);
        paneUdfoerBetaling.setVgap(0);
        paneUdfoerBetaling.setAlignment(Pos.CENTER);
        paneUdfoerBetaling.getStyleClass().add("paneUdfoerBetaling");
        paneMaster.add(paneUdfoerBetaling, 0, masterRow++);

        tmpCol = 0;
        tmpRow = 0;

        Button btnUdfoerBetaling = new Button("Udfør");
        btnUdfoerBetaling.getStyleClass().add("btnUdfoerBetaling");
        paneUdfoerBetaling.add(btnUdfoerBetaling, tmpCol++, tmpRow);
        btnUdfoerBetaling.setOnAction(event -> this.udfoerBetalingAction());

        Button btnAfbrydBetaling = new Button("Afbryd");
        btnAfbrydBetaling.getStyleClass().add("btnAfbrydBetaling");
        paneUdfoerBetaling.add(btnAfbrydBetaling, tmpCol++, tmpRow);
        btnAfbrydBetaling.setOnAction(event -> this.afbrydBetalingAction());

       return sceneBetaling;
    }

    private void udfoerBetalingAction()
    {
        double kassebeloeb;

        try {
            kassebeloeb = Double.parseDouble(this.txtfKassebeloebValue.getText());
            if (kassebeloeb < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException nfe) {
            ButtonType btnOk = new ButtonType("Roger", ButtonBar.ButtonData.OK_DONE);
            String txt = "";
            Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk);
            alert.setHeaderText("Kun positive tal og nul er gyldigt.");
            alert.setTitle("Ugyldigt input");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }

        double byttepenge = (Double.parseDouble(this.lblKlipValue.getText()) + kassebeloeb) - this.ordre.findTotalPris();
        if (byttepenge < 0) {
            ButtonType btnOk = new ButtonType("Roger", ButtonBar.ButtonData.OK_DONE);
            String txt = "";
            Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk);
            alert.setHeaderText("Kassebeløb skal være min. lig med total beløb.");
            alert.setTitle("For lavt beløb");
            Optional<ButtonType> result = alert.showAndWait();
            return;
        }

        // Set betalt attribut i ordre.
        this.ordre.setBetalt(LocalDate.now());

        WindowAfregnet wa = new WindowAfregnet("Kasse", this.stage);
        RadioButton rb = (RadioButton) this.tgMetode.getSelectedToggle();
        wa.setTotal(this.roundDouble2String(this.ordre.findTotalPris()));
        wa.setKlippekortBeloeb(this.lblKlipValue.getText());
        wa.setKasseIndMetode(rb.getText());
        wa.setKasseIndBeloeb(this.roundDouble2String(kassebeloeb));
        wa.setByttepenge(this.roundDouble2String(byttepenge));
        wa.showAndWait();

        // VIGTIGT! Nødvendigt for at ordren ikke slette, ved kald af initSceneStart().
        this.ordre = null;

        // Gå til startskærm
        this.initSceneStart();;
    }

    private void afbrydBetalingAction()
    {
       this.stage.setScene(this.sceneSalg);
       forceWindowRefresh();
    }

    private void updateAfregning(int antalKlip)
    {
       double klipValue = (this.ordre.findTotalKlipKrVærdi() / this.ordre.findTotalKlip()) * antalKlip;
       double resterendeValue = this.ordre.findTotalPris() - klipValue;
       this.lblKlipValue.setText(this.roundDouble2String(klipValue));
       this.lblResterendeValue.setText(this.roundDouble2String(resterendeValue));
       this.txtfKassebeloebValue.clear();
    }

    private void paaBeloebetAction()
    {
        this.txtfKassebeloebValue.setText(this.lblResterendeValue.getText());

    }



    // --- Helper methods ----------------------------------------------------------------------------------------------

    /**
     * Afrunder double til maks. 2 decimaler.
     * @param value
     * @return
     */
    private double roundDouble(double value)
    {
        return Math.round(value * 100) / 100.0;
    }

    /**
     * Afrunder double til maks. 2 decimaler og retunerer som string.
     * @param value
     * @return
     */
    private String roundDouble2String(double value)
    {
        String[] txtA = String.valueOf(this.roundDouble(value)).split("\\.");
        if (txtA.length > 1) while (txtA[1].length() < 2) txtA[1] += "0";
        return String.join(".", txtA);
    }



}