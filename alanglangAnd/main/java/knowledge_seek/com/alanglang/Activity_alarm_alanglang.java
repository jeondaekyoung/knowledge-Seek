package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

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

import knowledge_seek.com.Ad;
import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;
import knowledge_seek.com.youtube.DeveloperKey;
import knowledge_seek.com.youtube.YouTubeFailureRecoveryActivity;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_alanglang extends YouTubeFailureRecoveryActivity {

    private Alarm alarm;                            //알람
    private Ad ad;                                    //광고정보
    private WebView webView;
    final Activity activity = this;
    private static final String HTTPADDR = "http://www.knowledge-seek.com";
    //private static final String HTTPADDR = "http://182.162.143.24";
    private String ad_seq;                           //광고 시퀀스

    //통신 결과
    private static final int MESSAGE_OK = 1;
    private static final int ENTRY_SUCCESS = 2;
    private static final int ENTRY_FAIL = 3;

    //응모하기
    private LinearLayout entryLinearLayout;
    private EditText name_txt;
    private EditText phone_txt;
    private EditText email_txt;
    private Button entry_btn;

    private ImageButton btn_media;                   //소리끄기
    private boolean sound_act = true;               //소리 on, off
    private int nCurrentVolumn;                     //현재 볼륨


    private MediaPlayer mediaPlayer;        //MediaPlayer
    private YouTubePlayerView youTubeView;  //유뷰브
    private ImageView imageView;            //이미지광고

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

        //액티비티실행시 넘어온 Alarm객체가 있는지 확인하여 받아들인다.(볼륨땜시)
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            alarm = (Alarm)bundle.getSerializable("alarm");
        }
        ad = new Ad();

        //유튜브광고시 필요
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DeveloperKey.DEVELOPER_KEY, this);
        //->
        //이미지,소리광고시 필요
        imageView = (ImageView)findViewById(R.id.imageView);
        //외부광고
        webView = (WebView)findViewById(R.id.webView);

        //서버와 통신하여 서버에 있는 광고정보를 받는다.
        String st = HTTPADDR + "/and/adInfo.do";
        try {
            adInfoThread async = new adInfoThread(st);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
        }

        //응모하기(리니어레이아웃)있을시 필요
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
                Toast.makeText(getApplicationContext(), "응모하기", Toast.LENGTH_SHORT).show();

                String entry_do = HTTPADDR + "/and/entry.do?name=" + name + "&phone=" + phone + "&email=" + email + "&ad_seq=" + ad_seq;
                try {
                    eThread async = new eThread(entry_do);
                    async.start();
                } catch (URISyntaxException e1){
                    e1.printStackTrace();
                }
            }
        });


        //소리끄기
        btn_media = (ImageButton)findViewById(R.id.btn_media);
        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sound_act == true){
                    sound_act = false;
                    ((ImageButton)view).setImageResource(R.drawable.btn_sound_off);
                    if(ad.getYoutube_addr() != null){
                        AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                        nCurrentVolumn = am.getStreamVolume(AudioManager.STREAM_MUSIC);
                        am.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    }
                    killMediaPlayer();
                } else {
                    sound_act = true;
                    ((ImageButton)view).setImageResource(R.drawable.btn_sound_on);
                    if(ad.getYoutube_addr() != null){
                        AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                        am.setStreamVolume(AudioManager.STREAM_MUSIC, nCurrentVolumn, 0);
                    } else {
                        String st = HTTPADDR + "/fileupload/sound/" + ad.getAd_sound_server();
                        Log.d("-진우- 소리파일 ", st);
                        try{
                            playAudio(st);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //알람끄기
        Button btnFinish=(Button)findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }


    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MESSAGE_OK:
                    //Log.d("-진우- ", "handler MESSAGE_OK 실행");
                    //유뷰트광고
                    if(ad.getYoutube_addr() != null){
                        youTubeView.setVisibility(View.VISIBLE);
                        if(alarm != null){
                            Log.d("-진우- 유뷰브소리 " , String.valueOf(alarm.getVolume()));
                            AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, alarm.getVolume(), 0);
                        } else {
                            //Log.d("-진우- 유뷰브소리 " , "월래 소리냄");
                        }
                    }
                    //이미지,소리광고
                    if(ad.getAd_image_server() != null){
                        imageView.setVisibility(View.VISIBLE);

                        String st = HTTPADDR + "/fileupload/image/" + ad.getAd_image_server();
                        //Log.d("-진우- 이미지", st);
                        BitmapFactory.Options bmOptions;
                        bmOptions = new BitmapFactory.Options();
                        bmOptions.inSampleSize = 1;
                        OpenHttpConnection opHttpCon = new OpenHttpConnection();
                        opHttpCon.execute(imageView, st);
                    }

                    //외부광고
                    if(ad.getAd_url() != null){
                        webView.setVisibility(View.VISIBLE);
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl("http://" + ad.getAd_url());
                        webView.setWebViewClient(new AdWebViewClient());
                        webView.setWebChromeClient(new WebChromeClient() {
                            @Override
                            public void onProgressChanged(WebView view, int progress) {
                                //매개변수로 제공되는 progress의 범위는 0부터 10,000까지 사용한다.
                                //웹 페이지가 100% 출력될때까지 화면 출력을 지연한다.
                                activity.setProgress(progress * 100);
                            }
                        });
                    }

                    //소리재생
                    if(ad.getAd_sound_server() != null){
                        sound_act = true;
                        try{
                            String st = HTTPADDR + "/fileupload/sound/" + ad.getAd_sound_server();
                            Log.d("-진우- 소리재생 ", st);
                            playAudio(st);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    //응모하기
                    if(ad.getEntry_or() != null && ad.getEntry_or().equals("Y")){
                        entryLinearLayout.setVisibility(View.VISIBLE);
                    }
                    break;
                //<- 응모하기
                case ENTRY_SUCCESS:
                    Toast.makeText(getApplicationContext(), "응모하였습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case ENTRY_FAIL:
                    Toast.makeText(getApplicationContext(), "응모한 이벤트입니다.", Toast.LENGTH_SHORT).show();
                    break;
                //->
            }
        }
    };

    //<-유뷰트 playerview
    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if(!b){
            //youTubePlayer.loadVideo("YQHsXMglC9A");
            if(ad.getYoutube_addr() != null){
                youTubePlayer.loadVideo(ad.getYoutube_addr());
            }
        }
    }
    //->

    private class adInfoThread extends Thread {
        String result = "";
        URI uri;

        public adInfoThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            //Log.d("-진우- ", "run() 실행");
            HttpClient httpclient =  null;
            HttpPost httppost = null;
            try{
                httpclient = new DefaultHttpClient();
                httppost = new HttpPost(uri);

                HttpParams params = httpclient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

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

            //Log.d("-진우- 광고정보 ", result);
            try{
                JSONObject json = new JSONObject(result);

                //null이 넘어올때는 null이 넘어오지만 문자 null이 넘어온다.
                /*Log.d("-진우1- ", json.getString("ad_image_server"));
                Log.d("-진우2- ", String.valueOf(json.getString("ad_image_server").length()));
                Log.d("-진우3- ", String.valueOf(json.getString("ad_image_server") == null));
                ad.setAd_image_server(json.getString("ad_image_server"));
                Log.d("-진우4- ", ad.getAd_image_server());
                Log.d("-진우5- ", String.valueOf(ad.getAd_image_server().length()));*/

                if(!json.getString("ad_sound_server").equals("null")){
                    ad.setAd_sound_server(json.getString("ad_sound_server"));
                }
                if(!json.getString("ad_image_server").equals("null")){
                    ad.setAd_image_server(json.getString("ad_image_server"));
                }
                if(!json.getString("ad_url").equals("null")){
                    ad.setAd_url(json.getString("ad_url"));
                }
                if(!json.getString("youtube_addr").equals("null")){
                    ad.setYoutube_addr(json.getString("youtube_addr"));
                }
                if(!json.getString("entry_or").equals("null")){
                    ad.setEntry_or(json.getString("entry_or"));
                }
                //Log.d("-진우- 광고정보 ", ad.toString());
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
            //Log.d("-진우- 응모하기", "run() 실행");

            HttpClient httpclient = null;
            HttpPost httppost = null;
            try{
                //자원반납으로 인하여 위로 옮긴다?
                httpclient = new DefaultHttpClient();
                httppost = new HttpPost(uri);

                HttpParams params = httpclient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

                HttpResponse response = httpclient.execute(httppost);
                StatusLine status = response.getStatusLine();

                //Log.d("-진우- ", String.valueOf(status.getStatusCode()));
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
            //자원 반환이 이루어지지않은건가?
            finally {
                httpclient.getConnectionManager().shutdown();
            }


            //Log.d("-진우- 응모결과 ", "-" + result + "-");
            if(result.contains("success")){
                //Log.d("-진우- ", "응모하였습니다.");
                handler.sendEmptyMessage(ENTRY_SUCCESS);
            } else {
                //Log.d("-진우- ", "응모한 이벤트입니다.");
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
            //Log.d("Error ", "Oh no");
        }
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(url);
        AudioManager am = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        if(alarm == null){
            Log.d("-진우- 알람 ", "널이다");
            //am.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
        } else {
            Log.d("-진우- 알람 ", alarm.toString());
            am.setStreamVolume(AudioManager.STREAM_MUSIC, alarm.getVolume(), 0);
        }

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

    //이미지광고시 이미지불러오기
    private class OpenHttpConnection extends AsyncTask<Object, Void, Bitmap>{

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


}
