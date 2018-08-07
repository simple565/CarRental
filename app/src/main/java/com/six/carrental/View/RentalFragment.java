package com.six.carrental.View;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.oragee.banners.BannerView;
import com.six.carrental.CarAdapter;
import com.six.carrental.DataManager;
import com.six.carrental.Entity.CarsInfo;
import com.six.carrental.R;
import com.six.carrental.Util.RxExceptionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RentalFragment extends Fragment {
    private List <CarsInfo.ResultBean> mResultBeanList = new ArrayList <> ();
    private ListView mListView;
    private CarAdapter mCarAdapter;
    private int[] views = {R.drawable.carousel_one, R.drawable.carousel_two, R.drawable.carousel_three};
    private int index;


    public RentalFragment() {
        super ();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_rental, container, false );
        iniData ();
        iniView ( view );
        return view;
    }

    private void iniData() {
        DataManager.getInstance ().getApiService ()
                .getCanCar ()
                .subscribeOn ( Schedulers.io () )
                .observeOn ( AndroidSchedulers.mainThread () )
                .subscribe ( new Observer <CarsInfo> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CarsInfo carsInfo) {
                        if (carsInfo.getStatus () != 200) {
                            Toast.makeText ( getActivity (), "获取数据失败", Toast.LENGTH_SHORT ).show ();
                        } else {
                            Log.e ( "首页数据", "长度:" + carsInfo.getLength () );
                            mResultBeanList = carsInfo.getResult ();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e ( "测试", RxExceptionUtil.exceptionHandler ( e ) );
                    }

                    @Override
                    public void onComplete() {
                        mCarAdapter = new CarAdapter ( getActivity (), mResultBeanList );
                        mCarAdapter.setDataCallback ( new CarAdapter.DataCallback () {
                            @Override
                            public void click(View view) {
                                index = (int) view.getTag ();
                                Intent intent = new Intent ( getActivity (), ReservationActivity.class );
                                intent.putExtra ( "carId", mResultBeanList.get ( index ).getId () );
                                Log.e ( "测试", "test" +  mResultBeanList.get ( index ).getId ());
                                intent.putExtra ( "driver", mResultBeanList.get ( index ).getDriver_name () );
                                intent.putExtra ( "license", mResultBeanList.get ( index ).getUnion () );
                                intent.putExtra ( "type", mResultBeanList.get ( index ).getBrand () );
                                intent.putExtra ( "date", mResultBeanList.get ( index ).getFree_date () );
                                intent.putExtra ( "startTime", mResultBeanList.get ( index ).getStart_time () );
                                intent.putExtra ( "endTime", mResultBeanList.get ( index ).getEnd_time () );
                                startActivity ( intent );
                            }
                        } );
                        mListView.setAdapter ( mCarAdapter );
                        Log.e ( "测试", "请求数据完成" );
                    }
                } );

    }

    private void iniView(final View view) {
        Log.e ( "测试", "初始化页面" + SystemClock.currentThreadTimeMillis () );
        BannerView bannerView = view.findViewById ( R.id.rental_banner );
        List <View> mViews = new ArrayList <> ();
        for (int i = 0; i < views.length; i++) {
            ImageView imageView = new ImageView ( getActivity () );
            imageView.setBackgroundResource ( views[i] );
            mViews.add ( imageView );
        }
        bannerView.startLoop ( true );
        bannerView.setViewList ( mViews );
        mListView = view.findViewById ( R.id.rental_lv_showCars );

    }



}
