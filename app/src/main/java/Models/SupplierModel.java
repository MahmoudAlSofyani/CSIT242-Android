package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierModel implements Parcelable {

    private String modelName;
    private List<String> modelColors = null;
    private List<String> modelCapacities = null;

    public SupplierModel() {
    }

    public SupplierModel(String modelName, List<String> modelColors, List<String> modelCapacities) {
        super();
        this.modelName = modelName;
        this.modelColors = modelColors;
        this.modelCapacities = modelCapacities;
    }

    private SupplierModel(Parcel in) {
        this.modelName = in.readString();

    }

    public static final Creator<SupplierModel> CREATOR = new Creator<SupplierModel>() {
        @Override
        public SupplierModel createFromParcel(Parcel in) {
            return new SupplierModel(in);
        }

        @Override
        public SupplierModel[] newArray(int size) {
            return new SupplierModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(modelName);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<String> getModelColors() {
        return modelColors;
    }

    public void setModelColors(List<String> modelColors) {
        this.modelColors = modelColors;
    }

    public List<String> getModelCapacities() {
        return modelCapacities;
    }

    public void setModelCapacities(List<String> modelCapacities) {
        this.modelCapacities = modelCapacities;
    }


}