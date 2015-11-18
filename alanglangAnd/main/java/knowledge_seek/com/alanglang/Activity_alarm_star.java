package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_star extends Activity {

    private Alarm alarm;
    private MediaPlayer mediaPlayer;

    private static final String HTTPADDR = "http://www.knowledge-seek.com";
    //private static final String HTTPADDR = "http://182.162.143.24";
    private ImageView star_imageview;       //스타 이미지뷰
    String starbg_name;             //스타배경 이미지 이름

    //통신 결과
    private static final int MESSAGE_OK = 1;

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

        star_imageview = (ImageView)findViewById(R.id.star_imageview);

        //액티비티실행시 넘어온 Alarm객체가 있는지 확인하여 받아들인다.(볼륨땜시)
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            alarm = (Alarm)bundle.getSerializable("alarm");
        }

        //서버와 통신하여 서버에 있는 배경이미지파일을 알아온다.
        String st = HTTPADDR + "/and/starbg.do";
        try {
            starbgThread async = new starbgThread(st);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
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

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MESSAGE_OK:
                    //Log.d("-진우- ", "handler MESSAGE_OK 실행");
                    String st = HTTPADDR + "/fileupload/bg/" + starbg_name;
                    //Log.d("-진우- 배경이미지", st);
                    BitmapFactory.Options bmOptions;
                    bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
                    OpenHttpConnection opHttpCon = new OpenHttpConnection();
                    opHttpCon.execute(star_imageview, st);
                    break;
            }
        }
    };


    private class starbgThread extends Thread {
        String result = "";
        URI uri;

        public starbgThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            //Log.d("-진우-", "run() 실행");
            HttpClient httpclient = null;
            HttpPost httppost = null;
            try {
                httpclient = new DefaultHttpClient();
                httppost = new HttpPost(uri);

                HttpParams params = httpclient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 1000);

                HttpResponse response = httpclient.execute(httppost);
                StatusLine status = response.getStatusLine();

                //Log.d("-진우- ", String.valueOf(status.getStatusCode()));
                if (status.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"),64);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                }
            }catch(Exception e){
                Log.e("log_tag", "Error in http connection "+e.toString());
            } finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 배경이미지정보 ", result);
            try {
                JSONObject json = new JSONObject(result);

                starbg_name = json.getString("star_bg_server");
            } catch(JSONException e){
                Log.e("-진우- JSON", "Error parsing data "+e.toString());
            }
            handler.sendEmptyMessage(MESSAGE_OK);
        }
    }

    //이미지광고시 이미지불러오기
    private class OpenHttpConnection extends AsyncTask<Object, Void, Bitmap> {
        private ImageView bmImage;
        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap mBitmap = null;
            bmImage = (ImageView)objects[0];
            String url = (String)objects[1];
            InputStream in = null;
            try{
                in = new URL(url).openStream();
                mBitmap = BitmapFactory.decodeStream(in);
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            bmImage.setImageBitmap(bitmap);
        }
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
