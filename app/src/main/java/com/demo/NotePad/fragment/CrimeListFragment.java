package com.demo.NotePad.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.NotePad.R;
import com.demo.NotePad.activity.CrimeActivity;
import com.demo.NotePad.model.Crime;
import com.demo.NotePad.model.CrimeTab;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/10.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("记录表");
        mCrimes = CrimeTab.get(getActivity()).getCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //getListAdapter得到强转的adapter，因为泛型了Crime也不用再次需要强转了！
        Crime c = ((CrimeAdapter) getListAdapter()).getItem(position);

        Intent intent = new Intent(getActivity() , CrimeActivity.class);
        //这里的UUID是Serializable对象，put的是Serializable
        intent.putExtra(CrimeFragment.EXTRA_ID , c.getID());
        startActivity(intent);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime>{
        //使用构造方法来绑定Crimes的数组，0代表不传入预定的布局
        public CrimeAdapter (ArrayList<Crime> crimes){
            super(getActivity(), 0 , crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                //得到布局，获取控件实例
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);
                Crime c = getItem(position);//不需要强转

                TextView titleTV = (TextView) convertView.findViewById(R.id.crime_list_item_titleTV);
                TextView dateTV = (TextView) convertView.findViewById(R.id.crime_list_item_dateTV);
                CheckBox solvedCB = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCB);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月dd日 EEE");

                titleTV.setText(c.getTitle());
                dateTV.setText(sdf.format(c.getDate()));
                //这里记得要在xml中设置focusable为false！！！否则无法响应点击事件！
                solvedCB.setChecked(c.isSolved());

            }
            return convertView;
        }

    }
}
