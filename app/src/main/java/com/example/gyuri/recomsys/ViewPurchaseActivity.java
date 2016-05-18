package com.example.gyuri.recomsys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyuri.recomsys.model.Purchase;

/**
 * Created by Andras on 19/05/16.
 */
public class ViewPurchaseActivity extends Activity {
    public Purchase pur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

    }

    @Override
    protected void onResume() {
        super.onResume();
        createShelf();
    }

    private void createShelf() {
        Bundle b = getIntent().getExtras();
        String value = ""; // or other values
        if(b != null) {
            value = b.getString("key");
        }
        pur = new Purchase(value);

        ImageView qImageView = (ImageView) findViewById(R.id.imageView2);
        int resID = getResources().getIdentifier(pur.book.getPicture(), "drawable", getPackageName());
        qImageView.setBackgroundResource(resID);


        TextView tv1 = (TextView) findViewById(R.id.textLabel);
        TextView tv2 = (TextView) findViewById(R.id.textLabel2);

        tv1.setText(pur.book.getAuthor() + " - " + pur.book.getTitle());
        tv2.setText(String.valueOf(pur.book.getPrice())+" Ft");
    }

}
