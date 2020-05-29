package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding mBinding;
    String name,sex="",pwd,pwdok,phone;
    boolean sms,protocol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mBinding.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = mBinding.editName.getText().toString().trim();
                pwd = mBinding.editPwd.getText().toString().trim();
                pwdok = mBinding.editPwdOk.getText().toString().trim();
                phone = mBinding.editPhone.getText().toString().trim();
                sms=mBinding.switchSms.isChecked();
                protocol=mBinding.checkProsocol.isChecked();
                String toast;
                if (name.equals(""))
                {
                   toast="请填写昵称";
                }
                else if (phone.equals("")){
                    toast="请填写手机号码";
                }
                else if (sex.equals("")){
                    toast="请选择性别";
                }
                else if (pwd.length()!=6){
                    toast="密码长度必须为6位";
                }
                else  if (!pwd.equals(pwdok))
                {
                    toast="请确保密码一致";
                }else if (phone.length()!=11){
                    toast="请使用正确的手机号码";
                }else  if (!protocol){
                    toast="请同意本软件的相关协议和策略，否则无法注册！";
                }else{
                    SharedPreferences sp=getSharedPreferences("user_info",MODE_PRIVATE);
                    String temp = sp.getString("phone"+phone,"0");
                    if (!temp.equals("0")){
                        toast="该手机号码已被注册";
                    }else{
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("phone"+phone,phone);
                        editor.putString("name"+phone,name);
                        editor.putString("sex"+phone,sex);
                        editor.putString("pwd"+phone,pwd);
                        temp=(sms?"1":"0");
                        editor.putString("sms"+phone,temp);
                        editor.apply();


                        finish();
                    }


                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                    toast="注册成功";
                }

                Toast.makeText(RegisterActivity.this,toast,Toast.LENGTH_SHORT).show();
            }
        });

        mBinding.radioGroupSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.radio_man){
                    sex=mBinding.radioMan.getText().toString();
                }
                else {
                    sex = mBinding.radioWoman.getText().toString();
                }
            }
        });
    }
}
