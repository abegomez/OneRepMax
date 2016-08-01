package myapp.abrahamjohngomez.com.onerepmax;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryuhyoko on 6/27/2016.
 */
public class TableControllerMax extends DatabaseHandler {

    public TableControllerMax(Context context) {

        super(context);
    }
    public boolean create(MaxObject maxObject) {

        ContentValues values = new ContentValues();

        values.put("activityname", maxObject.exerciseName);
        values.put("maxes", maxObject.max);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("maxes", null, values)> 0;
        db.close();

        return createSuccessful;
    }

    public List<MaxObject> read() {
        List<MaxObject> recordsList = new ArrayList<MaxObject>();

        String sql = "SELECT * FROM maxes ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String exerciseName = cursor.getString(cursor.getColumnIndex("activityname"));
                Double max = Double.parseDouble(cursor.getString(cursor.getColumnIndex("maxes")));

                MaxObject maxObject = new MaxObject();
                maxObject.id = id;
                maxObject.exerciseName = exerciseName;
                maxObject.max = max;

                recordsList.add(maxObject);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }
}
