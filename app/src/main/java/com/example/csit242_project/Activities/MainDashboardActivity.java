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

import java.lang.reflect.Array;
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
    Supplier Samsung = new Supplier();
    Supplier Google = new Supplier();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindashboard_activity);
        db = FirebaseFirestore.getInstance();

        SupplierModel GalaxyS205G = new SupplierModel();
        SupplierModel GalaxyS20Plus5G = new SupplierModel();
        SupplierModel GalaxyS20Ultra5G = new SupplierModel();
        SupplierModel GalaxyNote205G = new SupplierModel();
        List<SupplierModel> SamsungModels = new ArrayList<>();

        SupplierModel iPhone12Mini = new SupplierModel();
        SupplierModel iPhone12 = new SupplierModel();
        SupplierModel iPhone12Pro = new SupplierModel();
        SupplierModel iPhone12ProMax = new SupplierModel();
        List<SupplierModel> AppleModels = new ArrayList<>();

        SupplierModel Pixel5 = new SupplierModel();
        SupplierModel Pixel4a = new SupplierModel();
        SupplierModel Pixel4a5G = new SupplierModel();
        List<SupplierModel> GoogleModels = new ArrayList<>();

        List<String> Pixel5_Colors = new ArrayList<>();
        Pixel5_Colors.add("Just Black");
        Pixel5_Colors.add("Sorta Sage");

        List<String> Pixel4a_Pixel4a5G_Colors = new ArrayList<>();
        Pixel4a_Pixel4a5G_Colors.add("Just Black");

        List<String> Pixel5_Pixel4a_Pixel4a5G_Capacity = new ArrayList<>();
        Pixel5_Pixel4a_Pixel4a5G_Capacity.add("128GB");

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

        List<String> GalaxyS205G_Colors = new ArrayList<String>();
        GalaxyS205G_Colors.add("Cosmic Gray");
        GalaxyS205G_Colors.add("Cloud Blue");
        GalaxyS205G_Colors.add("Cloud Pink");
        GalaxyS205G_Colors.add("Cloud White");

        List<String> GalaxyS20Plus5G_Colors = new ArrayList<String>();
        GalaxyS20Plus5G_Colors.add("Cosmic Black");
        GalaxyS20Plus5G_Colors.add("Cosmic Gray");
        GalaxyS20Plus5G_Colors.add("Cloud Blue");
        GalaxyS20Plus5G_Colors.add("Aura Blue");

        List<String> GalaxyS20Ultra5G_Colors = new ArrayList<String>();
        GalaxyS20Ultra5G_Colors.add("Cosmic Black");
        GalaxyS20Ultra5G_Colors.add("Cosmic Gray");

        List<String> GalaxyNote205G_Colors = new ArrayList<String>();
        GalaxyNote205G_Colors.add("Mystic Bronze");
        GalaxyNote205G_Colors.add("Mystic Green");
        GalaxyNote205G_Colors.add("Mystic Gray");

        List<String> GalaxyS205G_GalaxyNote205G_Capacity = new ArrayList<>();
        GalaxyS205G_GalaxyNote205G_Capacity.add("128GB");

        List<String> GalaxyS20Plus5G_GalaxyS20Ultra5G_Capacity = new ArrayList<>();
        GalaxyS20Plus5G_GalaxyS20Ultra5G_Capacity.add("128GB");
        GalaxyS20Plus5G_GalaxyS20Ultra5G_Capacity.add("256GB");
        GalaxyS20Plus5G_GalaxyS20Ultra5G_Capacity.add("512GB");



        GalaxyS205G.setModelName("Galaxy S20 5G");
        GalaxyS205G.setModelColors(GalaxyS205G_Colors);
        GalaxyS205G.setModelCapacities(GalaxyS205G_GalaxyNote205G_Capacity);

        GalaxyS20Plus5G.setModelName("Galaxy S20+ 5G");
        GalaxyS20Plus5G.setModelColors(GalaxyS20Plus5G_Colors);
        GalaxyS20Plus5G.setModelCapacities(GalaxyS20Plus5G_GalaxyS20Ultra5G_Capacity);

        GalaxyS20Ultra5G.setModelName("Galaxy S20 Ultra 5G");
        GalaxyS20Ultra5G.setModelColors(GalaxyS20Ultra5G_Colors);
        GalaxyS20Ultra5G.setModelCapacities(GalaxyS20Plus5G_GalaxyS20Ultra5G_Capacity);

        GalaxyNote205G.setModelName("Galaxy Note 20 5G");
        GalaxyNote205G.setModelColors(GalaxyNote205G_Colors);
        GalaxyNote205G.setModelCapacities(GalaxyS205G_GalaxyNote205G_Capacity);


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

        Pixel5.setModelName("Pixel 5");
        Pixel5.setModelColors(Pixel5_Colors);
        Pixel5.setModelCapacities(Pixel5_Pixel4a_Pixel4a5G_Capacity);

        Pixel4a.setModelName("Pixel 4a");
        Pixel4a.setModelColors(Pixel4a_Pixel4a5G_Colors);
        Pixel4a.setModelCapacities(Pixel5_Pixel4a_Pixel4a5G_Capacity);

        Pixel4a5G.setModelName("Pixel 4a 5G");
        Pixel4a5G.setModelColors(Pixel4a_Pixel4a5G_Colors);
        Pixel4a5G.setModelCapacities(Pixel5_Pixel4a_Pixel4a5G_Capacity);

        GoogleModels.add(Pixel5);
        GoogleModels.add(Pixel4a);
        GoogleModels.add(Pixel4a5G);

        Google.setSupplierName("Google");
        Google.setSupplierCategory("Mobile Phones");
        Google.setSupplierModels(GoogleModels);

        SamsungModels.add(GalaxyS205G);
        SamsungModels.add(GalaxyS20Plus5G);
        SamsungModels.add(GalaxyS20Ultra5G);
        SamsungModels.add(GalaxyNote205G);

        Samsung.setSupplierName("Samsung");
        Samsung.setSupplierCategory("Mobile Phones");
        Samsung.setSupplierModels(SamsungModels);


        AppleModels.add(iPhone12Mini);
        AppleModels.add(iPhone12);
        AppleModels.add(iPhone12Pro);
        AppleModels.add(iPhone12ProMax);

        Apple.setSupplierName("Apple");
        Apple.setSupplierCategory("Mobile Phones");
        Apple.setSupplierModels(AppleModels);

        AddSuppliers(Apple);
        AddSuppliers(Samsung);
        AddSuppliers(Google);
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
