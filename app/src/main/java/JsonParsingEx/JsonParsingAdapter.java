package JsonParsingEx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.List;

import ExaData.tutorial.Training.R;

public class JsonParsingAdapter extends ArrayAdapter<FoodItems> {

//      we pass the fooddetial list to adapter constructor, made view items reflected
//      for UI part
//      to set image to  view from Jsonparser class

    List<FoodItems> foodlist; // pass to constructor which called from JsonParser class

    private Context context;

    TextView name, price;
    ImageView imageView;

    public JsonParsingAdapter
            (@NonNull Context context, List<FoodItems> foodlist) {
        super(context, R.layout.json_custom_adapter, foodlist);
        this.foodlist = foodlist;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);


        if(convertView == null){
            convertView = inflater.inflate(R.layout.json_custom_adapter,null);
        }
        name = (TextView) convertView.findViewById(R.id.gridViewName);
        price=(TextView) convertView.findViewById(R.id.gridViewPirce);
        imageView=(ImageView) convertView.findViewById(R.id.gridimageViewJsonParser);

        FoodItems foodItems = foodlist.get(position);
        name.setText(foodItems.getName());
        price.setText(foodItems.getPrice());


//        Grid -> add dependency on gradle file, for image view
        try {

            URL glideimageurl = new URL(foodItems.getImageurl());
            Glide.with(context).load(glideimageurl).into(imageView);

//           when image has been clicked, pass data from
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(),CartPage.class);
                    intent.putExtra("foodname",foodlist.get(position).getName());
                    intent.putExtra("foodprice",foodlist.get(position).getPrice());
                    intent.putExtra("foodimage",foodlist.get(position).getImageurl());
                    context.startActivity(intent);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView ;


    }




}
