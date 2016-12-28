package damirvk.hr.pricetracker.parsing;

import android.os.AsyncTask;

import damirvk.hr.pricetracker.db.CarEntry;

/**
 * Created by SomeUser on 28.12.2016..
 */
public class URLFinderAsync extends AsyncTask<String, Void, CarEntry> {
    @Override
    protected CarEntry doInBackground(String... params) {
        return new URLFinder().getDataFromUrl(params[0]);
    }
    @Override
    protected  void onPostExecute(CarEntry result) {
        super.onPostExecute(result);
    }
}
