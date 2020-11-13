package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ManageSuppliersActivity extends AppCompatActivity {

    FirebaseFirestore db;
    List<DocumentSnapshot> listOfSuppliers;
    int numSuppliers;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
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
                }
            }
        });
    }

}
