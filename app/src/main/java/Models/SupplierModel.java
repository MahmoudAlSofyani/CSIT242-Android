package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupplierModel implements Serializable {

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