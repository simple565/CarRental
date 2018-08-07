package com.six.carrental.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.UserInfo;
import com.six.carrental.R;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.Util.RxExceptionUtil;
import com.six.carrental.Util.SharedPreferencesUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SetPasswordActivity extends AppCompatActivity {
    EditText edit_psw, edit_psw_ag;
    int flag;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_set_password );
        if (getIntent ().getExtras () != null) {
            flag = getIntent ().getExtras ().getInt ( "flag" );
            phone = getIntent ().getExtras ().getString ( "phone" );
            Log.e ( "测试", "手机号" + phone );
        }
        iniView ();
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.set_password_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        edit_psw = findViewById ( R.id.set_password );
        edit_psw_ag = findViewById ( R.id.set_password_check );
        final Button finish = findViewById ( R.id.set_password_submit );
        finish.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (edit_psw.getText ().toString ().equals ( edit_psw.getText ().toString () )) {
                    //提交新密码
                    sendInfo ();
                    //注册页面完成跳转主页
                    //修改密码完成跳转我的
                    /*Intent intent = new Intent ( SetPasswordActivity.this, MainActivity.class );
                    intent.putExtra ( "flag", flag );
                    startActivity ( intent );
                    finish ();*/

                }
            }
        } );
    }

    private void sendInfo() {
        Map <String, String> userInfo = new HashMap <> ();
        userInfo.put ( "tel", phone );
        userInfo.put ( "password", edit_psw.getText ().toString () );

        //提交表单
        DataManager.getInstance ().getApiService ()
                .getId ( userInfo )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <UserInfo> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //mDisposable = d;
                        Log.e ( "测试", "onSubscribe " );
                    }

                    @Override
                    public void onNext(UserInfo info) {
                        if (info.getStatus () == 200) {
                            SharedPreferencesUtil.getInstance ( SetPasswordActivity.this )
                                    .setDate ( "tel", phone );
                            SharedPreferencesUtil.getInstance ( SetPasswordActivity.this ).setDate ( "user_id", info.getUser_id () );
                        } else {
                            Toast.makeText ( SetPasswordActivity.this, info.getMessage (), Toast.LENGTH_SHORT ).show ();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "测试", "onError: " + RxExceptionUtil.exceptionHandler ( e ) );

                    }

                    @Override
                    public void onComplete() {

                        startActivity ( new Intent ( SetPasswordActivity.this, MainActivity.class ));
                        //mDisposable.dispose ();
                        //Log.e ( "测试", "onComplete " + mDisposable.isDisposed () );*//*
                    }
                } );

    }
}