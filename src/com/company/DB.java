package com.company;

import java.io.File;
import java.sql.*;

public class DB {
    private final String url = "jdbc:sqlite:C:/Program Files/sqlite/db/";
    private final String path = "C:/Program Files/sqlite/db/";
    private String fileName;

    // Only used within this class! //
    public void correctNameEnding(String name) {
        // Correct to .db ending //
        if (name.contains(".db")) {
            this.fileName = name;
        } else {
            this.fileName = name + ".db";
        }
    }

    // Only used within this class! //
    public boolean checkIfDbExists() {
        File db = new File(this.path + this.fileName);
        if (db.exists()) {
            //System.out.println("exists");
            return true;
        } else {
            //System.out.println("DB: " + this.fileName + " does not exists");
            return false;
        }
    }

    public void connectToDB(String name) {
        correctNameEnding(name);
        if (checkIfDbExists()) {
            try (Connection conn = DriverManager.getConnection(this.url + this.fileName)) {
                if (conn != null) {
                    System.out.println("Connected to: " + this.fileName);
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        } else {
            System.out.println(this.fileName + " does not exists");
        }
    }

    public void createNewDatabase(String name) {
        correctNameEnding(name);
        if (!checkIfDbExists()) {
            try (Connection conn = DriverManager.getConnection(this.url + this.fileName)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println(this.fileName + " has been created!");
                }
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        } else {
            System.out.println(this.fileName + " does already exists!");
        }
    }

    public void createNewTable(String tableName) {
        if (checkIfDbExists()) {
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n"
                    + "id integer PRIMARY KEY,\n"
                    + "name text NOT NULL,\n"
                    + "capacity real\n"
                    + ");";

            try (Connection conn = DriverManager.getConnection(this.url + this.fileName);
                 Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println(tableName + " was created in " + this.fileName);
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            }
        } else {
            System.out.println("Cannot insert table into non-existing DB!");
        }
    }

}
