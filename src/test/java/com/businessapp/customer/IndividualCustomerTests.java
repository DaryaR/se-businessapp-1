package com.businessapp.customer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.businessapp.model.IndividualCustomer;

public class IndividualCustomerTests extends TestCase {
    
    final String emptyStr = "";
    final IndividualCustomer testCustomer = new IndividualCustomer();
    
 
 
    public void testName(){
        final String testStr = "Petersen";
        testCustomer.setName(testStr);
        assertEquals(testStr, testCustomer.getName());
        testCustomer.setName(emptyStr);
        assertEquals(emptyStr, testCustomer.getName());
        testCustomer.setName(null);
        assertNull(testCustomer.getName());
    }
 
    public void testFirstName(){
        final String testStr = "Peter";
        testCustomer.setFirstName(testStr);
        assertEquals(testStr, testCustomer.getFirstName());
        testCustomer.setFirstName(emptyStr);
        assertEquals(emptyStr, testCustomer.getFirstName());
        testCustomer.setFirstName(null);
        assertNull(testCustomer.getFirstName());
    }
 

 
}
