package com.demo.NotePad.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.demo.NotePad.R;
import com.demo.NotePad.model.Crime;

/**
 * Created by Administrator on 2016/7/7.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container,false);
        mTitleText = (EditText) view.findViewById(R.id.crime_title);
       //添加EditText监听器
        mTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());//得到标题内容
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }
}
