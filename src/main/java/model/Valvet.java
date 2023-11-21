package src.main.java.model;

import java.util.HashMap;

public class Valvet {

    // Replace this later. Should be loaded from JSON.
    private HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();

    public Valvet(){

    }

    public String createCustomer(String firstName, String lastName, int personalNumber) throws Exception{
        if (customers.containsKey(personalNumber)){
            throw new CustomerAlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, lastName, personalNumber);
        customers.put(personalNumber, newCustomer);
        return "Customer: " + newCustomer + " successfully created!";
    }

    public String deleteCustomer(){

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
