package com.example.gyuri.recomsys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

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

        return;


//        LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
//
//        ViewGroup grouplist = (ViewGroup) findViewById(R.id.groups_linear_layout);
//        ViewGroup shelf = (ViewGroup) li.inflate(R.layout.shelf_layout, null);
//        ViewGroup bookslist = (ViewGroup) li.inflate(R.layout.book_list_layout, null);
//        LinearLayout books = (LinearLayout) li.inflate(R.layout.books_linear_layout, null);
//
//        int arraySize = 1;//pur.size() /*< 5 ? rg.getBooks().size() : 5*/;
////        Book[] bestBooks = new Book[arraySize];
////        List<Book> l = new ArrayList<>(rg.getBooks().keySet());
////        for (int i = 0; i < arraySize; i++) {
////            bestBooks[i] = l.get(i);
////        }
//
//
//            LinearLayout book = (LinearLayout) li.inflate(R.layout.book_layout, null);
//            ((TextView) book.getChildAt(1)).setText(pur.book.getTitle());
//            ((TextView) book.getChildAt(2)).setText(pur.book.getAuthor());
//        int resID = getResources().getIdentifier(pur.book.getPicture(), "drawable", getPackageName());
//            ((ImageButton) book.getChildAt(0)).setImageResource(resID);
//
//            View.OnClickListener ocl = new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    Intent intent = new Intent(UsersActivity.this, PurchaseBookActivity.class);
////                    intent.putExtra("BOOK", b.writeToString());
////                    startActivity(intent);
//                }
//            };
//
//            books.addView(book);
//            book.setOnClickListener(ocl);
//            ((ImageButton) book.getChildAt(0)).setOnClickListener(ocl);
//
//
//        bookslist.addView(books);
//        shelf.addView(bookslist);
//        grouplist.addView(shelf);
//        ((TextView) ((RelativeLayout) shelf.getChildAt(0)).getChildAt(0)).setText("SAME IN ENGLISH");
    }

}
