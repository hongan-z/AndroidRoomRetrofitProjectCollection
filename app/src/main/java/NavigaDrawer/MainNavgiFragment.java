package NavigaDrawer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import ExaData.tutorial.Training.R;



//// 加载 Fragment 有两种方式
//静态加载
//在activtiy 的XML中 声明 fragment  在fragment 标签中通过 name 指定实例化的fragment 的类
//动态加载：
//通过fragment 事务来添加，删除，替换fragment

public class MainNavgiFragment extends Fragment {

    private onFragmentBtnSelected listener;
    private onImageBtnSelected listener2;
    private ImageButton imageButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_naviga_fragment, container, false);
        Button button = view.findViewById(R.id.Load_button);
        imageButton = view.findViewById(R.id.imageButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonSelected();
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener2.onImageButtonSelected();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onFragmentBtnSelected) {
            listener = (onFragmentBtnSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listerner");
        }

        if (context instanceof onImageBtnSelected) {
            listener2 = (onImageBtnSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement listerner");
        }

    }




    public interface onFragmentBtnSelected {
        public void onButtonSelected();
    }

    public interface onImageBtnSelected{
        public void onImageButtonSelected();
    }

}