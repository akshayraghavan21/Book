package r.akshay.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RateResult extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter rSectionsPageAdapter;
    private ViewPager rViewPager;
    private String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rate_result);

        Intent intent = getIntent();
        String selected = intent.getStringExtra("chosen");

        Log.d(TAG, "onCreate: Receiving intent."+ selected);

        Bundle args = new Bundle();
        args.putString("yay", selected);
        RateFragment1.putArgs(args);
        RateFragment2.putArgs2(args);

        rSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        rViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(rViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(rViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RateFragment1(), "Search");
        adapter.addFragment(new RateFragment2(), "Upload");
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_logoutonly, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logoutonly:
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
