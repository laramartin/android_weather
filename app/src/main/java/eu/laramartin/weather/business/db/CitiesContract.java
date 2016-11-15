package eu.laramartin.weather.business.db;

import android.provider.BaseColumns;

/**
 * Created by Lara on 06/11/2016.
 */

public class CitiesContract {

    public CitiesContract() {
    }

    public static final class CitiesEntry implements BaseColumns {

        public static final String TABLE_NAME = "cities";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
//        public static final String COLUMN_CURRENT_TEMP = "current_temp";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                CitiesContract.CitiesEntry.TABLE_NAME + "(" +
                CitiesContract.CitiesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CitiesContract.CitiesEntry.COLUMN_NAME + " TEXT NOT NULL" +
//                CitiesContract.CitiesEntry.COLUMN_CURRENT_TEMP + " INTEGER NOT NULL" +
                ");";
    }
}
