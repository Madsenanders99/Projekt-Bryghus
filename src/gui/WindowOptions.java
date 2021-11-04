package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Kategori;
import model.Prisliste;
import model.Produkt;
import controller.Controller;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class WindowOptions extends Stage {
	private Controller controller;
	private ComboBox<Kategori> kategorier = new ComboBox<>();
    private ComboBox<Prisliste> prislister = new ComboBox<>();
    TextField txfProdukt = new TextField();
    TextField txfBeskrivelse = new TextField();
    TextField txfPris = new TextField();
    TextField txfKlip = new TextField();


    /**
	 *
	 * @param title
	 * @param owner
	 */
	public WindowOptions(String title, Stage owner) {
		this.controller = controller.getController();
		this.initOwner(owner);
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setMinWidth(900); // 1000
		this.setMinHeight(400);
		this.setWidth(1100);
		this.setHeight(600);
		this.setResizable(false);

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		scene.getStylesheets().add("gui/windowOptions.css");
		this.setScene(scene);
	}

	/**
     *
     * @param pane
     */
    private void initContent(GridPane pane)
    {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.getStyleClass().add("pane");
        
       Label lblProdukt = new Label("Produktnavn");
       pane.add(lblProdukt, 0, 0);
       pane.add(txfProdukt, 1, 0);
       Label lblBeskrivelse = new Label("Produktbeskrivelse");

       pane.add(lblBeskrivelse, 0, 1);
       pane.add(txfBeskrivelse, 1, 1);
       
       Label lblVælgKategori = new Label("Vælg Kategori");
       kategorier = new ComboBox();
       pane.add(lblVælgKategori, 0, 3);
       pane.add(kategorier, 1, 3);
       for (int i = 0; i < controller.getAllKategorier().size(); i++) {
           kategorier.getItems().add(i, controller.getAllKategorier().get(i));
       }
       Label lblVælgPrisliste = new Label ("Vælg prisliste");
       prislister = new ComboBox();
       pane.add(lblVælgPrisliste, 0, 6);
       pane.add(prislister, 1, 6);
        for (int i = 0; i < controller.getAllPrislister().size(); i++) {
            prislister.getItems().add(i, controller.getAllPrislister().get(i));
        }

       Label lblPris = new Label("Vælg pris");
       pane.add(lblPris, 0, 4);

       pane.add(txfPris, 1, 4);
       
       Label lblKlip = new Label("Vælg antal klip");
       pane.add(lblKlip, 0, 5);

       pane.add(txfKlip, 1, 5);
       
       Button btnOpretProdukt = new Button("Opret Produkt");
       pane.add(btnOpretProdukt, 1, 7);
       btnOpretProdukt.setOnAction(event -> this.opretProdukt());
       
       Button btnDone = new Button("Færdig");
       pane.add(btnDone, 7, 8);
       btnDone.setOnAction(event -> this.doneAction());

    }
	private void doneAction() {
		WindowOptions.this.hide();
	}

	private void opretProdukt() {
        String navn = txfProdukt.getText();
        String beskrivelse = txfBeskrivelse.getText();
        int klip = parseInt(txfKlip.getText());
        double pris = parseDouble(txfPris.getText());


        Produkt produkt = new Produkt (navn);
        produkt.setBeskrivelse(beskrivelse);

        prislister.getValue().createPris(produkt, pris, klip);


        kategorier.getValue().addPris(prislister.getValue().getPriser().get(prislister.getValue().getPriser().size() - 1));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Produkt");
        alert.setHeaderText(null);
        alert.setContentText("Produktet er blevet oprettet! :D");

        alert.showAndWait();
    }

}
