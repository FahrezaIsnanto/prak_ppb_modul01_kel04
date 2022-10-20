package com.example.aplikasimodul1kel4;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    private TextView tvNameMain;
    private Button btnLogoutMain;
    private Button exit;
    private ListView listView;

    private String[] mahasiswa = {
            "Muhammad Fahreza Isnanto - 21120120120009",
            "Didan Hasan Murtaqi - 21120120140141",
            "Muhammad Fadhil Sulthan - 21120120140144",
            "Donny Ridwan Setiawan - 21120120130083"
    };

    private ArrayList<String> data;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPreference();
        logout();
    }

    private void initView() {
        listView = findViewById(R.id.list);
        data = new ArrayList<>();
        getData();
        adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,data);
        listView.setAdapter(adapter);
        tvNameMain = findViewById(R.id.tvNameMain);
        btnLogoutMain = findViewById(R.id.btnLogoutMain);
        exit = findViewById(R.id.btnLogoutMain);
    }


    private void getData(){
        Collections.addAll(data,mahasiswa);
    }

    private void initPreference() {
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        String username = preferences.getString("username", "");

        tvNameMain.setText("Halo, "+username);
    }

    private void logout() {
        exit.setOnClickListener(view -> showAlertDialog());
    }

    public void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Apa kalian ingin Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deletePreference();
                        Intent login = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(login);
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void deletePreference(){
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().remove("username").commit();
        preferences.edit().remove("password").commit();
    }




}