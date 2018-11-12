package r.akshay.book;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;
import java.util.jar.Attributes;

public class BookNameUpload extends AppCompatActivity{

    private Uri filePath;
    private Task<Uri> downloadUrl;
    private final int PICK_IMAGE_REQUEST = 71;

    private final static String TAG = "Fragment1";

    private String name, nameUpload, descUpload;
    private String ratin;

    FirebaseStorage storage;
    StorageReference storageReference;
    private DatabaseReference mDatabase;

    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booknameupload);

        Intent intent = getIntent();
        name = intent.getStringExtra("CourseID");
        Log.d(TAG, "Getting the intent " + name);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        final EditText NameUpload = findViewById(R.id.NameUpload);
        final TextView text = findViewById(R.id.textView9);
        String abc = text.getText().toString()+ " " + name;
        text.setText(abc);
        final EditText ShortDescUpload = findViewById(R.id.ShortDescUpload);
        Button ImageUploadBtn = findViewById(R.id.ImageUploadBtn);
        ImageButton AllUpload = findViewById(R.id.AllDetailsupload);
        final EditText ratingBar = findViewById(R.id.ratingbar);


        ImageUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
                Log.d(TAG, "onClick: Image Clicked");
            }
        });
        AllUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameUpload = NameUpload.getText().toString().trim();
                descUpload = ShortDescUpload.getText().toString();
                ratin = ratingBar.getText().toString();
                Double rating1 = Double.parseDouble(ratin);
                uploadImage();
                uploadInfo(name, nameUpload, rating1 ,descUpload, downloadUrl);

            }
        });
    }
   
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
                filePath = data.getData();
        }
    }
    private void uploadInfo(String nam, String nameUplo, Double ratin, String descUplo, Task<Uri> download) {
        mDatabase = FirebaseDatabase.getInstance().getReference("/RateRecyclerview");
        Log.d(TAG, "uploadInfo: " + " " + nam +  nameUplo + " " + ratin + " " + descUplo);
        mDatabase.child(nameUpload).child("id").setValue(nam);
        mDatabase.child(nameUpload).child("title").setValue(nameUplo);
        mDatabase.child(nameUpload).child("shortdesc").setValue(descUplo);
        mDatabase.child(nameUpload).child("rating").setValue(ratin);

    }


    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final String a = nameUpload.toLowerCase();

            final StorageReference ref = storageReference.child("/"+ a);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(BookNameUpload.this, "Uploaded " + nameUpload, Toast.LENGTH_LONG).show();
                            mDatabase.child(nameUpload).child("image").setValue(ref.getDownloadUrl().toString());
                            finish();
//                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(BookNameUpload.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }
}
