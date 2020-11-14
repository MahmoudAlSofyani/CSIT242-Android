package Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.example.csit242_project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Models.Supplier;

public class SuppliersAdapter extends ArrayAdapter<Supplier> {

    Context mContext;
    List<Supplier> supplierList = new ArrayList<>();

    NetworkImageView supplierLogo;
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
        if (supplierListItem == null) {
            supplierListItem = LayoutInflater.from(mContext).inflate(R.layout.managesuppliers_customrow, parent, false);
        }

        supplierLogo = (NetworkImageView) supplierListItem.findViewById(R.id.managesuppliersActivity_supplierImage_ImageView);
        supplierName = (TextView) supplierListItem.findViewById(R.id.managesuppliersActivity_supplierName_TextView);
        supplierCategory = (TextView) supplierListItem.findViewById(R.id.managesuppliersActivity_supplierCategory_TextView);

        Supplier currentSupplier = supplierList.get(position);

        supplierName.setText(currentSupplier.getSupplierName());
        supplierCategory.setText(currentSupplier.getSupplierCategory());

        // TODO: Get logo and set the image view to that logo. Also need to modify the Supplier class to support this and this is the url to get the logo
        String url = "https://logo.clearbit.com/" + currentSupplier.getSupplierName().toLowerCase() + ".com";

        return supplierListItem;
    }
}
