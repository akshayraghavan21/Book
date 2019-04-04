package r.akshay.book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

@SuppressLint("Registered")
public class Login extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "Login";

    public FirebaseAuth mAuth;
    ProgressBar progressBar;
    public EditText EmailText,PasswordText;
    public Button loginButton;
    public TextView toSignUp;
    public String name, email, contactNo, regNo, password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Log.d(TAG, "onCreate: Login page.");

        progressBar = (ProgressBar) findViewById(R.id.prog);

        mAuth = FirebaseAuth.getInstance();
        EmailText = findViewById(R.id.loginEmail);
        PasswordText = findViewById(R.id.loginPWD);
        loginButton = findViewById(R.id.loginEnter);
        toSignUp = findViewById(R.id.textViewSignup);


        findViewById(R.id.textViewSignup).setOnClickListener(this);
        findViewById(R.id.loginEnter).setOnClickListener(this);
    }
    private void userLogin(){
        email = EmailText.getText().toString().trim();
        password = PasswordText.getText().toString().trim();


        if (email.isEmpty()) {
            Toast.makeText(this,"Email not Entered",Toast.LENGTH_LONG).show();
            EmailText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this,"Incorrect Email ID",Toast.LENGTH_LONG).show();
            EmailText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this,"Password blank",Toast.LENGTH_LONG).show();
            PasswordText.requestFocus();
            return;
        }

        if (password.length() < 3) {
            Toast.makeText(this,"Password short",Toast.LENGTH_LONG).show();
            PasswordText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
}
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.textViewSignup:
                finish();
                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.loginEnter:
                userLogin();
                break;
        }
    }
}
