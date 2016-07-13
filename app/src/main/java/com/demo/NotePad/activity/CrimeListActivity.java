package com.demo.NotePad.activity;

import android.support.v4.app.Fragment;

import com.demo.NotePad.fragment.CrimeFragment;
import com.demo.NotePad.fragment.CrimeListFragment;

/**
 * Created by Administrator on 2016/7/10.
 */
public class CrimeListActivity extends  SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }

}
