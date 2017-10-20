package com.businessapp.customer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.businessapp.model.IndividualCustomer;

public class IndividualCustomerTests extends TestCase {
    
    final String emptyStr = "";
    final IndividualCustomer testFirstCustomer = new IndividualCustomer();
    
 
    public void testCustomerConstructor(){
    
        final IndividualCustomer testSecondCustomer = new IndividualCustomer("abab", "12.01.2017", "Hans","Mueller");
        
        assertEquals("abab", testSecondCustomer.getId());
        assertEquals("Hans", testSecondCustomer.getFirstName());
        assertEquals("Mueller", testSecondCustomer.getName());
    
    }
    
    
    public void testName(){
        final String testStr = "Mueller";
        testFirstCustomer.setName(testStr);
        assertEquals(testStr, testFirstCustomer.getName());
        testFirstCustomer.setName(emptyStr);
        assertEquals(emptyStr, testFirstCustomer.getName());
        testFirstCustomer.setName(null);
        assertNull(testFirstCustomer.getName());
    }
 
    public void testFirstName(){
        final String testStr = "Hans";
        testFirstCustomer.setFirstName(testStr);
        assertEquals(testStr, testFirstCustomer.getFirstName());
        testFirstCustomer.setFirstName(emptyStr);
        assertEquals(emptyStr, testFirstCustomer.getFirstName());
        //testFirstCustomer.setFirstName(null);
        //assertNull(testFirstCustomer.getFirstName());
    }
 
    
    //test id
    //test created

 
}
