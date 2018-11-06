package r.akshay.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class RateFragment2 extends Fragment {
    private static final String TAG = "RateTab1Fragment";
    private Button btnTEST;
    private String selected;
    //    private Spinner myspinner;
    FloatingActionButton fupload;
    private FloatingActionButton upload;
    private LinearLayout layoutFileupload, layoutNameupload;
    private boolean fabExpanded = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment2, container, false);
        btnTEST = (Button) view.findViewById(R.id.buttonf2);

        Log.d(TAG, "Created Fragment 2: ");
        layoutFileupload = (LinearLayout) view.findViewById(R.id.layoutLocalupload);
        layoutNameupload = (LinearLayout) view.findViewById(R.id.layoutNameOnlyUplaod);
        fupload = (FloatingActionButton) view.findViewById(R.id.fabSetting);
        fupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded ){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }

        });
        closeSubMenusFab();
        return view;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void closeSubMenusFab() {
        layoutFileupload.setVisibility(View.INVISIBLE);
        layoutNameupload.setVisibility(View.INVISIBLE);
        fupload.setImageResource(R.drawable.ic_create_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab() {
        layoutFileupload.setVisibility(View.VISIBLE);
        layoutNameupload.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fupload.setImageResource(R.drawable.ic_clear_black_24dp);
        fabExpanded = true;
    }
}
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
//        return view;

