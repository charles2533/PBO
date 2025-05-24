package com.example.pertemuan_13;

import javafx.fxml.FXML;
import javafx.scene.control.*;



public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button helloButton;
    //    private boolean clicked = false;
    @FXML
    private TextField Text;



    @FXML
    protected void onAlertButtonClick(){
        String nama = Text.getText();
        Alert alert = new Alert(Alert.AlertType.INFORMATION );

        alert.setTitle("Message");
        alert.setHeaderText("Message");
        alert.setContentText("Hello, " + nama + "!");
        alert.show();

    }
}