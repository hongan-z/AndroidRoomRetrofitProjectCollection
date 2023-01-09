package RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao // annotation
public interface RoomDao {


    @Insert
    void insert(Users users);

    @Update
    void update(Users users);
                              // column_name id = :id parameter pass to method
    @Query("DELETE FROM Users WHERE id=:id")
    void delete(int id);

    @Query("select * from Users")
    List<Users> getAllUsers();


    @Delete
    void deletall(Users users);

}
