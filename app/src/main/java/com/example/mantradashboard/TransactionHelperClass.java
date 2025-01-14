package com.example.mantradashboard;

public class TransactionHelperClass {

    String itemname, transtype, quantity, price, deldate, expdate, status;

    public TransactionHelperClass() {;
    }

    public TransactionHelperClass(String itemname, String transtype, String quantity, String price, String deldate, String expdate, String status) {
        this.itemname = itemname;
        this.transtype = transtype;
        this.quantity = quantity;
        this.price = price;
        this.deldate = deldate;
        this.expdate = expdate;
        this.status = status;
    }

    public String getItemName() {
        return itemname;
    }

    public void setItemName(String itemname) {
        this.itemname = itemname;
    }
    public String getTranstype() {
        return transtype;
    }

    public void setTranstype(String transtype) {
        this.transtype = transtype;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeldate() {
        return deldate;
    }

    public void setDeldate(String deldate) {
        this.deldate = deldate;
    }

    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }
    public String getStatus() {return status;}

    public void setStatus(String status) {
        this.status = status;
    }
}
