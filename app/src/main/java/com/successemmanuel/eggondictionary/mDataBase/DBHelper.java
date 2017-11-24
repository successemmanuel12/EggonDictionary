package com.successemmanuel.eggondictionary.mDataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    public DBHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(Constants.CREATE_TB);
            db.execSQL(Constants.CREATE_TB1);
            db.execSQL(Constants.CREATE_TB2);
            db.execSQL(Constants.CREATE_TB3);
            db.execSQL(Constants.CREATE_TB4);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constants.DROP_TB);
        db.execSQL(Constants.DROP_TB1);
        db.execSQL(Constants.DROP_TB2);
        db.execSQL(Constants.DROP_TB3);
        db.execSQL(Constants.DROP_TB4);
        onCreate(db);
    }
//FAVORITE WORD DECLARATIONS
    public Cursor getFavWords(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {Constants.WORD,Constants.TYPE,Constants.DEFINITION, Constants.EXAMPLES};
        Cursor data = db.query(Constants.TB_NAME,columns,null,null,null,null,null);
       return data;
    }
    /**
     * Returns only the ID that matches the word passed in
     * @param word
     * @return
     */
    public Cursor getItemID(String word ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Constants.ROW_ID + ","+Constants.TYPE+","+Constants.DEFINITION+","+Constants.EXAMPLES+" FROM " + Constants.TB_NAME +
                " WHERE " + Constants.WORD + " = '" + word + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Delete from database
     * @param id
     * @param word
     */
    public void deleteFavWord(int id, String word){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + Constants.TB_NAME + " WHERE "
                + Constants.ROW_ID + " = '" + id + "'" +
                " AND " + Constants.WORD + " = '" + word + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + word + " from database.");
        db.execSQL(query);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Constants.TB_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    //MEANING DECLARATIONS
    public Cursor getMeaning(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {Constants.WORD_ID,Constants.DEFINITION};
        Cursor data = db.query(Constants.MEANING_TB,columns,null,null,null,null,null);
        return data;
    }
    //Returns only the ID that matches the word passed in
    public Cursor getMeaningID(String word_id ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Constants.ROW_ID + ","+Constants.DEFINITION+" FROM " + Constants.MEANING_TB +
                " WHERE " + Constants.WORD_ID + " = '" + word_id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}

