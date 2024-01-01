package Tests;

import com.github.javafaker.Faker;
import src.main.java.Main;
import src.main.java.model.Account;
import src.main.java.model.Customer;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Random;

public class GenerateDemoData {
    static Random random = new Random();

    public static void generateMultipleCustomers(int AmountOfCustomers) throws Exception{
        for(int i = 0; i < AmountOfCustomers; i++){
            generateFakeCustomer();
        }
    }
    public static Customer generateFakeCustomer() throws Exception {
        return Main.getValvet().createCustomer(randomFirstName(),randomLastName(),randomPersonalNumber());
    }
    public static String generateRandomAccounts() throws Exception{
        HashMap<String, Customer> customers = Main.getValvet().getAllCustomers();
        for (Customer customer : customers.values()){
            for(int i = 0; i < random.nextInt(0,3); i++) {
                Main.getValvet().createAccount(customer.getPERSONAL_NUMBER());
                System.out.println("Created account for customer: " + customer.getFullName());
            }
        }
        return "Random Accounts Successfully generated.";
    }
    public static String LoadAllAccounts() throws Exception{
        HashMap<String, Customer> customers = Main.getValvet().getAllCustomers();
            for (Customer customer : customers.values()){
                for(Account account : customer.getAccounts().values()){
                    double depAmount = random.nextInt(10000, 100000);
                    Main.getValvet().makeTransaction("6543 - 420720965", account.getAccountNumber(), depAmount);
                    System.out.println(depAmount + "Successfully added to account: " + account.getAccountNumber());
                }
            }
        return "All Accounts successfully loaded.";
    }
    public static String randomPersonalNumber() {
        String year = "" + random.nextInt(1930, 2010);

        String month = "" + random.nextInt(1,12);
        month = month.length()<2 ? month = "0" + month : month;

        String day = "" + random.nextInt(1,28);
        day = day.length()<2 ? day = "0" + day : day;

        String lastDigits = "" + random.nextInt(1000, 9999);

        String personalNumber = year + month + day + lastDigits;
        System.out.println(personalNumber);

        return  personalNumber;
    }
    public static String randomFirstName(){
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        System.out.println(firstName);
        return firstName;
    }
    public static String randomLastName(){
        Faker faker = new Faker();
        String lastName = faker.name().lastName();
        System.out.println(lastName);
        return lastName;
    }

}
