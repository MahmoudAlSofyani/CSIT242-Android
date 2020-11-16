package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Adapters.SuppliersAdapter;
import Models.Supplier;
import Models.SupplierModel;

public class ManageSuppliersActivity extends AppCompatActivity {

    FirebaseFirestore db;
    List<DocumentSnapshot> listOfSuppliers;
    int numSuppliers;
    ArrayList<Supplier> supplierArrayList = new ArrayList<>();
    ListView supplier_ListView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.managesuppliers_activity);

        getListOfSuppliers();
        final Intent intent = getIntent();
        numSuppliers = intent.getIntExtra("NUM_SUPPLIERS", 0);
        supplier_ListView = (ListView) findViewById(R.id.managesuppliersActivity_supplierList_ListView);


        supplier_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("I WAS CLICKED: " + id);
                Supplier supplier = listOfSuppliers.get(position).toObject(Supplier.class);
                // TODO: Pass supplier object with intent to SupplierInformationActivity class

                Intent goToSupplierInformation = new Intent(getApplicationContext(), SupplierInformationActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("Supplier", supplier);
                goToSupplierInformation.putExtras(bundle);
                startActivity(goToSupplierInformation);
            }
        });
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
        SuppliersAdapter suppliersAdapter;
        for(int i = 0; i < numSuppliers; i++) {
            supplierArrayList.add(new Supplier(list.get(i).get("supplierName").toString(), list.get(i).get("supplierCategory").toString(), list.get(i).get("supplierLogoURL").toString(), (List<SupplierModel>) list.get(i).get("supplierModels")));
        }

        suppliersAdapter = new SuppliersAdapter(this, supplierArrayList);
        supplier_ListView.setAdapter(suppliersAdapter);
    }
}
