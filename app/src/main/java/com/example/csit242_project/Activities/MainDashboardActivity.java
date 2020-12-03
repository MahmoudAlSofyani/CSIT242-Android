package com.example.csit242_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

public class MainDashboardActivity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView numSuppliers_TextView;
    int numOfSuppliers;
    BottomNavigationView bottomNavigationView;
    Context context = MainDashboardActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindashboard_activity);
        db = FirebaseFirestore.getInstance();

        numSuppliers_TextView = findViewById(R.id.maindashboardActivity_numSuppliers_TextView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.bottomNavigation_menu_home);

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
                case R.id.bottomNavigation_menu_statistics: {
                    Intent intent = new Intent(context, StatisticsActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_suppliers: {
                    Intent intent = new Intent(context, ManageSuppliersActivity.class);
                    intent.putExtra("NUM_SUPPLIERS", numOfSuppliers);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_transactions: {
                    Intent intent = new Intent(context, ManageTransactions.class);
                    startActivity(intent);
                    break;
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

    public void goToRecordNewSale(View view) {
        Intent intent = new Intent(this, ManageSales.class);
        startActivity(intent);
    }
}
