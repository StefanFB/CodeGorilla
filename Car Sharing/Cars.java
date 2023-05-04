package carsharing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cars {

    static List<Car> carList = new ArrayList<>();
    static int thisCompany = 0;

    static void listAllCars(Connection conn, int id) {
        // Clear carList when another company has been invoked, update thisCompany to current
        if (id != thisCompany) {
            carList.clear();
            thisCompany = id;
        }
        //Execute query
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  String.format("SELECT id, name FROM car WHERE company_id = %d", id);
            ResultSet rs = stmt.executeQuery(sql);

            int counter = 0;
            int carID = 0;
            String carName;
            while(rs.next()) {
                // Retrieve by column name
                carID = rs.getInt("id");
                carName = rs.getString("name");

                // Display values, update counter
                Car car = new Car(carID, carName);
                carList.add(car);
                counter++;
            }
            // If no result, inform user
            if (counter == 0) {
                System.out.println("\nThe car list is empty!");
            } else {
                System.out.println("\nCar list:");
                for (int i = 0; i < carList.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, carList.get(i).name);
                }
            }
        } catch (Exception e) {
            System.out.println("Something went wrong with the connection at listAllCars");
            e.getMessage();
        }
    }

    static void createCar(Connection conn, int companyID) {
        // Ask for car name
        Scanner scan = new Scanner(System.in);

        System.out.printf("\nEnter the car name:\n> ");
        String name = scan.nextLine();
        //Execute query
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql =  String.format("INSERT INTO car (name, company_id) " +
                    "VALUES ('%s', %d)", name, companyID);
            stmt.executeUpdate(sql);
            System.out.println("The car was added!");

        } catch (Exception e) {
            System.out.println("Something went wrong with the connection at createCar");
            System.out.println(e.getMessage());
        }
    }
}
