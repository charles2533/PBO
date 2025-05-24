package com.example.pertemuan_13;


import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController extends Controller {
    @FXML
    private TextField nrp;
    @FXML
    private PasswordField nama;
    @FXML
    private Button signUp;

    @FXML
    public void onSignUpButtonClick() throws IOException {
        switchStage("signUp.fxml","SignUp");

    }



    @FXML
    public void onLoginButtonClick() throws IOException {//throws ini biarin kalau error ya error nanti bisa diganti jadi try catch
        String inputNRP = nrp.getText();
        String inputNama = nama.getText();
        if (validateLogin(inputNRP,inputNama)){
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

    public boolean validateLogin(String nrp, String nama) {
        Connection DB = databaseConnection.getConnection();

        String verifyLogin = "SELECT * FROM mahasiswa WHERE NRP = ? AND Nama = ?";
        try(PreparedStatement statement = DB.prepareStatement(verifyLogin)) {

            statement.setString(1, nrp);
            System.out.println("halo");
            statement.setString(2, nama);
            ResultSet rs = statement.executeQuery();

            return rs.next(); // true jika user ditemukan
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
