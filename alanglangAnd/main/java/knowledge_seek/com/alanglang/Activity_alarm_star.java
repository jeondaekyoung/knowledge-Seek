package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_star extends Activity {

    private Alarm alarm;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //폰의 상태를 깨운다.
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_alarm_star);

        //액티비티실행시 넘어온 Alarm객체가 있는지 확인하여 받아들인다.(볼륨땜시)
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            alarm = (Alarm)bundle.getSerializable("alarm");
        }

        Button btnFinish=(Button)findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        try {
            playAudio();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        killMediaPlayer();
        StaticWakeLock.lockOff(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void playAudio() throws Exception{
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kyc_sp1);

        AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        if(alarm == null){
            Log.d("-진우- 알람 ", "널이다");
            //am.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
        } else {
            Log.d("-진우- 알람 ", alarm.toString());
            am.setStreamVolume(AudioManager.STREAM_MUSIC, alarm.getVolume(), 0);
        }

        mediaPlayer.start();
    }

    private void killMediaPlayer(){
        if(mediaPlayer != null){
            try{
                mediaPlayer.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
