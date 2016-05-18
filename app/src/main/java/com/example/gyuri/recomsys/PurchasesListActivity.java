package com.example.gyuri.recomsys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.gyuri.recomsys.model.DataSource;
import com.example.gyuri.recomsys.model.Purchase;

import java.util.ArrayList;

/**
 * Created by Andras on 13/05/16.
 */
public class PurchasesListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PurchasesRecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Purchase> prs;

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

        prs = DataSource.getPurchasesForUser(DataSource.currentUser,this);
        mAdapter = new PurchasesRecyclerAdapter(prs);
        mAdapter.context = this;
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((PurchasesRecyclerAdapter) mAdapter).setOnItemClickListener(
                new PurchasesRecyclerAdapter.MyClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {
                        Log.i("TAGGIE", " Clicked on Item " + position);
                        Intent intent = new Intent(PurchasesListActivity.this, ViewPurchaseActivity.class);
                        Bundle b = new Bundle();
                        b.putString("key", prs.get(position).writeToString()); //Your id
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                });
    }
}
