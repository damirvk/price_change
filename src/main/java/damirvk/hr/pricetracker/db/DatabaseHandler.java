package damirvk.hr.pricetracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SomeUser on 27.12.2016..
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "mobile_de";

    // Contacts table name
    private static final String TABLE_ENTRIES = "entries";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_URL = "url";
    private static final String KEY_START_PRICE = "start_price";
    private static final String KEY_CURRENT_PRICE = "current_price";
    private static final String KEY_SHORT_TITLE = "short_title";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_URL + " TEXT,"
                + KEY_START_PRICE + " TEXT," + KEY_CURRENT_PRICE + " TEXT," + KEY_SHORT_TITLE + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);

        // Create tables again
        onCreate(db);
    }

    public void addCarEntry(CarEntry carEntry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_URL, carEntry.getUrl());
        values.put(KEY_START_PRICE, carEntry.getStart_price());
        values.put(KEY_CURRENT_PRICE, carEntry.getCurrentPrice());
        values.put(KEY_SHORT_TITLE, carEntry.getShortTitle());

        // Inserting Row
        db.insert(TABLE_ENTRIES, null, values);
        db.close(); // Closing database connection
    }

    public List<CarEntry> getAllEntries() {
        List<CarEntry> etryList = new ArrayList<CarEntry>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ENTRIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CarEntry entry = new CarEntry();
                entry.setIid(Integer.parseInt(cursor.getString(0)));
                entry.setUrl(cursor.getString(1));
                entry.setStart_price(cursor.getString(2));
                entry.setCurrentPrice(cursor.getString(3));
                entry.setShortTitle(cursor.getString(4));
                // Adding contact to list
                etryList.add(entry);
            } while (cursor.moveToNext());
        }

        // return contact list
        return etryList;
    }

    public int updateCarEntry(CarEntry carEntry) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_URL, carEntry.getUrl());
        values.put(KEY_START_PRICE, carEntry.getStart_price());
        values.put(KEY_CURRENT_PRICE, carEntry.getCurrentPrice());
        values.put(KEY_SHORT_TITLE, carEntry.getShortTitle());
        // updating row
        return db.update(TABLE_ENTRIES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(carEntry.getIid()) });
    }
    public void deleteCarEntry(CarEntry carEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ENTRIES, KEY_ID + " = ?",
                new String[]{String.valueOf(carEntry.getIid())});
        db.close();
    }

}