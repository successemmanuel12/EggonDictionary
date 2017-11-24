package com.successemmanuel.eggondictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.successemmanuel.eggondictionary.mDataBase.DBAdapter;


public class DefinitionActivity extends AppCompatActivity {
    TextView wordTxt;
    TextView typeTxt;
    TextView definitionTxt;
    TextView exampleTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wordTxt = (TextView)findViewById(R.id.text_selected);
        typeTxt = (TextView)findViewById(R.id.text_type);
        definitionTxt = (TextView)findViewById(R.id.text_meaning);
        exampleTxt = (TextView) findViewById(R.id.text_example);

        Intent i = this.getIntent();
        final String word= i.getExtras().getString("WORD_KEY");
        final String type= i.getExtras().getString("TYPE_KEY");
        final String definition= i.getExtras().getString("DEFINITION_KEY");
        final String example = i.getExtras().getString("EXAMPLE_KEY");

        wordTxt.setText(word);
        typeTxt.setText(type);
        definitionTxt.setText(definition);
        exampleTxt.setText(example);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(word, type, definition,example);
            }
        });
    }
    private void save(String word, String type, String definition, String example)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        if(db.addFavWord(word,type, definition,example))
        {
            Toast.makeText(this,"Successfully added to Favorite Words",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Unable To Save", Toast.LENGTH_SHORT).show();
        }

        db.closeDB();

    }
}
