package com.example.pertemuan_13;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

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
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private Button add;
    @FXML
    private TableView<Mahasiswa> tableMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, String> colNrp;

    @FXML
    private TableColumn<Mahasiswa, String> colNama;
    @FXML private TextField tfNrp;
    @FXML private TextField tfNama;

    ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();

    @FXML
    public void clearTextFields() {
        tfNrp.clear();
        tfNama.clear();
    }

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

    public void loadUserData(){
        mahasiswaList.clear();
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


    }


    public void onLogoutButtonClick() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login-view.fxml"));
//        Scene scene = new Scene(loader.load());
//        stage.setScene(scene);
//        stage.setTitle("Login");
//        stage.show();

        this.switchStage("Login-view.fxml","Login");

    }

    public void onAddButtonClick() throws IOException{
        String newNrp = tfNrp.getText();
        String newNama = tfNama.getText();

        if (newNrp.isEmpty() || newNama.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR );
            alert.show();
            return;
        }
        try (Connection db = databaseConnection.getConnection();
             PreparedStatement query = db.prepareStatement("INSERT INTO mahasiswa(NRP, Nama) VALUES(?, ?)")) {

            query.setString(1, newNama);
            query.setString(2, newNrp);

            query.executeUpdate();
            mahasiswaList.add(new Mahasiswa(newNrp, newNama));
            tableMahasiswa.refresh();
            clearTextFields();
            loadUserData();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void onDeleteButtonClick() {
        Mahasiswa selected = tableMahasiswa.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Pilih data yang ingin dihapus.");
            alert.show();
            return;
        }

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM mahasiswa WHERE NRP = ?")) {

            stmt.setString(1, selected.getNrp());
            stmt.executeUpdate();

            mahasiswaList.remove(selected);
            tableMahasiswa.refresh();
            clearTextFields();

        } catch (SQLException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Gagal Hapus");
            alert.setContentText("Terjadi kesalahan saat menghapus data.");
            alert.show();
        }
    }


    public void onUpdateButtonClick() {
        Mahasiswa selected = tableMahasiswa.getSelectionModel().getSelectedItem();
        if (selected == null){
            Alert alert = new Alert(Alert.AlertType.ERROR );
            alert.show();
            return;
        }
            String newNrp = tfNrp.getText();
            String newNama = tfNama.getText();

            if (newNrp.isEmpty() || newNama.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR );
                alert.show();
                return;
            }
            try (Connection db = databaseConnection.getConnection();
                 PreparedStatement query = db.prepareStatement("UPDATE mahasiswa SET Nama = ?, NRP = ? WHERE NRP = ?")) {

                query.setString(1, newNama);
                query.setString(2, newNrp);
                query.setString(3, selected.getNrp());
                query.executeUpdate();

                // Update objek lokal dan refresh table
                selected.setNrp(newNrp);
                selected.setNama(newNama);
                loadUserData();
                clearTextFields();

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }



}
