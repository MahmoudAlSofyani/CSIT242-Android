package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;

import Models.Supplier;

public class SupplierInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Supplier supplier = (Supplier) getIntent().getParcelableExtra("Supplier");
        if(supplier != null) {
            System.out.println(supplier.getSupplierName());
            System.out.println(supplier.getSupplierModels().get(0).getModelName());
        }
    }
}
