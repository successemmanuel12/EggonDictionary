package com.successemmanuel.eggondictionary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.successemmanuel.eggondictionary.mDataBase.DBHelper;

import java.util.ArrayList;

public class FavoriteWords extends AppCompatActivity {

    private static final String TAG = "FavoriteWords";
    DBHelper myDB;
    ListView wordList;
    Context c;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_words);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        wordList = (ListView)findViewById(R.id.fav_word_list);
        myDB = new DBHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getFavWords();

        if (data.getCount()==0){
            Toast.makeText(FavoriteWords.this, "No Words Added Yet",Toast.LENGTH_LONG).show();
        }else {
            while (data.moveToNext()){
                theList.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
                wordList.setAdapter(listAdapter);
            }
        }
        wordList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + word);

                Cursor data = myDB.getItemID(word); //get the id associated with that word
                int itemID = -1;
                String type = null;
                String definition = null;
                String example = null;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                    type = data.getString(1);
                    definition = data.getString(2);
                    example = data.getString(3);
                }
                if(itemID > -1){

                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(FavoriteWords.this, Word.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("word",word);
                    editScreenIntent.putExtra("type",type);
                    editScreenIntent.putExtra("definition", definition);
                    editScreenIntent.putExtra("example", example);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("No ID associated with that name");
                }
            }
        });
    }
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
