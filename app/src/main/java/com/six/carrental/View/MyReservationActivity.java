package com.six.carrental.View;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.six.carrental.DataManager;
import com.six.carrental.Entity.ReservationEntity;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.R;
import com.six.carrental.ReservationAdaptor;
import com.six.carrental.Util.RxExceptionUtil;
import com.six.carrental.Util.SharedPreferencesUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyReservationActivity extends AppCompatActivity {
    private ReservationEntity mReservationEntity;
    private ListView mListView;
    private ReservationAdaptor mReservationAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_my_reservation );
        iniData ();
        iniView ();
    }

    private void iniData() {
        DataManager.getInstance ().getApiService ()
                .getHistoryOrder ( SharedPreferencesUtil.getInstance ( MyReservationActivity.this ).getData ( "user_id" ) )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ReservationEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "我的预约", "获取数据中" );
                    }

                    @Override
                    public void onNext(ReservationEntity reservationEntity) {
                        if (reservationEntity.getStatus () == 200) {
                            mReservationEntity = reservationEntity;
                        } else {
                            Toast.makeText ( MyReservationActivity.this, "获取数据失败", Toast.LENGTH_SHORT ).show ();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "我的预约", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {
                        mReservationAdaptor = new ReservationAdaptor ( MyReservationActivity.this, mReservationEntity.getResult () );
                        mListView.setAdapter ( mReservationAdaptor );
                    }
                } );

    }

    private void iniView() {
        android.support.v7.widget.Toolbar toolbar = findViewById ( R.id.myReservation_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        TabLayout tabLayout = findViewById ( R.id.myReservation_tab );

        mListView = findViewById ( R.id.myReservation_list );
        mListView.setOnItemClickListener ( new AdapterView.OnItemClickListener () {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Intent intent = new Intent ( MyReservationActivity.this, ShowOrderActivity.class );
                intent.putExtra ( "flag", "1" );
                intent.putExtra ( "status", mReservationEntity.getResult ().get ( position ).getCar_message ().getStatus () );
                intent.putExtra ( "accept", mReservationEntity.getResult ().get ( position ).getCar_message ().getAccept () );
                intent.putExtra ( "car_id", mReservationEntity.getResult ().get ( position ).getCar_id () );
                startActivity ( intent );
            }
        } );
        tabLayout.addOnTabSelectedListener ( new TabLayout.OnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e ( "测试", "选中"+tab.getPosition ());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }


}
