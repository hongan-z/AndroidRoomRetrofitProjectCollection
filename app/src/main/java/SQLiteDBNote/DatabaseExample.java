package SQLiteDBNote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.GridView;

import ExaData.tutorial.Training.R;

public class DatabaseExample extends AppCompatActivity {
    GridView gridView;
    DatabaseDao databaseDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_example);

        gridView = (GridView)findViewById(R.id.databaseexampleGridvew);
        databaseDao = new DatabaseDao(this);

        viewData();
//        final DatabaseAdapter adapter =
//                new DatabaseAdapter(DatabaseExample.this,databaseDao.getData());
//
//        gridView.setAdapter(adapter);

    }

    public void viewData() {
        final DatabaseAdapter adapter =
                new DatabaseAdapter(DatabaseExample.this, databaseDao.getData());
        gridView.setAdapter(adapter);
    }


}