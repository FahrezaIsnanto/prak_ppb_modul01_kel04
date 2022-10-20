package com.example.aplikasimodul1kel4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class LoginActivity extends AppCompatActivity {

    private EditText etUsernameLogin, etPasswordLogin;

    private Button btnSignUpLogin, btnSignInLogin;

    String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        login();
        register();

    }

    private void initView(){
        etUsernameLogin = findViewById(R.id.etUsernameLogin);
        etPasswordLogin = findViewById(R.id.etPasswdLogin);
        btnSignUpLogin = findViewById(R.id.btnSignUpLogin);
        btnSignInLogin = findViewById(R.id.btnSignInLogin);
    }

    private void register(){
        btnSignUpLogin.setOnClickListener(V->{
            Intent reg = new Intent(this, RegisterActivity.class);
            startActivity(reg);
            finish();
        });
    }

    private Boolean validation(){
        username = etUsernameLogin.getText().toString();
        password = etPasswordLogin.getText().toString();

        if(username.isEmpty()){
            Toast.makeText(this,"Isikan username",Toast.LENGTH_SHORT).show();
            Log.e("Validation","false");
            return false;
        }

        if(password.isEmpty()){
            Toast.makeText(this,"Isikan Password",Toast.LENGTH_SHORT).show();
            Log.e("Validation","false");
            return false;
        }
        Log.e("Validation","true");
        return true;
    }

    private void login(){
        btnSignInLogin.setOnClickListener(V->{
            actLogin();
        });
    }

    private void actLogin(){
        if(validation()){
            if(loginData()){
                Log.e("actLogin","true");
                Intent main = new Intent(this,MainActivity.class);
                startActivity(main);
                finish();
            }else{
                Log.e("actLogin","false");
                Toast.makeText(this,"Login Gagal",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private Boolean loginData(){

        if(username.equals("fahreza") && password.equals("fahreza123")){
            Log.e("loginData","true");
            setPreference();
            return true;
        }else{
            Log.e("loginData","false");
            return false;
        }
    }

    private void setPreference(){
        // shared preferences
        SharedPreferences preferences = getSharedPreferences("LoginPreference", MODE_PRIVATE);
        preferences.edit().putString("username",username).commit();
        preferences.edit().putString("password",password).commit();
        Log.e("preferencesUsernameL",preferences.getString("username",""));
        Log.e("preferencesPasswordL",preferences.getString("password",""));
    }
}