package RoomDB;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



// if we have multiple table(entities) we can adding
// @Database(entities = {Users.class, Producer.class, Employee.class},version = 1)
@Database(entities = {Users.class},version = 1)
public abstract class RoomDatabaseUsers extends RoomDatabase
{

    // abstract method no argument return the class annotation Dao
    // this method will allow us to  access Dao
    public abstract RoomDao getDao();

    // at running we can require instance of database by calling
    // Room.databaseBuilder()
    public static RoomDatabaseUsers INSTANCE;
    public static RoomDatabaseUsers getInstance(Context context){
        // if not instance be created, we create it
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    RoomDatabaseUsers.class, "UserSampleData")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();


        }

        return INSTANCE;
    }


}
