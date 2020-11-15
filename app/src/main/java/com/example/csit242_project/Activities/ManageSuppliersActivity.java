package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapters.SuppliersAdapter;
import Models.Supplier;

public class ManageSuppliersActivity extends AppCompatActivity {

    FirebaseFirestore db;
    List<DocumentSnapshot> listOfSuppliers;
    int numSuppliers;

    ListView supplier_ListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.managesuppliers_activity);

        getListOfSuppliers();
        Intent intent = getIntent();
        numSuppliers = intent.getIntExtra("NUM_SUPPLIERS", 0);



    }



    public void getListOfSuppliers() {

        db.collection("suppliers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    listOfSuppliers = task.getResult().getDocuments();
                    populateListOfSuppliers(listOfSuppliers);
                }
            }
        });
    }

    public void populateListOfSuppliers(List<DocumentSnapshot> list) {

        ArrayList<Supplier> supplierArrayList = new ArrayList<>();
        SuppliersAdapter suppliersAdapter;

        supplier_ListView = (ListView) findViewById(R.id.managesuppliersActivity_supplierList_ListView);

        for(int i = 0; i < numSuppliers; i++) {
            supplierArrayList.add(new Supplier(list.get(i).get("supplierName").toString(), list.get(i).get("supplierCategory").toString(), list.get(i).get("supplierLogoURL").toString()));
        }

        suppliersAdapter = new SuppliersAdapter(this, supplierArrayList);
        supplier_ListView.setAdapter(suppliersAdapter);
    }

}
