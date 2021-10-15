package gui;



import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MainApp extends Application
{
    private Stage stage;
    private Scene sceneStart;
    private Scene sceneSalg;
    private final ArrayList<Button> btnsPrisliste = new ArrayList<>();

    //private ListView<Konference> lvwKonferencer;
    // private Button btnForedrag, btnDeltagere, btnUdflugter, btnHoteller;
    //private Konference selectedKonference;
    // private DeltagereWindow deltagereWindow;
    //private HotellerWindow hotellerWindow;
    // private UdflugterWindow udflugterWindow;

    // Temp fields
    private ArrayList<String> prislister = new ArrayList<>();

    @Override
    public void init() {
        // --- Start: Temp ---
        prislister.add("Detailsalg");
        prislister.add("Barsalg");
        // --- End: Temp ---

        //Controller.init();

    }

    @Override
    public void start(Stage stage) {
        // Set-up stage
        this.stage = stage;
        this.stage.setTitle("Aarhus Bryghus");
        this.stage.setResizable(true);
        this.stage.setMinWidth(400);
        this.stage.setMinHeight(400);
        this.stage.setWidth(1100);
        this.stage.setHeight(600);

        // Set-up sceneStart
        this.initSceneStart();
        this.stage.setScene(sceneStart);
        this.stage.show();

        // -------------------------
        this.initSceneSalg();
        this.stage.setScene(this.sceneSalg);
        // --------------------------

        //this.deltagereWindow = new DeltagereWindow("", this.stage);
        //this.udflugterWindow = new UdflugterWindow("", this.stage);
        //this.hotellerWindow = new HotellerWindow("", this.stage);
    }

    /**
     * @param pane
     */
    private void initSceneStart()
    {
        GridPane pane = new GridPane();
        this.sceneStart = new Scene(pane);
        this.sceneStart.getStylesheets().add("gui/mainWindow.css");
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // pane.add(element, at col, at ro, extending columns, extending rows)
        // Prisliste buttons gridpane
        GridPane panePrislisteBtns = new GridPane();
        pane.add(panePrislisteBtns, 0, 0);
        panePrislisteBtns.setGridLinesVisible(false);
        panePrislisteBtns.setPadding(new Insets(0, 30, 30, 30));
        panePrislisteBtns.setHgap(10);
        panePrislisteBtns.setVgap(30);
        //panePrislisteBtns.setPrefHeight(500);
        //GridPane.setHalignment(panePrislisteBtns, HPos.CENTER);
        pane.setAlignment(Pos.CENTER);

        // Buttons
        int btnMinWidth = 200;
        for (int i = 0; i < prislister.size(); i++) {
            Button btn = new Button(prislister.get(i));
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
     * @param id
     */
    private void initSceneSalg()
    {
        // Hent Kategorier fra controller
        ArrayList<String> kategorier = new ArrayList<>();
        kategorier.add("Lys øl");
        kategorier.add("Mørk øl");
        kategorier.add("Lyserød øl");
        kategorier.add("Spirit of Aarhus");
        kategorier.add("Merchandise");


        GridPane pane = new GridPane();
        this.sceneSalg = new Scene(pane);
        this.sceneSalg.getStylesheets().add("gui/sceneSalg.css");
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.getStyleClass().add("pane");


        // pane.add(element, at col, at ro, extending columns, extending rows)

        // --- Kategorier -----------------------------------------------
        Label lblKat = new Label("Kategorier");
        lblKat.getStyleClass().add("lblKat");
        pane.add(lblKat, 0, 0);

        GridPane paneKat = new GridPane();
        paneKat.setGridLinesVisible(true);
        paneKat.setPadding(new Insets(20));
        paneKat.setHgap(10);
        paneKat.setVgap(10);
        paneKat.getStyleClass().add("paneKat");
        pane.add(paneKat, 0, 1);

        ArrayList<Button> btnsKategori = new ArrayList<>();

        for (int i = 0; i < kategorier.size(); i++) {
            Button btn = new Button(kategorier.get(i));
            btn.setId(String.valueOf(i));
            btn.setMinWidth(100);
            btn.setPrefWidth(200);
            btn.setPrefHeight(100);
            //btn.setMaxWidth(btnMaxWidth);
            int btnsPrRow = 3;
            paneKat.add(btn, i % btnsPrRow, i / btnsPrRow);
            btn.getStyleClass().add("btnKat");
            // Event listeners
            //btn.setOnAction(this::selectPrislisteAction);
            //btn.setOnAction(event -> this.selectPrislisteAction(event));
        }



        // --- Ordre ----------------------------------------------------
        Label lblOrdre = new Label("Ordre");
        lblOrdre.getStyleClass().add("lblOrdre");
        pane.add(lblOrdre, 1, 0);
        GridPane paneOrdre = new GridPane();
        paneOrdre.setGridLinesVisible(true);
        paneOrdre.setPadding(new Insets(20));
        paneOrdre.setHgap(10);
        paneOrdre.setVgap(10);
        paneOrdre.getStyleClass().add("paneOrdre");
        pane.add(paneOrdre, 1, 1);


    }


    /**
     *
     * @param event
     */
    private void selectPrislisteAction(ActionEvent event)
    {
        Button btn = (Button) event.getSource();
        int id = Integer.parseInt(btn.getId());
        System.out.println("Setting prisliste: " + this.prislister.get(id));
        this.initSceneSalg();
        this.stage.setScene(this.sceneSalg);

    }

}
