package gui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Optional;


public class WindowAfregnet extends Stage {

    private Label lblTotalValue;
    private Label lblKlippekortBeloeb;
    private Label lblKasseIndMetode;
    private Label lblKasseIndBeloeb;
    private Label byttePenge;

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


    public void setByttePenge(String byttePenge)
    {
        this.byttePenge.setText(byttePenge);
    }


    /**
     *
     *
     */
    private void initContent(GridPane paneWrapper)
    {
        GridPane paneMaster = new GridPane();
        paneWrapper.add(paneMaster, 0, 0);
        paneMaster.setGridLinesVisible(true);
        paneMaster.setPadding(new Insets(20));
        paneMaster.setHgap(10);
        paneMaster.setVgap(10);

        int masterCol = 0;
        int masterRow = 0;
        int tmpCol = 0;
        int tmpRow = 0;

        // Total
        Label lblTotal = new Label("Total");
        lblTotal.getStyleClass().add("lblTotal");
        paneMaster.add(lblTotal, masterCol++, masterRow);

        this.lblTotalValue = new Label();
        this.lblTotalValue.getStyleClass().add("lblTotalValue");
        GridPane.setHalignment(this.lblTotalValue, HPos.RIGHT);
        paneMaster.add(lblTotalValue, masterCol++, masterRow);

        // Klippekort
        masterCol = 0;
        masterRow++;

        Label lblKlippekort = new Label("Klippekort");
        lblKlippekort.getStyleClass().add("lblKlippekort");
        paneMaster.add(lblKlippekort, masterCol++, masterRow);

        this.lblKlippekortBeloeb = new Label();
        GridPane.setHalignment(this.lblKlippekortBeloeb, HPos.RIGHT);
        this.lblKlippekortBeloeb.getStyleClass().add("lblKlippekortBeloeb");
        paneMaster.add(this.lblKlippekortBeloeb, masterCol++, masterRow);

        // Kasse ind
        masterCol = 0;
        masterRow++;

        this.lblKasseIndMetode = new Label();
        this.lblKasseIndMetode.getStyleClass().add("lblKasseIndMetode");
        paneMaster.add(this.lblKasseIndMetode, masterCol++, masterRow);

        this.lblKasseIndBeloeb = new Label();
        GridPane.setHalignment(this.lblKasseIndBeloeb, HPos.RIGHT);
        this.lblKasseIndBeloeb.getStyleClass().add("lblKasseIndBeloeb");
        paneMaster.add(this.lblKasseIndBeloeb, masterCol++, masterRow);











    }


    private void okAction()
    {
        this.hide();
    }
}
