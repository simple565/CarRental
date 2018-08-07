package com.six.carrental;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.carrental.Entity.CarsInfo;
import com.six.carrental.View.ReservationActivity;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;

/**
 * @author Lian
 * @create 2018/7/27
 * @Describe 首页可预约车辆数据适配器
 */
public class CarAdapter extends BaseAdapter {
    private Context mContext;
    private DataCallback mDataCallback;
    private List <CarsInfo.ResultBean> mResultBeanList;
    private int[] favicon = {R.drawable.carousel_one, R.drawable.car_two,
            R.drawable.car_three, R.drawable.car_four, R.drawable.car_five};


    public CarAdapter(Context context, List <CarsInfo.ResultBean> list) {
        super ();
        this.mContext = context;
        this.mResultBeanList = list;
    }

    public void setDataCallback(DataCallback dataCallback) {
        this.mDataCallback = dataCallback;
    }

    @Override
    public int getCount() {
        return mResultBeanList == null ? 0 : mResultBeanList.size ();
    }

    @Override
    public CarsInfo.ResultBean getItem(int position) {
        return mResultBeanList.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder ();
            convertView = LayoutInflater.from ( mContext ).inflate ( R.layout.item_lv_carrental, null );
            viewHolder.carPic = convertView.findViewById ( R.id.item_carRental_iv_car );
            viewHolder.name = convertView.findViewById ( R.id.item_carRental_tv_name );
            viewHolder.licensePlate = convertView.findViewById ( R.id.item_carRental_tv_licensePlates );
            viewHolder.carType = convertView.findViewById ( R.id.item_carRental_tv_carType );
            viewHolder.freeTime = convertView.findViewById ( R.id.item_carRental_tv_freeTime );
            viewHolder.mButton = convertView.findViewById ( R.id.item_carRental_btn_reservation );
            convertView.setTag ( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        CarsInfo.ResultBean resultBean = mResultBeanList.get ( position );
        viewHolder.carPic.setBackgroundResource ( favicon[position % 5] );
        viewHolder.name.setText ( resultBean.getDriver_name () );
        viewHolder.licensePlate.setText ( String.format ( "车牌:%s", resultBean.getUnion () ) );
        viewHolder.carType.setText ( String.format ( "车型:%s", resultBean.getBrand () ) );
        String time = resultBean.getFree_date () + "  " + resultBean.getStart_time () + "  " + resultBean.getEnd_time ();
        viewHolder.freeTime.setText ( String.format ( "空闲时间:%s", time ) );
        viewHolder.mButton.setTag ( position );
        viewHolder.mButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mDataCallback.click ( v );
            }
        } );
        return convertView;
    }


    public interface DataCallback {
        void click(View view);
    }

    static class ViewHolder {
        ImageView carPic;
        TextView name;
        TextView licensePlate;
        TextView carType;
        TextView freeTime;
        Button mButton;
    }
}
