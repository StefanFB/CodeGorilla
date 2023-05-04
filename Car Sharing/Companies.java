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
    List<Company> companyList = new ArrayList<>();

    public static void getAllCompanies() {
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
                System.out.printf("%d. %s\n", id, name);
                count++;
            }
            // If no result, inform user
            if (count == 0) {
                System.out.println("\nThe company list is empty!");
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
