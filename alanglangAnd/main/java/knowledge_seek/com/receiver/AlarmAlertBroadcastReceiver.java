package knowledge_seek.com.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.alanglang.Activity_alarm_alanglang;
import knowledge_seek.com.alanglang.Activity_alarm_eng;
import knowledge_seek.com.alanglang.Activity_alarm_star;
import knowledge_seek.com.service.AlarmServiceBroadcastReceiver;

/**
 * Created by sjw on 2015-10-02.
 */
public class AlarmAlertBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmServiceIntent = new Intent(context, AlarmServiceBroadcastReceiver.class);
        context.sendBroadcast(alarmServiceIntent, null);

        StaticWakeLock.lockOn(context);
        Bundle bundle = intent.getExtras();
        final Alarm alarm = (Alarm)bundle.getSerializable("alarm");

        //Log.d("-진우- 알람울려? ", alarm.toString());
        Intent alarmActiveActivity;
        //알람화면 등록 - 광고화면, 영어학습화면, 스타화면
        //alarmActiveActivity = new Intent(context, Activity_alarm_alanglang.class);
        if(alarm.getType() == Alarm.Type.ALANGLANG){
            alarmActiveActivity = new Intent(context, Activity_alarm_alanglang.class);
        } else if(alarm.getType() == Alarm.Type.ENG){
            alarmActiveActivity = new Intent(context, Activity_alarm_eng.class);
        } else {
            alarmActiveActivity = new Intent(context, Activity_alarm_star.class);
        }

        alarmActiveActivity.putExtra("alarm", alarm);
        /*
        * FLAG_ACTIVITY_NEW_TASK : 이 엑티비티 플래그를 사용하여 엑티비티를 호출하게 되면 새로운 태스크를 생성하여
        * 그 태스크안에 액티비티를 추가하게 됩니다. 단, 기존에 존재하는 태스크들중에 생성하려는 액티비티와 동일한 affinity를
        * 가지고 있는 태스크가 있다면 그곳으로 새 액티비티가 들어가게 됩니다.
        * 하나의 어플리케이션
        * */
        alarmActiveActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmActiveActivity);
    }
}
