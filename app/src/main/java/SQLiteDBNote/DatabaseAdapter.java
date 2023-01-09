package SQLiteDBNote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ExaData.tutorial.Training.R;

public class DatabaseAdapter extends ArrayAdapter<InventoryModel> {

    List<InventoryModel> inventoryModelList;
    private Context context;
    public byte[] byteImage;
    ImageView photo;

    public DatabaseAdapter(@NonNull Context context, List<InventoryModel> inventoryModelList) {
        super(context, R.layout.database_adapter,inventoryModelList);
        this.inventoryModelList = inventoryModelList;
        this.context = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView = inflater.inflate(R.layout.database_adapter,null);

        }
        TextView names =(TextView)convertView.findViewById(R.id.name_adapter);
        TextView price =(TextView) convertView.findViewById(R.id.price_adapter);
        photo =(ImageView) convertView.findViewById(R.id.imageAdpter);
        TextView category = (TextView) convertView.findViewById(R.id.category_adapter);

        names.setText(inventoryModelList.get(position).getPname());
        price.setText(inventoryModelList.get(position).getPprice());
        byteImage =  inventoryModelList.get(position).getPhoto();
        category.setText(inventoryModelList.get(position).getPcategory());


        if(byteImage !=null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage,0, byteImage.length);
            photo.setImageBitmap(bitmap);
        }


        return  convertView;
    }

}