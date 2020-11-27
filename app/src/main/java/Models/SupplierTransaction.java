package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class SupplierTransaction {


    String supplierName;
    HashMap<Integer, String> modeAndQty;
    Double totalDue;

    public SupplierTransaction(String supplierName, HashMap<Integer, String> modeAndQty, Double totalDue) {
        this.supplierName = supplierName;
        this.modeAndQty = modeAndQty;
        this.totalDue = totalDue;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public HashMap<Integer, String> getModeAndQty() {
        return modeAndQty;
    }

    public void setModeAndQty(HashMap<Integer, String> modeAndQty) {
        this.modeAndQty = modeAndQty;
    }

    public Double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(Double totalDue) {
        this.totalDue = totalDue;
    }
}
