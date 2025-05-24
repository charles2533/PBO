package com.example.pertemuan_13;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SignUpController extends Controller {
    @FXML
    private Button signUp;
    @FXML
    private TextField nrp;
    @FXML
    private PasswordField nama;

    @FXML
    public void onSignUpButtonClick() throws IOException {
        String inputNRP = nrp.getText();
        String inputNama = nama.getText();
        try {
            signUpUser(inputNRP,inputNama);
            this.switchStage("home-view.fxml","Home");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean signUpUser(String nrp, String nama) throws SQLException {
        Connection DB = databaseConnection.getConnection();
        String insertFields = "INSERT INTO mahasiswa(NRP, Nama) VALUES(?, ?)";
        try (PreparedStatement statement = DB.prepareStatement(insertFields)) {
            statement.setString(1, nrp);
            statement.setString(2, nama);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return false;
    }



}
