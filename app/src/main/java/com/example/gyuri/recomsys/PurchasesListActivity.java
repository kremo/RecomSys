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
    private ArrayList<String> purchasesString;
    private ArrayList<Purchase> purchases;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* purchases = UsersActivity.GetPurchases();
        for(Purchase p : purchases){
            Date date = p.date;
            purchasesString.add(date.toString());
        }*/

        purchasesString = new ArrayList<String>();
        purchasesString.add("2015 dec. 1.");
        purchasesString.add("2016 jan. 12.");
        purchasesString.add("2016 febr. 21.");
        purchasesString.add("2016 marc. 25.");
        purchasesString.add("2016 apr. 30.");
        purchasesString.add("2016 may. 27.");



        //View view = inflater.inflate(R.layout.fragment_purchases_list, container, false);

        setContentView(R.layout.fragment_purchases_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.purchases_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PurchasesRecyclerAdapter(purchasesString);
        mRecyclerView.setAdapter(mAdapter);
    }
}
