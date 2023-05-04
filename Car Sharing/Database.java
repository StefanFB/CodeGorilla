package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";

    private String databaseFileName;
    private Connection connection;

    public Database(String databaseFileName) {
        this.databaseFileName = databaseFileName;
    }

    public Connection getConnection() {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection, enable auto-commit
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL);
            connection.setAutoCommit(true);

        } catch(SQLException se) {
            System.out.println(se.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    public void createCompanyTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS company (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) UNIQUE NOT NULL )";
            stmt.executeUpdate(sql);
        } catch(Exception se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        }
    }

    public void createCarTable(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            String sql =  "CREATE TABLE IF NOT EXISTS car (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255) UNIQUE NOT NULL," +
                    "company_id INT NOT NULL," +
                    "FOREIGN KEY (company_id) REFERENCES company(id) )";
            stmt.executeUpdate(sql);
        } catch(Exception se) {
            System.out.println(se.getMessage());
            se.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if(connection!=null) {
                connection.close();
            }
        } catch(SQLException se){
            se.printStackTrace();
        }
    }
}
