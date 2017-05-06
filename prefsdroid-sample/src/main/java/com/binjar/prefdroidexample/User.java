package com.binjar.prefdroidexample;

/**
 * Created by Arif Islam on 07-May-17.
 */

public class User {

    private String name;
    private int    age;
    private String address;

    User() {
        //
    }

    User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    void setAge(int age) {
        this.age = age;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }
}
