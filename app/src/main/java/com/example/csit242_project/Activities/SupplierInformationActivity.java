package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

import Adapters.SupplierModelAdapter;
import Models.Supplier;
import Models.SupplierModel;

public class SupplierInformationActivity extends AppCompatActivity {

    MaterialTextView supplierName_TextView;
    ListView supplierModels_ListView;
    private SupplierModelAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.supplierinformation_activity);

        supplierName_TextView = findViewById(R.id.supplierInformationActivity_supplierName_TextView);
        supplierModels_ListView = findViewById(R.id.supplierInformationActivity_supplierModels_ListView);

        Supplier supplier;
        supplier = (Supplier) getIntent().getExtras().getSerializable("Supplier");

        if(supplier != null) {

            supplierName_TextView.setText(supplier.getSupplierName());
            ArrayList<SupplierModel> supplierModels = new ArrayList<>();
            for(int i = 0; i < supplier.getSupplierModels().size(); i++) {
                supplierModels.add(new SupplierModel(supplier.getSupplierModels().get(i).getModelName(), supplier.getSupplierModels().get(i).getModelColors(), supplier.getSupplierModels().get(i).getModelCapacities()));

            }

            adapter = new SupplierModelAdapter(this, supplierModels);
            supplierModels_ListView.setAdapter(adapter);
        }
    }
}
