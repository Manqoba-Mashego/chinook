/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.u24589137.u24589137_chinook.models;

/**
 *
 * @author manqo
 */
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String title;
    private String city;
    private String country;
    private String phone;
    private String supervisor;
    
    public Employee(int id, String firstName, String lastName, String title, String city, String country, String phone, String supervisor){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.supervisor = supervisor;
    }
    
    public int getId(){ 
        return id; 
    }
    public String getFirstName(){
        return firstName; 
    }
    public String getLastName(){
        return lastName; 
    }
    public String getTitle(){ 
        return title; 
    }
    public String getCity(){ 
        return city; 
    }
    public String getCountry(){ 
        return country; 
    }
    public String getPhone(){ 
        return phone; 
    }
    public String getSupervisor(){ 
        return supervisor;
    }
    
}
