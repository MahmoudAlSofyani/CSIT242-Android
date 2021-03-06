package Models;

import java.io.Serializable;
import java.util.List;

public class Supplier implements Serializable{

    private String supplierName;
    private String supplierCategory;
    private List<SupplierModel> supplierModels = null;
    private String supplierLogoURL;

    public Supplier() {
    }

    public Supplier(String supplierName, String supplierCategory, String supplierLogoUrl, List<SupplierModel> supplierModels) {
        super();
        this.supplierName = supplierName;
        this.supplierCategory = supplierCategory;
        this.supplierModels = supplierModels;
        this.supplierLogoURL = supplierLogoUrl;
    }

    public Supplier(String supplierName, String supplierCategory, String supplierLogoURL) {
        this.supplierName = supplierName;
        this.supplierCategory = supplierCategory;
        this.supplierLogoURL = supplierLogoURL;
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

    public String getSupplierLogoURL() { return supplierLogoURL; };
    public void setSupplierLogoURL(String supplierLogoURL) { this.supplierLogoURL = supplierLogoURL; }


}