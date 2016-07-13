package com.demo.NotePad.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/10.
 */
public class CrimeTab {
    private ArrayList<Crime> mCrimes;
    private static CrimeTab sCrimeTab;//  s 前缀代表是静态变量
    private Context mAppContext;

    private CrimeTab (Context appContext){
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        /*for (int i = 0; i <50 ; i++) {
            Crime c = new Crime();
            c.setTitle("title + "+i);
            c.setSolved( i%2 == 0 );
            mCrimes.add(c);//添加50个记录到集合中
        }*/
    }
    public static CrimeTab get(Context c){
        if(sCrimeTab == null){
            sCrimeTab = new CrimeTab(c);
        }
        return sCrimeTab;
    }

    public ArrayList<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c : mCrimes){
            if(c.getID().equals(id)){
                return  c;
            }
        }
        return null;
    }
    public void addCrime(Crime c){
        mCrimes.add(c);
    }
}
