package com.six.carrental.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.R;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SetInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_set_info );
        iniView ();
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.set_info_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        Button button = findViewById ( R.id.set_info_submit );
        button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                submitData ();
            }
        } );
    }

    private void submitData() {
        Map <String, String> userInfo = new HashMap <> ();
        userInfo.put ( "user_id", "9" );
        userInfo.put ( "name", "admin" );
        userInfo.put ( "sex", "female" );
        DataManager.getInstance ().getApiService ()
                .updateUserinfo ( userInfo )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "修改信息", "提交信息" );
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Log.e ( "修改信息", resultEntity.getMessage () );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "修改信息", "错误信息：" + e.getMessage () );

                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }
}
