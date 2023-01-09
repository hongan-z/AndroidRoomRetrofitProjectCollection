package JsonParsingEx;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.GridView;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import ExaData.tutorial.Training.R;

// This class for that to convert json file in text format into javascript object
// AsyncTask
public class JsonParser extends AsyncTask<String, String, Void> {

    //   create arraylist to store details that i am fetching
    public List<FoodItems> fooddetails = new ArrayList<>();
    //    json array containing entire json data
    JSONArray jsonArray;
    //   create a string variable to store the final data converted
    String result = "";
//    declear context and activity so we know finally we fetch to which UI and Activity

    Activity activity;
    Context context;
    JsonParsingAdapter jsonParsingAdapter;
    //    reading stream of data line by line from url when url are opened
//    and after reading store jsonArrsy
    BufferedInputStream bufferedInputStream;

    //    showing the the progress before dynamic data is fetched and update the UI
    public ProgressDialog progressDialog;

    //    constructor
    public JsonParser(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        this.progressDialog = new ProgressDialog(this.context);
    }


    // handle that display what is show on the screen such as progressDialog before doBackground
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // if any progress bars on screen- dismiss them
        progressDialog.dismiss();
        progressDialog.setMessage("Wait.....Loading");
        progressDialog.show();
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                JsonParser.this.cancel(true);
            }
        });


    }

    @Override
    protected Void doInBackground(String... strings) {

        HttpURLConnection httpURLConnection = null;
        try {

//            pass the url inside this instance(object)
            URL url = new URL(DataUrl.fetchData);
//            open webpage
            httpURLConnection = (HttpURLConnection) url.openConnection();
//          BufferedInputStream  reading stream  read raw bytes VS. BufferedReader reads characters(Text)
            bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());

            result = readStream(bufferedInputStream);
//callback([.........) , remove "(" and ")"
            result = result.substring
                    (result.indexOf("(") + 1, result.lastIndexOf(")"));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // readStream() ->logic of method of readSream() is that reading the json file from the url
//    in form of bytes,line, appending lines
    private String readStream(BufferedInputStream bufferedInputStream) throws IOException {

//         bufferedInput doInBackground -> stream of bytes
//        pass bufferedInput into BufferedReader class -> pass this stream of bytes
//        made a string builder to append each line the data
//        a string variable named line which wil lbe append to stringBuilder
//        BufferedReader reads a couple of characters from input Steam and
//        InputStreamReader read one character from input Steam


//     BufferedReader reads characters(Text) VS. BufferedInputStream  reading stream  read raw bytes
//        bytes -> one character -> couple of characters (texts) -> line ->
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }


    //  when we get javascript object, onPostExecute() will handle that display json object to UI such as Gridview or Listview after doBackground() is done
    @Override
    protected void onPostExecute(Void aVoid) {
        try {

            jsonArray = new JSONArray(result);
            if (jsonArray != null) {
                for (int index = 0; index < jsonArray.length(); index++) {

//                    from json data parsing , put into model which is FoodItems class
                    //FoodItems foodItems = new FoodItems();
                    FoodItems foodItems = new FoodItems();
                    //ProductID , ProductName........ should same as name as in json file
                    foodItems.id = jsonArray.getJSONObject(index).getInt("ProductID");
                    foodItems.name = jsonArray.getJSONObject(index).getString("ProductName");
                    foodItems.price = jsonArray.getJSONObject(index).getString("UnitPrice");

                    // get image url, appending 1.jpg, 2.jpg, ......10.jpg,11.jpg.....
                    foodItems.imageurl = DataUrl.imageData + foodItems.id + ".jpg";

                    // pass into array, which is array<fooddetial>,    finally!!!
                    fooddetails.add(foodItems);
                }
            }

            GridView gridView;
            gridView = (GridView) this.activity.findViewById(R.id.listviewfetchfoodDetail); // id from jsonParsingExmaple ->Gridviw
            jsonParsingAdapter = new JsonParsingAdapter(this.context, fooddetails);
            gridView.setAdapter(jsonParsingAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

        this.progressDialog.dismiss();
        // super.onPostExecute(unused);

    }
}
