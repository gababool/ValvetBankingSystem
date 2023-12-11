package Tests;

import org.junit.jupiter.api.Test;
import src.main.java.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ValvetTests {

    private Valvet valv = new Valvet("1337");
    @Test
    public void shouldCreateCustomer() throws Exception {
        String customerString = "Name: Hans, Surname: Bertil, PNO: 123";
        Customer customer = valv.createCustomer("Hans", "Bertil", "123");
        assertEquals(customer.toString(), customerString);
    }

    @Test
    public void customerShouldHaveEmptyAccount() throws Exception {
        Customer customer = valv.createCustomer("Hans", "Bertil", "123");
        assertEquals(customer.getAccount("1337-123-1").toString(), "Account 1337-123-1: Balance 0,000000 kr");
    }

    @Test
    public void shouldCreateSecondAccount() throws Exception {
        Customer customer = valv.createCustomer("Hans", "Bertil", "123");
        valv.createAccount("123");
        assertEquals(customer.getAccount("1337-123-2").toString(),"Account 1337-123-2: Balance 0,000000 kr");
    }


    @Test
    public void shouldDeleteCustomer() throws Exception {

    }


}

