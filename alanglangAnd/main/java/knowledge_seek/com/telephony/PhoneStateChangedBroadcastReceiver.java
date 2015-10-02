package knowledge_seek.com.telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sjw on 2015-10-02.
 */
public class PhoneStateChangedBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("-진우- " + getClass().getSimpleName(), "onReceiver()");
    }
}
