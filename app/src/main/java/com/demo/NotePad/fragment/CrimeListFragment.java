package com.demo.NotePad.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;

import com.demo.NotePad.model.Crime;
import com.demo.NotePad.model.CrimeTab;

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
    }
}
