package com.example.csit242_project.Activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Brand;
import Models.Supplier;
import Models.SupplierModel;

public class MainDashboardActivity extends AppCompatActivity {

    FirebaseFirestore db;
    Supplier Apple = new Supplier();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindashboard_activity);
        db = FirebaseFirestore.getInstance();


        SupplierModel iPhone12Mini = new SupplierModel();
        SupplierModel iPhone12 = new SupplierModel();
        SupplierModel iPhone12Pro = new SupplierModel();
        SupplierModel iPhone12ProMax = new SupplierModel();
        List<SupplierModel> AppleModels = new ArrayList<>();

        List<String> iPhone12Mini_iPhone12_Colors = new ArrayList<String>();
        iPhone12Mini_iPhone12_Colors.add("Black");
        iPhone12Mini_iPhone12_Colors.add("White");
        iPhone12Mini_iPhone12_Colors.add("Red");
        iPhone12Mini_iPhone12_Colors.add("Green");
        iPhone12Mini_iPhone12_Colors.add("Blue");

        List<String> iPhone12Mini_iPhone12_Capacity = new ArrayList<String>();
        iPhone12Mini_iPhone12_Capacity.add("64GB");
        iPhone12Mini_iPhone12_Capacity.add("128GB");
        iPhone12Mini_iPhone12_Capacity.add("256GB");

        List<String> iPhone12Pro_iPhone12ProMax_Colors = new ArrayList<String>();
        iPhone12Pro_iPhone12ProMax_Colors.add("Silver");
        iPhone12Pro_iPhone12ProMax_Colors.add("Graphite");
        iPhone12Pro_iPhone12ProMax_Colors.add("Gold");
        iPhone12Pro_iPhone12ProMax_Colors.add("Pacific Blue");

        List<String> iPhone12Pro_iPhone12ProMax_Capacity = new ArrayList<String>();
        iPhone12Pro_iPhone12ProMax_Capacity.add("128GB");
        iPhone12Pro_iPhone12ProMax_Capacity.add("256GB");
        iPhone12Pro_iPhone12ProMax_Capacity.add("512GB");


        iPhone12Mini.setModelName("iPhone 12 Mini");
        iPhone12Mini.setModelColors(iPhone12Mini_iPhone12_Colors);
        iPhone12Mini.setModelCapacities(iPhone12Mini_iPhone12_Capacity);

        iPhone12.setModelName("iPhone 12");
        iPhone12.setModelColors(iPhone12Mini_iPhone12_Colors);
        iPhone12.setModelCapacities(iPhone12Mini_iPhone12_Capacity);

        iPhone12Pro.setModelName("iPhone 12 Pro");
        iPhone12Pro.setModelColors(iPhone12Pro_iPhone12ProMax_Colors);
        iPhone12Pro.setModelCapacities(iPhone12Pro_iPhone12ProMax_Capacity);

        iPhone12ProMax.setModelName("iPhone 12 Pro Max");
        iPhone12ProMax.setModelColors(iPhone12Pro_iPhone12ProMax_Colors);
        iPhone12ProMax.setModelCapacities(iPhone12Pro_iPhone12ProMax_Capacity);

        AppleModels.add(iPhone12Mini);
        AppleModels.add(iPhone12);
        AppleModels.add(iPhone12Pro);
        AppleModels.add(iPhone12ProMax);

        Apple.setSupplierName("Apple");
        Apple.setSupplierCategory("Mobile Phones");
        Apple.setSupplierModels(AppleModels);

        AddSuppliers(Apple);
    }


    public void AddSuppliers(Supplier supplier) {

        db.collection("suppliers").document(supplier.getSupplierName()).set(supplier).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Document added");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("ERROR");
            }
        });
    }
}
