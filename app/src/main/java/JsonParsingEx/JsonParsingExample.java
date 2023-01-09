package JsonParsingEx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ExaData.tutorial.Training.R;

public class JsonParsingExample extends AppCompatActivity {

    JsonParser jsonParser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_json_parsing_example);

        try {

            jsonParser = (JsonParser) new JsonParser(this, this).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}