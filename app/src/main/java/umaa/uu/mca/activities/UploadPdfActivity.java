package umaa.uu.mca.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import umaa.uu.mca.Helper.Constants;

public class UploadPdfActivity extends AppCompatActivity {
    Uri pdfUri;
    Button selectFile,upload,fetch;
    TextView notification;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();

        fetch=findViewById(R.id.btnDownloadFile);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadPdfActivity.this,DownloadPdfRecyclerActivity.class));
            }
        });
        selectFile=findViewById(R.id.btnSelectFile);
        upload=findViewById(R.id.btnUploadFile);
        notification=findViewById(R.id.txtViewFileNotification);

        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("3kbjxnwTnkMNQbpf1ES7VktC1b82")) {
                    if (ContextCompat.checkSelfPermission(UploadPdfActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        selectPdf();
                    } else {
                        ActivityCompat.requestPermissions(UploadPdfActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 9);
                    }
                }else{
                    Toast.makeText(UploadPdfActivity.this, FirebaseAuth.getInstance().getCurrentUser().getUid()+"==3kbjxnwTnkMNQbpf1ES7VktC1b82", Toast.LENGTH_SHORT).show();
                }
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("3kbjxnwTnkMNQbpf1ES7VktC1b82")) {
                    if (pdfUri != null) {
                        uploadFile(pdfUri);

                    } else {
                        Toast.makeText(UploadPdfActivity.this, "Select A File", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UploadPdfActivity.this, "Only for Admin user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void uploadFile(Uri pdfUri) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading File....");
        progressDialog.setProgress(0);
        progressDialog.show();
        final String fileName="UMAA Magazine "+System.currentTimeMillis()+".pdf";
        final String fileName1="UMAA Magazine "+System.currentTimeMillis()+"";
        final StorageReference storageReference=storage.getReference();
        storageReference.child("Uploads").child(fileName).putFile(pdfUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String url=taskSnapshot.getUploadSessionUri().getPath();
                DatabaseReference databaseReference=database.getReference(Constants.DATABASE_PATH_UPLOADS);

                databaseReference.child(fileName1).setValue(taskSnapshot.getMetadata()
                        .getReference().getDownloadUrl().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UploadPdfActivity.this,"File Sucessfully Uploaded",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(UploadPdfActivity.this,"File not Uploaded",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UploadPdfActivity.this,"File not Uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                int countProgress= (int) (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                progressDialog.setProgress(countProgress);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==9 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                selectPdf();
        }else{
            Toast.makeText(UploadPdfActivity.this,"Please Provide Permission",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectPdf() {

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,86);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==86 && resultCode==RESULT_OK && data!=null){
            pdfUri=data.getData();
            notification.setText("A file is selected "+data.getData().getLastPathSegment());
        }else{
            Toast.makeText(UploadPdfActivity.this,"Please Select a File",Toast.LENGTH_SHORT).show();
        }
    }
}
