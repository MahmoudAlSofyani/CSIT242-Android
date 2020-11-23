package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
public class AddNewSupplier extends AppCompatActivity {

    TextInputEditText supplierName_textView;
    Spinner supplierCategory_spinner;
    ArrayAdapter<String> spinnerAdapter;
    String category = "";
    FirebaseFirestore db;
    String[] supplierCategories = {"Computers", "Home Entertainment", "Mobile Phones", "Computer Accessories"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_supplier);

        db = FirebaseFirestore.getInstance();
        supplierName_textView = findViewById(R.id.addSupplierActivity_supplierName_EditText);
        supplierCategory_spinner = findViewById(R.id.addSupplierActivity_supplierCategory_Spinner);

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, supplierCategories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        supplierCategory_spinner.setAdapter(spinnerAdapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = supplierCategories[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        supplierCategory_spinner.setOnItemSelectedListener(listener);
    }

    public void addNewSupplier(View view) {
        String name = supplierName_textView.getText().toString();
        String supplierLogoUrl = "";
        Map<String, Object> supplier = new HashMap<>();
        supplierLogoUrl = "https://logo.clearbit.com/" + name.toLowerCase() + ".com";
        supplier.put("supplierName", name);
        supplier.put("supplierCategory", category);
        supplier.put("supplierLogoUrl", supplierLogoUrl);
        db.collection("suppliers").document(name).set(supplier).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddNewSupplier.this, "Supplier was added successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddNewSupplier.this, ManageSuppliersActivity.class);
                startActivity(intent);
            }
        });
    }
}
