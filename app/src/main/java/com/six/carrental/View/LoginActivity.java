package com.six.carrental.View;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.UserInfo;
import com.six.carrental.R;
import com.six.carrental.Util.RxExceptionUtil;
import com.six.carrental.Util.SharedPreferencesUtil;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    private EditText phone, password;
    private boolean isOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );
        iniView ();
    }

    private void iniView() {
        phone = findViewById ( R.id.login_account );
        if (SharedPreferencesUtil.getInstance ( this ).getData ( "tel" ) != null) {
            phone.setText ( SharedPreferencesUtil.getInstance ( this ).getData ( "tel" ) );
        }
        password = findViewById ( R.id.login_password );
        TextView tv_register = findViewById ( R.id.login_register );
        tv_register.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( LoginActivity.this, RegisterActivity.class );
                intent.putExtra ( "flag", 0 );
                startActivity ( intent );
                //finish ();
            }
        } );
        TextView tv_forget = findViewById ( R.id.login_forgetPassword );
        tv_forget.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( LoginActivity.this, RegisterActivity.class );
                intent.putExtra ( "phone", phone.getText ().toString () );
                intent.putExtra ( "flag", 2 );
                startActivity ( intent );
                finish ();
            }
        } );

        final Button login = findViewById ( R.id.login_btn_login );
        //账号密码验证
        login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String phoneNum, passwordValue;
                //startActivity ( new Intent ( LoginActivity.this, MainActivity.class ) );
                if (phone != null && password != null) {
                    phoneNum = phone.getText ().toString ();
                    passwordValue = password.getText ().toString ();
                    checkUser ( phoneNum, passwordValue );
                } else {
                    Toast.makeText ( LoginActivity.this, "请填写手机或密码", Toast.LENGTH_SHORT ).show ();
                }

            }
        } );
    }

    private void checkUser(final String phone, String password) {
        Map <String, String> user = new HashMap <> ();
        user.put ( "tel", phone );
        user.put ( "password", password );
        DataManager.getInstance ().getApiService ()
                .userLogin ( user )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <UserInfo> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "登录", "登录中" );
                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        if (userInfo.getStatus () != 200) {
                            Toast.makeText ( LoginActivity.this, userInfo.getMessage (), Toast.LENGTH_SHORT ).show ();
                        } else {
                            isOk = true;
                            SharedPreferencesUtil.getInstance ( LoginActivity.this ).setDate ( "tel", phone );
                            SharedPreferencesUtil.getInstance ( LoginActivity.this ).setDate ( "user_id", userInfo.getUser_id () );
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "登录", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {
                        if (isOk) {
                            SharedPreferencesUtil.getInstance ( LoginActivity.this ).setTime ( "loginTime",
                                    System.currentTimeMillis () );
                            Log.e ( "登录时间", "" + System.currentTimeMillis () );
                            startActivity ( new Intent ( LoginActivity.this, MainActivity.class ) );
                            finish ();
                        }
                    }
                } );
    }
}
