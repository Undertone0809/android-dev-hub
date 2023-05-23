package com.example.comprehensiveapplication.wxlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comprehensiveapplication.R;

public class WXLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Fragment weixinFragment = new weixinFragment();
    private Fragment friendFragment = new friendFragment();
    private Fragment contactFragment = new contactFragment();
    private Fragment settingFragment = new settingFragment();

    private FragmentManager fm;

    private LinearLayout tab01,tab02,tab03,tab04;
    private ImageView tab01_img;
    private ImageView tab02_img;
    private ImageView tab03_img;
    private ImageView tab04_img;
    private Button btn;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxlayout);
        Context context = this;

        weixinFragment = new weixinFragment();
        friendFragment = new friendFragment();
        contactFragment = new contactFragment();
        settingFragment = new settingFragment();

        tab01 = findViewById(R.id.tab01);
        tab02 = findViewById(R.id.tab02);
        tab03 = findViewById(R.id.tab03);
        tab04 = findViewById(R.id.tab04);


        fm = getSupportFragmentManager();

        initialfragment();
        tab01_img = findViewById(R.id.tab01_img);
        tab02_img = findViewById(R.id.tab02_img);
        tab03_img = findViewById(R.id.tab03_img);
        tab04_img = findViewById(R.id.tab04_img);
        tab01.setOnClickListener(this);
        tab02.setOnClickListener(this);
        tab03.setOnClickListener(this);
        tab04.setOnClickListener(this);


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WXLayoutActivity.this,WXLayoutChatActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initialfragment(){
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(androidx.appcompat.R.id.content,weixinFragment);
        transaction.add(androidx.appcompat.R.id.content,friendFragment);
        transaction.add(androidx.appcompat.R.id.content,contactFragment);
        transaction.add(androidx.appcompat.R.id.content,settingFragment);
        Hide(transaction);
        transaction.show(weixinFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tab01: show(1); reset(); tab01_img.setImageResource(R.drawable.work_filling); break;
            case R.id.tab02: show(2); reset(); tab02_img.setImageResource(R.drawable.comment_filling); break;
            case R.id.tab03: show(3); reset(); tab03_img.setImageResource(R.drawable.notification_filling); break;
            case R.id.tab04: show(4); reset(); tab04_img.setImageResource(R.drawable.user_filling); break;
            default: break;
        }
    }

    private void show(int i) {
        FragmentTransaction transaction = fm.beginTransaction();
        Hide(transaction);
        switch (i){
            case 1:transaction.show(weixinFragment);break;
            case 2:transaction.show(friendFragment);break;
            case 3:transaction.show(contactFragment);break;
            case 4:transaction.show(settingFragment);break;
            default:break;
        }
        transaction.commit();
    }

    private void Hide(FragmentTransaction transaction) {
        transaction.hide(weixinFragment);
        transaction.hide(friendFragment);
        transaction.hide(contactFragment);
        transaction.hide(settingFragment);
    }

    private void reset(){
        tab01_img.setImageResource(R.drawable.work);
        tab02_img.setImageResource(R.drawable.comment);
        tab03_img.setImageResource(R.drawable.notification);
        tab04_img.setImageResource(R.drawable.user);
    }
}