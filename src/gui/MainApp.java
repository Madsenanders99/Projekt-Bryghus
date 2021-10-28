package gui;

import controller.Controller;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Kategori;
import model.Prisliste;

import java.util.ArrayList;


public class MainApp extends Application
{
    private Stage stage;
    private Scene scenePrisliste;
    private Scene sceneSalg;
    private GridPane paneSalg;
    private GridPane paneKategorier;
    private GridPane paneProdukter;
    private Label lblKategoriHeadline;
    private Controller controller;
    @Override
    public void init() {
        //Controller.init();
        controller = Controller.getController();
        controller.createObjects();
    }

    @Override
    public void start(Stage stage) {
        // Set-up stage
        this.stage = stage;
        this.stage.setTitle("Aarhus Bryghus");
        this.stage.setResizable(true);
        this.stage.setMinWidth(500); // 1000
        this.stage.setMinHeight(400);
        this.stage.setWidth(1300);
        this.stage.setHeight(700);

        // Set-up scenePrisliste
        this.initScenePrisliste();
        this.stage.setScene(scenePrisliste);
        this.stage.show();

        // -------------------------
        //this.initSceneSalg();
        //this.stage.setScene(this.sceneSalg);
        // --------------------------

        //this.deltagereWindow = new DeltagereWindow("", this.stage);
        //this.udflugterWindow = new UdflugterWindow("", this.stage);
        //this.hotellerWindow = new HotellerWindow("", this.stage);
    }

    /**
     *
     */
    private void initScenePrisliste()
    {
        // -------------------------------
        // Tmp prislister
        ArrayList<Prisliste> prislister = new ArrayList<>();
        for (int i = 0; i < controller.getAllPrislister().size(); i++) {
            prislister.add(controller.getAllPrislister().get(i));
        }
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
        panePrislisteBtns.setVgap(30);
        //panePrislisteBtns.setPrefHeight(500);
        //GridPane.setHalignment(panePrislisteBtns, HPos.CENTER);
        pane.setAlignment(Pos.CENTER);

        // Buttons
        int btnMinWidth = 200;
        for (int i = 0; i < prislister.size(); i++) {
            Button btn = new Button(prislister.get(i).getNavn());
            btn.setId(String.valueOf(i));
            btn.setMinWidth(btnMinWidth);
            btn.setPrefWidth(600);
            btn.setPrefHeight(100);
            //btn.setMaxWidth(btnMaxWidth);
            panePrislisteBtns.add(btn, 0, i + 0);
            btn.getStyleClass().add("btnPrisliste");
            // Event listeners
            //btn.setOnAction(this::selectPrislisteAction);
            btn.setOnAction(event -> this.selectPrislisteAction(event));
        }
    }

    /**
     *
     * @param event
     */
    private void selectPrislisteAction(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        int id = Integer.parseInt(btn.getId());
        this.initSceneSalg();
        this.stage.setScene(this.sceneSalg);
    }

    /**
     *
     */
    private void initSceneSalg()
    {
        this.paneSalg = new GridPane();
        this.sceneSalg = new Scene(paneSalg);
        this.sceneSalg.getStylesheets().add("gui/sceneSalg.css");
        paneSalg.setGridLinesVisible(true);
        paneSalg.setPadding(new Insets(20));
        paneSalg.setHgap(10);
        paneSalg.setVgap(10);
        paneSalg.getStyleClass().add("paneSalg");
        // pane.add(element, at col, at ro, extending columns, extending rows)

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(60);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(40);
        paneSalg.getColumnConstraints().addAll(col1, col2);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        paneSalg.getRowConstraints().addAll(row1, row2);

        // --- Kategorier ---
        this.lblKategoriHeadline = new Label("Vælg kategori");
        GridPane.setHalignment(this.lblKategoriHeadline, HPos.CENTER);
        lblKategoriHeadline.getStyleClass().add("lblKategoriHeadline");
        paneSalg.add(lblKategoriHeadline, 0, 0);
        this.paneKategorier = this.createKategorier();
        paneSalg.add(this.paneKategorier, 0, 1);

        // --- Ordre ---
        Label lblOrdre = new Label("Ordre");
        lblOrdre.getStyleClass().add("lblOrdre");
        paneSalg.add(lblOrdre, 1, 0);
        paneSalg.add(this.createOrdre(), 1, 1);
    }

    /**
     *
     */
    private GridPane createKategorier()
    {
        // ---------------------------------------------
        // Hent Kategorier fra controller
        ArrayList<Kategori> kategorier = new ArrayList<>();
        for (int i = 0; i < controller.getAllKategorier().size(); i++) {
            kategorier.add(controller.getAllKategorier().get(i));
        }
        // ----------------------------------------------

        GridPane paneKat = new GridPane();
        paneKat.setGridLinesVisible(true);
        paneKat.setPadding(new Insets(20));
        paneKat.setHgap(10);
        paneKat.setVgap(10);
        paneKat.getStyleClass().add("paneKat");

        for (int i = 0; i < kategorier.size(); i++) {
            Button btn = new Button(kategorier.get(i).getNavn());
            btn.setId(String.valueOf(i));
            int btnsPrRow = 4;
            paneKat.add(btn, i % btnsPrRow, i / btnsPrRow);
            btn.getStyleClass().add("btnKat");
            // Event listeners
            //btn.setOnAction(this::selectPrislisteAction);
            btn.setOnAction(event -> this.selectKategoriAction(event));
        }

        return paneKat;
    }

    /**
     *
     * @param event
     */
    private void selectKategoriAction(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        int id = Integer.parseInt(btn.getId());
        this.paneSalg.getChildren().remove(this.paneKategorier);
        this.lblKategoriHeadline.setText(btn.getText());
        this.paneProdukter = this.createProdukter();
        this.paneSalg.add(this.paneProdukter, 0,1);

    }

    private GridPane createOrdre()
    {
        // --------------------------------------------------------
        // Temp ordrelinjer
        ArrayList<tmpOrdrelinje> ordrelinjer = new ArrayList<>();

        tmpOrdrelinje ol1 = new tmpOrdrelinje();
        ordrelinjer.add(ol1);
        ol1.setNavn("IPA");
        ol1.setAntal(1);
        ol1.setStkPris(68.50);
        ol1.setRabat(0);

        tmpOrdrelinje ol2 = new tmpOrdrelinje();
        ordrelinjer.add(ol2);
        ol2.setNavn("Hvedeøl");
        ol2.setAntal(1);
        ol2.setStkPris(55.99);
        ol2.setRabat(10);

        tmpOrdrelinje ol3 = new tmpOrdrelinje();
        ordrelinjer.add(ol3);
        ol3.setNavn("Sort Silke");
        ol3.setAntal(3);
        ol3.setStkPris(49.25);
        ol3.setRabat(20);
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
        for (tmpOrdrelinje ol : ordrelinjer) {
            col = -1;
            row++;

            Label lblProduktnavn = new Label(ol.getNavn());
            paneOrdre.add(lblProduktnavn, ++col, row);
            lblProduktnavn.getStyleClass().add("lblProduktnavn");

            TextField txtfAntal = new TextField(String.valueOf(ol.getAntal()));
            paneOrdre.add(txtfAntal, ++col, row);
            txtfAntal.getStyleClass().add("txtfAntal");

            TextField txtfStkPris = new TextField(String.valueOf(ol.getStkPris()));
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


    private GridPane createProdukter()
    {
        // ------------------------------------
        // tmpProdukter
        ArrayList<tmpProdukt> produkter = new ArrayList<tmpProdukt>();

        tmpProdukt p;
        p = new tmpProdukt();
        produkter.add(p);
        p.setNavn("Klosterbryg");
        p.setPris(57.50);
        p.setBeskrivelse("KLOSTERBRYG, 6,0 %\n" +
                "Klosterbryg er en fusion af en\n" +
                "engelsk strong ale og en dansk\n" +
                "luksusøl. Den er krydret med\n" +
                "de klassiske humler Fuggles og\n" +
                "Goldings. Nyd den som en allround\n" +
                "øl eller til lyse retter. Drik\n" +
                "den ved kældertemperatur,\n" +
                "cirka 6 grader Celsius, evt. en\n" +
                "anelse varmere, for at aromaen\n" +
                "kan komme til sin ret.");

        p = new tmpProdukt();
        produkter.add(p);
        p.setNavn("Sweet Georgia Brown");
        p.setPris(45.25);
        p.setBeskrivelse("SWEET GEORGIA BROWN, 5,5 %\n" +
                "Sweet Georgia Brown blev lanceret til\n" +
                "Fodvarmeriets jazztelt i sommeren 2009. Den\n" +
                "blev så stor en succes at den nu fås fast på\n" +
                "flaske. Den er inspireret af de klassiske\n" +
                "engelske brown ales, her med en let sødme\n" +
                "fra de mørke malte og rørsukker. Fås kun på\n" +
                "60 cl flaske.");

        p = new tmpProdukt();
        produkter.add(p);
        p.setNavn("Sweet Georgia Brown");
        p.setPris(45.25);
        p.setBeskrivelse("Beskrivelse");
        // ------------------------------------


        GridPane paneProdukter = new GridPane();
        paneProdukter.setGridLinesVisible(true);
        paneProdukter.setPadding(new Insets(20));
        paneProdukter.setHgap(10);
        paneProdukter.setVgap(10);
        paneProdukter.getStyleClass().add("paneProdukter");

        for (int i = 0; i < produkter.size(); i++) {
            GridPane paneProdSelect = new GridPane();
            paneProdSelect.setGridLinesVisible(true);
            paneProdSelect.setPadding(new Insets(20));
            paneProdSelect.setHgap(10);
            paneProdSelect.setVgap(10);
            paneProdSelect.getStyleClass().add("paneProdSelect");

            TextArea txtaProd = new TextArea();
            txtaProd.setText(produkter.get(i).getNavn());
            txtaProd.setEditable(false);
            txtaProd.getStyleClass().add("txtaProdSelect");
            txtaProd.setOnMouseClicked(event -> this.købProdukt(event));
            int elmsPrRow = 4;
            paneProdSelect.add(txtaProd, 0, 0);


            paneProdukter.add(paneProdSelect, i % elmsPrRow, i / elmsPrRow);

            //Button btn = new Button(produkter.get(i).getNavn());
            //btn.setId(String.valueOf(i));
            //btn.setMinWidth(100);
            //btn.setPrefWidth(200);
            //btn.setPrefHeight(100);
            //btn.setMaxWidth(btnMaxWidth);
            //int btnsPrRow = 4;
            //paneProdukter.add(btn, i % btnsPrRow, i / btnsPrRow);
            //btn.getStyleClass().add("btnKat");
            // Event listeners
            //btn.setOnAction(this::selectPrislisteAction);
            //btn.setOnAction(event -> this.selectKategoriAction(event));
        }

        return paneProdukter;

    }

    private void købProdukt(Event event)
    {
        //Button btn = (Button) event.getSource();
        //int id = Integer.parseInt(btn.getId());

        System.out.println("Tilføj");
    }


}