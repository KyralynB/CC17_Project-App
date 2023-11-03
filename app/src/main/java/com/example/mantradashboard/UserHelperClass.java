package com.example.mantradashboard;

public class UserHelperClass {

    String fname, lname, email, role, password, contact_num, address;

    public UserHelperClass() {
    }

    public UserHelperClass(String fname, String lname, String email, String role, String password, String contact_num, String address) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.contact_num = contact_num;
        this.address = address;
    }

    public UserHelperClass(String transtype, String quantity, String price, String deldate, String expdate) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
