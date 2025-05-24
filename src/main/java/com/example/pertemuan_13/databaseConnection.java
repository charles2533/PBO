package com.example.pertemuan_13;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

    public class databaseConnection {
        public static Connection databaseLink;

        public static Connection getConnection(){

            String databaseName = "mahasiswa";// ganti nama sesuai database name
            String user = "postgres";
            String password = "charles253"; // ganti pass ku
            String url = "jdbc:postgresql://localhost:5432/" + databaseName;

            try {
                Class.forName("org.postgresql.Driver");
                databaseLink = DriverManager.getConnection(url, user, password);
                System.out.println("Database berhasil");
            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL Driver not found.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Connection to the database failed.");
                e.printStackTrace();
            }

            return databaseLink;
        }

        public static Statement getStatement() throws SQLException {
            return getConnection().createStatement();
        }
    }

