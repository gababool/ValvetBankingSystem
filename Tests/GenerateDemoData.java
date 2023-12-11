package Tests;

import com.github.javafaker.Faker;
import src.main.java.Main;
import src.main.java.model.Customer;

import java.util.Random;

public class GenerateDemoData {

    public static void generateMultipleCustomers(int AmountOfCustomers) throws Exception{
        for(int i = 0; i < AmountOfCustomers; i++){
            generateFakeCustomer();
        }
    }
    public static Customer generateFakeCustomer() throws Exception {
        return Main.getValvet().createCustomer(randomFirstName(),randomLastName(),randomPersonalNumber());
    }
    public static String randomPersonalNumber() {
        Random random = new Random();
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
