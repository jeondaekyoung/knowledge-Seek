package knowledge_seek.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

import knowledge_seek.com.service.AlarmServiceBroadcastReceiver;

/**
 * Created by sjw on 2015-10-02.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }
    }

    /*
        * AlarmService를 브러드캐스트 등록
        * */
    protected void callAlarmScheduleService(){
        Intent alarmServiceIntent = new Intent(this, AlarmServiceBroadcastReceiver.class);
        sendBroadcast(alarmServiceIntent, null);
    }
}
