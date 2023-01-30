package shahbaz4311.tasbeeh.utils;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasbeeh.db";
    private static final String TABLE_NAME = "tasbeeh";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_RECITED = "recited";
    private static final String COLUMN_COUNT = "count";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_RECITED + " INTEGER,"
                + COLUMN_COUNT + " INTEGER"
                + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }


    public int insertRecord(Record record) {
//        //Update the record if it already exists
        int recordId=doesExist(record);
        if (recordId!=-1) {
            record.setId(recordId);
            updateRecord(record);
            return recordId;
        }
        //if the record doesn't already exist insert it.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, record.getName());
        values.put(COLUMN_DATE, record.getDate());
        values.put(COLUMN_RECITED, record.getRecited());
        values.put(COLUMN_COUNT, record.getCount());

        int id= (int) db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(COLUMN_DATE));
                @SuppressLint("Range") boolean isRecited = cursor.getInt(cursor.getColumnIndex(COLUMN_RECITED)) > 0;
                @SuppressLint("Range") int count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT));
                records.add(new Record(id, name, isRecited, count, date));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return records;
    }

    @SuppressLint("Range")
    public int doesExist(Record record) {
        String sql = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_DATE + " = '" +
                record.getDate() + "' and " + COLUMN_NAME + " = '" + record.getName()+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        }
        cursor.close();
        db.close();
        return id;

    }

    public boolean updateRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECITED, record.getRecited());
        values.put(COLUMN_COUNT, record.getCount());

        int rowsAffected=db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(record.getId())});
        db.close();
        return rowsAffected>0;
    }

    /*
     * Deletes all the records belonging ot a specific date
     * */
    public void deleteRecord(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_DATE + " = ?", new String[]{date});
        db.close();
    }

    /*
     * Deletes all the record with specific Id
     * */
    public void deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @SuppressLint("SimpleDateFormat")
    public List<Record> getAllTasbeehNames() {
        List<Record> tasbeehNames = new ArrayList<>();
        String sql = "SELECT distinct "+COLUMN_NAME+" FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                tasbeehNames.add(new Record(name, false, 0, new SimpleDateFormat("MMMM dd, yyyy").format(new Date())));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasbeehNames;


    }
}