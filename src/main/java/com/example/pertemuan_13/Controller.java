package com.example.pertemuan_13;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;


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
