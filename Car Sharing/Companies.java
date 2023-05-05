package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Companies {

    static List<Company> companyList = new ArrayList<>();

    public static boolean getAllCompanies() {
        // Reset companyList, since we will read the list from the database
        companyList.clear();

        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(Main.JDBC_DRIVER);

            // Open a connection, enable auto-commit
            conn = DriverManager.getConnection(Main.DB_URL);
            conn.setAutoCommit(true);

            //Execute query
            stmt = conn.createStatement();
            String sql =  "SELECT id, name FROM company";
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            while(rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");

                // Display values, update counter
                Company company = new Company(id, name);
                companyList.add(company);
                count++;
            }
            // If no result, inform user
            if (count == 0) {
                System.out.println("\nThe company list is empty!");
                rs.close();
                return false;
            } else {
                System.out.println("\nChoose a company:");
                for (int i = 0; i < companyList.size(); i++) {
                    System.out.printf("%d. %s\n", i+1, companyList.get(i).name);
                }
            }

            // Clean-up environment
            rs.close();
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
        return true;
    }

    public static void addCompany() {
        Scanner scan = new Scanner(System.in);

        System.out.printf("\nEnter the company name:\n> ");
        String name = scan.nextLine();

        Connection conn = null;
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName(Main.JDBC_DRIVER);

            // Open a connection, enable auto-commit
            conn = DriverManager.getConnection(Main.DB_URL);
            conn.setAutoCommit(true);

            //Execute query
            stmt = conn.createStatement();
            String sql =  String.format("INSERT INTO company (name) VALUES ('%s')", name);
            // System.out.println(sql);
            stmt.executeUpdate(sql);
            System.out.println("The company was created!");

            conn.close();

        } catch(SQLException se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void selectCompany(Connection conn, int ID) {
        String companyName = null;
        Statement stmt = null;
        try {
            //Execute query
            stmt = conn.createStatement();
            String sql =  String.format("SELECT id, name FROM company WHERE id = %d", ID);
            ResultSet rs = stmt.executeQuery(sql);

            int count = 0;
            int id = 0;

            while(rs.next()) {
                // Retrieve by column name
                id = rs.getInt("id");
                companyName = rs.getString("name");

                // Display values, update counter
                // System.out.printf("%d. %s\n", id, companyName);
                count++;
            }
            // If no result, inform user
            if (count == 0) {
                System.out.println("\nThis company does not exist!");
            }

            // Show menu for this company
            int option = -1;
            while (option != 0) {
                System.out.printf("\n'%s' company\n1. Car list\n2. Create a car\n0. Back\n> ", companyName);
                Scanner scan = new Scanner(System.in);
                option = scan.nextInt();
                switch(option) {
                    case 1 -> Cars.listAllCars(conn, ID);
                    case 2 -> Cars.createCar(conn, ID);
                    case 0 -> Main.companyChosen = false;
                }
            }
        } catch(Exception se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        }
    }
}
