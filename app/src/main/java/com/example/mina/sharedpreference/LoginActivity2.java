package com.example.mina.sharedpreference;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;


public class LoginActivity2 extends Activity {
    EditText value;
    Button login;
    String valuestring = null;
    public SharedPreferences setting;
    public static File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        value = (EditText) findViewById(R.id.textname);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new ClickButton());
        file = new File("/data/data/ com.example.mina.sharedpreference/shared_prefs","LoginInfo.xml");
        //找這個檔案裡面的資料
        if(file.exists()){
            ReadValue();
            if(!valuestring.equals("")){
                SendIntent();
            }
        }
    }
    class ClickButton implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(view == login){
                if (value != null){
                    valuestring = value.getEditableText().toString();
                    setting = getSharedPreferences("LoginInfo",0);
                    setting.edit().putString("VALUESTRING",valuestring).commit();
                    SendIntent();
                }
            }
        }
    }
    public void ReadValue(){
        setting = getSharedPreferences("LoginInfo",0);
        valuestring = setting.getString("VALUESTRING","");
    }
    public void SendIntent(){
        Intent it = new Intent();
        it.setClass(LoginActivity2.this,LogoutActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("VALUE",valuestring);
        it.putExtras(bundle);
        startActivity(it);
        LoginActivity2.this.finish();
    }

}
