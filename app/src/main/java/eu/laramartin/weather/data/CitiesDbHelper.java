package eu.laramartin.weather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lara on 06/11/2016.
 */

public class CitiesDbHelper extends SQLiteOpenHelper {

    public final static String DB_NAME = "weather_database.db";
    public final static int DB_VERSION = 1;
    public final static String LOG_TAG = CitiesDbHelper.class.getCanonicalName();

    public CitiesDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CitiesContract.CitiesEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // TODO define onUpgrade method of DbHelper
    }

    public void insertCity() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CitiesContract.CitiesEntry.COLUMN_NAME, "London");
        values.put(CitiesContract.CitiesEntry.COLUMN_CURRENT_TEMP, 14);
        long id = db.insert(CitiesContract.CitiesEntry.TABLE_NAME, null, values);
        Log.v(LOG_TAG, "ID row inserted: " + String.valueOf(id));
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                CitiesContract.CitiesEntry._ID,
                CitiesContract.CitiesEntry.COLUMN_NAME,
                CitiesContract.CitiesEntry.COLUMN_CURRENT_TEMP
        };
        Cursor cursor = db.query(
                CitiesContract.CitiesEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
}
