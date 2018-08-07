package com.six.carrental;


import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;


/**
 * @author Lian
 * @create 2018/8/2
 * @Describe 选择器
 */
public class myDatePicker {
    private Context mContext;
    private BottomSheetDialog mBottomSheetDialog;
    private View dateView, timeView;
    private getResult mGetResult;
    private String freeTime, freeDate = "";
    private String startH = null, endH = null, startM = null, endM = null;

    public myDatePicker(Context context, getResult getResult) {
        this.mContext = context;
        this.mGetResult = getResult;
        this.mBottomSheetDialog = new BottomSheetDialog ( context );
    }

    public void showDatePicker() {
        this.dateView = LayoutInflater.from ( mContext ).inflate ( R.layout.dialog_select_date, null, false );
        mBottomSheetDialog.setContentView ( dateView );
        iniDatePicker ( dateView );
        mBottomSheetDialog.show ();
    }

    private void iniDatePicker(View dateView) {
        final DatePicker datePicker = dateView.findViewById ( R.id.publish_datePicker );
        TextView cancel = dateView.findViewById ( R.id.publish_cancel );
        cancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss ();
            }
        } );
        TextView confirm = dateView.findViewById ( R.id.publish_confirm );
        confirm.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String date = datePicker.getYear () + "-" + datePicker.getMonth () + "-" + datePicker.getDayOfMonth ();
                mGetResult.getDate ( date );
                mBottomSheetDialog.dismiss ();
            }
        } );


    }

    public void showTimePicker() {
        this.timeView = LayoutInflater.from ( mContext ).inflate ( R.layout.dialog_select_time, null, false );
        mBottomSheetDialog.setContentView ( timeView );
        iniTimePicker ();
        mBottomSheetDialog.show ();
    }

    private void iniTimePicker() {
        final TimePicker start = timeView.findViewById ( R.id.reservation_startTime );
        start.setIs24HourView ( true );
        start.setOnTimeChangedListener ( new TimePicker.OnTimeChangedListener () {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                freeTime = hourOfDay + ":" + minute;
            }
        } );

        TimePicker end = timeView.findViewById ( R.id.reservation_endTime );
        end.setIs24HourView ( true );
        end.setOnTimeChangedListener ( new TimePicker.OnTimeChangedListener () {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                freeTime = freeTime + "  " + hourOfDay + ":" + minute;
            }
        } );
        TextView cancel = timeView.findViewById ( R.id.reservation_cancel );
        cancel.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss ();
            }
        } );
        TextView confirm = timeView.findViewById ( R.id.reservation_confirm );
        confirm.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                mGetResult.getTime ( freeTime );
                mBottomSheetDialog.dismiss ();
            }
        } );

    }


    public interface getResult {
        void getDate(String string);

        void getTime(String string);
    }
}
