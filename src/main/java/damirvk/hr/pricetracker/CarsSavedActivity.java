package damirvk.hr.pricetracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import damirvk.hr.pricetracker.adapter.CarsAdapter;
import damirvk.hr.pricetracker.db.CarEntry;
import damirvk.hr.pricetracker.db.DatabaseHandler;

public class CarsSavedActivity extends AppCompatActivity {

    private ListView mListView;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_saved);

        mListView = (ListView) findViewById(R.id.cars_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        List<CarEntry> allEntries = db.getAllEntries();

        CarsAdapter adapter = new CarsAdapter(this, allEntries);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1
                CarEntry carEntry = allEntries.get(position);

                // 2
                Intent detailIntent = new Intent(context, CarWebViewActivity.class);

                // 3
                detailIntent.putExtra("url", carEntry.getUrl());
                // 4
                startActivity(detailIntent);
            }
        });
    }
}
