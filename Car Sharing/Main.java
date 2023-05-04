package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";

    // String arrays for menu options
    static String[] mainMenu = new String[]{
            "\n1. Log in as a manager",
            // "2. Execute SQL command",
            "0. Exit"};
    static String[] managerMenu = new String[]{
            "\n1. Company list",
            "2. Create a company",
            "0. Back"
    };

    static boolean isRunning = true;
    static boolean isLoggedIn = false;

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection, enable auto-commit
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //Execute query
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS company (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) UNIQUE NOT NULL )";
            stmt.executeUpdate(sql);

            // Let user choose option from main menu
            while (isRunning) {
                showMainMenu();
                while (isLoggedIn) {
                    showManagerMenu();
                }
            }

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
    private static void showMainMenu() {
        // Print the main menu streaming from the array
        Arrays.stream(mainMenu).forEach(System.out::println);
        System.out.print("> ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 0 -> isRunning = false;
            case 1 -> isLoggedIn = true;
            case 2 -> executeSQLCommand();
        }
    }

    private static void showManagerMenu() {
        // Print the manager menu streaming from the array
        Arrays.stream(managerMenu).forEach(System.out::println);
        System.out.print("> ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 0 -> isLoggedIn = false;
            case 1 -> Companies.getAllCompanies();
            case 2 -> Companies.addCompany();
        }
    }

    private static void executeSQLCommand() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nPlease enter your query:\n> ");
        String query = scan.nextLine();

        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection, enable auto-commit
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            conn.setAutoCommit(true);

            //Execute query
            stmt = conn.createStatement();
            stmt.executeUpdate(query);

            // Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
