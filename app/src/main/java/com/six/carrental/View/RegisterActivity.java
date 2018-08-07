package com.six.carrental.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.six.carrental.R;

public class RegisterActivity extends AppCompatActivity {
    String phoneNum;
    int flag = 0;//该值将传递到密码设置页面，决定密码设置完后跳转页面，默认跳转为主页
    EditText account, code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );
        if (getIntent ().getExtras () != null) {
            flag = getIntent ().getExtras ().getInt ( "flag" );
            Log.e ( "测试", "flag:" + flag );
        }

        iniView ();
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.register_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        account = findViewById ( R.id.register_account );
        code = findViewById ( R.id.register_code );
        final Button getCode = findViewById ( R.id.register_getCode );
        getCode.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //获取验证码
                getCode.setEnabled ( false );
            }
        } );
        TextView login = findViewById ( R.id.register_next );
        login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                phoneNum = account.getText ().toString ();
                Intent intent = new Intent ( RegisterActivity.this, SetPasswordActivity.class );
                intent.putExtra ( "flag", flag );
                intent.putExtra ( "phone", phoneNum );
                startActivity ( intent );
                finish ();
            }
        } );
    }
}
