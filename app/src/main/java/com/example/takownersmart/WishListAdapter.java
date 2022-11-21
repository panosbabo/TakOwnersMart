package com.example.takownersmart;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Reference: Some parts of the following code is from an online Android example https://github.com/ravizworldz/AndroidRoomDB_Java
public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.MyViewHolder> {

    // Private Variables for the Wish List Adapter
    private Context context;
    private List<Guitar> guitarListItems;
    private Button removeItem;

    // Fetching guitar images from drawable
    private Integer imageid[] = {
            R.drawable.p3dc,
            R.drawable.p4dc,
            R.drawable.p5dc,
            R.drawable.p7dc,
            R.drawable.ltd2016,
            R.drawable.ltd2020,
    };

    public WishListAdapter(Context context) {
        this.context = context;
    }

    public void setGuitarList(List<Guitar> guitarListItems) {
        this.guitarListItems = guitarListItems;
        notifyDataSetChanged();
    }

    // Adapter Initialized for the inflation of the wishlist items
    @NonNull
    @Override
    public WishListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wish_item, parent, false);

        // Creating an instance for the Remove item button
        removeItem = view.findViewById(R.id.remove_item_btn);

        // Function for the Remove Item button to remove each individual item from the list
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calling function to run Dao command on DELETE
                removeItemList(viewType);
                // Message displayed when user adds an item to the wishlist
                Toast.makeText(v.getContext(), "Your item has been removed.", Toast.LENGTH_SHORT).show();
                // Returning to previous activity as a refresh
                ((Activity)context).finish();
            }
        });
        return new MyViewHolder(view);
    }

    // Function to run Dao DELETE command for the specific item selected from the wishlist
    public void removeItemList(int position) {
        // Calling Database
        WishListDatabase db = WishListDatabase.getDbInstance(this.context);

        // Calling Database Access Object command for delete
        db.wishDao().delete(guitarListItems.get(position));
    }

    // A View holder for the adapter
    @Override
    public void onBindViewHolder(@NonNull WishListAdapter.MyViewHolder holder, int position) {

        // Holder to display the item's details on screen
        holder.tvguitarBrand.setText(this.guitarListItems.get(position).guitarBrand);
        holder.tvguitarModel.setText(this.guitarListItems.get(position).guitarModel);
        holder.tvguitarPrice.setText(this.guitarListItems.get(position).guitarPrice);
        holder.tvguitarOwner.setText(this.guitarListItems.get(position).guitarOwner);

        // Switch for Guitar Images
        switch (guitarListItems.get(position).guitarModel) {
            case "P3DC":
                holder.guitarImage.setImageResource(imageid[0]);
                break;
            case "P4DC":
                holder.guitarImage.setImageResource(imageid[1]);
                break;
            case "P5DC":
                holder.guitarImage.setImageResource(imageid[2]);
                break;
            case "P7DC":
                holder.guitarImage.setImageResource(imageid[3]);
                break;
            case "LTD2016":
                holder.guitarImage.setImageResource(imageid[4]);
                break;
            case "LTD2020":
                holder.guitarImage.setImageResource(imageid[5]);
                break;
        }
    }

    // getItemCount to retrieve the size of guitarListItems
    @Override
    public int getItemCount() {
        return  this.guitarListItems.size();
    }

    // Recycler Viewer for the wish list
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvguitarBrand;
        TextView tvguitarModel;
        TextView tvguitarPrice;
        TextView tvguitarOwner;
        ImageView guitarImage;


        // Holder for the Texts and Image Views
        public MyViewHolder(View view) {
            super(view);
            tvguitarBrand = view.findViewById(R.id.textViewGuitarBrand);
            tvguitarModel = view.findViewById(R.id.textViewGuitarModel);
            tvguitarPrice = view.findViewById(R.id.textViewGuitarPrice);
            tvguitarOwner = view.findViewById(R.id.textViewGuitarOwner);
            guitarImage = view.findViewById(R.id.imageViewGuitar);
        }
    }
}