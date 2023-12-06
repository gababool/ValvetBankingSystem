package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Valvet implements Serializable{

    private String clearingNumber;
    private HashMap<String, Customer> customers;

    public Valvet(String clearingNumber){
        this.clearingNumber = clearingNumber;
        this.customers = new HashMap<String, Customer>();
    }
    public Valvet(){}

    public Customer createCustomer(String firstName, String surname, String personalNumber) throws Exception{
        if (this.customers.containsKey(personalNumber)){
            throw new AlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, surname, personalNumber);
        this.customers.put(personalNumber, newCustomer);
        createAccount(personalNumber);
        System.out.println("Customer: " + newCustomer + " successfully created!");
        return newCustomer;
    }

    public Customer deleteCustomer(String personalNumber) throws Exception {
        Customer customer = this.customers.get(personalNumber);
        if (customer.getTotalBalance() != 0) {
            throw new BalanceNotZeroException("Deletion failed. Customer has remaining balance: " + customer.getTotalBalance());
        }
        System.out.println("Customer " + customer + " successfully removed");
        return this.customers.remove(personalNumber);
    }

    public Account createAccount(String personalNumber) throws Exception {
        if (!customers.containsKey(personalNumber)) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = this.customers.get(personalNumber);
        String accountNumber = clearingNumber + "-" + customer.getPERSONAL_NUMBER() + "-" + (customer.getNumberOfAccounts()+1);
        return customer.createAccount(accountNumber);
    }


    public Customer updateCustomerFirstName(String personalNumber, String name){
        Customer customer = customers.get(personalNumber);
        customer.setFirstName(name);
        return customer;
    }

    public Customer updateCustomerSurname(String personalNumber, String name){
        Customer customer = customers.get(personalNumber);
        customer.setSurname(name);
        return customer;
    }

    public void viewAllCustomers(){
        // Comparator for printing in alphabetical order
        System.out.println("List of customers: ");
        for (Customer customer : this.customers.values()){
            System.out.println(customer);
        }
    }

    public Customer getCustomer(String personalNumber){
        return this.customers.get(personalNumber);
    }

    public Transaction makeTransaction(String senderAccountNumber, String receiverAccountNumber, double amount) throws Exception{
        Account sender = null;
        for (Customer customer : customers.values()){
            HashMap<String, Account> accounts = customer.getAccounts();
                if (accounts.containsKey(senderAccountNumber)){
                    sender = accounts.get(senderAccountNumber);
                }
            }
        Account receiver = null;
        for (Customer customer : customers.values()){
            HashMap<String, Account> accounts = customer.getAccounts();
            if (accounts.containsKey(receiverAccountNumber)){
                receiver = accounts.get(receiverAccountNumber);
            }
        }

        if (receiver == null){
            throw new NotFoundException("Receiver account not found");
        }
        if (sender == null){
            throw new NotFoundException("Sender account not found");
        }

        Transaction transaction = new Transaction(amount, receiver.getAccountNumber(), sender.getAccountNumber());
        sender.sendTransaction(transaction);
        receiver.receiveTransaction(transaction);
        return transaction;
    }

    public Transaction makeWithdrawal(String senderAccountNumber, String receiverAccountNumber, double amount) throws Exception{
        Account sender = null;
        for (Customer customer : customers.values()){
            HashMap<String, Account> accounts = customer.getAccounts();
            if (accounts.containsKey(senderAccountNumber)){
                sender = accounts.get(senderAccountNumber);
            }
        }
        if (sender == null){
            throw new NotFoundException("Sender account not found");
        }
        Transaction transaction = new Transaction(amount, receiverAccountNumber, sender.getAccountNumber());
        sender.sendTransaction(transaction);
        return transaction;
    }

    // We assume this information comes externally and is executed automatically
    public Transaction makeDeposit(String senderAccountNumber, String receiverAccountNumber, double amount) throws NotFoundException {
        Account receiver = null;
        for (Customer customer : customers.values()){
            HashMap<String, Account> accounts = customer.getAccounts();
            if (accounts.containsKey(receiverAccountNumber)){
                receiver = accounts.get(receiverAccountNumber);
            }
        }
        if (receiver == null){
            throw new NotFoundException("Receiver account not found");
        }
        Transaction transaction = new Transaction(amount, receiverAccountNumber, senderAccountNumber);
        receiver.receiveTransaction(transaction);
        return transaction;
    }
}