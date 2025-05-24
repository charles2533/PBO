module com.example.pertemuan_13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.pertemuan_13 to javafx.fxml;
    exports com.example.pertemuan_13;
}