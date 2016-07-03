package com.apps.serghei.sleeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.apps.serghei.sleeper.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView day = (TextView) findViewById(R.id.current_day_text_view);
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMMM d");
        String date = format.format(rightNow.getTime());
        day.setText(date);

    }
    public void riseNow(View view) {
    }
    public void sleepNow(View view) {
    }
    public void showYesterday(View view) {
    }
    public void showTomorrow(View view) {
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
