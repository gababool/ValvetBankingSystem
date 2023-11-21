package src.main.java.model;

import java.util.HashMap;

public class Valvet {
    private HashMap<Integer, Customer> customers;

    public Valvet(){
        this.customers =  new HashMap<Integer, Customer>();
        // Replace this later. Should be loaded from JSON.
    }

    public String createCustomer(String firstName, String lastName, int personalNumber) throws Exception{
        if (customers.containsKey(personalNumber)){
            throw new AlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, lastName, personalNumber);
        customers.put(personalNumber, newCustomer);
        return "Customer: " + newCustomer + " successfully created!";
    }

    public String createAccount(int accountID, int personalNumber) throws Exception{
        for (Customer customer : customers.values()) {
                if (customer.getAccounts().get(accountID) != null){
                    throw new AlreadyExistsException(accountID);
                }
            }
        Customer customer = customers.get(personalNumber);
        customer.createAccount(accountID)
    }

    public String deleteCustomer(){

    }

    public String viewAllCustomers(){

    }

    public Customer viewCustomer(){

    }

    public String makeTransaction(){

    }

}
