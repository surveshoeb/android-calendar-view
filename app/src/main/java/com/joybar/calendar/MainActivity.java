package com.joybar.calendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import static com.joybar.librarycalendar.fragment.CalendarViewFragment.setUnSelecteable;


public class MainActivity extends AppCompatActivity implements
        CalendarViewPagerFragment.OnPageChangeListener,
        CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener,
        CalendarViewFragment.OnDateLoaded{

    private TextView tv_date;
    private boolean isChoiceModelSingle = false;
    private List<CalendarDate> mListDate = new ArrayList<>();
    Fragment fragment;

    private String startRange = "20-11-2017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_date = (TextView) findViewById(R.id.tv_date);
        fragment = CalendarViewPagerFragment.newInstance(isChoiceModelSingle);
        initFragment();

    }

    private void initFragment(){
       FragmentManager fm = getSupportFragmentManager();
       FragmentTransaction tx = fm.beginTransaction();
       // Fragment fragment = new CalendarViewPagerFragment();
       tx.replace(R.id.fl_content, fragment);
       tx.commit();
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_im, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_single:
                isChoiceModelSingle = true;
                initFragment();
                break;
            case R.id.menu_multi:
                isChoiceModelSingle = false;
                initFragment();
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    public void onDateClick(CalendarDate calendarDate) {

       if (!calendarDate.isUnSelectable()) {
           int year = calendarDate.getSolar().solarYear;
           int month = calendarDate.getSolar().solarMonth;
           int day = calendarDate.getSolar().solarDay;
           if (isChoiceModelSingle) {
               tv_date.setText(year + "-" + month + "-" + day);
           } else {
               //System.out.println(calendarDate.getSolar().solarDay);
               mListDate.add(calendarDate);
               tv_date.setText(listToString(mListDate));
           }
       }

    }

    @Override
    public void onDateCancel(CalendarDate calendarDate) {
        int count = mListDate.size();
        for (int i = 0; i < count; i++) {
            CalendarDate date = mListDate.get(i);
            if (date.getSolar().solarDay == calendarDate.getSolar().solarDay) {
                mListDate.remove(i);
                break;
            }
        }
        tv_date.setText(listToString(mListDate));
    }

    @Override
    public void onPageChange(int year, int month) {
        tv_date.setText(year + "-" + month);
        mListDate.clear();
    }

    private static String listToString(List<CalendarDate> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (CalendarDate date : list) {
            stringBuffer.append(date.getSolar().solarYear + "-" + date.getSolar().solarMonth + "-" + date.getSolar().solarDay).append(" ");
        }
        return stringBuffer.toString();
    }

    @Override
    public void onDateLoaded() {
        setUnSelecteable("17-11-2017");
        setUnSelecteable("20-11-2017");
    }


    List<CalendarDate> finalList = new ArrayList<>();

    @Override
    public List<CalendarDate> outOffRange(List<CalendarDate> calendarDateList) {
        List<CalendarDate> updatedList = calendarDateList;

        int startYear = Integer.parseInt(startRange.split("-")[2]);
        int startMonth = Integer.parseInt(startRange.split("-")[1]);
        int startDate = Integer.parseInt(startRange.split("-")[0]);


        for (int i = 0; i < calendarDateList.size(); i++) {
            int year = updatedList.get(i).getSolar().solarYear;
            int month = updatedList.get(i).getSolar().solarMonth;
            int day = updatedList.get(i).getSolar().solarDay;

            int j = 0;

            if (startYear == year || (j<28 && j >0)) {
                if (startMonth == month || (j<28 && j >0)) {
                    if (startDate <= day || (j<28 && j >0)) {
                        for (; j < 28; j++) {

                            if (i < updatedList.size()) {
                                updatedList.get(i).setInRange(true);
                                finalList.add(updatedList.get(i));
                            }
                            else
                                break;
                            i++;
                        }
                    }
                }
            }
        }

       return updatedList;
    }

    @Override
    public List<CalendarDate> finalList(List<CalendarDate> finalList) {
        return finalList;
    }


}