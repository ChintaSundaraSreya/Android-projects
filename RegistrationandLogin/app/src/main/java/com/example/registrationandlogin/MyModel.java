package com.example.registrationandlogin;

public class MyModel {
    String name,city,number,address,email;

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public MyModel() {
    }

    public MyModel( String address, String city, String email,String name, String number) {
        this.address=address;
        this.city = city;
        this.email=email;
        this.name = name;
        this.number = number;
    }
}
