package carsharing;

public class Customer {
    int customerID;
    String customerName;
    Integer rentedCarID;

    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.rentedCarID = null;
    }
}
