package RoomDB;

import android.annotation.SuppressLint;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ExaData.tutorial.Training.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    // declare variable
    private Context context;
    private List<Users> usersdataList;
    private RoomAdapterListener roomAdapterListener;
    // constructor

    public RoomAdapter(Context context, RoomAdapterListener listener) {

        this.context = context;
        usersdataList = new ArrayList<Users>();
        roomAdapterListener = listener;
    }

    // Layout insert using this method
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userrow_cardview_insider_recycler_adapter, parent, false);


        return new ViewHolder(view);
    }


    //
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // check RoomActivity -> method fetchall() , have wholedata - >userdataList
        Users users = usersdataList.get(position);
        holder.name.setText(users.getUname());
        holder.password.setText(users.getUpassword());

        holder.delet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  position get from removeFromList() method which be called in RoomActivity(main activity)
                // in main activity implement RoomAdapterLister, override onDelete method
                roomAdapterListener.OnDelete(users.getId(), position);
            }
        });
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // position get from removeFromList() method which be called in RoomActivity(main activity)
               // in main activity(RoomActivity) implement RoomAdapterLister, override onUpdate method
                roomAdapterListener.OnUpdate(users.getId(), position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return usersdataList.size();
    }


    public void addDetailInLists(Users users) {
        // bind the recycler view list with data coming Dao class query inside  RoomActivity
        usersdataList.add(users);
        notifyDataSetChanged();
    }

    // interface RoomAdapterListenner -> get position
    public void removeFromList(int position) {
        usersdataList.remove(position);
        notifyDataSetChanged();
    }

    public void updateFromList(int position , Users users)
    {
        usersdataList.set(position, users);
        notifyDataSetChanged();
    }

    // inside adapter class make one more class ViewHolder extends RecyclerView.ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, password;
        ImageView delet, update;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.namefetchedinCardview);
            password = (TextView) itemView.findViewById(R.id.passwordfetchedinCardview);
            delet = (ImageView) itemView.findViewById(R.id.deleIcon);
            update = (ImageView) itemView.findViewById(R.id.editIcon);
        }
    }
}
