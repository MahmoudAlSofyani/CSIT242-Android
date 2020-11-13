package com.example.csit242_project.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

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

import java.util.HashMap;
import java.util.Map;

public class MainDashboardActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindashboard_activity);
        db = FirebaseFirestore.getInstance();

//        addNewUser();
        readData();
    }


    public void addNewUser() {

        Map<String, Object> user = new HashMap<>();
        user.put("firstName", "Mahmoud");
        user.put("lastName", "AlSofyani");

        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                System.out.println(documentReference.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Error " + e);
            }
        });
    }

    public void readData() {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for(QueryDocumentSnapshot document : task.getResult()) {
                        // to get all fields in the document
                        System.out.println(document.getId() + " => " + document.getData());
                        //or to get specific fields by their key
                        System.out.println(document.getId() + " => " + document.get("firstName") );
                    }
                } else {
                    System.out.println("Error " + task.getException());
                }
            }
        });
    }
}
