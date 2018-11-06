package r.akshay.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class list extends AppCompatActivity {
    private TextView tn, tdsc;
    private ImageView imga;
    private Button button;
    private EditText ediname;
    private String namein;

    DatabaseReference databaselist;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Intent intent=getIntent();
        String name=intent.getStringExtra("TB name");
        final String desc = intent.getStringExtra("TB desc");

        databaselist = FirebaseDatabase.getInstance().getReference("Users");
//        final String id = databaselist.push().getKey();



        tn=findViewById(R.id.textView6);
        tn.setText(name);



        tdsc = findViewById(R.id.textView8);
        tdsc.setText(desc);

        button = findViewById(R.id.Button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Please" + namein, Toast.LENGTH_SHORT).show();
                ediname = findViewById(R.id.Getname);
                namein = ediname.getText().toString().trim();

                if(!TextUtils.isEmpty(namein)){
                    databaselist.setValue(namein);
//                    databaselist.setValue(desc);

                }
                else{
                    Toast.makeText(getBaseContext(), "Please enter the name of the book", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
