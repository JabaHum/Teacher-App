package glowsomecomputingsolutions.com.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import glowsomecomputingsolutions.com.Fragments.PrimaryFragment;
import glowsomecomputingsolutions.com.Fragments.SecondaryFragment;
import glowsomecomputingsolutions.com.Model.Users;
import glowsomecomputingsolutions.com.R;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        //Users users = new Users();
        //recieveObject();
        //recieving object from login activity

        Intent intent = getIntent();
        Users users = intent.getParcelableExtra("Users");

        Toast.makeText(this, users.getSchooltype(), Toast.LENGTH_SHORT).show();

        if (users.getSchooltype()!= null && users.getSchooltype().equals("primary")){
            PrimaryFragment fragment_1 = new PrimaryFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frame,fragment_1);
            fragmentTransaction.commit();
        }else if (users.getSchooltype()!= null && users.getSchooltype().equals("secondary"))
        {
            SecondaryFragment fragment_2 = new SecondaryFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame,fragment_2);
            fragmentTransaction.commit();
        }





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

    private void recieveObject(){

    }

}
