package com.demo.NotePad.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.demo.NotePad.R;
import com.demo.NotePad.model.Crime;
import com.demo.NotePad.model.CrimeTab;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/7.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleText;
    private Button mDataButton;
    private CheckBox mSolvedCheckBox;
    public static final String EXTRA_ID = "com.demo.crime_id";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
        //得到UUID通过getSerializableExtrad， 然后从CrimeTab中通过id得到该对象！！！
        UUID crimeID = (UUID) getArguments().getSerializable(EXTRA_ID);
        mCrime = CrimeTab.get(getActivity()).getCrime(crimeID);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime, container,false);
        mTitleText = (EditText) view.findViewById(R.id.crime_title);

        mTitleText.setText(mCrime.getTitle());
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
        mDataButton = (Button) view.findViewById(R.id.data_btn);
        //这里注意下小写y，大写M，小写d ；大写E； 后面的大写H，小写m，小写s。
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEE HH:mm:ss");
        mDataButton.setText(   sdf.format(mCrime.getDate())  );//得到2016年07月09日 周六 10:12:31
        mDataButton.setEnabled(false);//设置不可以点击
        //单选设置
        mSolvedCheckBox = (CheckBox) view.findViewById(R.id.solve_cb);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        return view;
    }
    public static Fragment newInstance(UUID id){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ID , id);
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    public void  returnResult(){//因为fragment没有setResult方法，所以需要用托管的activity的这个方法
        getActivity().setResult(Activity.RESULT_OK  ,null);
    }
}
