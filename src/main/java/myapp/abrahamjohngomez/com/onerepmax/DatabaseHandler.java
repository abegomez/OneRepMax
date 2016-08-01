package myapp.abrahamjohngomez.com.onerepmax;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ryuhyoko on 6/27/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "MaxDatabase";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE maxes " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "activityname TEXT," +
                "maxes DOUBLE)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS maxes";
        db.execSQL(sql);

        onCreate(db);
    }
}
