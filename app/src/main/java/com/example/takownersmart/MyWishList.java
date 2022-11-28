package com.example.takownersmart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.takownersmart.db.Guitar;
import com.example.takownersmart.db.WishListDatabase;
import java.util.List;

public class MyWishList extends AppCompatActivity {

    // Variables for a recycler view adapter
    private WishListAdapter wishListAdapter;
    private List<Guitar> guitarListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);

        // View Variables for the wishlist
        RecyclerView wishListView;
        TextView emptyView;
        TextView empty_hint;

        // Initializing Views to the related R.id's
        wishListView = (RecyclerView) findViewById(R.id.idWishList);
        emptyView = (TextView) findViewById(R.id.empty_wishlist);
        empty_hint = (TextView) findViewById(R.id.hint_wishlist);

        // Initializing Recycler View for the WishList
        initRecyclerView();

        // Loading all items in the WishList
        loadWishList();

        // If-Else statement to check whether the WishList is empty or not
        // and display the related content in each case.
        if (guitarListItems.isEmpty()) {
            wishListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            empty_hint.setVisibility(View.VISIBLE);
        }
        else {
            // Creating a descent textview for the wish list page title
            TextView titleview = new TextView(this);
            titleview = findViewById(R.id.wishlist_titleview);
            titleview.setTypeface(Typeface.DEFAULT_BOLD);

            wishListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            empty_hint.setVisibility(View.GONE);
        }
    }

    // Reference: This part of the following code is from an online Android example https://github.com/ravizworldz/AndroidRoomDB_Java
    // Function that initializes an adapter for the recycler view
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.idWishList);
        // Setting layout to Linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Adding a divider line as decoration item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Setting the adapter to WishListAdapter
        wishListAdapter = new WishListAdapter(this);
        recyclerView.setAdapter(wishListAdapter);
    }

    // Function that allows to load all items from the wishlist
    private void loadWishList() {
        WishListDatabase db = WishListDatabase.getDbInstance(this.getApplicationContext());
        guitarListItems = db.wishDao().getAllGuitars();
        wishListAdapter.setGuitarList(guitarListItems);
    }

    // Ordinary onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadWishList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    // Reference Complete
}