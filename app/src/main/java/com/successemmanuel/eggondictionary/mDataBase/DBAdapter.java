package com.successemmanuel.eggondictionary.mDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
    Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper=new DBHelper(c);
    }

    //OPEN DB
    public void openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //CLOSE
    public void closeDB()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //INSERT DATA
    public boolean addFavWord(String word, String type, String definition, String example)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constants.WORD, word);
            cv.put(Constants.TYPE, type);
            cv.put(Constants.DEFINITION, definition);
            cv.put(Constants.EXAMPLES, example);
            db.insert(Constants.TB_NAME, Constants.ROW_ID, cv);

            return true;

        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    protected boolean addMeaning(int word_id, String definition){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.WORD_ID,word_id);
            cv.put(Constants.DEFINITION, definition);
            db.insert(Constants.MEANING_TB,Constants.ROW_ID,cv);

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
            return  false;
    }
    protected boolean addExamples(int word_id, String examples){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.WORD_ID,word_id);
            cv.put(Constants.EXAMPLES, examples);
            db.insert(Constants.MEANING_TB,Constants.ROW_ID,cv);

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
    protected boolean addPos(int word_id, String pos){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.WORD_ID,word_id);
            cv.put(Constants.PART, pos);
            db.insert(Constants.MEANING_TB,Constants.ROW_ID,cv);

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
    protected boolean addWords(String type, String words){
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constants.TYPE,type);
            cv.put(Constants.FAV_WORDS, words);
            db.insert(Constants.MEANING_TB,Constants.ROW_ID,cv);

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
}
