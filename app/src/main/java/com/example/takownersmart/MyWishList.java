package com.example.takownersmart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MyWishList extends AppCompatActivity {

    // Variables for a recycler view.
    private RecyclerView wishListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wish_list);

        wishListView = findViewById(R.id.idWishList);
        wishListView.setLayoutManager(new LinearLayoutManager(this));
        wishListView.setHasFixedSize(true);

//        //initializing adapter for recycler view.
//        final PersonRVAdapter adapter = new PersonRVAdapter();
//        //setting adapter class for recycler view.
//        personsRV.setAdapter(adapter);
//        //passing a data from view modal.
//        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
//        //below line is use to get all the courses from view modal.
//        viewmodal.getAllPersons().observe(this, new Observer<List<PersonModal>>() {
//            @Override
//            public void onChanged(List<PersonModal> models) {
//                //when the data is changed in our models we are adding that list to our adapter class.
//                adapter.submitList(models);
//            }
//        });

    }
}