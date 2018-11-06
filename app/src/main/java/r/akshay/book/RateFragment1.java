package r.akshay.book;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.FirebaseApp;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class RateFragment1 extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST;
//    private Spinner myspinner;
    private String selected, name;

    List<dataofbooks> dataofbooksList;
    RecyclerView recyclerView;

    //Database connectivity
    private DatabaseReference databaseReference;

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            name = bundle.getString("name");
//            age = bundle.getInt("age");
        }
    }

    public static RateFragment1 newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);

        RateFragment1 fragment = new RateFragment1();
        fragment.setArguments(bundle);

        return fragment;
    }
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("message");
//    myRef.setValue("Hello, World!");
//
//    myRef.addValueEventListener(new ValueEventListener() {
//        @Override
//        public void onDataChange(DataSnapshot dataSnapshot) {
//            // This method is called once with the initial value and again
//            // whenever data at this location is updated.
//            String value = dataSnapshot.getValue(String.class);
//            Log.d(TAG, "Value is: " + value);
//        }
//
//        @Override
//        public void onCancelled(DatabaseError error) {
//            // Failed to read value
//            Log.w(TAG, "Failed to read value.", error.toException());
//        }
//    });

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment1,container,false);

//        if(FirebaseApp.getApps(getContext()).isEmpty()){
//            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        }

//        assert getArguments() != null;
         Log.d(TAG, "Does the arg work?:"+ name);
//        String strtext = getArguments().getString("you");
        readBundle(getArguments());
        TextView txt = view.findViewById(R.id.searchresheading2);
        String s = txt.getText().toString();
        s= s+ name;
        txt.setText(s);

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//        myRef.setValue("Hello, World!");
//
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
////                String value = dataSnapshot.getValue();
//                Log.d(TAG, "Value is: please " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "This dint work.", error.toException());
//            }
//        });

//        databaseReference.child(Dataofbooks).setValue("ITE1016", "This is awesome");

        recyclerView = view.findViewById(R.id.raterecycle);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataofbooksList = new ArrayList<>();
        dataofbooksList.add(new dataofbooks("ITE1016", "W3 Resources", "This is really good for hands on experience", 4, R.drawable.w3));
        dataofbooksList.add(new dataofbooks("ITE1016", "O'Rielly", "This is really good for business aspect of MAD", 4.3, R.drawable.bestbrielly));
        dataofbooksList.add(new dataofbooks("ITE1016", "Android Developers", "Detailed, but bad for beginners", 4.9, R.drawable.androiddev));
        dataofbooksList.add(new dataofbooks("ITE1016", "Building iPhone Apps with HTML, CSS and JavaScript", "Detailed, but bad for beginners", 2.9, R.drawable.iphoneappsorielly));
        dataofbooksList.add(new dataofbooks("ITE1016", "Android for programmers", "Detailed, but bad for beginners", 1.9, R.drawable.aforbeginners));
        dataofbooksList.add(new dataofbooks("ITE1016", "Guide for iOS-Apple developers", "Detailed, but bad for beginners", 2.5, R.drawable.iosdev));

        SearchProductAdapter adapter = new SearchProductAdapter(getActivity(), dataofbooksList);
        recyclerView.setAdapter(adapter);

//        TextView searchhead=view.findViewById(R.id.searchresheading);
//        String s= searchhead.getText().toString();
//        s=s+" "+ strtext;
//        searchhead.setText(s);

//        RateResult activity = (RateResult) getActivity();
//        String myDataFromActivity = activity.getMyd();
//        return view;
////
//        Bundle bundle=this.getArguments()
//        String sr= bundle.getString("chosen");
//        TextView ratesearch = view.findViewById(R.id.searchresheading2);
//        ratesearch.setText(sr);




//        Intent intent=((Activity)getContext()).getIntent();
//        if (getActivity().getIntent().hasExtra("chosen")) {
//
//            selected = getIntent().getStringExtra("chosen");
//        }
//        Log.d("Selected: ", selected);
//        String s= ratesearch.getText().toString();
//        s=s+" "+ selected;
//        ratesearch.setText(selected);
//        btnTEST = (Button) view.findViewById(R.id.buttonf1);
//
//        Spinner myspinner= view.findViewById(R.id.spinner1);
//
//        ArrayAdapter<String> myada = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
//
//        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        myspinner.setAdapter(myada);
//        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
//                selected= adapterView.getItemAtPosition(pos).toString();
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        btnTEST.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getActivity(),RateResult.class).putExtra("chosen",selected);
//                startActivity(intent);
//            }
//        });
        return view;
//        return inflater.inflate(R.layout.rate_fragment1,container, false);
    }
}
