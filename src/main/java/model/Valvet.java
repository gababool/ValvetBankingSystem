package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class Valvet implements Serializable{

    private long clearingNumber;
    private HashMap<Integer, Customer> customers;
    private Set<Long> accountNumbers;
    private int currentTransactionNumber;
    private Random random = new Random();

    public Valvet(long clearingNumber){
        this.clearingNumber = (clearingNumber * 1000000000);
        this.customers = new HashMap<Integer, Customer>();
        this.accountNumbers = new HashSet<>();
        this.currentTransactionNumber = 100000;
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
        for (Account account : customer.getAccounts().values()){
            accountNumbers.remove(account.getAccountID());
        }
        System.out.println("Customer " + customer + " successfully removed");
        return this.customers.remove(personalNumber);
    }

    public Account createAccount(int personalNumber) throws Exception {
        if (!customers.containsKey(personalNumber)) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = this.customers.get(personalNumber);
        long accountID = clearingNumber + generateRandomNumber();
        Account createdAccount = customer.createAccount(accountID);
        accountNumbers.add(accountID);
        return createdAccount;
    }

    private long generateRandomNumber(){
        long number = 0;
        do {
            number = random.nextInt(100000000, 999999999);

        } while (accountNumbers.contains(number));
        return number;
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
