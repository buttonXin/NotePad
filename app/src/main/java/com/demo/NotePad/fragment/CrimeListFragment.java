package com.demo.NotePad.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
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
    private static final int REQUEST_CRIME = 1 ;
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
        startActivityForResult(intent, REQUEST_CRIME);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CRIME){
            //处理结果
        }
    }

    private class CrimeAdapter extends ArrayAdapter<Crime>{
        //使用构造方法来绑定Crimes的数组，0代表不传入预定的布局
        public CrimeAdapter (ArrayList<Crime> crimes){
            super(getActivity(), 0 , crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                //得到布局，获取控件实例
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime, null);


                viewHolder.titleTV = (TextView) convertView.findViewById(R.id.crime_list_item_titleTV);
                viewHolder.dateTV = (TextView) convertView.findViewById(R.id.crime_list_item_dateTV);
                viewHolder.solvedCB = (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCB);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Crime c = getItem(position);//不需要强转
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 MM月dd日 EEE");
            viewHolder.titleTV.setText(c.getTitle());
            viewHolder.dateTV.setText(sdf.format(c.getDate()));
            //这里记得要在xml中设置focusable为false！！！否则无法响应点击事件！
            viewHolder.solvedCB.setChecked(c.isSolved());
            return convertView;
        }
        //这里没有添加ViewHolder前会乱序的！！！添加后就不乱序了！
        // 而且adapter.notifyDataSetChanged也会及时得到更新，否则也不会及时更新的...
        class ViewHolder{
            TextView titleTV;
            TextView dateTV;
            CheckBox solvedCB ;
        }
    }
    //这里在返回托管该Fragment的Activity的onResume方法中更新适配器！
    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }
}
