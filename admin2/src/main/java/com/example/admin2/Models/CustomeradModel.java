package com.example.admin2.Models;

public class CustomeradModel {
    String customerAddress;


    public CustomeradModel ( String customerAddress) {

        this.customerAddress = customerAddress;
    }


    public String getCustomerAddress ( ) {
        return customerAddress;
    }

    public void setCustomerAddress (String customerAddress) {
        this.customerAddress = customerAddress;
    }


}
