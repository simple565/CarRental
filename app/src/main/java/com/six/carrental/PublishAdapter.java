package com.six.carrental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.six.carrental.Entity.CarsInfo;

import java.util.List;

/**
 * @author Lian
 * @create 2018/7/28
 * @Describe 我的发布列表数据适配器
 */
public class PublishAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    List <CarsInfo.ResultBean> mResultBeanList;

    public PublishAdapter(Context context, List <CarsInfo.ResultBean> mResultBeanList) {
        mLayoutInflater = LayoutInflater.from ( context );
        this.mResultBeanList = mResultBeanList;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder ();
            convertView = mLayoutInflater.inflate ( R.layout.item_publish, null );
            viewHolder.tv_license = convertView.findViewById ( R.id.item_publish_tv_licensePlates );
            viewHolder.tv_type = convertView.findViewById ( R.id.item_publish_tv_carType );
            viewHolder.tv_time = convertView.findViewById ( R.id.item_publish_tv_freeTime );
            viewHolder.tv_status = convertView.findViewById ( R.id.item_publish_tv_status );
            convertView.setTag ( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        CarsInfo.ResultBean resultBean = mResultBeanList.get ( position );
        String date = resultBean.getFree_date () + " " + resultBean.getStart_time () + " " + resultBean.getEnd_time ();
        viewHolder.tv_time.setText ( String.format ( "空闲时间:%s", date ) );
        viewHolder.tv_license.setText ( String.format ( "车牌:%s", resultBean.getUnion () ) );
        viewHolder.tv_type.setText ( String.format ( "车型:%s", resultBean.getBrand () ) );

        if (resultBean.getStatus ().equals ( "1" ) && resultBean.getAccept ().equals ( "0" )) {
            viewHolder.tv_status.setText ( R.string.reserved );
        } else if (resultBean.getStatus ().equals ( "1" ) && resultBean.getAccept ().equals ( "1" )) {
            viewHolder.tv_status.setText ( R.string.doing );
            viewHolder.tv_status.setBackgroundResource ( R.drawable.btn_orange_bg );
        } else if (resultBean.getStatus ().equals ( "2" )) {
            viewHolder.tv_status.setText ( R.string.end );
            viewHolder.tv_status.setBackgroundResource ( R.drawable.btn_gray_bg );
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tv_license;
        TextView tv_type;
        TextView tv_time;
        TextView tv_status;

    }

}
