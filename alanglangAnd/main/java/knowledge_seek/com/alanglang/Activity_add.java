package knowledge_seek.com.alanglang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.BaseActivity;
import knowledge_seek.com.database.Database;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_add extends BaseActivity{

    int mSelect = 0;    //pop에 사용

    private Alarm alarm;
    private TimePicker timePicker;
    LinearLayout popType;
    LinearLayout popType2;

    //월, 화, 수, 목, 금, 토, 일
    ImageView day1, day2, day3, day4, day5, day6, day7;
    boolean day1_image = false;
    boolean day2_image = false;
    boolean day3_image = false;
    boolean day4_image = false;
    boolean day5_image = false;
    boolean day6_image = false;
    boolean day7_image = false;

    //볼륨
    private SeekBar volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //넘어온 alarm 데이터가 있을 경우
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey("alarm")){
            setAlarm((Alarm)bundle.getSerializable("alarm"));
        } else {
            setAlarm(new Alarm());
        }
        Log.d("-진우- 설정화면 : ", alarm.toString());

        //알람시간 셋팅
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        //Log.d("-진우- 시간 ", alarm.getAlarmTimeString());
        //Log.d("-진우- 시간2: ", alarm.getAlarmTime().toString());
        //Log.d("-진우- 시간3 : ", alarm.getAlarmTimeView());
        String[] timePieces = alarm.getAlarmTimeString().split(":");
        timePicker.setCurrentHour(Integer.parseInt(timePieces[0]));
        timePicker.setCurrentMinute(Integer.parseInt(timePieces[1]));


        //안드로이드 5.0에서 버그로 인하여 작동하지 않는다.
        /*timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hours, int minutes) {
                Log.d("-진우- 시간변경됨", "오케이");
                Calendar newAlarmTime = Calendar.getInstance();
                newAlarmTime.set(Calendar.HOUR_OF_DAY, hours);
                newAlarmTime.set(Calendar.MINUTE, minutes);
                newAlarmTime.set(Calendar.SECOND, 0);
                alarm.setAlarmTime(newAlarmTime);
                Log.d("-진우- 알람시간 : ", alarm.getAlarmTimeString());
                *//*
                alarmPreferenceListAdapter.setMathAlarm(getMathAlarm());
                alarmPreferenceListAdapter.notifyDataSetChanged();
                *//*
            }
        });*/

        //알람타입 셋팅
        popType = (LinearLayout)findViewById(R.id.popType);
        //Log.d("-진우- 알람타입 : ", String.valueOf(alarm.getType()));
        TextView alarm_type = (TextView)findViewById(R.id.alarm_type);
        alarm_type.setText(String.valueOf(alarm.getType()).concat(" 알람"));

        //스타알람 View셋팅
        popType2 = (LinearLayout)findViewById(R.id.popType2);
        if(alarm.getType() == Alarm.Type.STAR){
            popType2.setVisibility(View.VISIBLE);
            //Log.d("-진우- 스타타입 : ", String.valueOf(alarm.getStar()));
            TextView alarm_type2 = (TextView)findViewById(R.id.alarm_type2);
            alarm_type2.setText(String.valueOf(alarm.getStar()));
        } else {
            popType2.setVisibility(View.GONE);
        }

        //알람타입 설정
        popType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "알람타입눌렸다.", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(Activity_add.this)
                        .setTitle("알람타입")
                        .setSingleChoiceItems(R.array.type, mSelect, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                mSelect = which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String[] type = getResources().getStringArray(R.array.type);
                                TextView text = (TextView) findViewById(R.id.alarm_type);
                                text.setText(type[mSelect]);

                                //알람에 타입저장
                                Alarm.Type t = Alarm.Type.values()[mSelect];
                                alarm.setType(t);

                                if (2 == mSelect) {
                                    popType2.setVisibility(View.VISIBLE);
                                    Alarm.Star s = Alarm.Star.values()[0];
                                    alarm.setStar(s);
                                } else {
                                    popType2.setVisibility(View.GONE);
                                }
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

        //스타알람 설정
        popType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "스타알람눌렸다.", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(Activity_add.this)
                        .setTitle("스타알람")
                        .setSingleChoiceItems(R.array.type2, mSelect, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                mSelect = which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String[] type = getResources().getStringArray(R.array.type2);
                                TextView text = (TextView)findViewById(R.id.alarm_type2);
                                text.setText(type[mSelect]);

                                //알람에 스타저장
                                Alarm.Star s = Alarm.Star.values()[mSelect];
                                alarm.setStar(s);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });

        //알람반복 셋팅
        day1 = (ImageView) findViewById(R.id.day_mon);
        day2 = (ImageView) findViewById(R.id.day_tue);
        day3 = (ImageView) findViewById(R.id.day_wed);
        day4 = (ImageView) findViewById(R.id.day_thu);
        day5 = (ImageView) findViewById(R.id.day_fri);
        day6 = (ImageView) findViewById(R.id.day_sat);
        day7 = (ImageView) findViewById(R.id.day_sun);

        String[] days = alarm.getRepeatDaysString().split(",");
        //Log.d("-진우- 알람반복", Arrays.toString(days));
        for (String s :days) {
            switch (s){
                case "월":
                    day1.setImageResource(R.drawable.mon_on);
                    day1_image = true;
                    break;
                case "화":
                    day2.setImageResource(R.drawable.tue_on);
                    day2_image = true;
                    break;
                case "수":
                    day3.setImageResource(R.drawable.wed_on);
                    day3_image = true;
                    break;
                case "목":
                    day4.setImageResource(R.drawable.thu_on);
                    day4_image = true;
                    break;
                case "금":
                    day5.setImageResource(R.drawable.fri_on);
                    day5_image = true;
                    break;
                case "토":
                    day6.setImageResource(R.drawable.sat_on);
                    day6_image = true;
                    break;
                case "일":
                    day7.setImageResource(R.drawable.sun_on);
                    day7_image = true;
                    break;
            }
        }

        day1.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[1];
                if (day1_image) {
                    if(alarm.getDays().length > 1){
                        day1.setImageResource(R.drawable.mon_off);
                        day1_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day1.setImageResource(R.drawable.mon_on);
                    day1_image = true;
                    alarm.addDay(thisDay);
                }
                //Log.d("-진우- 클릭요일 ", thisDay.toString());
            }

        });
        day2.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[2];
                if (day2_image) {
                    if(alarm.getDays().length > 1){
                        day2.setImageResource(R.drawable.tue_off);
                        day2_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day2.setImageResource(R.drawable.tue_on);
                    day2_image = true;
                    alarm.addDay(thisDay);
                }
            }
        });
        day3.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[3];
                if (day3_image) {
                    if(alarm.getDays().length > 1){
                        day3.setImageResource(R.drawable.wed_off);
                        day3_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day3.setImageResource(R.drawable.wed_on);
                    day3_image = true;
                    alarm.addDay(thisDay);
                }
            }
        });
        day4.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[4];
                if (day4_image) {
                    if(alarm.getDays().length > 1){
                        day4.setImageResource(R.drawable.thu_off);
                        day4_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day4.setImageResource(R.drawable.thu_on);
                    day4_image = true;
                    alarm.addDay(thisDay);
                }
            }
        });
        day5.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[5];
                if (day5_image) {
                    if(alarm.getDays().length > 1){
                        day5.setImageResource(R.drawable.fri_off);
                        day5_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day5.setImageResource(R.drawable.fri_on);
                    day5_image = true;
                    alarm.addDay(thisDay);
                }
            }

        });
        day6.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[6];
                if (day6_image) {
                    if(alarm.getDays().length > 1){
                        day6.setImageResource(R.drawable.sat_off);
                        day6_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day6.setImageResource(R.drawable.sat_on);
                    day6_image = true;
                    alarm.addDay(thisDay);
                }
            }
        });
        day7.setOnClickListener(new ImageView.OnClickListener(){
            public void onClick(View v) {
                Alarm.Day thisDay = Alarm.Day.values()[0];
                if (day7_image) {
                    if(alarm.getDays().length > 1){
                        day7.setImageResource(R.drawable.sun_off);
                        day7_image = false;
                        alarm.removeDay(thisDay);
                    }
                }else{
                    day7.setImageResource(R.drawable.sun_on);
                    day7_image = true;
                    alarm.addDay(thisDay);
                }
            }
        });

        //볼륨 셋팅
        volume = (SeekBar)findViewById(R.id.volume);
        volume.setProgress(alarm.getVolume());

        //볼륨 설정
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Log.d("-진우- 볼륨크기 ", String.valueOf(i));
                alarm.setVolume(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        //다시알림 설정
        /*popRepeat = (LinearLayout)findViewById(R.id.popRepeat);
        Log.d("-진우- 다시알림 : ", String.valueOf(alarm.getRepeat()));
        TextView alarm_repeat = (TextView)findViewById(R.id.alarm_repeat);
        alarm_repeat.setText(String.valueOf(alarm.getRepeat()));

        popRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Activity_add.this)
                        .setTitle("다시알림")
                        .setSingleChoiceItems(R.array.repeat, mSelect, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                mSelect = which;
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String[] type = getResources().getStringArray(R.array.repeat);
                                TextView text = (TextView)findViewById(R.id.alarm_repeat);
                                text.setText(type[mSelect]);

                                //알람에 다시알림 저장
                                Alarm.Repeat r = Alarm.Repeat.values()[mSelect];
                                alarm.setRepeat(r);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });*/
    }

    //알람저장
    public void alarmRegister(View v) {

        //alarm.setAlarmActive(true);

        //시간설정
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        //Log.d("-진우- 몇시니? ", String.valueOf(hour) + ", " + String.valueOf(minute));
        Calendar newAlarmTime = Calendar.getInstance();
        newAlarmTime.set(Calendar.HOUR_OF_DAY, hour);
        newAlarmTime.set(Calendar.MINUTE, minute);
        newAlarmTime.set(Calendar.SECOND, 0);
        alarm.setAlarmTime(newAlarmTime);

        //데이터베이스
        Database.init(getApplicationContext());

        //alarm에 데이터가 있는지 없는지 확인
        if(getAlarm().getId() < 1){
            //alarm이 없을 경우 인세트
            //Log.d("-진우- 알람저장 : ", "alarm이 없을 경우");
            Database.create(getAlarm());
        } else {
            //alarm이 있을 경우 업데이트
            //Log.d("-진우- 알람저장 : ", "alarm이 있을 경우");
            Database.update(getAlarm());
        }

        callAlarmScheduleService();
        //Toast.makeText(this, getAlarm().getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG).show();
        finish();
    }

    public void setAlarm(Alarm alarm){
        this.alarm = alarm;
    }
    public Alarm getAlarm(){
        return alarm;
    }
}
