package com.github.fabiomqs.sample;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashSet;

public class TableViewApplication extends Application {
    @Override
    public void start( Stage stage ) throws Exception {
        final var view       = new TableView<Attachment>();
        final var columns    = view.getColumns();
        final var nameColumn = new TableColumn<Attachment, String>( "Name" );
        nameColumn.setCellValueFactory( new PropertyValueFactory<>( "name" ));
        columns.add(  nameColumn );
        final var loadedColumn = new TableColumn<Attachment, Boolean>( "Translate" );
        loadedColumn.setCellValueFactory( new PropertyValueFactory<>( "checked" ));
        loadedColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
        columns.add( loadedColumn );
        final var items = FXCollections.observableArrayList(
                new Attachment("Att 1", true, "1", "1"),
                new Attachment("Att 2", true, "1", "2"),
                new Attachment("Att 3", true, "1", "3"),
                new Attachment("Att 4", true, "1", "4"),
                new Attachment("Att 5", true, "1", "5"),
                new Attachment("Att 6", true, "1", "6"),
                new Attachment("Att 7", true, "1", "7"),
                new Attachment("Att 8", true, "1", "8"),
                new Attachment("Att 9", true, "1", "9"));
        view.setItems( items );
        view.setEditable( true );
        final var tranlateBtn = new Button( "Translate" );
        tranlateBtn.setMaxWidth( Double.MAX_VALUE );
        tranlateBtn.setOnAction( e -> {
            //final var translate = new HashSet<Attachment>();
            for( final var att : view.getItems()) {
                if( att.checkedProperty().get()) {
                    translate(att);
                }
            }

        });
        var root = new BorderPane( view, null, null, tranlateBtn, null );
        root.setPrefSize(800,600);
        stage.setScene( new Scene( root ));
        BorderPane.setAlignment( tranlateBtn, Pos.CENTER );
        stage.show();
    }

    private void translate(Attachment att) {
        System.out.println("=========================================================================");
        System.out.println("Translating { fileName: " + att.getName() + ", partId: " + att.getPartId() + ", attId: " + att.getAttachmentId() + " }");
        System.out.println("=========================================================================");
    }

    public static void main( String[] args ) {
        launch( args );
    }
}