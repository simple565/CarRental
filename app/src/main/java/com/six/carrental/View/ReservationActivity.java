package com.six.carrental.View;

import android.support.v7.widget.Toolbar;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.six.carrental.DataManager;
import com.six.carrental.Entity.ResultEntity;
import com.six.carrental.R;
import com.six.carrental.Util.RxExceptionUtil;
import com.six.carrental.Util.SharedPreferencesUtil;
import com.six.carrental.myDatePicker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

public class ReservationActivity extends AppCompatActivity implements myDatePicker.getResult {
    private EditText driver, type, license, date, time, phone;
    private String carID, driverName, carType, carLicense, freeDate, freeTime, phoneNum;
    private boolean isOK = false;
    private myDatePicker mMyDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_reservation );
        if (getIntent ().getExtras () != null) {
            carID = getIntent ().getExtras ().getString ( "carId" );
            driverName = getIntent ().getExtras ().getString ( "driver" );
            carType = getIntent ().getExtras ().getString ( "type" );
            carLicense = getIntent ().getExtras ().getString ( "license" );
            freeDate = getIntent ().getExtras ().getString ( "date" );
            freeTime = getIntent ().getExtras ().getString ( "startTime" ) + "  "
                    + getIntent ().getExtras ().getString ( "endTime" );
        }
        phoneNum = SharedPreferencesUtil.getInstance ( this ).getData ( "tel" );
        iniView ();
    }

    private void iniView() {
        Toolbar toolbar = findViewById ( R.id.reservation_toolbar );
        toolbar.setNavigationIcon ( R.drawable.ic_arrow_left );
        toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        } );
        driver = findViewById ( R.id.reservation_driver );
        driver.setText ( driverName );
        license = findViewById ( R.id.reservation_carLicense );
        license.setText ( carLicense );
        type = findViewById ( R.id.reservation_carType );
        type.setText ( carType );
        EditText free = findViewById ( R.id.reservation_free );
        free.setText ( freeTime );
        date = findViewById ( R.id.reservation_date );
        date.setText ( freeDate );
        time = findViewById ( R.id.reservation_time );
        time.setInputType ( InputType.TYPE_NULL );
        phone = findViewById ( R.id.reservation_phone );
        phone.setText ( phoneNum );
        mMyDatePicker = new myDatePicker ( ReservationActivity.this, this );
        time.setOnFocusChangeListener ( new View.OnFocusChangeListener () {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                mMyDatePicker.showTimePicker ();
            }
        } );


        final Button submit = findViewById ( R.id.reservation_submit );
        submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //检查选择的日期是否符合空闲时间
                //传递数据
                submitData ();

            }
        } );

    }

    private void submitData() {
        Map <String, String> order = new HashMap <> ();
        order.put ( "user_id", SharedPreferencesUtil.getInstance ( this ).getData ( "user_id" ) );
        order.put ( "user_phone", SharedPreferencesUtil.getInstance ( this ).getData ( "tel" ) );
        order.put ( "car_id", carID );
        order.put ( "order_date", freeDate );
        order.put ( "order_time", freeTime );
        Log.e ( "测试", order.toString () );
        DataManager.getInstance ().getApiService ()
                .orderCar ( order )
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <ResultEntity> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e ( "租借车辆", "上传订单中" );
                    }

                    @Override
                    public void onNext(ResultEntity resultEntity) {
                        Log.e ( "租借车辆", "返回数据：" + resultEntity.getMessage () );
                        if (resultEntity.getStatus () == 200) {
                            isOK = true;
                            Toast.makeText ( ReservationActivity.this, resultEntity.getMessage (), Toast.LENGTH_SHORT ).show ();
                        } else {
                            Toast.makeText ( ReservationActivity.this, resultEntity.getMessage (), Toast.LENGTH_SHORT ).show ();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "租借车辆", "错误：" + e.getMessage () + RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {
                        Log.e ( "租借车辆", "完成订单" );
                        if (isOK) {
                            finish ();
                        }

                    }
                } );
    }

    @Override
    public void getDate(String string) {

    }

    @Override
    public void getTime(String string) {
        time.setText ( string );
    }
}
