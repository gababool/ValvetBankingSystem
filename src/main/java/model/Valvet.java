package src.main.java.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Random;

public class Valvet implements Serializable{

    private String clearingNumber;
    private LinkedHashMap<String, Customer> customers;

    public Valvet(String clearingNumber){
        this.clearingNumber = clearingNumber;
        this.customers = new LinkedHashMap<String, Customer>();
    }
    public Valvet(){}

    public Customer createCustomer(String firstName, String surname, String personalNumber) throws Exception{
        if (!checkValidCustomerInfo(firstName, surname, personalNumber)){
            throw new InvalidInputException("Entered information not valid");
        }
        else if (this.customers.containsKey(personalNumber)){
            throw new AlreadyExistsException("Customer already exists.");
        }
        Customer newCustomer = new Customer(firstName, surname, personalNumber);
        this.customers.put(personalNumber, newCustomer);
        createAccount(personalNumber);
        System.out.println("Customer: " + newCustomer + " successfully created!");
        return newCustomer;
    }

    public void deleteCustomer(String personalNumber) throws Exception {
        Customer customer = this.customers.get(personalNumber);
        if (customer.getTotalBalance() != 0) {
           throw new BalanceNotZeroException("Deletion failed. Customer has remaining balance: " + customer.getTotalBalance());
        }
        System.out.println("Customer " + customer + " successfully removed");
        this.customers.remove(personalNumber);
    }

    public void createAccount(String personalNumber) throws Exception {
        if (!customers.containsKey(personalNumber)) {
            throw new NotFoundException("Customer not found");
        }
        Customer customer = this.customers.get(personalNumber);
        String accountNumber = clearingNumber + "-" + generateRandomUniqueNumber();
        customer.createAccount(accountNumber);
    }

    public void deleteAccount(String personalNumber, String accountNumber) throws BalanceNotZeroException, CannotBeZeroException {
        Account account = null;
        for (Customer customer : customers.values()) {
            HashMap<String, Account> accounts = customer.getAccounts();
            if (accounts.containsKey(accountNumber)) {
                account = accounts.get(accountNumber);
            }
        }
        if (!(account.getBalance() == 0)) {
            throw new BalanceNotZeroException("Account could not be deleted. Balance is not zero. ");
        }
        else if (customers.get(personalNumber).getNumberOfAccounts() == 1){
            throw new CannotBeZeroException("Customer cannot have zero accounts");
        }
        customers.get(personalNumber).closeAccount(accountNumber);
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

    public int generateRandomUniqueNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(100000000, 999999999);
        for (Customer customer : customers.values()){
            HashMap<String, Account> accounts = customer.getAccounts();
            if (accounts.containsKey(String.valueOf(randomNumber))){
                generateRandomUniqueNumber();
            }
        }
        return randomNumber;
    }

    public LinkedHashMap<String, Customer> getAllCustomers(){
        return customers;
    }

    public Customer getCustomer(String personalNumber){
        return this.customers.get(personalNumber);
    }

    public Transaction makeTransaction(String senderAccountNumber, String receiverAccountNumber, double amount) throws Exception{
        if (amount <= 0){
            throw new InvalidInputException("Amount must be greater than zero");
        }
        if (senderAccountNumber.isBlank()){
            throw new InvalidInputException("Sending account number cannot be blank");
        }
        if (receiverAccountNumber.isBlank()){
            throw new InvalidInputException("Receiving account number cannot be blank");
        }
        if (senderAccountNumber.matches(".*[a-zA-Z].*")){
            throw new InvalidInputException("Account number cannot contain letters");
        }
        if (receiverAccountNumber.matches(".*[a-zA-Z].*")){
            throw new InvalidInputException("Account number cannot contain letters");
        }

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

        Transaction transaction = new Transaction(amount, receiverAccountNumber, senderAccountNumber);

        if (Objects.equals(senderAccountNumber, receiverAccountNumber)) {
            throw new NotFoundException("Sending account cannot be same as receiving account");
        }
        if (sender == null && receiver == null){
            throw new NotFoundException("Sending and receiving accounts not found");
        }
        else if (sender != null && receiver == null){
            sender.sendTransaction(transaction);
            System.out.println("Withdrawal of " + amount + " successfully made");
        }
        else if (sender == null && receiver != null){
            receiver.receiveTransaction(transaction);
            System.out.println("Deposition of " + amount + " successfully made");
        }
        else {
            sender.sendTransaction(transaction);
            receiver.receiveTransaction(transaction);
        }

        return transaction;
    }

    public boolean checkValidCustomerInfo(String firstName, String surname, String personalNumber){
        boolean validPNO = true;
        boolean validName = true;
        if (firstName.isBlank() || surname.isBlank()){
            validName = false;
        }
        else if (personalNumber.length() != 12 ){
            validPNO = false;
        }
        else if (personalNumber.isBlank()){
            validPNO = false;
        }
        // Add more conditionals if necessary
        return validPNO && validName;
    }

}