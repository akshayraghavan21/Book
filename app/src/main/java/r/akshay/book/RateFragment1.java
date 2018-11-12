package r.akshay.book;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
public class RateFragment1 extends Fragment {
    private static final String TAG = "Tab1Fragment";
    private Button btnTEST;
    public static String name;
    List<dataofbooks> dataofbooksList;
    RecyclerView recyclerView;
    public String selected;
    private DatabaseReference databaseReference;
    public static void putArgs(Bundle args){
        name = args.getString("yay");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment1,container,false);

        TextView txt = view.findViewById(R.id.searchresheading2);
        String s = txt.getText().toString();
        s= s + " " + name;
        txt.setText(s);
        recyclerView = view.findViewById(R.id.raterecycle);
        recyclerView.setItemAnimator(new SlideInUpAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dataofbooksList = new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference("/RateRecyclerview/").orderByChild("id").equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    dataofbooks data = postSnapshot.getValue(dataofbooks.class);
                    Log.d(TAG, "Val: " + postSnapshot.getValue());
                    Log.d(TAG, "Checking the list: " + dataofbooksList);
                    dataofbooksList.add(data);
                }
                SearchProductAdapter adapter = new SearchProductAdapter(getContext(), dataofbooksList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
