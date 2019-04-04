package r.akshay.book;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("Registered")
public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Login";
    private Button SignUpBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    public EditText nameE,regNoE,EmailIDE,ContactNoE,PWDE;
    private String name, email, contactNo, regNo, password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Log.d(TAG, "onCreate: Login page.");

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progsup);

        nameE = findViewById(R.id.NameUser);
        regNoE = findViewById(R.id.RegNo);
        EmailIDE = findViewById(R.id.loginEmail);
        ContactNoE = findViewById(R.id.ContactNo);
        PWDE = findViewById(R.id.loginPWD);

//        findViewById(R.id.login_btn).setOnClickListener((View.OnClickListener) this);
        SignUpBtn = findViewById(R.id.signupButton);
        SignUpBtn.setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getBaseContext(),name,Toast.LENGTH_LONG).show();
//            }
//        });
//        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getBaseContext(),Login.class);
//                startActivity(intent);
//            }
//        });

    }
//    @Override
//    protected void onStart() {
//
//        super.onStart();
//        if(mAuth.getCurrentUser()!=null){
//            Intent intent=new Intent(this,MainActivity.class);
//            startActivity(intent);
//        }
//    }

    private void userValidate(){

        name = nameE.getText().toString();
        email = EmailIDE.getText().toString();
        contactNo = ContactNoE.getText().toString();
        regNo = regNoE.getText().toString();
        password = PWDE.getText().toString();
        //Insert all the constraint check methods//
        Pattern pattern = Pattern.compile("[1][0-9][A-Z]{3}[0-9]{4}");
//        Pattern pattern = Pattern.compile("");
        Pattern pattern1 = Pattern.compile("[1-9][0-9]{9}");
        Matcher matcher = pattern.matcher(regNo);
        Matcher matcher1 = pattern1.matcher(contactNo);

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Email Incorrect",Toast.LENGTH_LONG).show();
            EmailIDE.requestFocus();
            return;
        }
        if(!matcher.matches()){
            Toast.makeText(this,"Reg No Incorrect",Toast.LENGTH_LONG).show();
            regNoE.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Name Blank",Toast.LENGTH_LONG).show();
            nameE.requestFocus();
            return;
        }
        if(!matcher1.matches()){
            Toast.makeText(this,"Contact Incorrect",Toast.LENGTH_LONG).show();
            ContactNoE.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password Blank",Toast.LENGTH_LONG).show();
            nameE.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(SignUp.this, MainActivity.class));
                    } else {

                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, Login.class));
                break;

            case R.id.signupButton:
                userValidate();
                break;

        }
    }
}

