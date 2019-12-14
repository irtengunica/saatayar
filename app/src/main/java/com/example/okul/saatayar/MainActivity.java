package com.example.okul.saatayar;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    private Button saatsec2txt = null;
    private TimePicker timePicker;
    private int hour;
    private int minute;

    static final int TIME_DIALOG_ID = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saatsec2txt = (Button) findViewById(R.id.txtTime);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        saatsec2txt.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));

        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        saatsec2txt.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.txtTime:
                    showDialog(TIME_DIALOG_ID);
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hour, minute,true);
        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =  new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            hour = selectedHour;
            minute = selectedMinute;
            saatsec2txt.setText(new StringBuilder().append(padding_str(hour)).append(":").append(padding_str(minute)));
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
    };

    private static String padding_str(int c)
    {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
