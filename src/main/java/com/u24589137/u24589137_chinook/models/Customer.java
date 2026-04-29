package com.u24589137.u24589137_chinook.models;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String city;
    private String address;
    private String country;

    public Customer(int id, String firstName, String lastName, String email, String phone, String address, String city, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public int getId() { 
        return id;
    }
    public String getFirstName() { 
        return firstName; 
    }
    public String getLastName() { 
        return lastName; 
    }
    public String getEmail() { 
        return email; 
    }
    public String getPhone() { 
        return phone; 
    }
    public String getCountry() { 
        return country; 
    }
    public String getCity() { 
        return city; 
    }
    public String getAddress() { 
        return address; 
    }
}
