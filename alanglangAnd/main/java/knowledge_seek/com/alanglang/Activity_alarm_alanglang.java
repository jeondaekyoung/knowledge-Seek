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
import android.widget.EditText;
import android.widget.LinearLayout;
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

import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_alanglang extends Activity {
    private Alarm alarm;
    private WebView webView;
    final Activity activity = this;
    private static final String httpAddr = "http://182.162.143.24";
    private String ad_seq;          //광고 시퀀스
    //private static final String AD_DO = "http://182.162.143.24/and/ad.do";

    private static final int MESSAGE_OK = 1;
    private static final int ENTRY_SUCCESS = 2;
    private static final int ENTRY_FAIL = 3;

    //응모하기
    private LinearLayout entryLinearLayout;
    private EditText name_txt;
    private EditText phone_txt;
    private EditText email_txt;
    private Button entry_btn;

    private Button btn_media;

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

        String st = httpAddr + "/and/adSound.do";
        try {
            mThread async = new mThread(st);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
        }

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(httpAddr + "/and/ad.do");
        webView.setWebViewClient(new AdWebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                //매개변수로 제공되는 progress의 범위는 0부터 10,000까지 사용한다.
                //웹 페이지가 100% 출력될때까지 화면 출력을 지연한다.
                activity.setProgress(progress * 100);
            }
        });

        //응모하기 리니어레이아웃
        entryLinearLayout = (LinearLayout)findViewById(R.id.entryLinearLayout);
        name_txt = (EditText)findViewById(R.id.name_txt);
        phone_txt = (EditText)findViewById(R.id.phone_txt);
        email_txt = (EditText)findViewById(R.id.email_txt);
        entry_btn = (Button)findViewById(R.id.entry_btn);
        entry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d("-진우- 이름 " ,name_txt.getText().toString());
                String name = name_txt.getText().toString();
                String phone = phone_txt.getText().toString();
                String email = email_txt.getText().toString();
                int name_length = name_txt.getText().length();
                int phone_length = phone_txt.getText().length();
                int email_length = email_txt.getText().length();
                //Log.d("-진우- 크기 ", name_length + " " + phone_length + " " + email_length);
                if(name_length <= 0 || phone_length <= 0 || email_length <= 0){
                    Toast.makeText(getApplicationContext(), " 이름, 전화번호, 이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return ;
                }
                //Toast.makeText(getApplicationContext(), "응모하기", Toast.LENGTH_SHORT).show();

                String entry_do = httpAddr + "/and/entry.do?name=" + name + "&phone=" + phone + "&email=" + email + "&ad_seq=" + ad_seq;
                try {
                    eThread async = new eThread(entry_do);
                    async.start();
                } catch (URISyntaxException e1){
                    e1.printStackTrace();
                }
            }
        });


        Button btnFinish=(Button)findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        btn_media = (Button)findViewById(R.id.btn_media);
        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                killMediaPlayer();
            }
        });
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
                case ENTRY_SUCCESS:
                    Toast.makeText(getApplicationContext(), "응모하였습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case ENTRY_FAIL:
                    Toast.makeText(getApplicationContext(), "응모한 이벤트입니다.", Toast.LENGTH_SHORT).show();
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
                String ad_sound_server = json.getString("ad_sound_server");
                String entry_or = json.getString("entry_or");
                ad_seq = json.getString("ad_seq");

                //Log.d("-진우- ad_sound_server " , json.getString("ad_sound_server"));
                //Log.d("-진우- ad_seq", json.getString("ad_seq"));

                if(entry_or.equals("N")){
                    entryLinearLayout.setVisibility(View.GONE);
                }


                audio_url = httpAddr + "/fileupload/sound/" + ad_sound_server;
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

    private class eThread extends Thread {
        String result = "";
        URI uri;

        public eThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            Log.d("-진우- 응모하기", "run() 실행");
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

            Log.d("-진우- 응모결과 ", "-" + result + "-");
            if(result.contains("success")){
                Log.d("-진우- ", "응모하였습니다.");
                handler.sendEmptyMessage(ENTRY_SUCCESS);
            } else {
                Log.d("-진우- ", "응모한 이벤트입니다.");
                handler.sendEmptyMessage(ENTRY_FAIL);
            }
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
