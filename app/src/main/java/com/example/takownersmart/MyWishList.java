package com.example.takownersmart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.takownersmart.db.Guitar;
import com.example.takownersmart.db.WishListDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyWishList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyWishList extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // Variables for a recycler view adapter
    private WishListAdapter wishListAdapter;
    private List<Guitar> guitarListItems;

    public MyWishList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishList.
     */
    // TODO: Rename and change types and number of parameters
    public static MyWishList newInstance(String param1, String param2) {
        MyWishList fragment = new MyWishList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);

        // View Variables for the wishlist
        RecyclerView wishListView;
        TextView emptyView;
        TextView empty_hint;

        // Initializing Views to the related R.id's
        wishListView = (RecyclerView) view.findViewById(R.id.idWishList);
        emptyView = (TextView) view.findViewById(R.id.empty_wishlist);
        empty_hint = (TextView) view.findViewById(R.id.hint_wishlist);

        // Initializing Recycler View for the WishList
        RecyclerView recyclerView = view.findViewById(R.id.idWishList);
        // Setting layout to Linear
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Adding a divider line as decoration item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Setting the adapter to WishListAdapter
        wishListAdapter = new WishListAdapter(getContext());
        recyclerView.setAdapter(wishListAdapter);

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
            TextView titleview = new TextView(getContext());
            titleview = view.findViewById(R.id.wishlist_titleview);
            titleview.setTypeface(Typeface.DEFAULT_BOLD);

            wishListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            empty_hint.setVisibility(View.GONE);
        }

        return view;
    }

    // Reference: This part of the following code is from an online Android example https://github.com/ravizworldz/AndroidRoomDB_Java
    // Function that allows to load all items from the wishlist
    private void loadWishList() {
        WishListDatabase db = WishListDatabase.getDbInstance(this.getContext());
        guitarListItems = db.wishDao().getAllGuitars();
        wishListAdapter.setGuitarList(guitarListItems);
    }

    // Ordinary onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadWishList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    // Reference Complete
}