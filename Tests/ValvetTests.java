package Tests;

import org.junit.jupiter.api.Test;
import src.main.java.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ValvetTests {

    private Valvet valv = new Valvet("1337");
    @Test
    public void shouldCreateCustomer() throws Exception {
        String customerString = "Name: Hans, Surname: Bertil, PNO: 123";
        Customer customer = valv.createCustomer("Hans", "Bertil", 123);
        assertEquals(customer.toString(), customerString);
    }

    @Test
    public void customerShouldHaveEmptyAccount() throws Exception {
        Customer customer = valv.createCustomer("Hans", "Bertil", 123);
        assertEquals(customer.getAccount("1337-123-1").toString(), "Account 1337-123-1: Balance 0,000000 kr");
    }

    @Test
    public void shouldCreateSecondAccount() throws Exception {
        Customer customer = valv.createCustomer("Hans", "Bertil", 123);
        valv.createAccount(123);
        assertEquals(customer.getAccount("1337-123-2").toString(),"Account 1337-123-2: Balance 0,000000 kr");
    }

    @Test
    public void transactionSuccessful() throws Exception {
        Customer customer1 = valv.createCustomer("Hans", "Bertil", 123);
        Customer customer2 = valv.createCustomer("Sven", "Ingvar", 456);
        valv.makeDeposit("5555555", customer1.getAccount("1337-123-1"), 500);
        valv.makeTransaction(customer1.getAccount("1337-123-1"), customer2.getAccount("1337-456-1"), 200);
        assertEquals(customer1.getAccount("1337-123-1").getBalance(), 300);
        assertEquals(customer2.getAccount("1337-456-1").getBalance(), 200);
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {

    }


}

