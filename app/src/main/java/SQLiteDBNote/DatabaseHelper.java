package SQLiteDBNote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // create  database
    private static final String TABLE_NAME = "Factory";
    private static final String COL1 = "UniqueID";
    private static final String COL2 = "Name";
    private static final String COL3 = "Price";
    private static final String COL4 = "Photo";
    private static final String COL5 = "Category";




    // constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    // create table to store in db
    /*

     CREATE TABLE table_name(
         column1  datatype,
         column2  datatype,
         column3  datatype)

     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL2 + " TEXT NOT NULL,"
                + COL3 + " TEXT NOT NULL,"
                + COL4 + " BLOB,"  // BLOB is data type which is Binary Large Object  for store any binary data image video...
                + COL5 + " TEXT NOT NULL)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



}
