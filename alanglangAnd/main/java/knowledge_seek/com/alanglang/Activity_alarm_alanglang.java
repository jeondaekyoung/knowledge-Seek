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
import android.webkit.WebSettings;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_alanglang extends Activity {
    private Alarm alarm;
    private WebView webView;
    final Activity activity = this;
    private static final String AD_DO = "http://182.162.143.24/and/ad.do";

    private static final int MESSAGE_OK = 1;
    //List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();


    private String audio_url ;                     //미디어파일위치
    private MediaPlayer mediaPlayer;        //MediaPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("-진우- ", "알랑랑 시작");


        //폰의 상태를 깨운다.
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_alarm_alanglang);

        //넘어온 데이터받기
        //Bundle bundle = this.getIntent().getExtras();
        //alarm = (Alarm)bundle.getSerializable("alarm");

        String st = "http://182.162.143.24/and/adSound.do";
        try {
            mThread async = new mThread(st);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
        }

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(AD_DO);
        webView.setWebViewClient(new AdWebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int progress){
                //매개변수로 제공되는 progress의 범위는 0부터 10,000까지 사용한다.
                //웹 페이지가 100% 출력될때까지 화면 출력을 지연한다.
                activity.setProgress(progress * 100);
            }
        });


        /*webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setJavaScriptEnabled(true);*/
        //webView.addJavascriptInterface(new AudioInterface(this), "Android");
        //WebViewClient 지정
        /*webView.setWebChromeClient(new WebChromeClient());
        WebSettings set = webView.getSettings();
        //웹뷰에서 자바스크립트 실행가능
        set.setJavaScriptEnabled(true); // javascript를 실행할 수 있도록 설정
        set.setJavaScriptCanOpenWindowsAutomatically(true);  // javascript가 window.open()을 사용할 수 있도록 설정
        set.setBuiltInZoomControls(true);   // 안드로이드에서 제공하는 줌 아이콘을 사용할 수 있도록 설정
        set.setPluginState(WebSettings.PluginState.ON_DEMAND); // 플러그인을 사용할 수 있도록 설정
        set.setSupportMultipleWindows(true); // 여러개의 윈도우를 사용할 수 있도록 설정
        set.setSupportZoom(true); // 확대,축소 기능을 사용할 수 있도록 설정
        set.setBlockNetworkImage(false); // 네트워크의 이미지의 리소스를 로드하지않음
        set.setLoadsImagesAutomatically(true); // 웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정
        set.setUseWideViewPort(true); // wide viewport를 사용하도록 설정
        set.setCacheMode(WebSettings.LOAD_NO_CACHE); // 웹뷰가 캐시를 사용하지 않도록 설정*/


        //홈페이지 저장
        //webView.loadUrl("http://www.geniusjinu.com/and/ad.do");


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
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MESSAGE_OK:
                    //Toast.makeText(getApplicationContext(), "끝", Toast.LENGTH_SHORT).show();
                    //MediaPlayer
                    //audio_url = "http://www.geniusjinu.com/fileupload/sound/201510AS0001.mp3";
                    Log.d("-진우- 소리파일 전달잘되? ", audio_url);
                    try{
                        playAudio(audio_url);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //끝
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
            }

            Log.d("-진우- 광고정보 ", result);
            try{
                JSONObject json = new JSONObject(result);
                Log.d("-진우- ad_sound_server " , json.getString("ad_sound_server"));
                audio_url = "http://182.162.143.24/fileupload/sound/" + json.getString("ad_sound_server");
                /*JSONArray adSound = json.getJSONArray("ad");
                for(int i=0;i < adSound.length();i++){
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject e = adSound.getJSONObject(i);

                    map.put("id",  String.valueOf(i));
                    map.put("ad_seq", e.getString("ad_seq"));
                    map.put("ad_sound_server", e.getString("ad_sound_server"));
                    map.put("ad_image_server", e.getString("ad_image_server"));
                    *//*map.put("name", "지진명 :" + e.getString("eqid"));
                    map.put("magnitude", "강도 : " + e.getString("magnitude"));
                    map.put("datetime", "일자 :" + e.getString("datetime"));
                    map.put("lng", "경도 : " + e.getDouble("lng"));
                    map.put("lat", "위도 : " + e.getDouble("lat"));*//*
                    mylist.add(map);
                }*/
            }catch(JSONException e){
                Log.e("-진우- JSON", "Error parsing data "+e.toString());
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

    class AdWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);  //HTML문서 내 존재하는 새로운 URL는 웹뷰에서 로드한다.
            return true;
        }
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            Log.d("Error ", "Oh no");
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
