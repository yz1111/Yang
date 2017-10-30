package com.example.yangzhe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog.Builder;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userPassword;
    private Button btn_register;
    private Button btn_login;
    private Button btn_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除黑线条
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //查找控件
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        btn_register = (Button) findViewById(R.id.register);
        btn_login = (Button) findViewById(R.id.login);
        btn_exit = (Button) findViewById(R.id.exit);

        //监听事件
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册
                showMyDialog();
            }
        });


    }
    //注册
    public void showMyDialog(){

        Builder builder=new Builder(MainActivity.this);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.registerlayout, null);
        Button btn_confirm = (Button) view.findViewById(R.id.btn_confirm);
        Button btn_back = (Button) view.findViewById(R.id.btn_back);
    }
}
