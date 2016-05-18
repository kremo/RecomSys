package com.example.gyuri.recomsys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gyuri.recomsys.model.Purchase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Andras on 13/05/16.
 */
public class PurchasesListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PurchasesRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Purchase> purchases;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* purchases = UsersActivity.GetPurchases();
        for(Purchase p : purchases){
            Date date = p.date;
            purchasesString.add(date.toString());
        }*/




        //View view = inflater.inflate(R.layout.fragment_purchases_list, container, false);

        setContentView(R.layout.fragment_purchases_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.purchases_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PurchasesRecyclerAdapter(purchases);
        mRecyclerView.setAdapter(mAdapter);
    }
}
