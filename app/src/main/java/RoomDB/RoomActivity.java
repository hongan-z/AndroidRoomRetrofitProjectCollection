package RoomDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import ExaData.tutorial.Training.R;

public class RoomActivity extends AppCompatActivity implements RoomAdapterListener {


    private RoomDatabaseUsers roomDatabaseUserObject;
    private RoomDao roomDao;
    RoomAdapter roomAdapter;

    EditText name, password;
    Button submit;
    RecyclerView recyclerView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        name = (EditText) findViewById(R.id.eName);
        password = (EditText) findViewById(R.id.ePassword);
        submit = (Button) findViewById(R.id.submitbtn);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewRoom);

        roomDatabaseUserObject = RoomDatabaseUsers.getInstance(this);
        roomDao = roomDatabaseUserObject.getDao();

        roomAdapter = new RoomAdapter(this,this);
        recyclerView.setAdapter(roomAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchall();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Insert logic
                    Users users = new Users(0, name.getText().toString(),
                            password.getText().toString());
                    roomDao.insert(users);
                    Toast.makeText(getApplicationContext(), "Data insert Successfully", Toast.LENGTH_LONG).show();
                    roomAdapter.addDetailInLists(users);
                } catch (Exception e) {
                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(),"Data does not insert, Failed!",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private void fetchall() {

        // getAllUsers() -> has query gettting entire table contents

        List<Users> wholedata = roomDao.getAllUsers(); // pass -> adapter class ->variable is userdataList
        for (int i = 0; i < wholedata.size(); i++) {
            Users users = wholedata.get(i);
            roomAdapter.addDetailInLists(users);
        }
    }


    @Override
    public void OnUpdate(int id, int position) {

        List<Users> getAlldata = roomDao.getAllUsers();
        Users users = getAlldata.get(position);
        users.setUname(name.getText().toString());
        users.setUpassword(password.getText().toString());
        roomDao.update(users);
        roomAdapter.updateFromList(position, users);

    }

    @Override
    public void OnDelete(int id, int position) {
        roomDao.delete(id);
        roomAdapter.removeFromList(position);
    }
}