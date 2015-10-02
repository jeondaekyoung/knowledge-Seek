package knowledge_seek.com.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.database.Database;
import knowledge_seek.com.receiver.AlarmAlertBroadcastReceiver;

/**
 * Created by sjw on 2015-10-02.
 */
public class AlarmService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d("-진우- ", "AlarmService.onCreate() ");
        super.onCreate();
    }

    //DB에 저장되어있는 알람객체를 읽어와서 던져줌
    private Alarm getNext(){

        //정렬을 시키면서 저장한다. Set 생성, 알람활동이 사용인 것을 담는다.
        Set<Alarm> alarmQueue = new TreeSet<Alarm>(new Comparator<Alarm>() {
            @Override
            public int compare(Alarm lhs, Alarm rhs) {
                int result = 0;
                long diff = lhs.getAlarmTime().getTimeInMillis() - rhs.getAlarmTime().getTimeInMillis();
                if(diff > 0){
                    return 1;
                } else if(diff < 0){
                    return -1;
                }
                return result;
            }
        });

        //데이터베이스 생성
        Database.init(getApplicationContext());
        //데이터베이스의 알람저장리스트를 읽어온다.
        List<Alarm> alarms = Database.getAll();

        for(Alarm alarm : alarms){
            if(alarm.getAlarmActive()){
                //알람 활동이 사용으로 된 것만 통과
                alarmQueue.add(alarm);
            }
        }

        //맨처음 저장된 알람을 불러온다. 알람이 울리고나서
        //다시 맨처음 저장된 알람을 불러온다.
        if(alarmQueue.iterator().hasNext()){
            //Set 에 저장된 알람을 하나씩 불러온다.
            return alarmQueue.iterator().next();
        } else {
            return null;
        }

    }

    @Override
    public void onDestroy() {
        Database.deactivate();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("-진우- ", "AlarmService.onStartCommand()");

        //데이터베이스에 저장된 알람리스트 중에서 알람활동이 사용인 것을 불러온다.
        Alarm alarm = getNext();
        //Log.d("-진우- 알람등록 ", alarm.toString());
        if(alarm != null){
            //알람이 있으면 알람등록
            alarm.schedule(getApplicationContext());
            //Log.d(this.getClass().getSimpleName(), alarm.getTimeUntilNextAlarmMessage());

        } else {
            //알람이 없으면 알람취소
            Intent myIntent = new Intent(getApplicationContext(), AlarmAlertBroadcastReceiver.class);
            myIntent.putExtra("alarm", new Alarm());

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager alarmManager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);

            alarmManager.cancel(pendingIntent);
        }

        //START_NOT_STICKY : 메모리 공간 부족으로 서비스가 종료되었을 때, 서비스 재 실행하지 않음
        return START_NOT_STICKY;
    }

}
