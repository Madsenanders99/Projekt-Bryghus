package gui;

import javafx.event.ActionEvent;
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


public class WindowNumPad extends Stage {

    private TextField txtfDisplay;
    private int value;
    private Boolean okAction = false; // Will be set to true on press of ok button.

    /**
	 *
	 * @param title
	 * @param owner
	 */
	public WindowNumPad(String title, Stage owner) {
		this.initOwner(owner);
		this.initStyle(StageStyle.UTILITY);
		this.initModality(Modality.APPLICATION_MODAL);
		//this.setWidth(900);
		//this.setHeight(600);
		this.setResizable(false);

		this.setTitle(title);
		GridPane pane = new GridPane();
		this.initContent(pane);

		Scene scene = new Scene(pane);
		scene.getStylesheets().add("gui/windowNumPad.css");
		this.setScene(scene);
	}

    public Boolean getOkAction()
    {
        return this.okAction;
    }

    public int getValue()
    {
        return this.value;
    }

    public void setValue(int value)
    {
        this.value = value;
        this.txtfDisplay.setText(String.valueOf(value));
    }

    private void reset()
    {
        this.txtfDisplay.clear();
        this.value = 0;
        this.okAction = false;
    }



	/**
     *
     * @param pane
     */
    private void initContent(GridPane masterPane)
    {
        GridPane pane = new GridPane();
        masterPane.add(pane, 0, 0);
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(15));
        pane.setHgap(10);
        pane.setVgap(10);

        // Display
        this.txtfDisplay = new TextField();
        this.txtfDisplay.getStyleClass().add("txtfDisplay");
        pane.add(this.txtfDisplay, 0, 0);
        GridPane.setHalignment(this.txtfDisplay, HPos.CENTER);

        
        // --- NumPad ---------------------------------
        GridPane paneNumPad = new GridPane();
        pane.add(paneNumPad, 0, 1);
        paneNumPad.setGridLinesVisible(false);
        paneNumPad.setPadding(new Insets(10, 0, 0, 0));
        paneNumPad.setHgap(5);
        paneNumPad.setVgap(5);
        paneNumPad.setAlignment(Pos.CENTER);
        int currRow = 0;

        // Back button
        Button btnBack = new Button("Back");
        btnBack.getStyleClass().add("btnBack");
        paneNumPad.add(btnBack, 0, currRow, 2, 1);
        btnBack.setOnAction(event -> this.backAction());

        // Clear button
        Button btnClear = new Button("C");
        btnClear.getStyleClass().add("btnClear");
        paneNumPad.add(btnClear, 2, currRow, 1, 1);
        btnClear.setOnAction(event -> this.clearAction());
        
        currRow++;

        // NumPad buttons
        int btnsPrRow = 3;
        int extCols = 1;
        int[] keys = {7, 8, 9, 4, 5, 6, 1, 2, 3, 0};
        for (int i = 0; i < keys.length; i++) {
            Button btnNum = new Button(String.valueOf(keys[i]));
            btnNum.setId(String.valueOf(keys[i]));
            // paneNumPad.add(element, at col, at ro, extending columns, extending rows)
            if (i == keys.length - 1) {
                extCols = btnsPrRow;
                btnNum.getStyleClass().add("btnNumZero");
            }
            else {
                btnNum.getStyleClass().add("btnNum");
            }
            paneNumPad.add(btnNum, i % btnsPrRow, (i / btnsPrRow) + currRow, extCols, 1);
            int tmp = i;
            btnNum.setOnAction(event -> this.numKeyPressedAction(keys[tmp]));
        }
        
        // OK / Annuller buttons
        GridPane paneAction = new GridPane();
        pane.add(paneAction, 0, 2);
        paneAction.setGridLinesVisible(false);
        paneAction.setPadding(new Insets(20, 0, 0, 0));
        paneAction.setHgap(5);
        paneAction.setVgap(5);
        
        Button btnOk = new Button("OK");
        btnOk.getStyleClass().add("btnOk");
        paneAction.add(btnOk, 0, 0);
        btnOk.setOnAction(event -> this.okAction());

        Button btnAnnuller = new Button("Annuller");
        btnAnnuller.getStyleClass().add("btnAnnuller");
        paneAction.add(btnAnnuller, 1, 0);
        btnAnnuller.setOnAction(event -> this.annullerAction());
        

    }

    private void backAction()
    {
        int length = this.txtfDisplay.getText().length();
        this.txtfDisplay.deleteText(length - 1, length);

    }

    private void clearAction()
    {
        this.reset();
    }

    private void numKeyPressedAction(int value)
    {
       this.txtfDisplay.appendText(String.valueOf(value));
    }

    private void okAction()
    {
        try {
            this.value = Integer.parseInt(this.txtfDisplay.getText());
            this.okAction = true;
            this.hide();
        } catch (NumberFormatException nfe) {
            ButtonType btnOk = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            String txt = "";
            Alert alert = new Alert(Alert.AlertType.WARNING, txt, btnOk);
            alert.setHeaderText("Så skriv da et tal!");
            alert.setTitle("Ugyldigt input");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    private void annullerAction()
    {
        this.reset();
        this.hide();
    }
}
