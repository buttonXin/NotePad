package com.demo.NotePad.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/7.
 */
public class Crime  {
    private  static final String JSON_ID ="id";
    private  static final String JSON_TITLE = "title";
    private  static final String JSON_SOLVED = "solved";
    private  static final String JSON_DATA = "date";

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    public Crime(){
        mID = UUID.randomUUID();
        mDate = new Date();
    }
    public Crime(JSONObject json) throws JSONException {
        mID = UUID.fromString(json.getString(JSON_ID));
        if(json.has(JSON_TITLE)){//这里判断json数据中是否有这个title字段，因为有的内容没有title！
            mTitle = json.getString(JSON_TITLE);
        }
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATA));

    }
    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID , mID.toString());
        json.put(JSON_DATA , mDate.getTime());
        json.put(JSON_SOLVED , mSolved);
        json.put(JSON_TITLE , mTitle);
        return json;
    }

    public UUID getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
