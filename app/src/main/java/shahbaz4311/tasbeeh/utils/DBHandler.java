package shahbaz4311.tasbeeh.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasbeehcounter.db";
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


    public void insertRecord(Record record) {
        //Update the record if it already exists
        if (doesExist(record)) {
            updateRecord(record);
            return;
        }
        //if the record doesn't already exist insert it.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, record.getName());
        values.put(COLUMN_DATE, record.getDate());
        values.put(COLUMN_RECITED, record.getRecited());
        values.put(COLUMN_COUNT, record.getCount());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Record> getAllRecords() {
        List<Record> records = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
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

    public boolean doesExist(Record record) {
        String sql = "SELECT * FROM " + TABLE_NAME + " where " + COLUMN_DATE + " = " +
                record.getDate() + " and " + COLUMN_NAME + " = " + record.getName();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        boolean result = false;
        if (cursor.moveToFirst()) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;

    }

    public void updateRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_RECITED, record.getRecited());
        values.put(COLUMN_COUNT, record.getCount());

        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(record.getId())});
        db.close();
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
    public void delteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}