package com.successemmanuel.eggondictionary;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.successemmanuel.eggondictionary.DataObject.DataObjectCollection;
import com.successemmanuel.eggondictionary.mListView.CustomAdapter;


public class DictionaryMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView mListView;
    EditText mSearchList;
    CustomAdapter adapter;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        MobileAds.initialize(this, "@strings/banner_ad_unit_id");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mListView = findViewById(R.id.list_word);
        mSearchList = findViewById(R.id.search);

        adapter = new CustomAdapter(this, DataObjectCollection.getDataObjects());
        mListView.setAdapter(adapter);

        mSearchList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= null, chooser = null;
                intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                String[] to ={"successemmanuel12@gmail.com "," gyanchi6@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, to);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mail Us Suggestions about our App.");
                intent.putExtra(Intent.EXTRA_TEXT, " ");
                intent.setType("message/rfc8  22");
                chooser = Intent.createChooser(intent, "Send Mail");
                startActivity(chooser);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder builder = new AlertDialog.Builder(DictionaryMain.this);
            builder.setMessage("Are You Sure You want to Exit App ?");
            builder.setCancelable(true);
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dictionary_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(),Settings.class);
            startActivity(intent);
//        }else if (id == R.id.action_word){
//            Intent intent = new Intent(getApplicationContext(),WordOfTheDay.class);
//            startActivity(intent);
        }else if (id == R.id.action_contactUs) {
            Intent intent = null, chooser = null;
            intent = new Intent(Intent.ACTION_SEND);
            intent.setDataAndType(Uri.parse("mailto:"), "message/rfc8  22");
            String[] to = {"successemmanuel12@gmail.com "};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mail Us Suggestions about our App.");
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            chooser = Intent.createChooser(intent, "Send Mail");
            startActivity(chooser);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_save) {
            Intent intent = new Intent(getApplicationContext(),FavoriteWords.class);
            startActivity(intent);
        } else if (id == R.id.nav_pro) {

//        } else if (id == R.id.nav_word) {
//            Intent intent = new Intent(getApplicationContext(),WordOfTheDay.class);
//            startActivity(intent);

        } else if (id == R.id.nav_rateUs) {
            try{
                Uri uri1 = Uri.parse("details?id=com.successemmanuel.eggondictionary");
                Intent rateUs = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(rateUs);
            }catch (ActivityNotFoundException e){
                Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=com.successemmanuel.eggondictionary");
                Intent rateUs = new Intent(Intent.ACTION_VIEW, uri1);
                startActivity(rateUs);
            }

        }else if (id == R.id.nav_history){
            startActivity(new Intent(getApplicationContext(), History.class));

        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "EGGON DICTIONARY");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This is a great App, try and check it out.");
            startActivity(Intent.createChooser(shareIntent, "share Via"));

        } else if (id == R.id.nav_send) {
            Intent intent = null, chooser = null;
            intent = new Intent(Intent.ACTION_SEND);
            intent.setDataAndType(Uri.parse("mailto:"), "message/rfc8  22");
            String[] to = {"successemmanuel12@gmail.com "};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mail Us Suggestions about our App.");
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            chooser = Intent.createChooser(intent, "Send Mail");
            startActivity(chooser);
        }else if (id == R.id.nav_website){

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
