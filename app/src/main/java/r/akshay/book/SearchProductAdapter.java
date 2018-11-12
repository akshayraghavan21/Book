package r.akshay.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.ProductViewHolder> {
    private Context mCtx;
    private List<dataofbooks> dataofbooksList;

    //      public CardView cardeach;
    public SearchProductAdapter(Context mCtx, List<dataofbooks> dataofbooksList) {
        this.mCtx = mCtx;
        this.dataofbooksList = dataofbooksList;
    }

    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.customlayout, null);
        return new ProductViewHolder(view);
    }

    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        dataofbooks product = dataofbooksList.get(position);

        //holder.cardeach.setOnclickLi
        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        //holder.textViewRating.setStepSize((float) product.getRating());
        holder.textViewRating.setRating((float) product.getRating());

        //Changed it for the sake of Firebase
        Picasso.with(mCtx).load(product.getImage()).fit().into(holder.imageView);
//        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
    }

    public int getItemCount() {
        return dataofbooksList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewShortDesc;
        RatingBar textViewRating;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);
//                cardeach= itemView.findViewById(R.id.cardview);
            textViewTitle = itemView.findViewById(R.id.texttb);
            textViewShortDesc = itemView.findViewById(R.id.textrea);
            textViewRating = itemView.findViewById(R.id.ratingbar);
            imageView = itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
//                    User user = users.get(position);
                // We can access the data within the views
                if(mCtx instanceof SearchResult){
                Log.d(TAG, "onClick: Going to display from SearchResult activity");
                Toast.makeText(mCtx, textViewTitle.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mCtx, list.class);
                intent.putExtra("TB name", textViewTitle.getText());
//                intent.putExtra("TB rating", textViewRating.getRating());
////                intent.putExtra("img", (Parcelable) dataofbooksList.get(position));
//                intent.putExtra("TB desc", textViewShortDesc.getText());
                mCtx.startActivity(intent);
            }
            else if(mCtx instanceof RateResult){
                    Log.d(TAG, "onClick: Going to display from SearchResult activity");
                    Toast.makeText(mCtx, textViewTitle.getText(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mCtx, RateList.class);
                    intent.putExtra("TB name", textViewTitle.getText());
                    intent.putExtra("Activity", "SearchProduct");
//                intent.putExtra("TB rating", textViewRating.getRating());
////                intent.putExtra("img", (Parcelable) dataofbooksList.get(position));
//                intent.putExtra("TB desc", textViewShortDesc.getText());
                    mCtx.startActivity(intent);
                }
        }
        }
    }
}
