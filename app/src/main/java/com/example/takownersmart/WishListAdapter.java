package com.example.takownersmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishLT> {

    List<Guitar> guitarListItems;
    private Context context;

        public WishListAdapter(Context context) {
        this.context = context;
    }

        public void setGuitarList(List<Guitar> guitarListItems) {
        this.guitarListItems = guitarListItems;
        notifyDataSetChanged();
    }

    // Fetching guitar images from drawable
    private Integer imageid[] = {
            R.drawable.p3dc,
            R.drawable.p4dc,
            R.drawable.p5dc,
            R.drawable.p7dc,
            R.drawable.ltd2016,
            R.drawable.ltd2020,
    };

    // Adapter Initialized for the inflation of the wishlist items
    @NonNull
    @Override
    public WishLT onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wish_item, parent, false);

        return new WishLT(view).linkAdapter(this);
    }

    // A view holder for the adapter
    @Override
    public void onBindViewHolder(@NonNull WishLT holder, int position) {
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
        return guitarListItems.size();
    }
}

// Recycler Viewer for the wish list
class WishLT extends RecyclerView.ViewHolder{

    // Variables to be used for the adapter
    TextView tvguitarBrand;
    TextView tvguitarModel;
    TextView tvguitarPrice;
    TextView tvguitarOwner;
    ImageView guitarImage;
    private WishListAdapter adapter;
    private Context context;

    // Holder for the Texts and Image Views and Remove Button
    public WishLT (@NonNull View itemView) {
        super(itemView);

        // Views initialized to related Resource id's.
        tvguitarBrand = itemView.findViewById(R.id.textViewGuitarBrand);
        tvguitarModel = itemView.findViewById(R.id.textViewGuitarModel);
        tvguitarPrice = itemView.findViewById(R.id.textViewGuitarPrice);
        tvguitarOwner = itemView.findViewById(R.id.textViewGuitarOwner);
        guitarImage = itemView.findViewById(R.id.imageViewGuitar);

        // Creating an instance for the Remove item button
        Button removeItem = itemView.findViewById(R.id.remove_item_btn);

        // Function for the Remove Item button to remove each individual item from the list
        removeItem.setOnClickListener(v -> {
            // Calling function to run Dao command on DELETE
            removeItemList(getAdapterPosition());
            // Also removed from the adapter list
            adapter.guitarListItems.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());

            // Message displayed when user adds an item to the wishlist
//            Toast.makeText(v.getContext(), adapter.guitarListItems.get(getItemViewType()).guitarModel + " has been removed from My wishlist", Toast.LENGTH_SHORT).show();
            Toast.makeText(v.getContext(), "Your item has been removed", Toast.LENGTH_SHORT).show();
        });
    }

    // Function to run Dao DELETE command for the specific item selected from the wishlist
    public void removeItemList(int position) {
        // Calling Database
        WishListDatabase db = WishListDatabase.getDbInstance(this.context);

        // Calling Database Access Object command for delete
        db.wishDao().delete(adapter.guitarListItems.get(position));
    }

    // Adapter initialization
    public WishLT linkAdapter(WishListAdapter adapter){
        this.adapter = adapter;
        return this;
    }
}