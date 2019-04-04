package r.akshay.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class list extends AppCompatActivity {
    private String nameUp, descUp, imgage , name, actualID;
    private ImageView imga;
    private String subID;
    private Double rating;
    private Double rat;

    DatabaseReference databaselist;
    FirebaseDatabase firebaseDatabase;

    private final String TAG = "this fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Intent intent=getIntent();
        name=intent.getStringExtra("TB name");
        actualID=intent.getStringExtra("TB name");
        name = "/RateRecyclerview/" + name;

        final ImageView imageView = findViewById(R.id.dispimg);
        final RatingBar ratingBar = findViewById(R.id.ratingbar);
        final TextView NameUp = findViewById(R.id.nameOfBook);
        final TextView DescUp = findViewById(R.id.descOfBook);

        databaselist = FirebaseDatabase.getInstance().getReference(name);

        databaselist.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                nameUp = (String) dataSnapshot.child("title").getValue();
                Log.d(TAG, "REad data: " + nameUp);
                NameUp.setText(nameUp);

                descUp = (String) dataSnapshot.child("shortdesc").getValue();
                DescUp.setText(descUp);

                rating = dataSnapshot.child("rating").getValue(Double.class);
                ratingBar.setRating(Float.valueOf(String.valueOf(rating)));

                imgage = (String) dataSnapshot.child("image").getValue();
                Picasso.with(getBaseContext()).load(imgage).fit().into(imageView);

                subID = (String) dataSnapshot.child("id").getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                nameUp = "Failed";
                NameUp.setText(nameUp);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_upload:
                Intent intent = new Intent(this, BookNameUpload.class);
                intent.putExtra("CourseID",subID);
                startActivity(intent);
                return true;
            case R.id.action_rate:
                Intent intent1 = new Intent(this, RateList.class);
                intent1.putExtra("CourseName",actualID);
                intent1.putExtra("Activity", "list");
                startActivity(intent1);
                return true;
            case R.id.logoutmenu:
                FirebaseAuth.getInstance().signOut();
                Intent intentexit = new Intent(this,Login.class);
                intentexit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentexit);
                finish();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
