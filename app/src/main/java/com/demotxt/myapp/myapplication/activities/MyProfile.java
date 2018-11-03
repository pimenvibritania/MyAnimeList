package com.demotxt.myapp.myapplication.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.demotxt.myapp.myapplication.R;
import com.demotxt.myapp.myapplication.WelcomeActivity;

import java.util.Locale;

public class MyProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_my_profile);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id = item.getItemId();

        switch (id){

            case R.id.home:
                Intent h = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(h);
                break;

            case R.id.view:
                Intent v = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(v);
                break;
            case R.id.language:
                showChangeLanguageDialog();
                break;

        }

        return  true;

    }

    private void showChangeLanguageDialog() {
        final String[] listItems = {"Indonesia", "English", "日本", "русский", "ไทย"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MyProfile.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLocale("in");
                    recreate();
                }
                else if (i == 1){
                    setLocale("en");
                    recreate();
                }
                else if (i == 2){
                    setLocale("ja");
                    recreate();
                }
                else if (i == 3){
                    setLocale("ru");
                    recreate();
                }
                else if (i == 4){
                    setLocale("th");
                    recreate();
                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", " ");
        setLocale(language);
    }

}
