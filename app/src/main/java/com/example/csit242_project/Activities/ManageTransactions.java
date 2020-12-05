package com.example.csit242_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csit242_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import Adapters.TransactionsAdapter;
import Models.Transaction;

public class ManageTransactions extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FirebaseFirestore db;
    int numOfSuppliers;
    Context context = ManageTransactions.this;
    List<DocumentSnapshot> transactionItem;
    ListView transactionsList;
    TransactionsAdapter transactionsAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managetransactions_activity);

        db = FirebaseFirestore.getInstance();
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.bottomNavigation_menu_transactions);

        transactionsList = findViewById(R.id.manageTransactionsActivity_transactionList_ListView);

        getTransactions();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.bottomNavigation_menu_statistics: {
                    Intent intent = new Intent(context, StatisticsActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_home: {
                    Intent intent = new Intent(context, MainDashboardActivity.class);
                    startActivity(intent);
                    break;
                }
                case R.id.bottomNavigation_menu_suppliers: {
                    Intent intent = new Intent(context, ManageSuppliersActivity.class);
                    intent.putExtra("NUM_SUPPLIERS", numOfSuppliers);
                    startActivity(intent);
                    break;
                }
            }
            return true;
        }
    };

    public void getTransactions() {

        db.collection("transactions").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    ArrayList<Transaction> transactionArrayList = new ArrayList<>();

                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                       Transaction transaction = documentSnapshot.toObject(Transaction.class);

                        transactionArrayList.add(new Transaction(transaction.getSupplierName(), transaction.getTimestamp(), transaction.getTotalDue(), transaction.getItems()));
                        transactionsAdapter = new TransactionsAdapter(ManageTransactions.this, transactionArrayList);
                        transactionsList.setAdapter(transactionsAdapter);
                    }
                } else {
                    System.out.println("There was an error");
                }
            }
        });
    }
}
