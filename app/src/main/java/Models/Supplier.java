package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Supplier implements Parcelable {

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

    public Supplier(Parcel in) {
        this.supplierName = in.readString();
        this.supplierCategory = in.readString();
        this.supplierLogoURL = in.readString();
        this.supplierModels = new ArrayList<>();
        in.readList(this.supplierModels, SupplierModel.class.getClassLoader());
    }


    public static final Creator<Supplier> CREATOR = new Creator<Supplier>() {
        @Override
        public Supplier createFromParcel(Parcel in) {
            return new Supplier(in);
        }

        @Override
        public Supplier[] newArray(int size) {
            return new Supplier[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(supplierName);
        dest.writeString(supplierCategory);
        dest.writeString(supplierLogoURL);
        dest.writeList(supplierModels);
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