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
import android.view.Menu;
import android.view.MenuInflater;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity{
    private static final String TAG = "Tab1Fragment";
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
//        Query query = FirebaseDatabase.getInstance().getReference("/RateRecyclerview/").orderByChild("id").equalTo("ITE1016");

            recyclerView = (RecyclerView) findViewById(R.id.listv);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            dataofbooksList = new ArrayList<>();

        //Changed it for the sake of Firebase
            Query query = FirebaseDatabase.getInstance().getReference("/RateRecyclerview/").orderByChild("id").equalTo(selected);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d(TAG, "onDataChange: Hmm "+ dataSnapshot.getChildrenCount());
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        dataofbooks data = postSnapshot.getValue(dataofbooks.class);
                        Log.d(TAG, "Val: " + postSnapshot.getValue());
                        dataofbooksList.add(data);
                    }
                    SearchProductAdapter adapter = new SearchProductAdapter(SearchResult.this, dataofbooksList);
                    recyclerView.setAdapter(adapter);
                    Log.d(TAG, "Checking the list: " + dataofbooksList);


                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(SearchResult.this,databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            Log.d(TAG, "onCreateSearch: PLS"+ dataofbooksList);

            TextView searchhead=findViewById(R.id.searchresheading);
            String s= searchhead.getText().toString();
            s=s+" "+selected;
            searchhead.setText(s);

//        else if(selected.equals("ITE2016")) {
//            recyclerView = (RecyclerView) findViewById(R.id.listv);
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//            SearchProductAdapter adapter = new SearchProductAdapter(this, dataofbooksList);
//            recyclerView.setAdapter(adapter);
//            TextView searchhead=findViewById(R.id.searchresheading);
//            String s= searchhead.getText().toString();
//            s=s+" "+selected;
//            searchhead.setText(s);
//        }
//        else{
////            this.finish();
//            getFragmentManager().popBackStackImmediate();
//            Toast.makeText(this,"Wrong option chosen",Toast.LENGTH_SHORT).show();
//        }

        layoutFileupload = (LinearLayout) findViewById(R.id.layoutLocalupload);
        layoutNameupload = (LinearLayout) findViewById(R.id.layoutNameOnlyUplaod);
        SearchProductAdapter adapter = new SearchProductAdapter(SearchResult.this, dataofbooksList);
        recyclerView.setAdapter(adapter);


//        fupload = (FloatingActionButton) findViewById(R.id.fabSetting);
//        fupload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (fabExpanded){
//                    closeSubMenusFab();
//                } else {
//                    openSubMenusFab();
//                }
//            }
//        });
//        closeSubMenusFab();
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main2, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_upload2:
                Intent intent = new Intent(this, RateResult.class);
                intent.putExtra("chosen",selected);
                startActivity(intent);
                return true;
            case R.id.logoutmenu2:
                FirebaseAuth.getInstance().signOut();
                Intent intentexit = new Intent(this,Login.class);
                intentexit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentexit);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public void closeSubMenusFab() {
//        layoutFileupload.setVisibility(View.INVISIBLE);
//        layoutNameupload.setVisibility(View.INVISIBLE);
//        fupload.setImageResource(R.drawable.ic_create_black_24dp);
//        fabExpanded = false;
//    }
//
//    //Opens FAB submenus
//    public void openSubMenusFab() {
//        layoutFileupload.setVisibility(View.VISIBLE);
//        layoutNameupload.setVisibility(View.VISIBLE);
//        fupload.setImageResource(R.drawable.ic_clear_black_24dp);
//        fabExpanded = true;
    }



