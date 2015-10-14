package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Calendar;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_eng extends Activity {
    private Alarm alarm;
    private WebView webView;
    final Activity activity = this;
    private static final String ENG_DO = "http://182.162.143.24/and/eng.do";

    private static final int MESSAGE_OK = 1;

    private String audio_url;       //미디어파일위치
    private MediaPlayer mediaPlayer;    //MediaPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("-진우- ", "영어학습 시작");

        //폰의 상태를 깨운다.
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_alarm_eng);

        String st = "http://182.162.143.24/and/engSound.do";
        try {
            mThread async = new mThread(st);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
        }

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(ENG_DO);
        webView.setWebViewClient(new EngWebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int progress) {
                //매개변수로 제공되는 progress의 범위는 0부터 10,000까지 사용한다.
                //웹 페이지가 100% 출력될 때까지 화면 출력을 지연한다.
                activity.setProgress(progress * 100);
            }
        });

        Refresh();

        Button btnFinish=(Button)findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    void Refresh(){
        StringBuilder time = new StringBuilder();

        Calendar cal = Calendar.getInstance();
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };

        time.append(String.format("%d월 %d일 %s",
                cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH),
                week[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일"));

        TextView result = (TextView)findViewById(R.id.day);
        result.setText(time.toString());
    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case MESSAGE_OK:
                    Log.d("-진우- 소리파일 전달잘되?", audio_url);
                    try{
                        playAudio(audio_url);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
            }
        }

    };

    private class mThread extends Thread {
        String result = "";
        URI uri;

        public mThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            Log.d("-진우- ", "run() 실행");
            try{
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost(uri);

                HttpParams params = httpclient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

                HttpResponse response = httpclient.execute(httppost);
                StatusLine status = response.getStatusLine();

                Log.d("-진우- ", String.valueOf(status.getStatusCode()));
                if(status.getStatusCode() == 200){
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 64);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while((line = reader.readLine()) != null){
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                }
            }catch(Exception e){
                Log.d("log_tag", "Error in http connection " + e.toString());
            }

            Log.d("-진우- 광고정보 ", result);
            try{
                JSONObject json = new JSONObject(result);
                Log.d("-진우- eng_sound_server ", json.getString("eng_sound_server"));
                audio_url = "http://182.162.143.24/fileupload/sound/" + json.getString("eng_sound_server");
            }catch(JSONException e){
                Log.d("-진우- JSON", "Error parsing data " + e.toString());
            }

            handler.sendEmptyMessage(MESSAGE_OK);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        StaticWakeLock.lockOff(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    class EngWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);   //HTML 문서 내 존재하는 새로운 URL는 웹뷰에서 로드한다.
            return true;
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Toast.makeText(activity, "Oh no", Toast.LENGTH_SHORT).show();
        }
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
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
