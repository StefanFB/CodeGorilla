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
            "2. Log in as a customer",
            "3. Create a customer",
            "4. Execute SQL command",
            "0. Exit"};
    static String[] managerMenu = new String[]{
            "\n1. Company list",
            "2. Create a company",
            "0. Back"
    };

    static String[] customerMenu = new String[]{
            "\n1. Rent a car",
            "2. Return a rented car",
            "3. My rented car",
            "0. Back"
    };

    static boolean isRunning = true;
    static boolean isLoggedInAsManager = false;
    static boolean companyChosen = false;
    static boolean isLoggedInAsCustomer = false;

    static int thisCustomerId = 0;

    public static void main(String[] args) {

        // Set databaseFileName or overwrite with arguments
        String databaseFileName = "carsharing";
        if (args.length > 0 && args[0].equals("-databaseFileName")) {
            databaseFileName = args[1];
        }

        // Set up database and connection
        Database carSharing = new Database(databaseFileName);
        Connection conn = carSharing.getConnection();

        // Create tables: company and car
        carSharing.createCompanyTable(conn);
        carSharing.createCarTable(conn);
        carSharing.createCustomerTable(conn);

        Statement stmt = null;
        try {
            // Let user choose option from main menu
            while (isRunning) {
                showMainMenu(conn);
                while (isLoggedInAsManager) {
                    showManagerMenu();
                    while (companyChosen) {
                        showCompanyMenu(conn);
                    }
                }
                while (isLoggedInAsCustomer) {
                    showCustomerMenu(conn, thisCustomerId);
                }
            }

            // Clean-up environment
            carSharing.closeConnection();
        } catch(Exception se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        }
    }

    private static void showMainMenu(Connection conn) {
        // Print the main menu streaming from the array
        Arrays.stream(mainMenu).forEach(System.out::println);
        System.out.print("> ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            // When isRunning = false, the program will terminate
            case 0 -> isRunning = false;
            // When isLoggedIn = true, the managerMenu will be called
            case 1 -> isLoggedInAsManager = true;
            // When no customers available, this returns false, then we return to main menu
            case 2 -> {
                if (Customers.listAllCustomers(conn)) {

                    // When there are customers, we show them and await choice
                    System.out.print("0. Back\n> ");
                    int customerChoice = scan.nextInt();

                    // When '0. Back' is chosen, auto-return to main menu
                    if (customerChoice != 0) {

                        // Set flag, update chosen user and let main thread handle customer menu
                        isLoggedInAsCustomer = true;
                        thisCustomerId = customerChoice;
                    }
                }
            }
            case 3 -> Customers.createCustomer(conn);
            // Have debug options to directly execute SQL commands
            case 4 -> executeSQLCommand();
        }
    }

    private static void showManagerMenu() {
        // Print the manager menu streaming from the array
        Arrays.stream(managerMenu).forEach(System.out::println);
        System.out.print("> ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 0 -> isLoggedInAsManager = false;

            // Only if companies exist (i.e. when function return true) set flag and go into company menu
            case 1 -> companyChosen = Companies.getAllCompanies();
            case 2 -> Companies.addCompany();
        }
    }

    private static void showCompanyMenu(Connection conn) {
        System.out.println("\nChoose the company:");
        Companies.getAllCompanies();
        System.out.println("0. Back");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        if (choice == 0) {
            companyChosen = false;
        } else {
            Companies.selectCompany(conn, choice);
        }
    }

    private static void showCustomerMenu(Connection conn, int customerID) {
        // Print the manager menu streaming from the array
        Arrays.stream(customerMenu).forEach(System.out::println);
        System.out.print("> ");

        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            // Remove flag for customer, so we return to main menu
            case 0 -> isLoggedInAsCustomer = false;

            // Only if companies exist (i.e. when function return true) set flag and go into company menu
            case 1 -> Customers.rentACar(conn, customerID);
            case 2 -> Customers.returnMyRentedCar(conn, customerID);
            case 3 -> Customers.myRentedCar(conn, customerID);
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
