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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.MenuItem;

public class RateFragment2 extends Fragment {
    private static final String TAG = "RateTab1Fragment";
    private Button btnTEST;
    private String selected;
    //    private Spinner myspinner;

    public static String name;
    public static void putArgs2(Bundle args){
        name = args.getString("yay");
        Log.d(TAG, "Recieving intent from RateResult"+ name);
    }
    FloatingActionButton fupload, fLocalUpload, fFabFileUpload;
    private FloatingActionButton upload;
    private LinearLayout layoutFileupload, layoutNameupload;
    private boolean fabExpanded = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rate_fragment2, container, false);
        btnTEST = (Button) view.findViewById(R.id.buttonf2);

        TextView searchHeading = (TextView) view.findViewById(R.id.textView2);
        String finalDisplayHead = searchHeading.getText().toString() + " " + name;
        searchHeading.setText(finalDisplayHead);

        Log.d(TAG, "Created Fragment 2: ");
        layoutFileupload = (LinearLayout) view.findViewById(R.id.layoutLocalupload);
        layoutNameupload = (LinearLayout) view.findViewById(R.id.layoutNameOnlyUplaod);
        fupload = (FloatingActionButton) view.findViewById(R.id.fabSetting);
        fLocalUpload = (FloatingActionButton) view.findViewById(R.id.fablocalupload);
        fFabFileUpload = (FloatingActionButton) view.findViewById(R.id.fabfileupload);
        fLocalUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BookNameUpload.class);
                Log.d(TAG, "RateFragment Intent : " + name);
                intent.putExtra("CourseID", name);
                startActivity(intent);
            }
        });

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
        if (id == R.id.action_upload) {
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

