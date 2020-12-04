package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Models.Supplier;

public class AddNewSaleActivity extends AppCompatActivity {

    Spinner supplierName_Spinner;
    Spinner modelName_Spinner;
    TextInputEditText itemQty_EditText;
    MaterialTextView msrp_TextView;
    TextInputEditText discount_EditText;
    MaterialTextView totalQty_TextView;
    MaterialTextView subtotal_TextView;
    MaterialTextView totalDiscount_TextView;
    MaterialTextView totalVat_TextView;
    MaterialTextView totalDue_TextView;
    FirebaseFirestore db;


    ArrayAdapter<String> supplierSpinnerAdapter;
    ArrayAdapter<String> modelSpinnerAdapter;

    String[] suppliers = {"Apple", "Samsung", "Google"};
    ArrayList<String> models;

    String supplierName = "Apple";
    String chosenModel = "";

    String msrp = "";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_sale_activity);
        db = FirebaseFirestore.getInstance();

        supplierName_Spinner = findViewById(R.id.addNewSaleActivity_supplierName_Spinner);
        modelName_Spinner = findViewById(R.id.addNewSaleActivity_modelName_Spinner);
        itemQty_EditText = findViewById(R.id.addNewSaleActivity_itemQty_EditText);
        msrp_TextView = findViewById(R.id.addNewSaleActivity_msrp_TextView);
        discount_EditText = findViewById(R.id.addNewSaleActivity_discount_EditText);
        totalQty_TextView = findViewById(R.id.addNewSaleActivity_totalQty_TextView);
        subtotal_TextView = findViewById(R.id.addNewSaleActivity_subtotal_TextView);
        totalDiscount_TextView = findViewById(R.id.addNewSaleActivity_totalDiscount_TextView);
        totalVat_TextView = findViewById(R.id.addNewSaleActivity_totalVAT_TextView);
        totalDue_TextView = findViewById(R.id.addNewSaleActivity_totalDue_TextView);

        supplierSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, suppliers);
        supplierSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierName_Spinner.setAdapter(supplierSpinnerAdapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supplierName = suppliers[position];
                populateModelsSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                supplierName = "Apple";
                populateModelsSpinner();
            }
        };
        supplierName_Spinner.setOnItemSelectedListener(listener);
    }

    public void populateModelsSpinner() {

        final String document = supplierName;
        final Supplier[] supplier = new Supplier[1];
        models = new ArrayList<>();

        DocumentReference documentReference = db.collection("suppliers").document(document);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                supplier[0] = documentSnapshot.toObject(Supplier.class);
                for(int i = 0; i < supplier[0].getSupplierModels().size(); i++) {
                    models.add(supplier[0].getSupplierModels().get(i).getModelName());
                }

                modelSpinnerAdapter = new ArrayAdapter<String>(AddNewSaleActivity.this, android.R.layout.simple_spinner_item, models);
                modelSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                modelName_Spinner.setAdapter(modelSpinnerAdapter);

                AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        msrp_TextView.setText("MSRP Per Item: " + supplier[0].getSupplierModels().get(position).getMsrp());
                        msrp = supplier[0].getSupplierModels().get(position).getMsrp();
                        chosenModel = supplier[0].getSupplierModels().get(position).getModelName();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        msrp_TextView.setText("Please select a model");
                    }
                };

                modelName_Spinner.setOnItemSelectedListener(listener);
            }
        });
    }

    public void calculateClicked(View view) {
        calculateTotals(msrp);
    }

    public void calculateTotals(String msrp) {
        double discount;
        int qty = Integer.parseInt(itemQty_EditText.getText().toString());

        if(discount_EditText.getText().toString().equals("")) {
            discount = 0;
        } else {
            discount = Double.parseDouble(discount_EditText.getText().toString()) / 100;
        }

        totalQty_TextView.setText(String.valueOf(qty));

        double subtotal = qty * Double.parseDouble(msrp);
        double discountApplied = subtotal * discount;
        double vat = (subtotal - discountApplied) * 0.05;
        double totalDue = (subtotal - discountApplied) + vat;

        subtotal_TextView.setText(String.valueOf(subtotal));
        totalDiscount_TextView.setText(String.valueOf(discountApplied));
        totalVat_TextView.setText(String.valueOf(vat));
        totalDue_TextView.setText(String.valueOf(totalDue));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void executeSale(View view) {

        Map<String, String> sale = new HashMap<>();

        String totalQty = totalQty_TextView.getText().toString();
        String totalDiscount = totalDiscount_TextView.getText().toString();
        String totalDue = totalDue_TextView.getText().toString();
        String itemName = chosenModel;

        sale.put("totalQty", totalQty);
        sale.put("totalDiscount", totalDiscount);
        sale.put("totalDue", totalDue);
        sale.put("itemName", itemName);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String timestamp = dateTimeFormatter.format(now);

        db.collection("sales").document(timestamp).set(sale).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddNewSaleActivity.this, "Sale added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddNewSaleActivity.this, MainDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
