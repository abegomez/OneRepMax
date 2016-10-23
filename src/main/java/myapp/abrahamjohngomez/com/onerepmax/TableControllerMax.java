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

        values.put("activityname", maxObject.getExerciseName());
        values.put("maxes", maxObject.getMax());

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("maxes", null, values)> 0;
        db.close();

        return createSuccessful;
    }
    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM maxes";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
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
                maxObject.setId(id);
                maxObject.setExerciseName(exerciseName);
                maxObject.setMax(max);

                recordsList.add(maxObject);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public MaxObject readSingleRecord(int maxId) {
        MaxObject maxObject = null;

        String sql = "SELECT * FROM maxes WHERE id = " + maxId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String exercisename = cursor.getString(cursor.getColumnIndex("activityname"));
            double max = Double.parseDouble(cursor.getString(cursor.getColumnIndex("maxes")));

            maxObject = new MaxObject();
            maxObject.setId(id);
            maxObject.setExerciseName(exercisename);
            maxObject.setMax(max);
        }

        cursor.close();
        db.close();

        return maxObject;
    }
    public boolean update (MaxObject maxObject) {
        ContentValues values = new ContentValues();

        values.put("activityname", maxObject.getExerciseName());
        values.put("maxes", maxObject.getMax());

        String where = "id = ?";
        String[] whereArgs = { String.valueOf(maxObject.getId()) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("maxes", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }

    public boolean delete(int id) {

        String where = "id = '" + id + "'";
        SQLiteDatabase db = this.getWritableDatabase();

        boolean deleteSuccessful = db.delete("maxes", where, null) > 0;
        db.close();

        return deleteSuccessful;
    }

}
