package com.example.pertemuan_13;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    protected Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void switchStage(String fxmlFileName, String title) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource(fxmlFileName));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        Controller c = fxmlLoader.getController();
        c.setStage(stage);
    }


}
