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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Models.Transaction;

public class TransactionsAdapter extends ArrayAdapter<Transaction> {

    private Context mContext;
    private List<Transaction> transactionList = new ArrayList<>();

    public TransactionsAdapter(Context context, ArrayList<Transaction> list) {
        super(context, 0, list);
        mContext = context;
        transactionList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.managetransactions_customrow, parent, false);
        }
        Transaction currentTransaction = transactionList.get(position);

        MaterialTextView supplierName_TextView = (MaterialTextView) listItem.findViewById(R.id.managetransactionsActivity_supplierName_TextView);
        MaterialTextView totalDue_TextView = (MaterialTextView) listItem.findViewById(R.id.managetransactionsActivity_totalDue_TextView);
        MaterialTextView timestamp_TextView = (MaterialTextView) listItem.findViewById(R.id.managetransactionsActivity_timestamp_TextView);
        MaterialTextView item1_TextView =  (MaterialTextView) listItem.findViewById(R.id.managetransactionsActivity_item1_TextView);

        supplierName_TextView.setText("Supplier: " + currentTransaction.getSupplierName());
        totalDue_TextView.setText("Total Due: " + currentTransaction.getTotalDue());
        timestamp_TextView.setText("Timestamp: " + currentTransaction.getTimestamp());
        item1_TextView.setText("Items: " + currentTransaction.getItems().toString());

        return listItem;
    }
}
