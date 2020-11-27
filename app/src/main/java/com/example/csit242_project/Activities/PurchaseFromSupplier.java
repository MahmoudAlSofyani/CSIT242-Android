package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapters.SupplierModelAdapter;
import Models.Supplier;
import Models.SupplierModel;
import Models.SupplierTransaction;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class PurchaseFromSupplier extends AppCompatActivity {

    MaterialTextView supplierName_TextView;
    MaterialTextView subtotal_TextView;
    MaterialTextView vat_TextView;
    MaterialTextView total_TextView;
    MaterialTextView totalQty_TextView;
    ListView models_ListView;
    Supplier supplier;
    private SupplierModelAdapter adapter;
    int totalQty = 0;
    FirebaseFirestore db;

    List<String> modelCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_from_supplier_activity);

        db = FirebaseFirestore.getInstance();

        supplierName_TextView = findViewById(R.id.purchase_from_supplier_supplierName_TextView);
        models_ListView = findViewById(R.id.purchase_from_supplier_listView);
        subtotal_TextView = findViewById(R.id.purchase_from_supplier_subtotal_TextView);
        vat_TextView = findViewById(R.id.purchase_from_supplier_VAT_TextView);
        total_TextView = findViewById(R.id.purchase_from_supplier_total_TextView);
        totalQty_TextView = findViewById(R.id.purchase_from_supplier_totalqty_TextView);

        totalQty_TextView.setText(String.valueOf(totalQty));

        modelCount = new ArrayList<>();
        supplier = (Supplier) getIntent().getExtras().getSerializable("Supplier");

        if(supplier != null) {
            supplierName_TextView.setText(supplier.getSupplierName());
            ArrayList<SupplierModel> supplierModels = new ArrayList<>();
            if(supplier.getSupplierModels() == null) {
                Toast.makeText(this, "This supplier has no listed items.", Toast.LENGTH_SHORT).show();
            } else {
                for(int i = 0; i < supplier.getSupplierModels().size(); i++) {
                    supplierModels.add(new SupplierModel(supplier.getSupplierModels().get(i).getModelName(), supplier.getSupplierModels().get(i).getModelColors(), supplier.getSupplierModels().get(i).getModelCapacities()));
                }
                adapter = new SupplierModelAdapter(this, supplierModels);
                models_ListView.setAdapter(adapter);
            }

        } else {
            Toast.makeText(this, "There was an error. Please try again", Toast.LENGTH_SHORT).show();
        }

        models_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(supplier.getSupplierModels().get(position).getModelName());
                totalQty++;
                totalQty_TextView.setText(String.valueOf(totalQty));
                updateSubTotal(supplier.getSupplierModels().get(position).getModelName());
            }
        });
    }

    public void updateSubTotal(String model) {
        double modelPrice = 0;
        double currentSubTotal;

        if(subtotal_TextView.getText().toString().isEmpty()) {
            currentSubTotal = 0;
        } else {
            currentSubTotal = Double.parseDouble(subtotal_TextView.getText().toString());
        }

        switch(model) {
            case"iPhone 12 Mini": {
                modelPrice = 2999;
                break;
            }
            case "iPhone 12": {
                modelPrice = 3399;
                break;
            }
            case "iPhone 12 Pro": {
                modelPrice = 4199;
                break;
            }
            case "iPhone 12 Pro Max": {
                modelPrice = 4699;
                break;
            }
            case "Pixel 5": {
                modelPrice = 2565;
                break;
            }
            case "Pixel 4a": {
                modelPrice = 1280;
                break;
            }
            case "Pixel 4a 5G" : {
                modelPrice = 1831;
                break;
            }
            case "Galaxy S20 5G" : {
                modelPrice = 2699;
                break;
            }
            case "Galaxy S20+ 5G": {
                modelPrice = 3149;
                break;
            }
            case "Galaxy S20 Ultra 5G": {
                modelPrice = 3699;
                break;
            }
            case "Galaxy Note 20 5G": {
                modelPrice = 3849;
                break;
            }
        }
        currentSubTotal += modelPrice;
        subtotal_TextView.setText(String.valueOf(currentSubTotal));
        modelCount.add(model);
        updateVAT(currentSubTotal);
    }

    public void updateVAT(double subtotal) {
        double totalVat = subtotal * 0.05;
        DecimalFormat vatFormat = new DecimalFormat("#.00");
        vat_TextView.setText(String.valueOf(vatFormat.format(totalVat)));
        updateTotalDue(subtotal, totalVat);
    }

    public void updateTotalDue(double subtotal, double vat) {
        double total = subtotal + vat;
        total_TextView.setText(String.valueOf(total));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void submitTransaction(View view) {

        String supplierName = supplierName_TextView.getText().toString();
        Double totalDue = Double.parseDouble(total_TextView.getText().toString());

        int iphone12mini_Count = 0;
        int iphone12_Count = 0;
        int iphone12pro_Count = 0;
        int iphone12promax_Count = 0;
        int pixel5_Count = 0;
        int pixel4a_Count = 0;
        int pixel4a5g_Count = 0;
        int galaxys205g_Count = 0;
        int galaxys20plus5g_Count = 0;
        int galaxys20ultra5g_Count = 0;
        int galaxynote205g_Count = 0;

        for(int i = 0; i < modelCount.size(); i++) {

            switch(modelCount.get(i)) {
                case"iPhone 12 Mini": {
                    iphone12mini_Count++;
                    break;
                }
                case "iPhone 12": {
                    iphone12_Count++;
                    break;
                }
                case "iPhone 12 Pro": {
                    iphone12pro_Count++;
                    break;
                }
                case "iPhone 12 Pro Max": {
                    iphone12promax_Count++;
                    break;
                }
                case "Pixel 5": {
                    break;
                }
                case "Pixel 4a": {
                    pixel4a_Count++;
                    break;
                }
                case "Pixel 4a 5G" : {
                    pixel4a5g_Count++;
                    break;
                }
                case "Galaxy S20 5G" : {
                    galaxys205g_Count++;
                    break;
                }
                case "Galaxy S20+ 5G": {
                    galaxys20plus5g_Count++;
                    break;
                }
                case "Galaxy S20 Ultra 5G": {
                    galaxys20ultra5g_Count++;
                    break;
                }
                case "Galaxy Note 20 5G": {
                    galaxynote205g_Count++;
                    break;
                }
            }
        }

        SupplierTransaction newTransaction;
        Map<String, Object> transaction = new HashMap<>();

        switch (supplierName) {
            case "Apple": {
                transaction.put("iPhone 12 Mini", iphone12mini_Count);
                transaction.put("iPhone 12", iphone12_Count);
                transaction.put("iPhone 12 Pro", iphone12pro_Count);
                transaction.put("iPhone 12 Pro Max", iphone12promax_Count);
                break;
            }
            case "Google": {
                transaction.put("Pixel 5", pixel5_Count);
                transaction.put("Pixel 4a", pixel4a_Count);
                transaction.put("Pixel 4a 5G", pixel4a5g_Count);
                break;
            }
            case "Samsung": {
                transaction.put("Galaxy S20 5G", galaxys205g_Count);
                transaction.put("Galaxy S20+ 5G", galaxys20plus5g_Count);
                transaction.put("Galaxy S20 Ultra 5G", galaxys20ultra5g_Count);
                transaction.put("Galaxy Note 20 5G", galaxynote205g_Count);
                break;
            }
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        transaction.put("supplierName", supplierName);
        transaction.put("totalDue", totalDue);
        transaction.put("timestamp", dateTimeFormatter.format(now));

        db.collection("transactions").add(transaction).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(PurchaseFromSupplier.this, "Transaction saved successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PurchaseFromSupplier.this, ManageSuppliersActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("ERROR");
            }
        });
    }
}
