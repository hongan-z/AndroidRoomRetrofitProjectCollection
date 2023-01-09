package NavigaDrawer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ExaData.tutorial.Training.R;


public class SecondNavigaFragment extends Fragment {

// fragment create onCreatView return View of UI
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_naviga_fragment,container,false);
        return view; // if not UI display , you can return null as well
    }
}

//// 加载 Fragment 有两种方式
//静态加载
//在activtiy 的XML中 声明 fragment  在fragment 标签中通过 name 指定实例化的fragment 的类
//动态加载：
//通过fragment 事务来添加，删除，替换fragment