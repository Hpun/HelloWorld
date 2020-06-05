package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private static final int RESULT_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent:意图分为显示和隐式
                String phone =mBinding.editPhone.getText().toString();
                String pwd=mBinding.editPwd.getText().toString();
                SharedPreferences sp=getSharedPreferences("user_info",MODE_PRIVATE);
                if (phone.equals(sp.getString("phone"+phone,"error"))&&pwd.equals(sp.getString("pwd"+phone,"error"))){
                    Bundle bundle = new Bundle();
                    String userName = sp.getString("name"+phone,"0");
                    String userSms = "1".equals(sp.getString("sms"+phone,"0"))?"接受":"不接受";
                    String userSex = sp.getString("sex"+phone,"0");
                    UserInfo userInfo= new UserInfo(userName,pwd,userSex,phone,userSms);
                    bundle.putSerializable("userInfo",userInfo);
                    Intent intent=new Intent(MainActivity.this,HomeActivity.class);//上下文，目标activity得类
                    intent.putExtras(bundle);
                    //intent.putExtra("data_phone",phone);
                    //startActivity(intent);
                    startActivityForResult(intent,RESULT_CODE);
                }
                else {
                    Toast.makeText(MainActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBinding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==RESULT_CODE){
            if (resultCode == RESULT_OK){
                String s = Objects.requireNonNull(data).getStringExtra(HomeActivity.EXIT_HOME);
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
