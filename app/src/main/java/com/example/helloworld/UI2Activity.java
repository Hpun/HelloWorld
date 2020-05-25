package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.databinding.ActivityUI2Binding;

public class UI2Activity extends AppCompatActivity {
    private ActivityUI2Binding mBinding;
//    private TextView mTextView;
//    private Button mButtonLeft, mButtonRight, mButtonOK;
//    private Switch mSwitch;
//    private ProgressBar mProgressBar;
//    private EditText mEditTextNumber;
//    private RadioGroup mRadioGroup;
//    private ImageView mImageView;
//    private SeekBar mSeekBar;
//    private CheckBox mCheckBoxAndroid, mCheckBoxJava, mCheckBoxSQL;
//    private RatingBar mRatingBar;
        private String android = "", java = "", sql = "";
//    private RadioButton mRadioButtonAndroid,mRadioButtonApple,mRadioButtonAli;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_i_2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mTextView = findViewById(R.id.text_view);
        mButtonLeft = findViewById(R.id.button_left);
        mButtonRight = findViewById(R.id.button_right);
        mSwitch = findViewById(R.id.button_switch);
        mEditTextNumber = findViewById(R.id.edit_number);
        mButtonOK = findViewById(R.id.button_ok);
        mProgressBar = findViewById(R.id.progress_bar);
        mRadioGroup = findViewById(R.id.radio_group);
        mImageView = findViewById(R.id.image_view);
        mSeekBar = findViewById(R.id.seek_bar);
        mCheckBoxAndroid = findViewById(R.id.check_android);
        mCheckBoxJava = findViewById(R.id.check_java);
        mCheckBoxSQL = findViewById(R.id.check_sql);
        mRatingBar=findViewById(R.id.rating_bar);
        mRadioButtonAndroid=findViewById(R.id.radio_android);
        mRadioButtonApple=findViewById(R.id.radio_apple);
        mRadioButtonAli=findViewById(R.id.radio_ali);



        mButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTextView.setText(getResources().getString(R.string.botton_left));

            }
        });
        mButtonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextView.setText(getResources().getString(R.string.botton_right));
            }
        });
        mButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = mEditTextNumber.getText().toString();
                if (s.equals("")){
                    Toast.makeText(getApplicationContext(),getResources().getString(R.string.ok_hint),Toast.LENGTH_SHORT).show();
                }
                int temp;
                try {
                    temp = Integer.parseInt(s);//转换为整型数字
                } catch (NumberFormatException e) {
                    temp = 100;
                }
                mProgressBar.setProgress(temp);//设置进度
                mSeekBar.setProgress(temp);
                mTextView.setText(s);

                if (temp<=30){
                    mRadioButtonAndroid.setChecked(true);
                }else if (temp<=60){
                    mRadioButtonApple.setChecked(true);
                }else{
                    mRadioButtonAli.setChecked(true);
                }
            }
        });
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTextView.setText(isChecked ? getResources().getString(R.string.open) : getResources().getString(R.string.close));
            }
        });
        mCheckBoxAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    android = getResources().getString(R.string.logo);
                } else {
                    android = "";
                }
                mTextView.setText(android + java + sql);
            }
        });
        mCheckBoxJava.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    java = getResources().getString(R.string.java);
                } else {
                    java = "";
                }
                mTextView.setText(android + java + sql);
            }
        });
        mCheckBoxSQL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sql = getResources().getString(R.string.sql);
                } else {
                    sql = "";
                }
                mTextView.setText(android + java + sql);
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_android:
                        mImageView.setImageResource(R.drawable.android);
                        break;
                    case R.id.radio_apple:
                        mImageView.setImageResource(R.drawable.apple);
                        break;
                    case R.id.radio_ali:
                        mImageView.setImageResource(R.mipmap.ia_100000022);
                        break;

                    default:
                        break;
                }
            }

//    Progress是进度，当进度发生变化时调用该方法
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTextView.setText(String.valueOf(progress));
                mEditTextNumber.setText(String.valueOf(progress));

            }




            public void onStopTrackingTouch(SeekBar seekBar) {
//当结束触摸该控件时发生
            }

            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getApplicationContext(),rating+getResources().getString(R.string.appraise),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
