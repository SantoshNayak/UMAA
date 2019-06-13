package umaa.uu.mca.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import umaa.uu.mca.model.User;


public class MyAccount extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseUser user;
    String uid;
    EditText name,yearOfPassout,mobile,eMail;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        name = findViewById(R.id.editTextName);
        yearOfPassout = findViewById(R.id.editTextYop);
        mobile = findViewById(R.id.editTextMobile);
        eMail = findViewById(R.id.editTextEmail);
        update = findViewById(R.id.buttonUpdate);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(name.getText().toString(), yearOfPassout.getText().toString(), mobile.getText().toString(), eMail.getText().toString());

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(uid)
                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MyAccount.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(MyAccount.this, ProfileActivity.class));
                        } else {
                            //display a failure message
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                name.setText(dataSnapshot.child(uid).child("name").getValue(String.class));
                mobile.setText(dataSnapshot.child(uid).child("mobile").getValue(String.class));
                eMail.setText(dataSnapshot.child(uid).child("email").getValue(String.class));
                yearOfPassout.setText(dataSnapshot.child(uid).child("yop").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}