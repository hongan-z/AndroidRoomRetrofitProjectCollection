package SQLiteDBNote;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ExaData.tutorial.Training.MainActivity;

public class DatabaseDao extends MainActivity {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    public DatabaseDao(Context context) {
        databaseHelper = new DatabaseHelper(context);
        // open db that will be used for reading and writing
        // method onCreate() and onUpdate() in DatabaseHelper class will be call
        db = databaseHelper.getWritableDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Dao class will have all IRUD operation method
    public boolean insertData(String name, String price, byte[] photo ,String category)
    {
        ContentValues contentValues = new ContentValues();
        // key-values pair , key is column name on table
        //                 key <--> value
        contentValues.put("Name",name);
        contentValues.put("Price",price);
        contentValues.put("Photo",photo);
        contentValues.put("Category",category);

        long result =
                db.insert("Factory",null,contentValues);
        if(result == -1){
            return false;
        }
        return true;

    }

    // for Adapter to fill  gridview
    public List getData(){

        db = databaseHelper.getReadableDatabase();
        List detailsList = new ArrayList();
        String [] tableColumns = new String[]{"UniqueID", "Name", "Price","Photo","Category"};
        // cursor class has an API that allow app to read the columns that were return
        // from the query as well as iterate over the rows of the result set
        // select * from table_name -> result set after run button
        Cursor cursor = db.query("Factory", tableColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            InventoryModel detail= new InventoryModel();
            detail.setPname(cursor.getString(1));
            detail.setPprice(cursor.getString(2));
            detail.setPhoto(cursor.getBlob(3));  // byte long object is BLOB for image ....
            detail.setPcategory(cursor.getString(4));

            detailsList.add(detail);
            cursor.moveToNext();
        }
        return detailsList;

    }

    public Integer deleteData(String name){
        int result = db.delete("Factory","Name= ?", new String[]{name});
        return  result;
    }

    public boolean deleteAll(){
        // delete all data
        int result = db.delete("Factory", null, null);
        if(result == -1){
            return false;
        }
        return true;
    }




}
