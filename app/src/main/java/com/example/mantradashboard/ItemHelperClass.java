package com.example.mantradashboard;

public class ItemHelperClass {

    String itemName, qrCode, lowLev, itemType, unit, desc;

    public ItemHelperClass() {
    }

    public ItemHelperClass(String itemName, String qrCode, String lowLev, String itemType, String unit, String desc) {
        this.itemName = itemName;
        this.qrCode = qrCode;
        this.lowLev = lowLev;
        this.itemType = itemType;
        this.unit = unit;
        this.desc = desc;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getLowLev() {
        return lowLev;
    }

    public void setLowLev(String lowLev) {
        this.lowLev = lowLev;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
