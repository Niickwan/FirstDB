package com.company;

public class Main {

    public static void main(String[] args) {
	    DB db = new DB();
        db.connectToDB("test");
        db.createNewDatabase("test");
        db.connectToDB("test");
        db.createNewTable("MyTable");
    }
}
