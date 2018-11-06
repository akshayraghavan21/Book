package r.akshay.book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import java.util.* ;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.Rating;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity{
    private static final String TAG = "Tab1Fragment";
    //    int[] lol={5,6};
//    ArrayList<Integer> array_image = new ArrayList<>();
//    String[] Subject_author={"W3Resources","O'Rielly"};
//    String[] Subject_reason={"Really Good for practice","Really good for understanding"};
//    int[] starrate={5,2};
    private LinearLayout layoutFileupload, layoutNameupload;
    FloatingActionButton fupload;
    private boolean fabExpanded = false;
    private FloatingActionButton upload;

    private String selected;

    List<dataofbooks> dataofbooksList;
    RecyclerView recyclerView;

    //Changed it for the sake of Firebase
    private DatabaseReference mDataaseRef, mdata;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result);
        if (getIntent().hasExtra("chosen")) {

            selected = getIntent().getStringExtra("chosen");
        }

        if(selected.equals("ITE1016")) {
            recyclerView = (RecyclerView) findViewById(R.id.listv);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            dataofbooksList = new ArrayList<>();

//            dataofbooksList.add(new dataofbooks(1016, "O'Rielly", "This is really good for business aspect of MAD", 4.3, R.drawable.bestbrielly));
//            dataofbooksList.add(new dataofbooks(1016, "Android Developers", "Detailed, but bad for beginners", 4.9, R.drawable.androiddev));
//            dataofbooksList.add(new dataofbooks(1016, "Building iPhone Apps with HTML, CSS and JavaScript", "Detailed, but bad for beginners", 2.9, R.drawable.iphoneappsorielly));
//            dataofbooksList.add(new dataofbooks(1016, "Android for programmers", "Detailed, but bad for beginners", 1.9, R.drawable.aforbeginners));
//            dataofbooksList.add(new dataofbooks(1016, "Guide for iOS-Apple developers", "Detailed, but bad for beginners", 2.5, R.drawable.iosdev));

            //Changed it for the sake of Firebase
            Query query = FirebaseDatabase.getInstance().getReference("/RateRecyclerview/").orderByChild("id").equalTo("ITE1016");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onDataChange: Hmm "+ dataSnapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        dataofbooks data = postSnapshot.getValue(dataofbooks.class);
                        String  id = data.getId();
                        String title = data.getTitle();
                        String desc = data.getShortdesc();
                        Double rating = data.getRating();
                        int image = data.getImage();
                        dataofbooks a = new dataofbooks(id,title,desc,rating,image);
                        dataofbooksList.add(a);
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SearchResult.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

//            mdata = FirebaseDatabase.getInstance().getReference("/SearchRecyclerview/ITE1016/Android for programmers");
//            mdata.child("rating").setValue(3);
            SearchProductAdapter adapter = new SearchProductAdapter(this, dataofbooksList);
            recyclerView.setAdapter(adapter);
            Log.d(TAG, "onCreateSearch: PLS"+ dataofbooksList);

            TextView searchhead=findViewById(R.id.searchresheading);
            String s= searchhead.getText().toString();
            s=s+" "+selected;
            searchhead.setText(s);
        }
        else if(selected.equals("ITE2016")) {
            recyclerView = (RecyclerView) findViewById(R.id.listv);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            dataofbooksList = new ArrayList<>();
//            dataofbooksList.add(new dataofbooks(2016, "ML for Beginners", "This is really good for hands on experience", 4, R.drawable.ic_file_upload_black_24dp));
//            dataofbooksList.add(new dataofbooks(2016, "ML for Advance users", "This is really good for expertise", 4.5, R.drawable.ic_file_upload_black_24dp));
//            dataofbooksList.add(new dataofbooks(2016, "AI for VR", "Good for problems", 3.7, R.drawable.ic_file_upload_black_24dp));

            SearchProductAdapter adapter = new SearchProductAdapter(this, dataofbooksList);
            recyclerView.setAdapter(adapter);
            TextView searchhead=findViewById(R.id.searchresheading);
            String s= searchhead.getText().toString();
            s=s+" "+selected;
            searchhead.setText(s);
        }
        else{
//            this.finish();
            getFragmentManager().popBackStackImmediate();
            Toast.makeText(this,"Wrong option chosen",Toast.LENGTH_SHORT).show();
        }

        layoutFileupload = (LinearLayout) findViewById(R.id.layoutLocalupload);
        layoutNameupload = (LinearLayout) findViewById(R.id.layoutNameOnlyUplaod);

//        TextView tview2 = findViewById(R.id.Heading);
//        Log.d("Selected: ", selected);
//        String s = tview2.getText().toString() + selected;
//        tview2.setText(selected);


        fupload = (FloatingActionButton) findViewById(R.id.fabSetting);
        fupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });
        closeSubMenusFab();
    }
//        array_image.add(R.drawable.ic_clear_black_24dp);
//        array_image.add(R.drawable.ic_create_black_24dp);
//        array_image.add(R.drawable.ic_create_black_24dp);
//        RecyclerView lisv= findViewById(R.id.listv);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        lisv.setLayoutManager(mLayoutManager);
//        CustomAdapter customAdapter=new CustomAdapter();
//        lisv.setAdapter(customAdapter);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void closeSubMenusFab() {
        layoutFileupload.setVisibility(View.INVISIBLE);
        layoutNameupload.setVisibility(View.INVISIBLE);
        fupload.setImageResource(R.drawable.ic_create_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    public void openSubMenusFab() {
        layoutFileupload.setVisibility(View.VISIBLE);
        layoutNameupload.setVisibility(View.VISIBLE);
        fupload.setImageResource(R.drawable.ic_clear_black_24dp);
        fabExpanded = true;
    }
}



//    class CustomAdapter extends RecyclerView.Adapter{
//        @NonNull
//        @Override
//        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View view = getLayoutInflater().inflate(R.layout.customlayout, viewGroup, false);
//            return new RecyclerView.ViewHolder(view) {
//            };
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//        @Override
//        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//            Intent intent=getIntent();
//            String selected= Objects.requireNonNull(intent.getExtras()).getString("chosen");
//            assert selected != null;
//            if(selected.equals("ITE1016")){
//                ImageView image = (ImageView) findViewById(R.id.thumbnail);
//                TextView v1 = (TextView)    findViewById(R.id.texttb);
//                TextView v2 = (TextView) findViewById(R.id.textrea);
//                RatingBar re = (RatingBar) findViewById(R.id.ratingbar);
//                image.setImageResource(array_image.get(i));
//                v1.setText(Subject_author[i]);
//                v2.setText(Subject_reason[i]);
//                re.setRating(starrate[i]);}
//        }
//
//        @Override
//        public int getItemCount() {
//            return lol.length;
//        }
//    }
