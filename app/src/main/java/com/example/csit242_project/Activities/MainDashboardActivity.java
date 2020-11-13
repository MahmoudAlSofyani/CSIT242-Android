package com.example.csit242_project.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainDashboardActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView numSuppliers_TextView;
    int numOfSuppliers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindashboard_activity);
        db = FirebaseFirestore.getInstance();

        numSuppliers_TextView = findViewById(R.id.maindashboardActivity_numSuppliers_TextView);

        getSupplierCount();
    }

    public void getSupplierCount() {

        db.collection("suppliers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    numSuppliers_TextView.setText(String.valueOf(task.getResult().size()));
                    numOfSuppliers = task.getResult().size();
                } else {
                    System.out.println("Error");
                    numSuppliers_TextView.setText("Error Loading");
                }
            }
        });
    }

    public void goToManageSuppliers(View view) {
        Intent intent = new Intent(this, ManageSuppliersActivity.class);
        intent.putExtra("NUM_SUPPLIERS", numOfSuppliers);
        startActivity(intent);
    }
}
