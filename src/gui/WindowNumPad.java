package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class WindowNumPad extends Stage {

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

	/**
     *
     * @param pane
     */
    private void initContent(GridPane masterPane)
    {
        GridPane pane = new GridPane();
        masterPane.add(pane, 0, 0);
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(30));
        pane.setHgap(5);
        pane.setVgap(5);

        int currRow = 0;

        // Display
        TextField txtfDisplay = new TextField();
        txtfDisplay.getStyleClass().add("txtfDisplay");
        pane.add(txtfDisplay, 0, currRow, 3, 1);
        currRow++;

        // Back button
        Button btnBack = new Button("Back");
        btnBack.getStyleClass().add("btnBack");
        pane.add(btnBack, 0, currRow, 2, 1);

        // Clear button
        Button btnClear = new Button("C");
        btnClear.getStyleClass().add("btnClear");
        pane.add(btnClear, 2, currRow, 1, 1);
        currRow++;

        // NumPad buttons
        int btnsPrRow = 3;
        int extCols = 1;
        int[] keys = {7, 8, 9, 4, 5, 6, 1, 2, 3, 0};
        for (int i = 0; i < keys.length; i++) {
            Button btnNum = new Button(String.valueOf(keys[i]));
            // pane.add(element, at col, at ro, extending columns, extending rows)
            if (i == keys.length - 1) {
                extCols = btnsPrRow;
                btnNum.getStyleClass().add("btnNumZero");
            }
            else {
                btnNum.getStyleClass().add("btnNum");
            }
            pane.add(btnNum, i % btnsPrRow, (i / btnsPrRow) + currRow, extCols, 1);
        }

    }

}
