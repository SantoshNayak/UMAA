package umaa.uu.mca.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar=findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        findViewById(R.id.textViewMenuContactUs).setOnClickListener(this);
        findViewById(R.id.textViewMenuMagazine).setOnClickListener(this);
        findViewById(R.id.textViewMenuActivities).setOnClickListener(this);
        findViewById(R.id.textViewMenuMyAccount).setOnClickListener(this);
        findViewById(R.id.textViewMenuAboutUs).setOnClickListener(this);
        findViewById(R.id.textViewMenuNotification).setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewMenuContactUs:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;
            case R.id.textViewMenuAboutUs:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.textViewMenuMagazine:
                if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("3kbjxnwTnkMNQbpf1ES7VktC1b82")) {
                    startActivity(new Intent(this, UploadPdfActivity.class));
                }else
                {
                    startActivity(new Intent(this,DownloadPdfRecyclerActivity.class));
                }
                break;
            case R.id.textViewMenuActivities:
                if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals("3kbjxnwTnkMNQbpf1ES7VktC1b82")) {
                    startActivity(new Intent(this, UploadEventsActivity.class));
                }else
                {
                    startActivity(new Intent(this,EventsActivity.class));
                }
                break;
            case R.id.textViewMenuMyAccount:
                startActivity(new Intent(this, MyAccount.class));
                break;
            case R.id.textViewMenuNotification:
                Toast.makeText(ProfileActivity.this,"Work in progress", Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
