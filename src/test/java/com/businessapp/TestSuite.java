package com.businessapp;
import com.businessapp.customer.IndividualCustomerTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;



@RunWith(Suite.class)
@Suite.SuiteClasses({
   AppTest.class,
   IndividualCustomerTests.class
})

public class TestSuite {   
}  	