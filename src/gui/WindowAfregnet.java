package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;


public class WindowAfregnet extends Stage {

    private Label lblTotalValue;
    private Label lblKlippekortBeloeb;
    private Label lblKasseIndMetode;
    private Label lblKasseIndBeloeb;
    private Label lblByttepengeValue;

    /**
	 *
	 * @param title
	 * @param owner
	 */
	public WindowAfregnet(String title, Stage owner) {
		this.initOwner(owner);
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		//this.setWidth(800);
		//this.setHeight(400);
		this.setResizable(false);

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		scene.getStylesheets().add("gui/windowAfregnet.css");
		this.setScene(scene);
	}

    public void setTotal(String total)
    {
        this.lblTotalValue.setText(total);
    }

    public void setKlippekortBeloeb(String beloeb)
    {
        this.lblKlippekortBeloeb.setText(beloeb);
    }

    public void setKasseIndMetode(String metode)
    {
        this.lblKasseIndMetode.setText(metode);
    }

    public void setKasseIndBeloeb(String beloeb)
    {
        this.lblKasseIndBeloeb.setText(beloeb);
    }

    public void setByttepenge(String byttePenge)
    {
        this.lblByttepengeValue.setText(byttePenge);
    }


    /**
     *
     *
     */
    private void initContent(GridPane paneWrapper)
    {
        GridPane paneMaster = new GridPane();
        paneWrapper.add(paneMaster, 0, 0);
        paneMaster.setGridLinesVisible(false);
        paneMaster.setPadding(new Insets(10, 30, 40, 30));
        paneMaster.setHgap(10);
        paneMaster.setVgap(10);
        paneMaster.getStyleClass().add("paneMaster");

        int masterCol = 0;
        int masterRow = 0;
        int tmpCol = 0;
        int tmpRow = 0;

        // --- Info ---
        GridPane paneInfo = new GridPane();
        paneWrapper.add(paneInfo, 0, 0);
        paneInfo.setGridLinesVisible(false);
        paneInfo.setPadding(new Insets(20));
        paneInfo.setHgap(50);
        paneInfo.setVgap(0);
        paneMaster.add(paneInfo, 0, masterRow++);

        // Total
        Label lblTotal = new Label("Total");
        lblTotal.getStyleClass().add("lblTotal");
        paneInfo.add(lblTotal, tmpCol++, tmpRow);

        this.lblTotalValue = new Label();
        this.lblTotalValue.getStyleClass().add("lblTotalValue");
        GridPane.setHalignment(this.lblTotalValue, HPos.RIGHT);
        paneInfo.add(lblTotalValue, tmpCol++, tmpRow);

        // Klippekort
        tmpCol = 0;
        tmpRow++;

        Label lblKlippekort = new Label("Klippekort");
        lblKlippekort.getStyleClass().add("lblKlippekort");
        paneInfo.add(lblKlippekort, tmpCol++, tmpRow);

        this.lblKlippekortBeloeb = new Label();
        GridPane.setHalignment(this.lblKlippekortBeloeb, HPos.RIGHT);
        this.lblKlippekortBeloeb.getStyleClass().add("lblKlippekortBeloeb");
        paneInfo.add(this.lblKlippekortBeloeb, tmpCol++, tmpRow);

        // Kasse ind
        tmpCol = 0;
        tmpRow++;

        this.lblKasseIndMetode = new Label();
        this.lblKasseIndMetode.getStyleClass().add("lblKasseIndMetode");
        paneInfo.add(this.lblKasseIndMetode, tmpCol++, tmpRow);

        this.lblKasseIndBeloeb = new Label();
        GridPane.setHalignment(this.lblKasseIndBeloeb, HPos.RIGHT);
        this.lblKasseIndBeloeb.getStyleClass().add("lblKasseIndBeloeb");
        paneInfo.add(this.lblKasseIndBeloeb, tmpCol++, tmpRow);

        // Byttepenge
        tmpCol = 0;
        tmpRow++;

        GridPane paneByttepenge = new GridPane();
        paneByttepenge.setPadding(new Insets(10, 0, 0, 0));
        paneByttepenge.setHgap(50);
        paneByttepenge.getStyleClass().add("paneByttepenge");
        paneInfo.add(paneByttepenge, tmpCol++, tmpRow, 2, 1);

        Label lblByttepenge = new Label("Byttepenge");
        lblByttepenge.getStyleClass().add("lblByttepenge");
        paneByttepenge.add(lblByttepenge, 0, 0);

        this.lblByttepengeValue = new Label();
        GridPane.setHalignment(this.lblByttepengeValue, HPos.RIGHT);
        this.lblByttepengeValue.getStyleClass().add("lblByttepengeValue");
        paneByttepenge.add(this.lblByttepengeValue, 1, 0);

        // --- OK ---
        Button btnOk = new Button("OK");
        btnOk.getStyleClass().add("btnOk");
        GridPane.setHalignment(btnOk, HPos.CENTER);
        paneMaster.add(btnOk, 0, masterRow++);
        btnOk.setOnAction(event -> this.hide());

    }

}
