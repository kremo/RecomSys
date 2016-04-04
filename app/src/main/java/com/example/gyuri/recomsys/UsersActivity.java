package com.example.gyuri.recomsys;

import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gyuri.recomsys.model.Book;
import com.example.gyuri.recomsys.model.RecomGroup;
import com.example.gyuri.recomsys.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ALL_BOOKS = 50;
    User currentUser;
    ArrayList<RecomGroup> recomGroups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        createExampleShelves();


        //readGson();

        createAllBooksShelf();


    }

    private void writeBook(Book b) {
        String s = b.writeToString();
        Log.d("BOOK", s);
        Book b2 = new Book(s);
        Log.d("BOOK2", b2.getAuthor() + b2.getPublisher() + b2.getGenres());
    }

    private void writeRecomGroup(RecomGroup rg) {
        String s = rg.writeToString();
        Log.d("RECG", s);
        Book b2 = new Book(s);
        Log.d("RECG", rg.getName());
    }


    private void createAllBooksShelf() {
        LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        ViewGroup grouplist = (ViewGroup) findViewById(R.id.groups_linear_layout);
        ViewGroup shelf = (ViewGroup) li.inflate(R.layout.shelf_layout, null);
        ViewGroup bookslist = (ViewGroup) li.inflate(R.layout.book_list_layout, null);
        LinearLayout books = (LinearLayout) li.inflate(R.layout.books_linear_layout, null);

        RecomGroup rg = new RecomGroup("allBooks");
        rg.addUser(new User("Katona György", 1000001, "Gyuri", 22));

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

                LinearLayout book = (LinearLayout) li.inflate(R.layout.book_layout, null);
                ((TextView) book.getChildAt(1)).setText(title);
                ((TextView) book.getChildAt(2)).setText(author);
                int resID = getResources().getIdentifier("book" + Integer.toString(i), "drawable", getPackageName());
                ((ImageButton) book.getChildAt(0)).setImageResource(resID);

                books.addView(book);

                //rg.addBook(new Book(title, author, released, publisher, price, genres), 0);
                //writeBook(new Book(title, author, released, publisher, price, genres));


                RecomGroup recg = new RecomGroup("test");
                Book b = new Book(title, author, released, publisher, price, genres);
                Book b2 = new Book(title + "2", author, released, publisher, price, genres);

                recg.addBook(b, 0);
                recg.addBook(b2, 2);

                User u = new User("Katona György", 1000001, "Gyuri", 22);
                User u2 = new User("Katona Aladár", 1000023, "Gyuri", 22);

                recg.addUser(u);
                recg.addUser(u2);


                writeRecomGroup(recg);


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        recomGroups.add(rg);

        bookslist.addView(books);
        shelf.addView(bookslist);
        grouplist.addView(shelf);
    }

    private void createExampleShelves() {
        LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        ViewGroup grouplist = (ViewGroup) findViewById(R.id.groups_linear_layout);

        ViewGroup shelf = (ViewGroup) li.inflate(R.layout.shelf_layout, null);
        ViewGroup shelf2 = (ViewGroup) li.inflate(R.layout.shelf_layout, null);

        ViewGroup bookslist = (ViewGroup) li.inflate(R.layout.book_list_layout, null);
        ViewGroup bookslist2 = (ViewGroup) li.inflate(R.layout.book_list_layout, null);


        LinearLayout books = (LinearLayout) li.inflate(R.layout.books_linear_layout, null);

        LinearLayout books2 = (LinearLayout) li.inflate(R.layout.books_linear_layout, null);


        LinearLayout book = (LinearLayout) li.inflate(R.layout.book_layout, books);
        LinearLayout book2 = (LinearLayout) li.inflate(R.layout.book_layout, books);
        LinearLayout book3 = (LinearLayout) li.inflate(R.layout.book_layout, books);
        LinearLayout book4 = (LinearLayout) li.inflate(R.layout.book_layout, books);
        LinearLayout book5 = (LinearLayout) li.inflate(R.layout.book_layout, books);
        LinearLayout book6 = (LinearLayout) li.inflate(R.layout.book_layout, books);


        LinearLayout book7 = (LinearLayout) li.inflate(R.layout.book_layout, books2);
        LinearLayout book8 = (LinearLayout) li.inflate(R.layout.book_layout, books2);
        LinearLayout book9 = (LinearLayout) li.inflate(R.layout.book_layout, books2);
        LinearLayout book10 = (LinearLayout) li.inflate(R.layout.book_layout, books2);
        LinearLayout book11 = (LinearLayout) li.inflate(R.layout.book_layout, books2);
        LinearLayout book12 = (LinearLayout) li.inflate(R.layout.book_layout, books2);

        bookslist.addView(books);
        shelf.addView(bookslist);
        grouplist.addView(shelf);


        bookslist2.addView(books2);
        shelf2.addView(bookslist2);
        grouplist.addView(shelf2);


    }


    @Override
    protected void onStart() {
        super.onStart();
        // readGson();
    }

    private void logRecomGroups() {
        for (int i = 0; i < recomGroups.size(); i++)
            Log.d("recomGroups", recomGroups.get(i).getName());
    }

    @Override
    protected void onStop() {
        writeGson();
        super.onStop();
    }

    private void writeGson() {
        Gson gson = new Gson();
        String s = gson.toJson(recomGroups);

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput("recomGroups.txt", Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readGson() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("recomGroups.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            Gson gson = new Gson();
            Type listOfRecomGroupObject = new TypeToken<ArrayList<RecomGroup>>() {
            }.getType();
            String json = sb.toString();

            Log.d("JSON: ", json);
            //first run
            if (json.equals("null")) {
                recomGroups = new ArrayList<>();
                writeGson();
                Log.d("RECOM", "OK");
            } else
                recomGroups = gson.fromJson(json, listOfRecomGroupObject);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                FileOutputStream fos = openFileOutput("recomGroups.txt", Context.MODE_PRIVATE);
                fos.write("".getBytes());
                fos.close();
                Log.d("FILE", "MADE");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } catch (IOException e) {
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
            recomGroups.add(new RecomGroup("one"));
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


        } else if (id == R.id.nav_laci) {
            //másik lista

        } else if (id == R.id.nav_purchases) {
            //összes vásárlás

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
