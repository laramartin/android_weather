package eu.laramartin.weather.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    }


}
