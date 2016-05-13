package com.example.gyuri.recomsys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gyuri.recomsys.model.Book;
import com.example.gyuri.recomsys.model.Purchase;
import com.example.gyuri.recomsys.model.RecomGroup;
import com.example.gyuri.recomsys.model.User;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UsersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button bRegister;
    EditText etName, etUserName, etAge;

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;

    final String arraySeparator = "[!-_-!]";

    private static final int ALL_BOOKS = 50;
    public static User currentUser;
    public static ArrayList<RecomGroup> recomGroups = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Purchase> purchases = new ArrayList<>();
    public static boolean changed = false;
    static boolean firstrun = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //korválasztó legördülő listának






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_register);
        navigationView2.setNavigationItemSelectedListener(this);*/


        //createExampleShelves();

        if (changed) {
            writeToFiles();
            changed = false;
        }

        readFromFiles();

        if (users.size() == 0) {
            users.add(new User("Kovács László", 1000002, "Laci", 24));
            users.add(new User("Katona György", 1000001, "Gyuri", 22));
            writeToFiles();
        }

        for (User u : users) {
            if (u.getNickName().equals("Gyuri"))
                currentUser = u;
        }


        if (recomGroups.size() == 0) {
            createAllBooksRecomGroup();
            writeToFiles();
        }

        createShelvesForUser();


    }


    private void createShelvesForUser() {
        ViewGroup grouplist = (ViewGroup) findViewById(R.id.groups_linear_layout);
        assert grouplist != null;
        grouplist.removeAllViews();
        for (int i = 0; i < recomGroups.size(); i++)
            if (recomGroups.get(i).getName().equals("Minden Könyv"))
                if (i != (recomGroups.size() - 1)) {
                    recomGroups.add(recomGroups.get(i));
                    recomGroups.remove(i);
                }
        for (RecomGroup rg : recomGroups) {
            if (rg.getUsers().contains(currentUser)) {
                createShelf(rg);
                Log.d("KÖNYVREC", rg.getName());
                for(Book b: rg.getBooks().keySet())
                    Log.d("KÖNYV",b.getTitle());
            }
        }
    }

    private void createShelf(RecomGroup rg) {
        LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        ViewGroup grouplist = (ViewGroup) findViewById(R.id.groups_linear_layout);
        ViewGroup shelf = (ViewGroup) li.inflate(R.layout.shelf_layout, null);
        ViewGroup bookslist = (ViewGroup) li.inflate(R.layout.book_list_layout, null);
        LinearLayout books = (LinearLayout) li.inflate(R.layout.books_linear_layout, null);

        int arraySize = rg.getBooks().size() /*< 5 ? rg.getBooks().size() : 5*/;
        Book[] bestBooks = new Book[arraySize];
        rg.sortByValue();
        List<Book> l = new ArrayList<>(rg.getBooks().keySet());
        for (int i = 0; i < arraySize; i++) {
            bestBooks[i] = l.get(i);
        }


        for (final Book b : bestBooks) {
            LinearLayout book = (LinearLayout) li.inflate(R.layout.book_layout, null);
            ((TextView) book.getChildAt(1)).setText(b.getTitle());
            ((TextView) book.getChildAt(2)).setText(b.getAuthor());
            int resID = getResources().getIdentifier(b.getPicture(), "drawable", getPackageName());
            ((ImageButton) book.getChildAt(0)).setImageResource(resID);

            View.OnClickListener ocl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UsersActivity.this, PurchaseBookActivity.class);
                    intent.putExtra("BOOK", b.writeToString());
                    startActivity(intent);
                }
            };

            books.addView(book);
            book.setOnClickListener(ocl);
            ((ImageButton) book.getChildAt(0)).setOnClickListener(ocl);
        }

        bookslist.addView(books);
        shelf.addView(bookslist);
        grouplist.addView(shelf);

        ((TextView) ((RelativeLayout) shelf.getChildAt(0)).getChildAt(0)).setText(rg.getName());

    }


    private void createAllBooksRecomGroup() {


        RecomGroup rg = new RecomGroup();
        rg.setName("Minden könyv");


        for (int i = 1; i < ALL_BOOKS + 1; i++) {
            try {
                InputStream in = this.getAssets().open("book" + Integer.toString(i) + ".txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String title = reader.readLine();
                String author = reader.readLine();
                int released = Integer.parseInt(reader.readLine());
                String publisher = reader.readLine();
                int price = Integer.parseInt(reader.readLine());
                ArrayList<String> genres = new ArrayList<>();
                String line = reader.readLine();
                while (line != null) {
                    genres.add(line);
                    line = reader.readLine();
                }

                reader.close();

                rg.addBook(new Book(title, author, released, publisher, price, "book" + Integer.toString(i), genres), 0);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        boolean gotIt = false;
        for (RecomGroup recomGroup : recomGroups)
            if (recomGroup.getName().equals("Minden könyv"))
                gotIt = true;

        if (!gotIt) {
            for (User u : users)
                rg.addUser(u);
            recomGroups.add(rg);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        writeToFiles();
        createShelvesForUser();

    }


    @Override
    protected void onStart() {
        if (changed) {
            writeToFiles();
            changed = false;
        }
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void readFromFiles() {


        readRecomGroups();
        readUsers();
        readPurchases();


    }

    private void readPurchases() {
        FileInputStream fis = null;

        try {
            fis = openFileInput("purchases.txt");


            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String all = sb.toString();

            purchases.clear();
            String[] ps = all.split(Pattern.quote(arraySeparator));
            for (String str : ps) {
                if (str.length() > 0)
                    purchases.add(new Purchase(str));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readRecomGroups() {
        FileInputStream fis = null;

        try {
            fis = openFileInput("recomGroups.txt");


            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String all = new String();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                all = all.concat(line);
            }
            bufferedReader.close();


            String[] rgs = all.split(Pattern.quote(arraySeparator));

            recomGroups.clear();
            for (String str : rgs) {
                if (str.length() > 0) {
                    RecomGroup rg = new RecomGroup(str);
                    recomGroups.add(rg);
                }
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }


    private void readUsers() {
        FileInputStream fis = null;

        try {
            fis = openFileInput("users.txt");


            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            String all = sb.toString();


            users.clear();
            String[] us = all.split(Pattern.quote(arraySeparator));
            for (String str : us)
                if (str.length() > 0)
                    users.add(new User(str));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logRecomGroups() {
        for (RecomGroup rg : recomGroups)
            Log.d("RECOMGROUP", rg.getName());
    }

    @Override
    protected void onStop() {
        writeToFiles();
        super.onStop();
    }

    public void writeToFiles() {
        writeRecomGroups();
        writeUsers();
        writePurchases();
    }

    private void writePurchases() {
        String str = new String();
        for (Purchase p : purchases) {
            str = str.concat(p.writeToString() + arraySeparator);
        }
        try {
            FileOutputStream outputStream;
            outputStream = openFileOutput("purchases.txt", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeUsers() {
        String str = new String();
        for (User u : users) {
            str = str.concat(u.writeToString() + arraySeparator);
        }
        try {
            FileOutputStream outputStream;
            outputStream = openFileOutput("users.txt", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeRecomGroups() {
        String str = new String();
        for (RecomGroup rg : recomGroups) {
            str = str.concat(rg.writeToString() + arraySeparator);
        }

        try {
            FileOutputStream outputStream;
            outputStream = openFileOutput("recomGroups.txt", Context.MODE_PRIVATE);
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
            System.exit(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_registration) {
            Intent intent = new Intent(UsersActivity.this, RegisterActivity.class);
            //intent.putExtra("BOOK", b.writeToString());
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gyuri) {
            //egyik lista
            for (User u : users) {
                if (u.getNickName().equals("Gyuri"))
                    currentUser = u;
            }
            createShelvesForUser();


        } else if (id == R.id.nav_laci) {
            //másik lista
            for (User u : users) {
                if (u.getNickName().equals("Laci"))
                    currentUser = u;
            }
            createShelvesForUser();

        } else if (id == R.id.nav_purchases) {
            //összes vásárlás

            Intent intent = new Intent(UsersActivity.this, PurchasesListActivity.class);
            startActivity(intent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
