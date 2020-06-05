package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding mBinding;

    private long exitTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        String phone = intent.getStringExtra("data_phone");
        mBinding.textphone.setText(phone);
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        String textname = sp.getString("name" + phone, "0");
        String textsex = sp.getString("sex" + phone, "0");
        mBinding.textname.setText(textname);
        mBinding.textsex.setText(textsex);
    }

    /**
     * 拦截系统返回键
     */
    @Override
    public void onBackPressed() {
        exit();
    }

    public void exit(){
        long time = 2000;
        if (System.currentTimeMillis()-exitTime>time){
            //存储此次点击返回键的时间
            exitTime=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"快速点击两次，可退出app",Toast.LENGTH_SHORT).show();
        }else{
            //完全退出app
            finish();
        }

    }
}
