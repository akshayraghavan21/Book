package r.akshay.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class RateList extends AppCompatActivity{

    private String nameTitle,pathOfBook;
    private String Txt1, Txt2, img, Ed1, Ed2, FinalDesc;
    private double rating, FinalRating;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    private String TAG = "RateList";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratelist);

        final EditText newDesc = findViewById(R.id.newDesc);
        final EditText newRating = findViewById(R.id.newRating);
        final Button RateBtn = findViewById(R.id.button);
        final TextView prevDesc = findViewById(R.id.prevDesc);
        final TextView nameOfBook = findViewById(R.id.prevName);
        final RatingBar ratingBar = findViewById(R.id.rateRatingBar);
        final ImageView imgdisp = findViewById(R.id.rateImageView);

        Intent intent = getIntent();
        if(intent!=null){
            String activity = intent.getStringExtra("Activity");
            if(activity.equals("list")){
                nameTitle = intent.getStringExtra("CourseName");
                pathOfBook = "/RateRecyclerview/" + nameTitle;

            }
            else if (activity.equals("SearchProduct")){
                nameTitle = intent.getStringExtra("TB name");
                pathOfBook = "/RateRecyclerview/" + nameTitle;

            }
        }

            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(pathOfBook);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Txt1 = (String) dataSnapshot.child("title").getValue();
                    nameOfBook.setText(Txt1);

                    Txt2 = (String) dataSnapshot.child("shortdesc").getValue();
                    prevDesc.setText(Txt2);

                    img = (String) dataSnapshot.child("image").getValue();
                    Picasso.with(getBaseContext()).load(img).fit().into(imgdisp);

                    rating = (Double) dataSnapshot.child("rating").getValue();
                    ratingBar.setRating(Float.valueOf(String.valueOf(rating)));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
//                nameOfBook.setText("");

                }
            });
        RateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ed1 = newDesc.getText().toString();
                Ed2 = newRating.getText().toString();

                FinalRating = (Double.valueOf(Ed2) + rating)/2;
                databaseReference.child("rating").setValue(FinalRating);

                FinalDesc = Txt2 + " " + Ed1;
                databaseReference.child("shortdesc").setValue(FinalDesc);
                Toast.makeText(getBaseContext(),"The values re been updated" + FinalDesc + " " + FinalRating, Toast.LENGTH_LONG).show();
            }
        });




    }
}
