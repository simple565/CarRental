package com.six.carrental.View;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.R;
import com.six.carrental.Util.SharedPreferencesUtil;
import com.six.carrental.myDatePicker;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SetPublishActivity extends AppCompatActivity implements myDatePicker.getResult {
    private EditText driver, type, license, freeTime, freeDate;
    private boolean isOk = false;
    private myDatePicker mMyDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_set_publish );
        iniView ();
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.set_publish_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        driver = findViewById ( R.id.set_publish_driver );
        type = findViewById ( R.id.set_publish_type );
        license = findViewById ( R.id.set_publish_license );
        freeTime = findViewById ( R.id.set_publish_freeTime );
        freeDate = findViewById ( R.id.set_publish_freeDate );
        mMyDatePicker = new myDatePicker ( SetPublishActivity.this, this );
        freeTime.setOnFocusChangeListener ( new View.OnFocusChangeListener () {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mMyDatePicker.showTimePicker ();
            }
        } );


        freeDate.setOnFocusChangeListener ( new View.OnFocusChangeListener () {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mMyDatePicker.showDatePicker ();
            }
        } );

        Button submit = findViewById ( R.id.set_publish_submit );
        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                setPublish ();
            }
        } );

    }

    private void setPublish() {
        Map <String, String> map = new HashMap <> ();
        map.put ( "user_id", SharedPreferencesUtil.getInstance ( this ).getData ( "user_id" ) );
        map.put ( "driver_name", driver.getText ().toString () );
        map.put ( "brand", type.getText ().toString () );
        map.put ( "union", license.getText ().toString () );
        map.put ( "free_date", "2018-07-30" );
        map.put ( "start_time", "07:30" );
        map.put ( "end_time", "19:30" );
        DataManager.getInstance ()
                .getApiService ()
                .publishMessage ( map )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {//String
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "测试", "开始获取数据" );
                    }

                    @Override
                    public void onNext(ResultEntity s) {
                        if (s.getStatus () == 200) {
                            isOk = true;
                        }
                        Toast.makeText ( SetPublishActivity.this, s.getMessage (), Toast.LENGTH_SHORT ).show ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "测试", "获取数据错误" + e.getMessage () );
                    }

                    @Override
                    public void onComplete() {
                        Log.e ( "测试", "获取数据完成" );
                        if (isOk) {
                            Intent intent = new Intent ( SetPublishActivity.this, PublishActivity.class );
                            //返回数据给我的发布页面
                            setResult ( 100, intent );
                            finish ();
                        }

                    }
                } );
    }

    @Override
    public void getDate(String string) {
        freeDate.setText ( string );
    }

    @Override
    public void getTime(String string) {
        freeTime.setText ( string );
    }
}
