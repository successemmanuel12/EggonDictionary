package com.successemmanuel.eggondictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.successemmanuel.eggondictionary.mDataBase.DBHelper;


public class Word extends AppCompatActivity {

    DBHelper myDB;
    TextView wordTxt;
    TextView typeTxt;
    TextView definitionTxt;
    TextView exampleTxt;
    Button deleteBtn;
    AdView mAdView;
    private String selectedWord;
    private String selectedType;
    private String selectedDef;
    private String selectedEx;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        wordTxt = (TextView)findViewById(R.id.text_selected);
        typeTxt = (TextView)findViewById(R.id.text_type);
        definitionTxt = (TextView)findViewById(R.id.text_meaning);
        exampleTxt = (TextView) findViewById(R.id.text_example);

        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        myDB = new DBHelper(this);
        //get the intent extra from the Favorite word activity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedWord = receivedIntent.getStringExtra("word");
        selectedType = receivedIntent.getStringExtra("type");
        selectedDef = receivedIntent.getStringExtra("definition");
        selectedEx = receivedIntent.getStringExtra("example");
        //set the text to show the current selected word
        wordTxt.setText(selectedWord);
        typeTxt.setText(selectedType);
        definitionTxt.setText(selectedDef);
        exampleTxt.setText(selectedEx);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteFavWord(selectedID,selectedWord);
                toastMessage("removed from favorite words");
                startActivity(new Intent(Word.this, FavoriteWords.class));
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
