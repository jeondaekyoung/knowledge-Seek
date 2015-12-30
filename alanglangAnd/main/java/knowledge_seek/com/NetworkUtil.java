package knowledge_seek.com;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sjw on 2015-12-18.
 */
public class NetworkUtil {
    public static final int NETWORK_WIFI = 0;
    public static final int NETWORK_MOBILE = 1;
    public static final int NETWORK_NONE = 2;

    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return NETWORK_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return NETWORK_MOBILE;
        }
        return NETWORK_NONE;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetworkUtil.getConnectivityStatus(context);
        String status = null;
        if (conn == NetworkUtil.NETWORK_WIFI) {
            status = "WIFI";
        } else if (conn == NetworkUtil.NETWORK_MOBILE) {
            status = "MOBILE";
        } else if (conn == NetworkUtil.NETWORK_NONE) {
            status = "NONE";
        }
        return status;
    }
}
