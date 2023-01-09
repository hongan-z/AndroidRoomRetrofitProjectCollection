package JsonParsingEx;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ExaData.tutorial.Training.R;

public class CartPage extends AppCompatActivity {

    ImageView imageView;
    TextView textName, textPrice;
    Button buttonfood;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_json_page);
        imageView = (ImageView) findViewById(R.id.imageViewCart);
        textName = (TextView) findViewById(R.id.textViewName);
        textPrice = (TextView) findViewById(R.id.textViewPrice);
        buttonfood = (Button) findViewById(R.id.buttonfood);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            String name = intent.getStringExtra("foodname");
            String price = intent.getStringExtra("foodprice");
            String image = intent.getStringExtra("foodimage");

            textName.setText(name);
            textPrice.setText(price);
            Glide.with(getApplicationContext()).load(image).into(imageView);

        }
        buttonfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}