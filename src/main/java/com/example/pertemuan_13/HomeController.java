package com.example.pertemuan_13;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeController extends Controller {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person,String> nameCol;
    @FXML
    private TableColumn<Person,Integer> ageCol;
    @FXML
    private Button logOut;
    @FXML
    private TableView<Mahasiswa> tableMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, String> colNrp;

    @FXML
    private TableColumn<Mahasiswa, String> colNama;


    //    public void initialize(){
//        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//
//        ObservableList<Person> personList = FXCollections.observableArrayList();
//        personList.add(new Person("Charles",19));
//        personList.add(new Person("Lix",20));
//        personList.add(new Person("les",21));
//        personTable.setItems(personList);
//
//    }
public void initialize() {
    colNrp.setCellValueFactory(new PropertyValueFactory<>("nrp"));
    colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));

    tableMahasiswa.setItems( getMahasiswaList());
}

    public ObservableList<Mahasiswa> getMahasiswaList() {
        ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();

        try {
            Connection connection = databaseConnection.getConnection();
            String query = "SELECT * FROM mahasiswa";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println(resultSet);

            while (resultSet.next()) {
                String nrp = resultSet.getString("NRP");
                String nama = resultSet.getString("Nama");

                mahasiswaList.add(new Mahasiswa(nrp, nama));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Bisa tambah alert di sini kalau mau
        }

        return mahasiswaList;
    }


    public void onLogoutButtonClick() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//        stage.setTitle("Login");
//        stage.show();

        this.switchStage("Login-view.fxml","Login");

    }


}
