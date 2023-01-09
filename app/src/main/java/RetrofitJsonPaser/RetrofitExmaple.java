package RetrofitJsonPaser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ExaData.tutorial.Training.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitExmaple extends AppCompatActivity {

    TextView textView;

    // url to fetch the data form
    String dataurl = "https://jsonplaceholder.typicode.com/";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_exmaple);
        textView=(TextView) findViewById(R.id.text_json_retrofit);
        textView.setText("");

// step 1.       create a retrofit object

        Retrofit retrofit=new Retrofit.Builder().baseUrl(dataurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        step 2. convert json data to model class object
//        convert json data to API type
        RetrofitApi retrofitApi=retrofit.create(RetrofitApi.class); // pass json data

//        step3 create a call of model class
        Call<List<RetrofitModel>> call = retrofitApi.getModels(); // pass json data

//        processing the data and receive the response
        call.enqueue(new Callback<List<RetrofitModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitModel>> call, Response<List<RetrofitModel>> response) {
                List<RetrofitModel>data=response.body();

                for(int i= 0; i<data.size();i++){
                    textView.append("Number #" + data.get(i).getId() + " " +
                            " \nTitle: " + data.get(i).getTitle() + "\n\n\n ");
                }
            }

            @Override
            public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {

            }
        });


    }
}