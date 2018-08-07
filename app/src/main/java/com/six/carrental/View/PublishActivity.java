package com.six.carrental.View;

import android.content.Intent;
import android.media.MediaExtractor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.CarsInfo;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.PublishAdapter;
import com.six.carrental.R;
import com.six.carrental.Util.RxExceptionUtil;
import com.six.carrental.Util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableGroupBy;
import io.reactivex.schedulers.Schedulers;

public class PublishActivity extends AppCompatActivity {
    private List <CarsInfo.ResultBean> mResultBeanList = new ArrayList <> ();
    private PublishAdapter mPublishAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_publish );
        iniData ();
        iniView ();
    }

    private void iniData() {
        DataManager.getInstance ().getApiService ()
                .getPublished ( SharedPreferencesUtil.getInstance ( this ).getData ( "user_id" ) )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <CarsInfo> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CarsInfo resultEntity) {
                        if (resultEntity.getStatus () == 200) {
                            mResultBeanList = resultEntity.getResult ();
                        } else {
                            Toast.makeText ( PublishActivity.this, "获取数据失败", Toast.LENGTH_SHORT ).show ();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "获取已发布信息", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {
                        mPublishAdapter = new PublishAdapter ( PublishActivity.this, mResultBeanList );
                        mListView.setAdapter ( mPublishAdapter );
                    }
                } );
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.publish_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        mListView = findViewById ( R.id.publish_lv_order );
        mListView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Intent intent = new Intent ( PublishActivity.this, ShowOrderActivity.class );
                intent.putExtra ( "flag", "0" );
                intent.putExtra ( "status", mResultBeanList.get ( position ).getStatus () );
                intent.putExtra ( "accept", mResultBeanList.get ( position ).getAccept () );
                intent.putExtra ( "car_id", mResultBeanList.get ( position ).getId () );
                if (mResultBeanList.get ( position ).getStatus ().equals ( "0" )){
                    //车辆未被预约，没有订单编号则传递车辆信息
                    intent.putExtra ( "driver", mResultBeanList.get ( position ).getDriver_name () );
                    intent.putExtra ( "license", mResultBeanList.get ( position ).getUnion () );
                    intent.putExtra ( "type", mResultBeanList.get ( position ).getBrand () );
                    intent.putExtra ( "free_date", mResultBeanList.get ( position ).getFree_date () );
                    intent.putExtra ( "start_time", mResultBeanList.get ( position ).getStart_time () );
                    intent.putExtra ( "end_time", mResultBeanList.get ( position ).getEnd_time () );
                }
                startActivityForResult ( intent, 101 );
            }
        } );
        FloatingActionButton floatingActionButton = findViewById ( R.id.publish_button_add );
        floatingActionButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivityForResult ( new Intent ( PublishActivity.this, SetPublishActivity.class ), 100 );
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );
        iniData ();
    }
}
