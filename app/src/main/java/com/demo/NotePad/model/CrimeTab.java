package com.demo.NotePad.model;

import android.content.Context;
import android.text.format.Formatter;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/10.
 */
public class CrimeTab {
    private ArrayList<Crime> mCrimes;
    private static CrimeTab sCrimeTab;//  s 前缀代表是静态变量
    private Context mAppContext;
    private static final String FILENAME = "crimes.json";
    private NotePadJSONSerializer mSerializer ;

    private CrimeTab (Context appContext){
        mAppContext = appContext;
        mSerializer = new NotePadJSONSerializer(appContext , FILENAME);
       //先判断文件中有无Crime数组
        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
            e.printStackTrace();
        }
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

    public boolean saveCrimes()  {
        try {
            mSerializer.saveCrimes(mCrimes);
            //Toast.makeText(mAppContext ,"save success" , Toast.LENGTH_SHORT).show();
            return true;
        } catch (Exception e) {
            //Toast.makeText(mAppContext ,"save fail" +e.toString(), Toast.LENGTH_SHORT).show();
            return false ;
        }
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }
    public void deleteCrime(Crime crime){
        mCrimes.remove(crime);
    }
}
