package com.demo.NotePad.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;

import com.demo.NotePad.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/7/12.
 */
public class DatePickerFragment extends DialogFragment {//注意这里也需要导入V4包...

    public static final String EXTRA_DATE = "com.demo.date";
    private Date mDate;
    public static DatePickerFragment newInstance(Date date){
        Bundle bundle = new Bundle();//将bundle携带date 设置进fragment中
        bundle.putSerializable(EXTRA_DATE , date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    //fragment 之间传递数据信息通过CrimeFragment的setTargetFragment，这里get..得到对应fragment，
    //并通过intent携带信息
    private void sendResult(int resultOk) {
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE , mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode() , resultOk ,intent);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date) getArguments().getSerializable(EXTRA_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date ,null);

        DatePicker datePicker = (DatePicker) view.findViewById(R.id.dialog_date_datePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate = new GregorianCalendar(year ,month ,day ).getTime();
                //date改变后设置到bundle中
                getArguments().putSerializable(EXTRA_DATE , mDate);
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(R.string.date_for_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);
                    }
                })
                .create();

    }


}
/* 另一种代码的方法,这样不如xml中方便更改！
DatePicker dp = new DatePicker(getActivity());
        setView(dp)
*/
