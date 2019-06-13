package umaa.uu.mca.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class EventsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String key="CLICKED_MENU";
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        findViewById(R.id.textViewAlumniMeet).setOnClickListener(this);
        findViewById(R.id.textViewAlumniMilan).setOnClickListener(this);
        findViewById(R.id.textViewTechSession).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewAlumniMeet:
                intent = new Intent(EventsActivity.this, ShowEventsActivity.class);
                intent.putExtra(key, "Alumni Meet");
                startActivity(intent);
                break;
            case R.id.textViewAlumniMilan:
                intent = new Intent(EventsActivity.this, ShowEventsActivity.class);
                intent.putExtra(key, "Alumni Milan");
                startActivity(intent);
                break;
            case R.id.textViewTechSession:
                intent = new Intent(EventsActivity.this, ShowEventsActivity.class);
                intent.putExtra(key, "Tech Sessions");
                startActivity(intent);
                break;
        }
    }
}
