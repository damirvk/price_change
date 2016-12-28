package damirvk.hr.pricetracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import damirvk.hr.pricetracker.adapter.CarsAdapter;
import damirvk.hr.pricetracker.db.CarEntry;
import damirvk.hr.pricetracker.db.DatabaseHandler;

public class CarsSavedActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars_saved);

        mListView = (ListView) findViewById(R.id.cars_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        List<CarEntry> allEntries = db.getAllEntries();

        CarsAdapter adapter = new CarsAdapter(this, allEntries);
        mListView.setAdapter(adapter);


    }
}
