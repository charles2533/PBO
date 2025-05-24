package com.example.pertemuan_13;


import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Controller {
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;





    @FXML
    public void onLoginButtonClick() throws IOException { //throws ini biarin kalau error ya error nanti bisa diganti jadi try catch
        if (usernameText.getText().equals("admin")&& passwordText.getText().equals("password")){
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setContentText("Login successfull");
//            alert.show();
//            FXMLLoader fxmlLoader = new FXMLLoader(HomeController.class.getResource("home-view.fxml"));
//            Scene scene = new Scene(fxmlLoader.load());
//            stage.setTitle("Home");
//            stage.setScene(scene);
//            stage.show();
//            HomeController c =fxmlLoader.getController();
//            c.setStage(stage);
            databaseConnection.getConnection();
            this.switchStage("home-view.fxml","Home");

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Login failed");
            alert.show();
        }
//        Alert alert = new Alert(Alert.AlertType.WARNING);
//        alert.setContentText("Login is clicked");
//        alert.show();
    }



}
