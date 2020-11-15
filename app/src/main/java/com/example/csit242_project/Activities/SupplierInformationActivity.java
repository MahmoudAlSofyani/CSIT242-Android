package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import Models.Supplier;

public class SupplierInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Bundle bundle = this.getIntent().getExtras();
        Supplier supplier = null;
        if(bundle != null) {
            supplier = (Supplier) bundle.getSerializable("SUPPLIER");
            System.out.println(supplier.getSupplierName());
        }


    }
}
