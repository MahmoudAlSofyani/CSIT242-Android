package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.csit242_project.R;

import java.util.ArrayList;
import java.util.List;

import Models.Supplier;

public class SuppliersAdapter extends ArrayAdapter<Supplier> {

    Context mContext;
    List<Supplier> supplierList = new ArrayList<>();

    ImageView supplierLogo;
    TextView supplierName;
    TextView supplierCategory;

    public SuppliersAdapter(Context context, ArrayList<Supplier> list) {
        super(context, 0, list);
        mContext = context;
        supplierList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View supplierListItem = convertView;
        if(supplierListItem == null) {
            supplierListItem = LayoutInflater.from(mContext).inflate(R.layout.managesuppliers_customrow, parent, false);
        }

        supplierLogo = (ImageView) supplierListItem.findViewById(R.id.managesuppliersActivity_supplierImage_ImageView);
        supplierName = (TextView) supplierListItem.findViewById(R.id.managesuppliersActivity_supplierName_TextView);
        supplierCategory = (TextView) supplierListItem.findViewById(R.id.managesuppliersActivity_supplierCategory_TextView);

        Supplier currentSupplier = supplierList.get(position);

        // TODO: Get logo and set the image view to that logo. Also need to modify the Supplier class to support this
        supplierName.setText(currentSupplier.getSupplierName());
        supplierCategory.setText(currentSupplier.getSupplierCategory());

        return supplierListItem;
    }
}
