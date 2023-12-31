package com.getmythings.admin2.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.getmythings.admin2.Models.newproShowAllModel;
import com.getmythings.admin2.R;
import com.getmythings.admin2.adapters.newproShowAllAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NewProductsShowAll extends AppCompatActivity {


    RecyclerView recyclerView;
    newproShowAllAdapter newproShowAllAdapter;
    List<newproShowAllModel> newproShowAllModelList;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_products_show_all );


        firestore = FirebaseFirestore.getInstance( );
        recyclerView = findViewById( R.id.newpro_show_all_rec );
        String type = getIntent( ).getStringExtra( "type");
        getSupportActionBar( ).setTitle( "New Products Show All" );


        firestore = FirebaseFirestore.getInstance( );
        recyclerView = findViewById( R.id.newpro_show_all_rec );
        recyclerView.setLayoutManager( new GridLayoutManager( this, 2 ) );
        newproShowAllModelList = new ArrayList<>( );
        newproShowAllAdapter = new newproShowAllAdapter( this,newproShowAllModelList);
        recyclerView.setAdapter(newproShowAllAdapter);


        if (type == null || type.isEmpty( )) {
            firestore.collection( "NewProducts" )
                    .get( )
                    .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>( ) {
                        @Override
                        public void onComplete (@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful( )) {
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    newproShowAllModel newproShowAllModel = doc.toObject(newproShowAllModel.class );
                                    newproShowAllModelList.add(newproShowAllModel);
                                    newproShowAllAdapter.notifyDataSetChanged();
                                }
                            }
                        }

                    } );

        }
    }
}