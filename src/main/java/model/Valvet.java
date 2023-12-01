package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;

public class Valvet implements Serializable{

    private String clearingNumber;
    private HashMap<Integer, Customer> customers;

    public Valvet(String clearingNumber){
        this.clearingNumber = clearingNumber;
        this.customers = new HashMap<Integer, Customer>();
    }
    public Valvet(){}

    public Customer createCustomer(String firstName, String surname, int personalNumber) throws Exception{
        if (this.customers.containsKey(personalNumber)){
            throw new AlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, surname, personalNumber);
        this.customers.put(personalNumber, newCustomer);
        createAccount(personalNumber);
        System.out.println("Customer: " + newCustomer + " successfully created!");
        return newCustomer;
    }

    public Customer deleteCustomer(int personalNumber) throws Exception {
        Customer customer = this.customers.get(personalNumber);
        if (customer.getTotalBalance() != 0) {
            throw new BalanceNotZeroException("Deletion failed. Customer has remaining balance: " + customer.getTotalBalance());
        }
        System.out.println("Customer " + customer + " successfully removed");
        return this.customers.remove(personalNumber);
    }

    public Account createAccount(int personalNumber) throws Exception {
        if (!customers.containsKey(personalNumber)) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = this.customers.get(personalNumber);
        String accountNumber = clearingNumber + "-" + customer.getPERSONAL_NUMBER() + "-" + (customer.getNumberOfAccounts()+1);
        return customer.createAccount(accountNumber);
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
        // Comparator for printing in alphabetical order
        System.out.println("List of customers: ");
        for (Customer customer : this.customers.values()){
            System.out.println(customer);
        }
    }

    public Customer getCustomer(int personalNumber){
        return this.customers.get(personalNumber);
    }

    public Transaction makeTransaction(Account sender, Account receiver, double amount) throws Exception{
        //Exception for account not existing!!!!

        String receiverAccountNumber = receiver.getAccountNumber();
        String senderAccountNumber = sender.getAccountNumber();
        Transaction transaction = new Transaction(amount, receiverAccountNumber, senderAccountNumber);
        sender.sendTransaction(transaction);
        receiver.receiveTransaction(transaction);
        return transaction;
    }

    public Transaction makeWithdrawal(Account sender, String receiverAccountNumber, double amount) throws Exception{
        String senderAccountNumber = sender.getAccountNumber();
        Transaction transaction = new Transaction(amount, receiverAccountNumber, senderAccountNumber);
        sender.sendTransaction(transaction);
        return transaction;
    }

    // We assume this information comes externally and is executed automatically
    public Transaction makeDeposit(String senderAccountNumber, Account receiver, double amount){
        String receiverAccountNumber = receiver.getAccountNumber();
        Transaction transaction = new Transaction(amount, receiverAccountNumber, senderAccountNumber);
        receiver.receiveTransaction(transaction);
        return transaction;
    }
}