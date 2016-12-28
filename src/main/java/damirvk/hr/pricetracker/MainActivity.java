package damirvk.hr.pricetracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import damirvk.hr.pricetracker.db.CarEntry;
import damirvk.hr.pricetracker.db.DatabaseHandler;
import damirvk.hr.pricetracker.parsing.ChangedData;
import damirvk.hr.pricetracker.parsing.URLFinder;
import damirvk.hr.pricetracker.parsing.URLFinderAsync;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText etURLEnter;
    Button saveButton, checkChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etURLEnter = (EditText) findViewById(R.id.et_car_url);
        saveButton = (Button) findViewById(R.id.bt_save);
        checkChangesButton = (Button) findViewById(R.id.bt_check_changes);
        DatabaseHandler db = new DatabaseHandler(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUrl = etURLEnter.getText().toString();
                int idStart = newUrl.indexOf("id=") + 3;
                String carId = newUrl.substring(idStart, idStart + 9);
                Log.d("CarId", carId);
                CarEntry carEntry = null;
                try {
                    carEntry = new URLFinderAsync().execute(carId).get();
                    db.addCarEntry(carEntry);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Toast msg = Toast.makeText(getBaseContext(), carEntry.getCurrentPrice(), Toast.LENGTH_LONG);
                msg.show();
            }
        });

        checkChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CarEntry> allEntries = db.getAllEntries();
                ChangedData changedData = new ChangedData(allEntries);
                if (changedData.getPriceDropped().isEmpty()) {
                    Toast.makeText(getBaseContext(), "No price changes!", Toast.LENGTH_LONG).show();
                } else {
                    for (CarEntry entry : changedData.getPriceDropped()) {
                        Toast.makeText(getBaseContext(), "Price changed: " + entry.getUrl(), Toast.LENGTH_LONG).show();
                    }
                }
                if (!changedData.getSoldCars().isEmpty()) {
                    for (CarEntry ce : changedData.getSoldCars()) {
                        Toast.makeText(getBaseContext(), "Car sold: " + ce.getShortTitle(), Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        //initDb();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CarsSavedActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
