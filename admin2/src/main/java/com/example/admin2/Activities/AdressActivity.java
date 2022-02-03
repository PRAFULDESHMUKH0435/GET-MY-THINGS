package com.example.admin2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.example.admin2.Models.AddressModel;
import com.example.admin2.Models.NewProductModel;
import com.example.admin2.Models.PopularProductModel;
import com.example.admin2.Models.ShowAllModel;
import com.example.admin2.R;
import com.example.admin2.adapters.AddressAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class AdressActivity extends AppCompatActivity implements AddressAdapter.SelectedAddress {

    Button paymentbutton,addAddressBtn,paymentBtn;
    RecyclerView recyclerView;
    private List<AddressModel> addressModelList;
    private AddressAdapter addressAdapter;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String mAddress = " ";


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_adress );
        getSupportActionBar().hide();

        //GET DATA FROM DETAILED ACTIVITY
        Object obj = getIntent().getSerializableExtra("item");

        auth = FirebaseAuth.getInstance( );
        firestore = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.address_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        addressModelList = new ArrayList<>();
        addressAdapter = new AddressAdapter(getApplicationContext(),addressModelList,this);
        recyclerView.setAdapter(addressAdapter);


        paymentbutton = findViewById(R.id.payment_btn);
        paymentbutton.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick (View v) {
                double amount = 0;
                if (obj instanceof NewProductModel){
                    NewProductModel newProductModel = (NewProductModel) obj;
                    amount =  newProductModel.getPrice();
                }
                if (obj instanceof PopularProductModel){
                    PopularProductModel popularProductModel = (PopularProductModel) obj;
                    amount =  popularProductModel.getPrice();
                }
                if (obj instanceof ShowAllModel){
                    ShowAllModel showAllModel = (ShowAllModel) obj;
                    amount =  showAllModel.getPrice();
                }


                Intent intent = new Intent(AdressActivity.this,CashOnDelivery.class);
                intent.putExtra("amount",String.valueOf(amount));
                startActivity(intent);
            }
        } );

    }




    @Override
    public void setAddress (String address) {
      mAddress =  address;
    }
}