package r.akshay.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainFragment1 extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnTEST;
//    private Spinner myspinner;
    public String selected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout,container,false);
        btnTEST = (Button) view.findViewById(R.id.buttonf1);

        Spinner myspinner= view.findViewById(R.id.spinner1);

        ArrayAdapter<String> myada = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));

        myada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        myspinner.setAdapter(myada);
        myspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                selected= adapterView.getItemAtPosition(pos).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1 "+selected,Toast.LENGTH_SHORT).show();
//                Bundle bundle= new Bundle();
//                bundle.putString("chosen",selected);
//                MainFragment1 farg= new MainFragment1();
//                farg.setArguments(bundle);
                Intent intent=new Intent(getActivity(),RateResult.class).putExtra("chosen",selected);
                startActivity(intent);
            }
        });
        return view;
    }

}
