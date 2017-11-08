package com.joybar.librarycalendar.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joybar.librarycalendar.R;
import com.joybar.librarycalendar.data.CalendarDate;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by joybar on 2/24/16.
 */
public class CalendarGridViewAdapter extends BaseAdapter {

    private List<CalendarDate> mListData = new ArrayList<>();
    private Context context;

    public CalendarGridViewAdapter(Context context,List<CalendarDate> mListData) {
        this.context = context;
        this.mListData = mListData;
    }

    public List<CalendarDate> getListData() {
        return mListData;
    }


    public int getCount() {
        return mListData.size();
    }


    public Object getItem(int position) {
        return position;
    }



    public long getItemId(int position) {
        return position;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        CalendarDate calendarDate = mListData.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_calendar, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_day.setText(calendarDate.getSolar().solarDay+"");

        if (mListData.get(position).isInRange()) {
            if (mListData.get(position).isInThisMonth()) {
                viewHolder.tv_day.setTextColor(Color.parseColor("#000000"));
            } else {
                viewHolder.tv_day.setTextColor(Color.parseColor("#D7D7D7"));
            }
        } else {
            viewHolder.tv_day.setTextColor(Color.parseColor("#D7D7D7"));
        }

        if (calendarDate.isUnSelectable())
            viewHolder.column.setBackground(context.getResources().getDrawable(R.drawable.disabled));

        return convertView;
    }


    public static class ViewHolder {
        private LinearLayout column;
        private TextView tv_day;
        public ViewHolder(View itemView) {
            column = (LinearLayout) itemView.findViewById(R.id.column);
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
        }

    }



}

