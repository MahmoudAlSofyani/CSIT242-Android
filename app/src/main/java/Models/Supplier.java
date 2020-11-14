package Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Supplier {

    private String supplierName;
    private String supplierCategory;
    private List<SupplierModel> supplierModels = null;

    public Supplier() {
    }

    public Supplier(String supplierName, String supplierCategory, List<SupplierModel> supplierModels) {
        super();
        this.supplierName = supplierName;
        this.supplierCategory = supplierCategory;
        this.supplierModels = supplierModels;
    }

    public Supplier(String supplierName, String supplierCategory) {
        this.supplierName = supplierName;
        this.supplierCategory = supplierCategory;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierCategory() {
        return supplierCategory;
    }

    public void setSupplierCategory(String supplierCategory) {
        this.supplierCategory = supplierCategory;
    }

    public List<SupplierModel> getSupplierModels() {
        return supplierModels;
    }

    public void setSupplierModels(List<SupplierModel> supplierModels) {
        this.supplierModels = supplierModels;
    }

}