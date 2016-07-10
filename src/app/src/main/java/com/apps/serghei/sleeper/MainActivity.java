package com.apps.serghei.sleeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.apps.serghei.sleeper.MESSAGE";

    protected List<DayModel> days = new ArrayList<DayModel>() {
        {
            add(new DayModel() {
                {
                    CurrentDay = new GregorianCalendar(2016, 6, 1);
                    RiseTime = new GregorianCalendar(2016, 6, 1, 8, 50);
                    SleepTime = new GregorianCalendar(2016, 6, 1, 21, 38);
                    HoursSlept = 10.0;
                    Comments = "good";
                    Rating = 2;
                }
            });
            add(new DayModel() {
                {
                    CurrentDay = new GregorianCalendar(2016, 6, 2);
                    RiseTime = new GregorianCalendar(2016, 6, 2, 9, 0);
                    SleepTime = new GregorianCalendar(2016, 6, 2, 22, 45);
                    HoursSlept = 11.0;
                    Comments = "good";
                    Rating = 2;
                }
            });
            add(new DayModel() {
                {
                    CurrentDay = new GregorianCalendar(2016, 6, 3);
                    RiseTime = new GregorianCalendar(2016, 6, 3, 9, 50);
                    SleepTime = new GregorianCalendar(2016, 6, 3, 21, 15);
                    HoursSlept = 8.5;
                    Comments = "midnight accident, good";
                    Rating = 2;
                }
            });
            add(new DayModel() {
                {
                    CurrentDay = new GregorianCalendar(2016, 6, 4);
                    RiseTime = new GregorianCalendar(2016, 6, 4, 8, 50);
                    HoursSlept = 10.5;
                    Comments = "good";
                    Rating = 2;
                }
            });
        }
    };

    protected int dayIndex = 0;
    protected String text;

    protected SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMMM d");
    protected SimpleDateFormat hourFormat = new SimpleDateFormat("h:mm a");

    protected TextView text_day;
    protected Button button_rise;
    protected TextView text_rise;
    protected Button button_sleep;
    protected TextView text_sleep;
    protected TextView text_hoursslept;
    protected TextView text_comments;
    protected ToggleButton button_bad;
    protected ToggleButton button_ok;
    protected ToggleButton button_good;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views for performance
        text_day = (TextView) findViewById(R.id.current_day_text_view);
        button_rise = (Button) findViewById(R.id.button_rise);
        text_rise = (TextView) findViewById(R.id.rise_time);
        button_sleep = (Button) findViewById(R.id.button_sleep);
        text_sleep = (TextView) findViewById(R.id.sleep_time);
        text_hoursslept = (TextView) findViewById(R.id.hours_slept);
        text_comments = (TextView) findViewById(R.id.comments);
        button_bad = (ToggleButton) findViewById(R.id.button_bad);
        button_ok = (ToggleButton) findViewById(R.id.button_ok);
        button_good = (ToggleButton) findViewById(R.id.button_good);

        DisplayDay(days.get(dayIndex));
    }

    public void DisplayDay(){
        DisplayDay(null);
    }

    public void SetTopDate(Calendar currentDate){
        if (currentDate == null) {
            currentDate = Calendar.getInstance();
        }
        text = dateFormat.format(currentDate.getTime());
        text_day.setText(text);
    }

    public void SetRiseState(Calendar riseTime){
        if(riseTime == null) {
            button_rise.setVisibility(View.VISIBLE);
            text_rise.setVisibility(View.GONE);
        }else{
            button_rise.setVisibility(View.GONE);
            text_rise.setVisibility(View.VISIBLE);
            text = hourFormat.format(riseTime.getTime());
            text_rise.setText(text);
        }
    }

    public void SetSleepState(Calendar sleepTime){
        if(sleepTime == null) {
            button_sleep.setVisibility(View.VISIBLE);
            text_sleep.setVisibility(View.GONE);
        }else{
            button_sleep.setVisibility(View.GONE);
            text_sleep.setVisibility(View.VISIBLE);
            text = hourFormat.format(sleepTime.getTime());
            text_sleep.setText(text);
        }
    }

    public void SetHoursSlept(double hoursSlept){
        if(hoursSlept > 0){
            text = String.format("%.1f", hoursSlept);
            text_hoursslept.setText(text);
        }
    }

    public void SetComments(String comments){
        text_comments.setText(comments);
    }

    public void SetRating(int rating){
        switch(rating){
            case 0:
                SetBadRating();
                break;
            case 1:
                SetOKRating();
                break;
            case 2:
                SetGoodRating();
                break;
        }
    }

    public void SetBadRating(){
        button_bad.setChecked(true);
        button_ok.setChecked(false);
        button_good.setChecked(false);
    }

    public void SetOKRating(){
        button_bad.setChecked(false);
        button_ok.setChecked(true);
        button_good.setChecked(false);
    }

    public void SetGoodRating(){
        button_bad.setChecked(false);
        button_ok.setChecked(false);
        button_good.setChecked(true);
    }

    public void DisplayDay(DayModel day) {
        if (day == null)
            day = new DayModel();

        // set the date at the top to current date or one supplied
        SetTopDate(day.CurrentDay);

        // set the rise time button and text visibility and date
        SetRiseState(day.RiseTime);

        // set the sleep time button and text visibility and date
        SetSleepState(day.SleepTime);

        // set the hours slept
        SetHoursSlept(day.HoursSlept);

        // set the comments
        SetComments(day.Comments);

        // set the ratings
        SetRating(day.Rating);
    }

    public void riseNow(View view) {
    }

    public void sleepNow(View view) {
    }

    public void showYesterday(View view) {
        if(dayIndex > 0){
            dayIndex-=1;
            DisplayDay(days.get(dayIndex));
        }
    }

    public void showTomorrow(View view) {
        if (dayIndex < days.size() - 1) {
            dayIndex += 1;
            DisplayDay(days.get(dayIndex));
        }
    }

    public void toggleBad(View view) {
    }

    public void toggleOK(View view) {
    }

    public void toggleGood(View view) {
    }
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this,DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
}
