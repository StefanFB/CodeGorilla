package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";

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
            String create_table_company_sql =  "CREATE TABLE COMPANY (" +
                    "ID INT," +
                    "NAME VARCHAR(255) )";
            stmt.executeUpdate(create_table_company_sql);

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
