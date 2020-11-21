package com.example.csit242_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class StatisticsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Context context = StatisticsActivity.this;
    int numOfSuppliers;
    FirebaseFirestore db;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics_activity);
        db = FirebaseFirestore.getInstance();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.bottomNavigation_menu_statistics);

        getSupplierCount();
    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch(item.getItemId()) {
                case R.id.bottomNavigation_menu_expenses: {
                    Intent intent = new Intent(context, ExpensesActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_home: {
                    Intent intent = new Intent(context, MainDashboardActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_transactions: {
                    Intent intent = new Intent(context, ManageTransactions.class);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_suppliers: {
                    Intent intent = new Intent(context, ManageSuppliersActivity.class);
                    intent.putExtra("NUM_SUPPLIERS", numOfSuppliers);
                    startActivity(intent);
                }
            }
            return true;
        }
    };

    public void getSupplierCount() {

        db.collection("suppliers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    numOfSuppliers = task.getResult().size();
                } else {
                    System.out.println("Error");
                }
            }
        });
    }
}
