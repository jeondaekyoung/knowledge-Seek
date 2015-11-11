package knowledge_seek.com;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import knowledge_seek.com.receiver.AlarmAlertBroadcastReceiver;

/**
 * Created by sjw on 2015-10-02.
 */
public class Alarm implements Serializable {

    //가꼬가알람(광고), 영어알람, 스타알람(김영철)
    public enum Type {
        ALANGLANG,
        ENG,
        STAR;

        @Override
        public String toString() {
            switch (this.ordinal()){
                case 0:
                    return "알랑랑";
                case 1:
                    return "영어";
                case 2:
                    return "스타";
            }
            return super.toString();
        }
    }

    //스타알람
    public enum Star {
        KYC;

        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0:
                    return "김영철";
            }
            return super.toString();
        }
    }

    //알람 설정 요일
    public enum Day{
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY;

        @Override
        public String toString() {
            switch (this.ordinal()){
                case 0:
                    return "일";
                case 1:
                    return "월";
                case 2:
                    return "화";
                case 3:
                    return "수";
                case 4:
                    return "목";
                case 5:
                    return "금";
                case 6:
                    return "토";
            }
            return super.toString();
        }
    }

    public enum Repeat{
        MIN_0,
        MIN_5,
        MIN_10,
        MIN_15,
        MIN_20,
        MIN_30,
        MIN_60;

        @Override
        public String toString() {
            switch (this.ordinal()){
                case 0:
                    return "해제";
                case 1:
                    return "5분";
                case 2:
                    return "10분";
                case 3:
                    return "15분";
                case 4:
                    return "20분";
                case 5:
                    return "30분";
                case 6:
                    return "1시간";
            }
            return super.toString();
        }
    }

    private static final long serialVersionUID = 8699489847426803789L;
    private boolean selectable = true;
    private int id;
    private Boolean alarmActive = true;     //사용
    private Drawable active;
    private Calendar alarmTime = Calendar.getInstance();        //현재 날짜와 시간 정보를 가진 Calendar객체를 생성한다.
    private Day[] days = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};
    /*private String alarmTonePath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString();*/
    //private Boolean vibrate = true;     //진동
    //private String alarmName = "Alarm Clock";     //알람이름
    private Type type = Type.ALANGLANG;
    private Star star = Star.KYC;
    private Repeat repeat = Repeat.MIN_0;
    private int volume = 7;            //볼륨


    public Alarm(){
    }

    public boolean isSelectable(){
        return selectable;
    }
    public void setSelectable(boolean selectable){
        this.selectable = selectable;
    }

    //알람 on, off
    public Boolean getAlarmActive(){
        return alarmActive;
    }

    public void setAlarmActive(Boolean alarmActive){
        this.alarmActive = alarmActive;
    }


    public Calendar getAlarmTime(){

        //현재 시간(오늘)보다 alarmTime가 이전이냐? 이전이면 내일시간으로 변경
        if(alarmTime.before(Calendar.getInstance())){
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        //요일이 맞지않으면은 내일시간으로 변경
        //                                                     알람시간의 요일은 구한다
        //저장된 요일에 알람시간의 요일이 있으면 true에서 !false, 즉 저장된 요일이 없으면 내일날짜로 변경
        while(!Arrays.asList(getDays()).contains(Day.values()[alarmTime.get(Calendar.DAY_OF_WEEK)-1])){
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        return alarmTime;
    }

    //시간을 문자열로 화면에 보여줌
    public String getAlarmTimeView(){
        StringBuilder time = new StringBuilder();

        //오전, 오후
        if(0 <= alarmTime.get(Calendar.HOUR_OF_DAY) && alarmTime.get(Calendar.HOUR_OF_DAY) <= 11){
            time.append("오전 ");
        } else {
            time.append("오후 ");
        }

        //시간
        if(alarmTime.get(Calendar.HOUR_OF_DAY) <= 11){
            //오전
            if(alarmTime.get(Calendar.HOUR_OF_DAY) == 00){
                time.append("00");
            } else {
                if (alarmTime.get(Calendar.HOUR_OF_DAY) <= 9) {
                    time.append("0").append(String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY)));
                } else {
                    time.append(String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY)));
                }
            }
        } else {
            //오후
            if(alarmTime.get(Calendar.HOUR_OF_DAY) == 12){
                time.append("12");
            } else {
                if(alarmTime.get(Calendar.HOUR_OF_DAY) <= 21){
                    time.append("0").append(String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY) - 12));
                } else {
                    time.append(String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY) - 12));
                }
            }

        }
        time.append(":");

        //분
        if(alarmTime.get(Calendar.MINUTE) <= 9){
            time.append("0");
        }
        time.append(String.valueOf(alarmTime.get(Calendar.MINUTE)));

        return time.toString();
    }

    //시간을 문자열로 database에 저장
    public String getAlarmTimeString(){
        String time = "";
        if (alarmTime.get(Calendar.HOUR_OF_DAY) <= 9)
            time += "0";
        time += String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY));
        time += ":";

        if (alarmTime.get(Calendar.MINUTE) <= 9)
            time += "0";
        time += String.valueOf(alarmTime.get(Calendar.MINUTE));

        return time;
    }


    public void setAlarmTime(Calendar alarmTime){
        this.alarmTime = alarmTime;
    }

    public void setAlarmTime(String alarmTime){
        String[] timePieces = alarmTime.split(":");

        Calendar newAlarmTime = Calendar.getInstance();
        newAlarmTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timePieces[0]));
        newAlarmTime.set(Calendar.MINUTE, Integer.parseInt(timePieces[1]));
        newAlarmTime.set(Calendar.SECOND, 0);
        setAlarmTime(newAlarmTime);
    }

    public Day[] getDays(){
        return days;
    }

    public void setDays(Day[] days){
        this.days = days;
    }

    public void addDay(Day day){
        boolean contains = false;

        //day가 요일이 있으면 contains을 true로 변경
        for(Day d : getDays()){
            if(d.equals(day)){
                contains = true;
            }
        }
        //day가 요일이 없으면 ...
        if(!contains){
            List<Day> result = new LinkedList<Day>();
            for(Day d : getDays()){
                result.add(d);
            }
            result.add(day);
            setDays(result.toArray(new Day[result.size()]));
        }
    }

    //day가 월~일까지 day와 같지않은 요일을 배열로 반환??
    public void removeDay(Day day){
        List<Day> result = new LinkedList<Day>();
        for(Day d : getDays()){
            if(!d.equals(day)){
                result.add(d);
            }
        }
        setDays(result.toArray(new Day[result.size()]));
    }

     /*public String getAlarmTonePath(){
        return alarmTonePath;
    }*/

    /*public void setAlarmTonePath(String alarmTonePath){
        this.alarmTonePath = alarmTonePath;
    }*/

    /*public Boolean getVibrate(){
        return vibrate;
    }*/

    /*public void setVibrate(Boolean vibrate){
        this.vibrate = vibrate;
    }*/

    /*public String getAlarmName(){
        return alarmName;
    }*/

    /*public void setAlarmName(String alarmName){
        this.alarmName = alarmName;
    }*/

    /*public Difficulty getDifficulty(){
        return difficulty;
    }*/

    /*public void setDifficulty(Difficulty difficulty){
        this.difficulty = difficulty;
    }*/

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type = type;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Drawable getActive(){
        return active;
    }

    public void setActive(Drawable active){
        this.active = active;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int  getVolume() {
        return volume;
    }




    //요일을 문자열로 반환 (화면에 출력)
    public String getRepeatDaysString(){
        StringBuilder daysStringBuilder = new StringBuilder();

        Arrays.sort(getDays(), new Comparator<Day>() {
            @Override
            public int compare(Day lhs, Day rhs) {
                return lhs.ordinal() - rhs.ordinal();
            }
        });

        for(Day d : getDays()) {
            daysStringBuilder.append(d.toString());
            daysStringBuilder.append(',');
        }
        daysStringBuilder.setLength(daysStringBuilder.length() - 1);

        //Log.d("-진우- 확인 ", String.valueOf(daysStringBuilder.indexOf("일")));
        //일요일이 있을 경우 화면출력할 때 맨 뒤로 보낸다.
        int sunIndex = daysStringBuilder.indexOf("일");
        if(sunIndex != -1){
            daysStringBuilder.delete(0, 2);
            daysStringBuilder.append(",일");
        }

        return daysStringBuilder.toString();
    }

    //브로드캐스트 인텐트를 만들고 알람매니저에 셋팅한다.
    public void schedule(Context context){
        Log.d("-진우- 알람저장 ", this.toString() + ", " + this.getRepeat());
        Log.d("-진우- 알람아이디 ", String.valueOf(this.getId()));

        //알람 활동을 true로 만듬.. 필요한가?
        setAlarmActive(true);       //-->여기 어디서쓰는지 확인하자

        //AlarmServiceBroadcastReceiver를 브로드캐스트에 등록
        Intent myIntent = new Intent(context, AlarmAlertBroadcastReceiver.class);
        myIntent.putExtra("alarm", this);


        //PendingIntent : 인텐트를 전송하고자 하는 송신자가 인텐트1를 하나 생성한 후, 별도의 컴포넌트에게
        // 인텐트1를 나 대신 보내주기위해 전달하고자 할 때 사용되는 클래스이다.
        //FLAG_CANCEL_CURRENT : 이전에 생성한 PendingIntent는 취소하고, 새롭게 하나를 만듭니다.
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);;

        //AlarmManager : 일정 시간이 되면 사용자에게 알리는 기능을 제공한다.
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        //set(int type, long triggerAtTime, PendingIntent operation) : 일회성 알람을 생성하고 보낼 때 사용한다.
        //type:알람타입, triggerAtTime:알람이 실행되는 시간, operation:알람이 실행될 때 발생하는 펜딩인텐트이다.
        //setRepeating(int type, long triggerAtTime, long interval, PendingIntent operation) : 일정 시간이 경과되면,
        //          반복적으로 알람을 발생시키는 메서드이다. interval은 반복 주기이다.
        //AlarmManager.RTC_WAKEUP : UTC표준 시간을 기준으로 알람 시간을 설정하며 안드로이드가 절전 모드(슬립 모드)로
        //          되어 있을 때, 알람 시점에 PowerManager를 이용하여 안드로이드를 깨운다.

        alarmManager.set(AlarmManager.RTC_WAKEUP, getAlarmTime().getTimeInMillis(), pendingIntent);


    }

    //다음 알람까지의 시간을 문자열로 반환
    public String getTimeUntilNextAlarmMessage(){
        long timeDifference = getAlarmTime().getTimeInMillis() - System.currentTimeMillis();
        long days = timeDifference / (1000*60*60*24);
        long hours = timeDifference / (1000*60*60) - (days*24);
        long minutes = timeDifference / (1000*60) - (days*24*60) - (hours*60);
        long seconds = timeDifference / (1000) - (days*24*69*60) - (hours*60*60) - (minutes*60);
        String alert = "다음 알람시간은 ";
        if(days > 0){
            alert += String.format(" %d일 %d시 %d분 %d초 후입니다.", days, hours, minutes, seconds);
        } else {
            if(hours > 0){
                alert += String.format(" %d시, %d분 %d초 후입니다.", hours, minutes, seconds);
            } else {
                if(minutes > 0){
                    alert += String.format(" %d분 %d초 후입니다.", minutes, seconds);
                } else {
                    alert += String.format(" %d초 후입니다.", seconds);
                }
            }
        }
        return alert;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "selectable=" + selectable +
                ", id=" + id +
                ", alarmActive=" + alarmActive +
                ", time=" + getAlarmTimeView() +
                ", days=" + getRepeatDaysString() +
                ", type=" + type +
                ", star=" + star +
                ", volume=" + volume +
                '}';
    }
}
