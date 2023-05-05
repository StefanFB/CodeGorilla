package carsharing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customers {

    static List<Customer> customerList = new ArrayList<>();

    public static void createCustomer(Connection conn) {
        // Ask for customer name
        System.out.print("\nEnter the customer name:\n> ");
        Scanner scan = new Scanner(System.in);
        String customerName = scan.nextLine();

        //Execute query
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  String.format("INSERT INTO customer (name) " +
                    "VALUES ('%s')", customerName);
            stmt.executeUpdate(sql);
            System.out.println("The customer was added!");

        } catch (Exception e) {
            System.out.println("Something went wrong with the connection at createCustomer");
            System.out.println(e.getMessage());
        }
    }

    static boolean listAllCustomers(Connection conn) {
        Statement stmt = null;
        try {
            //Execute query
            stmt = conn.createStatement();
            String sql =  "SELECT id, name FROM customer";
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            while(rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String customerName = rs.getString("name");

                // Add to arraylist, update counter
                Customer customer = new Customer(id, customerName);
                customerList.add(customer);
                count++;
            }
            // If no result, inform user
            if (count == 0) {
                System.out.println("\nThe customer list is empty!");
                rs.close();
                return false;
            } else {
                System.out.println("\nCustomer list:");
                for (int i = 0; i < customerList.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, customerList.get(i).customerName);
                }
            }

            // Clean-up environment
            rs.close();
        } catch(Exception e) {
            System.out.println("Something went wrong with listAllCustomers");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public static void rentACar(Connection conn, int customerID) {
        // If customer has already rented a car, deny the rental of a new car
        Statement stmt = null;
        Integer carID = 0;
        String companyName = null;
        String carName = null;
        try {
            //Execute query
            stmt = conn.createStatement();
            String sql_carCheck = String.format("SELECT rented_car_id FROM customer " +
                    "WHERE id = %d", customerID);
            ResultSet rs = stmt.executeQuery(sql_carCheck);

            while (rs.next()) {
                // Retrieve by column name
                carID = rs.getInt("rented_car_id");
            }
            // If there is no car rented, inform user
            if (carID != 0) {
                System.out.println("\nYou've already rented a car!");
                rs.close();
                return;
            }
        } catch(Exception e) {
            System.out.println("Something went wrong with rentACar (check if user has rented a car");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // Let customer choose a company. Only when one or more companies exist
        Scanner scan = new Scanner(System.in);
        if (Companies.getAllCompanies()) {
            // Also print the back-option
            System.out.print("0. Back\n> ");

            // Listen for input user
            int companyID = scan.nextInt();

            // List all available cars from that company
            stmt = null;
            carID = null;
            carName = null;
            try {
                //Execute query
                stmt = conn.createStatement();
                String sql_carCheck = String.format("SELECT car.id, car.name, car.company_id FROM car " +
                        "LEFT JOIN customer ON customer.rented_car_id = car.id " +
                        "WHERE customer.name IS NULL AND car.company_id = %d", companyID);
                ResultSet rs = stmt.executeQuery(sql_carCheck);
                System.out.println("Choose a car: (after select stmt)");

                List<Car> carOptions = new ArrayList<>();

                while(rs.next()) {
                    // Retrieve by column name
                    carID = rs.getInt("car.id");
                    carName = rs.getString("car.name");
                    companyID = rs.getInt("car.company_id");

                    // Print the options
                    System.out.printf("%d. %s\n", carID, carName);
                    Car car = new Car(carID, carName);
                    carOptions.add(car);
                }
                System.out.println("0. Back\n> ");

                // Wait for input. Let customer choose a car from that company
                int carChoice = scan.nextInt();
                // Update chosen car data stored in arraylist, adjusting for the index starting at 0
                carID = carOptions.get(carChoice - 1).id;
                carName = carOptions.get(carChoice - 1).name;

                // Run SQL command to set a rented_car_id to this customer
                stmt = conn.createStatement();
                String sql_rentThisCar =  String.format("UPDATE customer SET customer.rented_car_id = %d " +
                        "WHERE id = %d", carID, customerID);
                stmt.executeUpdate(sql_rentThisCar);

                // Print message to confirm successful rental of car
                System.out.printf("You rented '%s'\n", carName);

                // Clean-up environment
                rs.close();
            } catch(Exception e) {
                System.out.println("Something went wrong with rentACar (listing possible car options");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void returnMyRentedCar(Connection conn, int customerID) {
        // Get rented car from customer
        Statement stmt = null;
        Integer carID = null;
        String companyName = null;
        String carName = null;
        try {
            // Check for a rented car
            stmt = conn.createStatement();
            String sql_carCheck =  String.format("SELECT rented_car_id FROM customer " +
                    "WHERE id = %d", customerID);
            ResultSet rs = stmt.executeQuery(sql_carCheck);

            while(rs.next()) {
                // Retrieve by column name
                carID = rs.getInt("rented_car_id");
            }
            // If there is no car rented, inform user
            if (carID == null || carID == 0) {
                System.out.println("\nYou didn't rent a car!");
                rs.close();
            } else {
                // SET rented_car_id to NULL for this customer
                stmt = conn.createStatement();
                String sql_returnCar =  String.format("UPDATE customer SET customer.rented_car_id = NULL " +
                        "WHERE id = %d", customerID);
                stmt.executeUpdate(sql_returnCar);

                // Display successfull return of car
                System.out.println("\nYou've returned a rented car!");

            }
            // Clean-up environment
            rs.close();
        } catch(Exception e) {
            System.out.println("Something went wrong with returnRentedCar");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void myRentedCar(Connection conn, int customerID) {
        Statement stmt = null;
        Integer carID = null;
        String companyName = null;
        String carName = null;
        try {
            //Execute query
            stmt = conn.createStatement();
            String sql_carCheck =  String.format("SELECT rented_car_id FROM customer " +
                    "WHERE id = %d", customerID);
            ResultSet rs = stmt.executeQuery(sql_carCheck);

            while(rs.next()) {
                // Retrieve by column name
                carID = rs.getInt("rented_car_id");
            }
            // If there is no car rented, inform user
            if (carID == null || carID == 0) {
                System.out.println("\nYou didn't rent a car!");
                rs.close();
            } else {
                //Execute query
                stmt = conn.createStatement();
                String sql_getCar =  String.format("SELECT car.name, company.name FROM car " +
                        "JOIN company ON car.company_id = company.id " +
                        "WHERE car.id = %d", carID);
                ResultSet rsCar = stmt.executeQuery(sql_getCar);

                // Get rented car
                while(rsCar.next()) {
                    // Retrieve by column name
                    carName = rsCar.getString("car.name");
                    companyName = rsCar.getString("company.name");
                }

                // Display car and company name
                System.out.printf("\nYour rented car:\n%s\nCompany:\n%s\n", carName, companyName);

                // Clean up
                rsCar.close();
            }

            // Clean-up environment
            rs.close();
        } catch(Exception e) {
            System.out.println("Something went wrong with myRentedCar");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
