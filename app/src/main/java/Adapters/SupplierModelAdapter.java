package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.csit242_project.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import Models.Supplier;
import Models.SupplierModel;

public class SupplierModelAdapter extends ArrayAdapter<SupplierModel> {

    private Context mContext;
    private List<SupplierModel> modelList = new ArrayList<>();
    MaterialTextView modelName_TextView;
    MaterialTextView modelColors_TextView;
    MaterialTextView modelCapacities_TextView;

    public SupplierModelAdapter(Context context, ArrayList<SupplierModel> list) {
        super(context, 0, list);
        mContext = context;
        modelList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.supplierinformation_listitem_activity, parent, false);
            SupplierModel currentModel = modelList.get(position);

            modelName_TextView = (MaterialTextView) listItem.findViewById(R.id.supplierInformationActivity_listItem_model_TextView);
            modelColors_TextView = (MaterialTextView) listItem.findViewById(R.id.supplierInformationActivity_listItem_colors_TextView);
            modelCapacities_TextView = (MaterialTextView) listItem.findViewById(R.id.supplierInformationActivity_listItem_capacity_TextView);

            StringBuilder colors = new StringBuilder();
            StringBuilder capacities = new StringBuilder();

            System.out.println(currentModel.getModelName());
            System.out.println(currentModel.getModelColors());

            for(int i = 0; i < currentModel.getModelColors().size(); i++) {
                colors.append(currentModel.getModelColors().get(i));
            }
            for(int i = 0; i < currentModel.getModelCapacities().size(); i++) {
                capacities.append(currentModel.getModelCapacities().get(i));
            }
            modelName_TextView.setText(currentModel.getModelName());
            modelColors_TextView.setText(colors);
            modelCapacities_TextView.setText(capacities);
        }
        return listItem;
    }
}
