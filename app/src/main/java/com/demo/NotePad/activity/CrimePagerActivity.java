package com.demo.NotePad.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.demo.NotePad.R;
import com.demo.NotePad.fragment.CrimeFragment;
import com.demo.NotePad.model.Crime;
import com.demo.NotePad.model.CrimeTab;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/12.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;
    private Context mContext;
    private ArrayList<Crime> mCrimes;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mViewPager = new ViewPager(mContext);
        mViewPager.setId(R.id.viewPager);//这里必须设置资源ID，因为ViewPager作为一个容器
        setContentView(mViewPager);

        mCrimes = CrimeTab.get(mContext).getCrimes();
        FragmentManager fm = getSupportFragmentManager();
        //这里用State而不用FragmentPagerAdapter的原因是State会将不用的Fragment销毁，节省内存！
        mViewPager.setAdapter(new  FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime c = mCrimes.get(position);
                //通过UUid得到相应的CrimeFragment
                return CrimeFragment.newInstance(c.getID());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        //这里设置跳转到那个Fragment来更改Title,这里是add了不是set，监听ViewPager当前页面的状态变化！
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override//这个方法是页面将会滚动到哪里
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override//这个是当前页面是那个
            public void onPageSelected(int position) {
                Crime c = mCrimes.get(position);//将title更改下
                if(c.getTitle() != null ) {
                    char[] chars = c.getTitle().toCharArray();
                    if(chars.length > 5 ) {
                        setTitle(c.getTitle().substring(0, 5));
                    }else {
                        setTitle(c.getTitle());
                    }
                }
            }
            @Override//这个是页面的行为状态！
            public void onPageScrollStateChanged(int state) {
            }
        });
        //这里设置点击了那个Fragment就跳转到那个Fragment
        UUID crimeID = (UUID) getIntent().getSerializableExtra(CrimeFragment.EXTRA_ID);
        //遍历查询id为哪一个Fragment,然后设置ViewPager
        for (int i = 0; i <mCrimes.size() ; i++) {
            if(mCrimes.get(i).getID().equals(crimeID)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
