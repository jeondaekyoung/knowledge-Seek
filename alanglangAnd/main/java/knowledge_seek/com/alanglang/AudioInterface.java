package knowledge_seek.com.alanglang;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.io.IOException;

/**
 * Created by sjw on 2015-10-06.
 */
public class AudioInterface {
    Context mContext;

    AudioInterface(Context c) {
        mContext = c;
    }

    //Play an audio file from the webpage
    @JavascriptInterface
    public void playAudio(String aud) { //String aud - file name passed
        //from the JavaScript function
        //String audio_url = "http://www.geniusjinu.com/fileupload/sound/" + aud;

        Log.d("-진우- ", "AudioInterface 시작");

        final MediaPlayer mp;

        /*mp = new MediaPlayer();
        try{
            Log.d("-진우- 오디어재생 ", aud);
            mp.setDataSource(aud);
            mp.prepare();
            mp.start();

        }catch (Exception e){
            e.printStackTrace();
        }*/

        try {
            AssetFileDescriptor fileDescriptor =
                    mContext.getAssets().openFd(aud);
            mp = new MediaPlayer();
            mp.setDataSource(fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            fileDescriptor.close();
            mp.prepare();
            mp.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
