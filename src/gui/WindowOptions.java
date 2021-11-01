package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WindowOptions extends Stage
{

    /**
     *
     * @param title
     * @param owner
     */
    public WindowOptions(String title, Stage owner)
    {
        this.initOwner(owner);
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setMinWidth(1000); // 1000
        this.setMinHeight(400);
        this.setWidth(1200);
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


       Button btnDone = new Button("Færdig");
       pane.add(btnDone, 0, 0);
       btnDone.setOnAction(event -> this.doneAction());

    }


    private void doneAction()
    {
        WindowOptions.this.hide();
    }




}
