package com.example.gyuri.recomsys;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyuri.recomsys.model.Purchase;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by Andras on 13/05/16.
 */
public class PurchasesRecyclerAdapter extends RecyclerView.Adapter {
    ArrayList<Purchase> purchases;
    private static MyClickListener myClickListener;
    Context context;

    public class PurchaseCard extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView label;
        ImageView icon;
        public PurchaseCard(View itemView) {
            super (itemView);
            label = (TextView) itemView.findViewById(R.id.label);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getPosition(), v);
        }
    }

    public PurchasesRecyclerAdapter(ArrayList<Purchase> allPurchases) {
        purchases = allPurchases;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_purchase, parent, false);
        PurchaseCard dataObjectHolder = new PurchaseCard(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Purchase actualPurchase = purchases.get(position);
        PurchaseCard attendeeCard = (PurchaseCard)viewHolder;
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        attendeeCard.label.setText(dateFormat.format(actualPurchase.date));
    }

    @Override
    public int getItemCount() {
        if(purchases == null){
            return 0;
        }
        return purchases.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
