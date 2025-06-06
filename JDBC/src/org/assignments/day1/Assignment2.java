package org.assignments.day1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/first", "root", "1210")) {

            int choice;
            do {
                System.out.println("Menu");
                System.out.println("1. Create Table");
                System.out.println("2. Display Columns of Table");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        String createSQL = createTable(sc);
                        try (PreparedStatement psCreate = dbConnection.prepareStatement(createSQL)) {
                            psCreate.execute();
                            System.out.println("Table created successfully!");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;

                    case 2:
                        System.out.print("Enter table name: ");
                        String tableName = sc.next();
                        showTableColumns(dbConnection, tableName);
                        break;

                    case 3:
                        System.out.println("Exiting");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String createTable(Scanner sc) {
        System.out.print("Enter table name: ");
        String tableName = sc.next();
        int choice;
        ArrayList<String> columnDefs = new ArrayList<>();
        ArrayList<String> columnNames = new ArrayList<>();

        do {
            System.out.print("Enter column name: ");
            String col = sc.next();
            System.out.println("Choose datatype: 1) VARCHAR  2) INT  3) FLOAT");
            int type = sc.nextInt();

            String dataType;
            switch (type) {
            case 1 : dataType = "VARCHAR(255)"; break;
            case 2 : dataType =  "INT"; break;
            case 3 : dataType =  "FLOAT"; break;
            default : dataType =  "VARCHAR(255)";
        };

            columnNames.add(col);
            columnDefs.add(col + " " + dataType);

            System.out.print("Add more columns? 1 for yes, 0 for no: ");
            choice = sc.nextInt();
        } while (choice != 0);

        System.out.println("Which column to set as PRIMARY KEY?");
        for (int i = 0; i < columnNames.size(); i++) {
            System.out.println((i + 1) + ": " + columnNames.get(i));
        }

        int pk = sc.nextInt();
        if (pk > 0 && pk <= columnDefs.size()) {
            pk--;
            columnDefs.set(pk, columnDefs.get(pk) + " PRIMARY KEY");
        } else {
            System.out.println("Invalid choice, no primary key set.");
        }

        String joinedColumns = String.join(", ", columnDefs);
        return "CREATE TABLE " + tableName + " ( " + joinedColumns + " );";
    }

    private static void showTableColumns(Connection conn, String tableName) {
        try {
            DatabaseMetaData dmd = conn.getMetaData();
            ResultSet tables = dmd.getTables(null, "first", tableName, new String[]{"TABLE"});

            if (tables.next()) {
                ResultSet resultCol = dmd.getColumns(null, "first", tableName, null);
                System.out.println("Columns in table '" + tableName + "':");
                while (resultCol.next()) {
                    String columnName = resultCol.getString("COLUMN_NAME");
                    System.out.println(columnName);
                }
                resultCol.close();
            } else {
                System.out.println("Table '" + tableName + "' not found!");
            }

        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }
}

