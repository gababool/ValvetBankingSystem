package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Valvet implements Serializable {

    private HashMap<Integer, Customer> customers;

    public Valvet(){
        this.customers = new HashMap<Integer, Customer>();
    }

    public Customer createCustomer(String firstName, String surname, int personalNumber) throws Exception{
        if (this.customers.containsKey(personalNumber)){
            throw new AlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, surname, personalNumber);
        createAccount(personalNumber);
        this.customers.put(personalNumber, newCustomer);
        System.out.println("Customer: " + newCustomer + " successfully created!");
        return newCustomer;
    }

    public Customer deleteCustomer(int personalNumber) throws Exception {
        int customerPNO = IOScanner.nextInt("Enter personal number of customer to be deleted: ");
        Customer customer = this.customers.get(customerPNO);
        if (customer.getTotalBalance() != 0) {
            throw new BalanceNotZeroException("Deletion failed. Customer has remaining balance.");
        }
        for (Account account : customer.getAccounts().values()){
            accountNumbers.remove(account.getAccountID());
        }
        System.out.println("Customer " + customer + " successfully removed");
        return this.customers.remove(customerPNO);

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

    public Customer updateCustomerFirstName(int personalNumber, String name){
        Customer customer = customers.get(personalNumber);
        customer.setFirstName(name);
        return customer;
    }

    public Customer updateCustomerSurname(int personalNumber, String name){
        Customer customer = customers.get(personalNumber);
        customer.setSurname(name);
        return customer;
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

    public Transaction makeTransaction(Account sender, Account receiver, double amount) throws Exception{
        currentTransactionNumber += 1;
        Transaction transaction = new Transaction(currentTransactionNumber, amount, sender.getAccountID(), receiver.getAccountID());
        sender.sendTransaction(transaction);
        receiver.receiveTransaction(transaction);
        return transaction;
    }

}
