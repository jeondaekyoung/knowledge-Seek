package knowledge_seek.com;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import knowledge_seek.com.alanglang.R;
import knowledge_seek.com.database.Database;

/**
 * Created by sjw on 2015-10-02.
 */
public class AlarmView extends LinearLayout {

    LinearLayout alarm_set;
    private Alarm alarm;

    private TextView set_time;
    private TextView set_day;
    private ImageView set_active;

    boolean activeImage = false;

    public AlarmView(Context context, Alarm mAlarm){
        super(context);

        alarm = mAlarm;

        //Layout Inflation
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.alarm_list, this, true);

        set_time = (TextView)findViewById(R.id.set_time);
        set_time.setText(alarm.getAlarmTimeView());

        set_day = (TextView)findViewById(R.id.day_mon);
        set_day.setText(alarm.getRepeatDaysString());

        alarm_set = (LinearLayout)findViewById(R.id.alarm_set);
        set_active = (ImageView) findViewById(R.id.img_active);
        activeImage = alarm.getAlarmActive();
        //Log.d("-진우- 알람활동 : ", String.valueOf(activeImage));
        if(activeImage){
            //Log.d("-진우- 알람활동1 : ", "켜져있음");
            set_active.setImageResource(R.drawable.ic_alarm_active);
            alarm_set.setAlpha(1);
            activeImage = true;
        } else {
            //Log.d("-진우- 알람활동1 : ", "꺼져있음");
            set_active.setImageResource(R.drawable.ic_alarm_inactive);
            alarm_set.setAlpha(0.6f);
            activeImage = false;
        }

        //알람시계 클릭시 알람 끄기 켜기
        set_active.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activeImage) {
                    //Log.d("-진우- 알람활동2 : ", "끔");
                    set_active.setImageResource(R.drawable.ic_alarm_inactive);
                    alarm_set.setAlpha(0.6f);
                    activeImage = false;
                    getAlarm().setAlarmActive(activeImage);
                } else {
                    //Log.d("-진우- 알람활동2 : ", "켬");
                    set_active.setImageResource(R.drawable.ic_alarm_active);
                    alarm_set.setAlpha(1);
                    activeImage = true;
                    getAlarm().setAlarmActive(activeImage);
                    //Toast.makeText(getContext(), alarm.getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG).show();
                }
                //변경 저장 여기
                //Database.init(getContext());
                //getAlarm().setAlarmActive(activeImage);
                Database.update(getAlarm());
                ((MainActivity)getContext()).callAlarmScheduleService();

            }
        });

    }

    /**
     * set Text
     */
    /*public void setText(int index, String data) {
        if (index == 0) {
            mText01.setText(data);
        } else if (index == 1) {
            mText02.setText(data);
        } else if (index == 2) {
            mText03.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }*/
    public void setSet_time(String time){
        set_time.setText(time);
    }
    public void setSet_day(String day){
        set_day.setText(day);
    }
    /**
     * set Icon
     */
    /*public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }    */
    public void setSet_active(Drawable active){
        set_active.setImageDrawable(active);
    }

    public Alarm getAlarm(){
        return alarm;
    }

    public LinearLayout getAlarm_set(){
        return alarm_set;
    }

    public void setActiveImage(boolean activeImage){
        this.activeImage = activeImage;
    }
    public void setAlarm(Alarm alarm){
        this.alarm = alarm;
    }
}
