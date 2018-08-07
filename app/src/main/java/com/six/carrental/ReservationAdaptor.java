package com.six.carrental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.six.carrental.Entity.ReservationEntity;

import java.util.Calendar;
import java.util.List;

/**
 * @author Lian
 * @create 2018/8/1
 * @Describe 我的预约数据适配器
 */
public class ReservationAdaptor extends BaseAdapter {
    private List <ReservationEntity.ResultBean> mReservationEntities;
    private int[] favicon = {R.drawable.carousel_one, R.drawable.car_two,
            R.drawable.car_three, R.drawable.car_four, R.drawable.car_five};
    private LayoutInflater mLayoutInflater;

    public ReservationAdaptor(Context context) {
        this.mLayoutInflater = LayoutInflater.from ( context );
    }

    public ReservationAdaptor(Context context, List <ReservationEntity.ResultBean> reservationEntities) {
        this.mLayoutInflater = LayoutInflater.from ( context );
        mReservationEntities = reservationEntities;
    }

    @Override
    public int getCount() {
        return mReservationEntities == null ? 5 : mReservationEntities.size ();
    }

    @Override
    public ReservationEntity.ResultBean getItem(int position) {
        return mReservationEntities.get ( position );
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder ();
            convertView = mLayoutInflater.inflate ( R.layout.item_my_reservation, null );
            viewHolder.status = convertView.findViewById ( R.id.item_reservation_tv_status );
            viewHolder.icon = convertView.findViewById ( R.id.item_reservation_iv_icon );
            viewHolder.name = convertView.findViewById ( R.id.item_reservation_tv_name );
            viewHolder.license = convertView.findViewById ( R.id.item_reservation_tv_licensePlates );
            viewHolder.type = convertView.findViewById ( R.id.item_reservation_tv_carType );
            viewHolder.free = convertView.findViewById ( R.id.item_reservation_tv_freeTime );
            viewHolder.rentalTime = convertView.findViewById ( R.id.item_reservation_tv_rentalTime );
            viewHolder.rentalPhone = convertView.findViewById ( R.id.item_reservation_tv_rentalPhone );
            viewHolder.mRelativeLayout = convertView.findViewById ( R.id.item_reservation_rl_info );
            convertView.setTag ( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        ReservationEntity.ResultBean resultBean = mReservationEntities.get ( position );
        viewHolder.icon.setBackgroundResource ( favicon[position%5] );
        viewHolder.name.setText ( resultBean.getCar_message ().getDriver_name () );
        viewHolder.license.setText ( String.format ( "车牌:%s", resultBean.getCar_message ().getUnion () ) );
        viewHolder.type.setText ( String.format ( "车型:%s", resultBean.getCar_message ().getBrand () ) );
        viewHolder.rentalPhone.setText ( String.format ( "预留电话:%s", resultBean.getUser_phone () ) );
        viewHolder.rentalTime.setText ( String.format ( "预约时间:%s  %s", resultBean.getOrder_date (), resultBean.getOrder_time () ) );
        if (resultBean.getCar_message ().getStatus ().equals ( "2" )) {
            viewHolder.status.setBackgroundResource ( R.drawable.btn_gray_bg );
            viewHolder.mRelativeLayout.setVisibility ( View.GONE );
            viewHolder.status.setText ( R.string.end );
        }else if (resultBean.getCar_message ().getAccept ().equals ( "1" )){
            viewHolder.status.setText ( R.string.doing);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView status;
        ImageView icon;
        TextView name;
        TextView type;
        TextView license;
        TextView free;
        TextView rentalTime;
        TextView rentalPhone;
        RelativeLayout mRelativeLayout;

    }
}
