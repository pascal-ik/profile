package pamtech.com.navigationdrawerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //button declarations
    Button b_button, m_button, a_button, s_button, t_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //button binding
        b_button = findViewById(R.id.browser_button);
        m_button = findViewById(R.id.music_button);
        a_button = findViewById(R.id.account_button);
        s_button = findViewById(R.id.settings_button);



        b_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBrowser();
            }
        });

        m_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMusic();
            }
        });

        a_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAccount();
            }
        });

        s_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSettings();
            }
        });

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_toast){
            Toast.makeText(this, "Slow and Steady", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_web) {
            Intent i = new Intent(this, BrowserActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_music) {
            Intent i = new Intent(this, MusicActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_account) {
            Intent i = new Intent(this, AccountActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if(id == R.id.nav_settings){
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void goToBrowser(){
        Intent browser = new Intent(this, BrowserActivity.class);
        startActivity(browser);
    }

    public void goToMusic(){
        Intent music = new Intent(this, MusicActivity.class);
        startActivity(music);
    }

    public void goToAccount(){
        Intent account = new Intent(this, AccountActivity.class);
        startActivity(account);
    }

    public void goToSettings(){
        Intent settings = new Intent(this, SettingsActivity.class);
        startActivity(settings);
    }


}