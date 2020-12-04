package com.example.csit242_project.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import Adapters.SuppliersAdapter;
import Models.Supplier;
import Models.SupplierModel;

public class ManageSuppliersActivity extends AppCompatActivity {

    FirebaseFirestore db;
    BottomNavigationView bottomNavigationView;
    List<DocumentSnapshot> listOfSuppliers;
    int numSuppliers;
    ArrayList<Supplier> supplierArrayList = new ArrayList<>();
    ListView supplier_ListView;
    Context context = ManageSuppliersActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.managesuppliers_activity);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        bottomNavigationView.setSelectedItemId(R.id.bottomNavigation_menu_suppliers);

        final Intent intent = getIntent();
        numSuppliers = intent.getIntExtra("NUM_SUPPLIERS", 0);
        supplier_ListView = (ListView) findViewById(R.id.managesuppliersActivity_supplierList_ListView);

        supplier_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Supplier supplier = listOfSuppliers.get(position).toObject(Supplier.class);
                Intent goToSupplierInformation = new Intent(getApplicationContext(), SupplierInformationActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Supplier", supplier);
                goToSupplierInformation.putExtras(bundle);
                startActivity(goToSupplierInformation);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getListOfSuppliers();
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
                case R.id.bottomNavigation_menu_statistics: {
                    Intent intent = new Intent(context, StatisticsActivity.class);
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

    public void getListOfSuppliers() {

        if(listOfSuppliers == null) {
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
    }

    public void populateListOfSuppliers(List<DocumentSnapshot> list) {
        SuppliersAdapter suppliersAdapter;
        String supplierLogoUrl = "";
        for(int i = 0; i < numSuppliers; i++) {
            supplierLogoUrl = "https://logo.clearbit.com/" + list.get(i).get("supplierName").toString().toLowerCase() + ".com";
            supplierArrayList.add(new Supplier(list.get(i).get("supplierName").toString(), list.get(i).get("supplierCategory").toString(), supplierLogoUrl, (List<SupplierModel>) list.get(i).get("supplierModels")));
        }
        suppliersAdapter = new SuppliersAdapter(this, supplierArrayList);
        supplier_ListView.setAdapter(suppliersAdapter);
    }

    public void goToAddNewSupplier(View view) {
        Intent intent = new Intent(this, AddNewSupplier.class);
        startActivity(intent);

    }

    public void goToRemoveSupplier(View view) {

    }
}
