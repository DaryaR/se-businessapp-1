package com.businessapp.customer;
import org.junit.Test;
import com.businessapp.model.IndividualCustomer;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;



public class IndividualCustomerTests {

    IndividualCustomer testCustomer = new IndividualCustomer();

    //Test Firstname
    @Test
    public void testFirstName(){
        testCustomer.setFirstName("Peter");
        assertEquals("Peter", testCustomer.getFirstName());
    }

    //Test Firstname Null
    @Test
    public void testFirstNameNull(){
        testCustomer.setFirstName(null);
        assertEquals(null, testCustomer.getFirstName());
    }

    //Test Firstname Empty
    @Test
    public void testFirstNameEmpty(){
        testCustomer.setFirstName("");
        assertEquals("", testCustomer.getFirstName());
    }

    //Test Name
    @Test
    public void testName(){
        testCustomer.setName("Meyer");
        assertEquals("Meyer", testCustomer.getName());
    }

    // Test Name Null
    @Test
    public void testNameNull(){
        testCustomer.setName(null);
        assertEquals(null, testCustomer.getName());
    }

    //Test Name Empty
    @Test
    public void testNameEmpty(){
        testCustomer.setName("");
        assertEquals("", testCustomer.getName());
    }

    //Test Id
    @Test
    public void testId(){
        String s1 = new String("234");
        testCustomer.setId(s1);
        assertThat(s1 == testCustomer.getId(), is(true));
    }

    //Test Id Null
    @Test
    public void testIdNull(){
        String s1 = null;
        testCustomer.setId(s1);
        assertThat(s1 == testCustomer.getId(), is(true));
    }
    
    //Test Id Empty
    @Test
    public void testIdEmpty(){
        String s1 = "";
        testCustomer.setId(s1);
        assertThat(s1 == testCustomer.getId(), is(true));
    }

    //Test Created
    @Test
    public void testCreated(){
        testCustomer.setCreated(new Date(2014,12,12));
        assertEquals(new Date(2014,12,12), testCustomer.getCreated());
    }
    
    //Test Created Null
    @Test
    public void testCreatedNull(){
        testCustomer.setCreated(null);
        assertEquals(null, testCustomer.getCreated());
    }

}