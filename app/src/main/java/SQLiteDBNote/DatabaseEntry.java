package SQLiteDBNote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

import ExaData.tutorial.Training.R;

public class DatabaseEntry extends AppCompatActivity {

    TextView tName;
    TextView tPrice;
    TextView tCategory;

    EditText eName;
    EditText ePrice;
    EditText eCategory;

    Button addDetails;
    Button delDetails;
    Button deleAll;
    Button upLoadImage;

    ImageView imageViewDetails;
    String picturePath;
    Bitmap bmp;
    byte[] imageConverted =null;

    DatabaseDao databaseDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_entry);

        tName = (TextView) findViewById(R.id.textViewName);
        tPrice = (TextView) findViewById(R.id.textViewPrice);
        tCategory = (TextView) findViewById(R.id.textcategory);

        eName =(EditText) findViewById(R.id.editTextName);
        ePrice =(EditText) findViewById(R.id.editTextPrice);
        eCategory =(EditText) findViewById(R.id.editTextCategory);

        addDetails =(Button) findViewById(R.id.subbutton);
        delDetails =(Button)findViewById(R.id.delebutton);
        deleAll = (Button) findViewById(R.id.deleAllbutton);
        upLoadImage =(Button) findViewById(R.id.imageUploadbutton);

        imageViewDetails=(ImageView) findViewById(R.id.imageView);

        databaseDao = new DatabaseDao(this);

        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = databaseDao.insertData(eName.getText().toString(),
                        ePrice.getText().toString(),
                        imageConverted,
                        eCategory.getText().toString());

                if(result)
                {
                    Toast.makeText(getApplicationContext(),"Added Database successfully",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DatabaseEntry.this,DatabaseExample.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(getApplicationContext(),"Failed! Added Database. ",Toast.LENGTH_LONG).show();

                }
            }
        });

        delDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deleteRows = databaseDao.deleteData(eName.getText().toString());
                if(deleteRows>0){
                    Toast.makeText(getApplicationContext(),"Delete name successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed to Delete name, Something Wrong",Toast.LENGTH_LONG).show();

                }
            }
        });

        deleAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean deleAllofculume = databaseDao.deleteAll();
                if(deleAllofculume == true){
                    Toast.makeText(getApplicationContext(),"Delete all of table data successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Failed to Delete all of data, check it! ",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

// store image into database
    public void addImageGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode == RESULT_OK)
        {
            Uri selectedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePath,null,null,null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            picturePath = c.getString(columnIndex);
            c.close();

            bmp = BitmapFactory.decodeFile(picturePath);


            imageViewDetails.setImageBitmap(bmp);

            // converting bitmap to byte[], image is in bitmap -->table in db is BLOB byte[] so we need convert it
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG,40,stream);
            imageConverted = stream.toByteArray();

        }

    }


}