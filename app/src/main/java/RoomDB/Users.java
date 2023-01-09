package RoomDB;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {  // table name is same as class name for Room library

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String uname,upassword;

    public Users(int id, String uname, String upassword) {

        this.id = id;
        this.uname = uname;
        this.upassword = upassword;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

}
