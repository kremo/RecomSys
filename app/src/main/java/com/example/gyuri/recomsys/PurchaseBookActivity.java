package com.example.gyuri.recomsys;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gyuri.recomsys.model.Book;
import com.example.gyuri.recomsys.model.Purchase;
import com.example.gyuri.recomsys.model.RecomGroup;

import java.util.Calendar;
import java.util.HashMap;

public class PurchaseBookActivity extends AppCompatActivity {

    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        book = new Book(getIntent().getStringExtra("BOOK"));

        LinearLayout ll = (LinearLayout) findViewById(R.id.content_purchase_book);
        int resID = getResources().getIdentifier(book.getPicture(), "drawable", getPackageName());
        ((ImageView) (ll.getChildAt(0))).setImageResource(resID);

        ((TextView) ((TableRow) ((TableLayout) ((ScrollView) (ll.getChildAt(1))).getChildAt(0)).getChildAt(0)).getChildAt(1)).setText(book.getTitle());
        ((TextView) ((TableRow) ((TableLayout) ((ScrollView) (ll.getChildAt(1))).getChildAt(0)).getChildAt(1)).getChildAt(1)).setText(book.getAuthor());
        ((TextView) ((TableRow) ((TableLayout) ((ScrollView) (ll.getChildAt(1))).getChildAt(0)).getChildAt(2)).getChildAt(1)).setText(Integer.toString(book.getPrice()).concat(" Ft"));
        ((TextView) ((TableRow) ((TableLayout) ((ScrollView) (ll.getChildAt(1))).getChildAt(0)).getChildAt(3)).getChildAt(1)).setText(Integer.toString(book.getReleased()));
        ((TextView) ((TableRow) ((TableLayout) ((ScrollView) (ll.getChildAt(1))).getChildAt(0)).getChildAt(4)).getChildAt(1)).setText(book.getPublisher());

        String genres = new String();
        for (String actual : book.getGenres()) {
            genres += genres.concat(actual + ", ");
        }
        genres = genres.substring(0, genres.length() - 2);
        ((TextView) ((TableRow) ((TableLayout) ((ScrollView) (ll.getChildAt(1))).getChildAt(0)).getChildAt(5)).getChildAt(1)).setText(genres);

        ((Button) ll.getChildAt(2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new purchase
                UsersActivity.purchases.add(new Purchase(book, UsersActivity.currentUser, Calendar.getInstance().getTime()));

                int i = book.getGenres().size();
                HashMap<String, Boolean> gotIt = new HashMap<String, Boolean>(i);

                for (String s : book.getGenres())
                    gotIt.put(s, false);

                for (RecomGroup rg : UsersActivity.recomGroups)
                    for (String s : book.getGenres())
                        if (rg.getName().equals("Legjobb " + s)) {

                            rg.addBook(book, 1);


                            if (!rg.getUsers().contains(UsersActivity.currentUser))
                                rg.addUser(UsersActivity.currentUser);
                            gotIt.put(s, true);
                            UsersActivity.changed = true;
                        }
                for (String s : book.getGenres()) {
                    if (!gotIt.get(s)) {
                        RecomGroup rg = new RecomGroup();
                        rg.setName("Legjobb " + s);
                        rg.addBook(book, 1);
                        rg.addUser(UsersActivity.currentUser);
                        UsersActivity.recomGroups.add(rg);
                        UsersActivity.changed = true;

                    }
                }

                Context context = getApplicationContext();
                CharSequence text = "Könyv megvásárolva!";

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(book.getTitle());
    }
}
