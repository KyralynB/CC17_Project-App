package com.example.mantradashboard;

public class UserHelperClass {

    String fname, lname, email, password, contact_num, address;

    public UserHelperClass() {
    }

    public UserHelperClass(String fname, String lname, String email, String password, String contact_num, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.contact_num = contact_num;
        this.address = address;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
