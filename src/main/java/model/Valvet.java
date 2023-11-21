package src.main.java.model;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class Valvet {

    // Replace this later. Should be loaded from JSON.
    private HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();

    public Valvet(){}

    public String createCustomer() throws Exception{
        String firstName = IOScanner.nextLine("Enter customer first name");
        String lastName = IOScanner.nextLine("Enter customer last name");
        int personalNumber = IOScanner.nextInt("Enter customer personal number");
        if (customers.containsKey(personalNumber)){
            throw new CustomerAlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, lastName, personalNumber);
        customers.put(personalNumber, newCustomer);
        return "Customer: " + newCustomer + " successfully created!";
    }

    public String deleteCustomer() throws Exception {
        int customerPNO = IOScanner.nextInt("Enter personal number of customer to be deleted");
        Customer customer = customers.get(customerPNO);
        if (customer.getTotalBalance() != 0){
            throw new BalanceNotZeroException("Deletion failed. Customer has remaining balance.");
        }
        customers.remove(customerPNO);
        return "Customer " + customer + " successfully removed";
    }

    public String createAccount(){

    }

    public String deleteAccount(){

    }

    public String viewAllCustomers(){

    }

    public Customer viewCustomer(){

    }

    public String makeTransaction(){

    }

}
