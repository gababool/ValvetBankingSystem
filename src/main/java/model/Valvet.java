package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Valvet implements Serializable {
    private HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();

    public Valvet(){}

    public String createCustomer() throws Exception{
        String firstName = IOScanner.nextLine("Enter customer first name: ");
        String lastName = IOScanner.nextLine("Enter customer last name: ");
        int personalNumber = IOScanner.nextInt("Enter customer personal number: ");
        if (this.customers.containsKey(personalNumber)){
            throw new AlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, lastName, personalNumber);
        this.customers.put(personalNumber, newCustomer);
        return "Customer: " + newCustomer + " successfully created!";
    }

    public String deleteCustomer() throws Exception {
        int customerPNO = IOScanner.nextInt("Enter personal number of customer to be deleted: ");
        Customer customer = this.customers.get(customerPNO);
        if (customer.getTotalBalance() != 0) {
            throw new BalanceNotZeroException("Deletion failed. Customer has remaining balance.");
        }
        this.customers.remove(customerPNO);
        return "Customer " + customer + " successfully removed";
    }

    public String createAccount(int accountID, int personalNumber) throws Exception{
        for (Customer customer : this.customers.values()) {
                if (customer.getAccounts().get(accountID) != null){
                    throw new AlreadyExistsException(accountID);
                }
            }
        Customer customer = this.customers.get(personalNumber);
        customer.createAccount(accountID);

        return "Account " + accountID + "was successfully created.";
    }

    public void viewAllCustomers(){
        System.out.println("List of customers: ");
        for (Customer customer : this.customers.values()){
            System.out.println(customer);
        }
    }

    public Customer getCustomer(int personalNumber){
        return this.customers.get(personalNumber);
    }

    public String makeTransaction(){
        return "TEMP";
    }

}
