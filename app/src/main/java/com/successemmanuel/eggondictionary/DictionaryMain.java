package com.successemmanuel.eggondictionary;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.successemmanuel.eggondictionary.DataObject.DataObjectCollection;
import com.successemmanuel.eggondictionary.mListView.CustomAdapter;


public class DictionaryMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ListView lv;
    SearchView sv;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView) findViewById(R.id.list_word);
        sv = (SearchView) findViewById(R.id.search);

        adapter = new CustomAdapter(this, DataObjectCollection.getDataObjects());
        lv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        }else if (id == R.id.action_word){
            Intent intent = new Intent(getApplicationContext(),WordOfTheDay.class);
            startActivity(intent);
        }else if (id == R.id.action_contactUs) {
            Intent intent = null, chooser = null;
            intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"successemmanuel12@gmail.com ", " gyanchi6@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mail Us Suggestions about our App.");
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            intent.setType("message/rfc8  22");
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

        } else if (id == R.id.nav_word) {
            Intent intent = new Intent(getApplicationContext(),WordOfTheDay.class);
            startActivity(intent);

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
            intent.setData(Uri.parse("mailto:"));
            String[] to = {"successemmanuel12@gmail.com ", " gyanchi6@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL, to);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Mail Us Suggestions about our App.");
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            intent.setType("message/rfc8  22");
            chooser = Intent.createChooser(intent, "Send Mail");
            startActivity(chooser);
        }else if (id == R.id.nav_website){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
