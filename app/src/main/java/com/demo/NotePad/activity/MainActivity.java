package com.demo.NotePad.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.NotePad.R;
import com.demo.NotePad.fragment.CrimeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();//得到Fragment管理类
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null){
            fragment = new CrimeFragment();
            fm.beginTransaction()//得到事务！作用有：添加，移除，附加， 分离，替换fragment
                    .add(R.id.fragmentContainer , fragment)
                    .commit();
        }
    }
}
