package com.six.carrental.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.CarsInfo;
import com.six.carrental.Entity.OrderEntity;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.R;
import com.six.carrental.Util.RxExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShowOrderActivity extends AppCompatActivity {
    private String status, accept, carID, flag, orderID;
    private TextView driver, license, type, free, phone, date, time;
    private TextView titleP, titleD, titleT;
    private OrderEntity mOrderEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_show_order );
        if (getIntent ().getExtras () != null) {
            flag = getIntent ().getExtras ().getString ( "flag" );
            status = getIntent ().getExtras ().getString ( "status" );
            accept = getIntent ().getExtras ().getString ( "accept" );
            carID = getIntent ().getExtras ().getString ( "car_id" );
        }
        if (!status.equals ( "0" ) && status != null) {
            iniData ();
        }
        iniView ();
    }

    private void iniData() {
        //根据carID获取订单ID
        DataManager.getInstance ().getApiService ()
                .getOrder ( carID )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <OrderEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "获取订单信息", "连接服务器...." );
                    }

                    @Override
                    public void onNext(OrderEntity orderEntity) {
                        orderID = orderEntity.getResult ().getId ();
                        mOrderEntity = orderEntity;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "获取订单信息", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {
                        driver.setText ( mOrderEntity.getCarinfo ().getDriver_name () );
                        license.setText ( mOrderEntity.getCarinfo ().getUnion () );
                        type.setText ( mOrderEntity.getCarinfo ().getBrand () );
                        String temp = mOrderEntity.getCarinfo ().getFree_date () + " "
                                + mOrderEntity.getCarinfo ().getStart_time () + "  "
                                + mOrderEntity.getCarinfo ().getEnd_time ();
                        free.setText ( temp );
                        phone.setText ( mOrderEntity.getResult ().getUser_phone () );
                        date.setText ( mOrderEntity.getResult ().getOrder_date () );
                        time.setText ( mOrderEntity.getResult ().getOrder_time () );
                    }
                } );
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.show_order_toolBar );
        setSupportActionBar ( toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        Button button = findViewById ( R.id.show_order_operate );
        driver = findViewById ( R.id.detail_driver );
        license = findViewById ( R.id.detail_license );
        type = findViewById ( R.id.detail_type );
        free = findViewById ( R.id.detail_freeTime );
        phone = findViewById ( R.id.detail_phone );
        date = findViewById ( R.id.detail_rentalDay );
        time = findViewById ( R.id.detail_rentalTime );
        titleP = findViewById ( R.id.detail_title_phone );
        titleD = findViewById ( R.id.detail_title_date );
        titleT = findViewById ( R.id.detail_title_time );
        if (flag != null && status != null && accept != null) {
            if (flag.equals ( "0" )) {
                if (status.equals ( "0" )) {
                    //司机取消订单
                    driver.setText ( getIntent ().getExtras ().getString ( "driver" ) );
                    license.setText ( getIntent ().getExtras ().getString ( "license" ) );
                    type.setText ( getIntent ().getExtras ().getString ( "type" ) );
                    String temp = getIntent ().getExtras ().getString ( "free_date" )+"  "
                            + getIntent ().getExtras ().getString ( "start_time" )+"  "
                            + getIntent ().getExtras ().getString ( "end_time" );
                    free.setText ( temp );
                    titleP.setVisibility ( View.GONE );
                    titleD.setVisibility ( View.GONE );
                    titleT.setVisibility ( View.GONE );
                    phone.setVisibility ( View.GONE );
                    date.setVisibility ( View.GONE );
                    time.setVisibility ( View.GONE );
                    button.setText ( R.string.cancel );
                    button.setOnClickListener ( new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            cancelPublish ();
                            finish ();
                        }
                    } );
                } else if (accept.equals ( "1" )) {
                    //司机完成订单
                    button.setText ( "完成订单" );
                    button.setBackgroundResource ( R.drawable.btn_orange_bg );
                    button.setOnClickListener ( new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            finishPublish ();
                            finish ();
                        }
                    } );
                } else {
                    //司机接收订单
                    button.setOnClickListener ( new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            acceptOrder ();
                            finish ();
                        }
                    } );
                }
            } else if (flag.equals ( "1" )) {
                if (status.equals ( "1" ) && accept.equals ( "0" )) {
                    //用户取消预约
                    button.setText ( R.string.cancel );
                    button.setOnClickListener ( new View.OnClickListener () {
                        @Override
                        public void onClick(View v) {
                            cancelOrder ();
                            finish ();
                        }
                    } );
                } else {
                    //乘客操作
                    button.setVisibility ( View.GONE );
                }
            }

        }
    }

    private void cancelOrder() {
        DataManager.getInstance ().getApiService ()
                .cancelOrder ( orderID )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "取消订单", "" );
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Toast.makeText ( ShowOrderActivity.this, resultEntity.getMessage (), Toast.LENGTH_SHORT ).show ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "取消订单", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    private void cancelPublish() {
        DataManager.getInstance ().getApiService ()
                .cancelPublishMes ( carID )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "取消发布", "" );
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Toast.makeText ( ShowOrderActivity.this, resultEntity.getMessage (), Toast.LENGTH_SHORT ).show ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "取消发布", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    private void acceptOrder() {
        DataManager.getInstance ().getApiService ()
                .acceptOrder ( orderID )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "接收订单", "接单" );
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Toast.makeText ( ShowOrderActivity.this, resultEntity.getMessage (), Toast.LENGTH_SHORT ).show ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "接收订单", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }

    private void finishPublish() {
        DataManager.getInstance ().getApiService ()
                .finishOrder ( orderID )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "完成订单", "" );
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Toast.makeText ( ShowOrderActivity.this, resultEntity.getMessage (), Toast.LENGTH_SHORT ).show ();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "完成订单", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {

                    }
                } );
    }
}
