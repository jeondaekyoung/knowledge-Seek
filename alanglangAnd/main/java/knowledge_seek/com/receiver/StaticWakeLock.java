package knowledge_seek.com.receiver;

import android.content.Context;
import android.os.PowerManager;

/**
 * Created by sjw on 2015-10-02.
 */
public class StaticWakeLock {

    //PowerManager는 Device의 전원 상태(Power state)를 제어할 수 있게해줌
    private static PowerManager.WakeLock wl = null;

    public static void lockOn(Context context){
        PowerManager pm = (PowerManager)context.getSystemService(Context.POWER_SERVICE);

        if(wl == null){
            //ACQUIRE_CAUSES_WAKEUP : WakeLock이 acquire()되는 순간 Screen과 Keyboard가 바로 켜집니다. 보통 긴급한
            // notification(착신전화,알람)등이 있을 때 주로 사용됩니다.
            wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "alanglang_ALARM");
        }
        wl.acquire();
    }

    public static void lockOff(Context context){
        try{
            if(wl != null){
                //사용이 끝나면 release()함
                wl.release();
            }
        } catch (Exception e){

        }
    }
}
