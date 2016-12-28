package damirvk.hr.pricetracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import damirvk.hr.pricetracker.CarsSavedActivity;
import damirvk.hr.pricetracker.R;
import damirvk.hr.pricetracker.db.CarEntry;

/**
 * Created by SomeUser on 28.12.2016..
 */
public class CarsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<CarEntry> mDataSource;

    public CarsAdapter(Context context, List<CarEntry> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_cars,parent, false);
        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.car_entry_title);

        TextView currentPrice =
                (TextView) rowView.findViewById(R.id.car_entry_price);

        CarEntry carEntry = (CarEntry) getItem(position);
        titleTextView.setText(carEntry.getShortTitle());
        currentPrice.setText(carEntry.getCurrentPrice());

        return rowView;
    }
}
