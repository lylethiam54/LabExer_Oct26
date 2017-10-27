package com.example.thiam.labexer_oct26;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2Activity extends AppCompatActivity {

    Button load_shared; Button load_intern;
    Button clear; Button back;
    TextView tv_display; FileInputStream fis;
    BufferedReader br; String user, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        load_shared = (Button) findViewById(R.id.lShared);
        load_intern = (Button) findViewById(R.id.lInternal);
        clear = (Button) findViewById(R.id.clearBtn);
        back = (Button) findViewById(R.id.backBtn);
        tv_display = (TextView) findViewById(R.id.tvdisplay);

    }

    public void goback (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void cleardisplay (View view) {
        tv_display.setText("");
    }

    public void loadpreference(View view) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        String user = preferences.getString("username","");
        String pwd = preferences.getString("password","");
        tv_display.setText("The password of " + user + " is " + pwd);
    }

    public void loadinternal (View view) throws IOException {
        String read = "";
        try{
            fis = openFileInput("output.txt");
            br = new BufferedReader(new InputStreamReader(fis));
            if ((read = br.readLine()) != null)
                user = read;
            if ((read = br.readLine()) != null)
                pwd = read;
            fis.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        tv_display.setText("The password of " + user + " is " + pwd);
    }
}