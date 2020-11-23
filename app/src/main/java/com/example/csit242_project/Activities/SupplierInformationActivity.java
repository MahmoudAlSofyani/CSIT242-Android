package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import Adapters.SupplierModelAdapter;
import Models.Supplier;
import Models.SupplierModel;

public class SupplierInformationActivity extends AppCompatActivity {

    MaterialTextView supplierName_TextView;
    ListView supplierModels_ListView;
    private SupplierModelAdapter adapter;
    Supplier supplier;
    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplierinformation_activity);
        supplierName_TextView = findViewById(R.id.supplierInformationActivity_supplierName_TextView);
        supplierModels_ListView = findViewById(R.id.supplierInformationActivity_supplierModels_ListView);
        db = FirebaseFirestore.getInstance();

        supplier = (Supplier) getIntent().getExtras().getSerializable("Supplier");
        System.out.println(supplier.getSupplierModels());
        

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
                supplierModels_ListView.setAdapter(adapter);
            }

        } else {
            Toast.makeText(this, "There was an error. Please try again", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeSupplier(View view) {

        db.collection("suppliers").document(supplier.getSupplierName()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(SupplierInformationActivity.this, "Supplier successfully deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SupplierInformationActivity.this, ManageSuppliersActivity.class);
                startActivity(intent);
            }
        });

    }
}
