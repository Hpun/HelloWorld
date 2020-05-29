package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.helloworld.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent=getIntent();
        String phone = intent.getStringExtra("data_phone");
        mBinding.textphone.setText(phone);
        SharedPreferences sp=getSharedPreferences("user_info",MODE_PRIVATE);
        String textname = sp.getString("name"+phone,"0");
        String textsex = sp.getString("sex"+phone,"0");
        mBinding.textname.setText(textname);
        mBinding.textsex.setText(textsex);
    }
}
