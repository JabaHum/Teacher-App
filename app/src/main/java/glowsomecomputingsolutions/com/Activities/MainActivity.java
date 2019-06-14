package glowsomecomputingsolutions.com.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import glowsomecomputingsolutions.com.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.miProfile:
                showProfile();
                return true;
            case R.id.miAbout:
                showAbout();
                return true;
            case R.id.miExit:
                exitApp();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void showProfile(){
        Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(profile);
    }
    private void showAbout(){
        Intent about = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(about);
    }
    private void exitApp(){
        finish();
    }
}
