package Models;

import java.util.HashMap;

public class Transaction {

    String supplierName;
    String timestamp;
    String totalDue;
    HashMap<String, Integer> items;

    public Transaction(String supplierName, String timeStamp, String totalDue, HashMap<String, Integer> items) {
        this.supplierName = supplierName;
        this.timestamp = timeStamp;
        this.totalDue = totalDue;
        this.items = items;
    }

    public Transaction() {}

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(String totalDue) {
        this.totalDue = totalDue;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }
}
