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
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.Eng;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_eng extends Activity {
    private Alarm alarm;
    private Eng engToday;           //오늘의 예문
    private List<Eng> engThrees;    //3일 영어 예문
    private String engThreesEndEngSeq;
    //private WebView webView;
    //final Activity activity = this;
    //private static final String HTTPADDR = "http://www.knowledge-seek.com";
    private static final String HTTPADDR = "http://182.162.143.24";
    //private static final String HTTPADDR = "http://192.168.123.103:8080";

    private static final int MESSAGE_OK = 1;
    private static final int ENGBG_OK = 2;
    private static final int ENGTHREE_OK = 3;

    private String audio_url;       //미디어파일위치
    private MediaPlayer mediaPlayer;    //MediaPlayer

    //컨텐츠
    private LinearLayout engTodayLinear;
    String engbg_name;                  //영어배경 이미지 이름
    //윗부분
    private TextView engTodayDate;              //오늘의 예문 날짜
    private TextView engTodaySentence;      //오늘의 예문 예뮨
    private TextView engTodayMean;            //오늘의 예문 뜻
    //private GestureDetector mGestures = null;       //제스처
    // 아랫 부분
    private LinearLayout eng1linear;
    private LinearLayout eng2linear;
    private LinearLayout eng3linear;
    private TextView eng1t1, eng1t2, eng1t3;
    private TextView eng2t1, eng2t2, eng2t3;
    private TextView eng3t1, eng3t2, eng3t3;

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

        //액티비티실행시 넘어온 Alarm객체가 있는지 확인하여 받아들인다.(볼륨땜시)
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            alarm = (Alarm)bundle.getSerializable("alarm");
        }
        engToday = new Eng();
        engThrees = new ArrayList<Eng>();

        //컨텐츠
        engTodayLinear = (LinearLayout)findViewById(R.id.engTodayLinear);
        //윗 부분
        engTodayDate = (TextView)findViewById(R.id.engTodayDate);
        engTodaySentence = (TextView)findViewById(R.id.engTodaySentence);
        engTodayMean = (TextView)findViewById(R.id.engTodayMean);
        //아랫 부분
        //engthreelinear = (LinearLayout)findViewById(R.id.engthreelinear);
        eng1linear = (LinearLayout)findViewById(R.id.eng1linear);
        eng2linear = (LinearLayout)findViewById(R.id.eng2linear);
        eng3linear = (LinearLayout)findViewById(R.id.eng3linear);
        eng1linear.setOnTouchListener(eng1Listener);
        eng2linear.setOnTouchListener(eng2Listener);
        eng3linear.setOnTouchListener(eng3Listener);
        eng1t1 = (TextView)findViewById(R.id.eng1t1);
        eng1t2 = (TextView)findViewById(R.id.eng1t2);
        eng1t3 = (TextView)findViewById(R.id.eng1t3);
        eng2t1 = (TextView)findViewById(R.id.eng2t1);
        eng2t2 = (TextView)findViewById(R.id.eng2t2);
        eng2t3 = (TextView)findViewById(R.id.eng2t3);
        eng3t1 = (TextView)findViewById(R.id.eng3t1);
        eng3t2 = (TextView)findViewById(R.id.eng3t2);
        eng3t3 = (TextView)findViewById(R.id.eng3t3);

        //서버와 통신하여 서버에 있는 배경이미지파일을 알아온다.
        String bg = HTTPADDR + "/and/engbg.do";
        try {
            engbgThread async = new engbgThread(bg);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
        }

        //서버와 통신하여 오늘의 예문 읽어오기
        //윗부분
       String st = HTTPADDR + "/and/engToday.do";
        try {
            engTodayThread async = new engTodayThread(st);
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
    }


    //통신후 해야할 일
    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case MESSAGE_OK:
                    Log.d("-진우- ", "handler MESSAGE_OK 실행");
                    engTodayDate.setText(engToday.getToday_date());
                    engTodaySentence.setText(engToday.getEng_sentence());
                    engTodayMean.setText(engToday.getEng_mean());
                    audio_url = HTTPADDR + "/fileupload/sound/" + engToday.getEng_sound_server();
                    Log.d("-진우- 소리파일 ", audio_url);
                    try{
                        playAudio(audio_url);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //서버와 통신하여 3개 영어
                    //아랫부분
                    String st3 = HTTPADDR + "/and/engThree.do";
                    try {
                        engThreeThread async = new engThreeThread(st3);
                        async.start();
                    } catch (URISyntaxException e1){
                        e1.printStackTrace();
                    }

                    break;
                case ENGBG_OK:
                    Log.d("-진우- ", "handler ENGBG_OK 실행");
                    String st = HTTPADDR + "/fileupload/bg/" + engbg_name;
                    Log.d("-진우- 배경이미지", st);
                    BitmapFactory.Options bmOptions;
                    bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
                    OpenHttpConnection opHttpCon = new OpenHttpConnection();
                    opHttpCon.execute(engTodayLinear, st);
                    break;
                case ENGTHREE_OK:
                    Log.d("-진우- ", "handler ENGTHREE_OK 실행");
                    eng1t1.setText(engThrees.get(2).getToday_date());
                    eng1t2.setText(engThrees.get(2).getEng_sentence());
                    eng1t3.setText(engThrees.get(2).getEng_mean());
                    eng2t1.setText(engThrees.get(1).getToday_date());
                    eng2t2.setText(engThrees.get(1).getEng_sentence());
                    eng2t3.setText(engThrees.get(1).getEng_mean());
                    eng3t1.setText(engThrees.get(0).getToday_date());
                    eng3t2.setText(engThrees.get(0).getEng_sentence());
                    eng3t3.setText(engThrees.get(0).getEng_mean());
                    break;
            }
        }
    };

    //컨텐츠 위부분 영어정보를 받아온다.
    private class engTodayThread extends Thread {
        String result = "";
        URI uri;

        public engTodayThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            Log.d("-진우- ", "engTodayThread.run() 실행");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(uri);
            try{
                //HttpClient httpclient = new DefaultHttpClient();
                //HttpPost httppost = new HttpPost(uri);

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
            //자원 반환이 이루어지지않은건가?
            finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 오늘의 영어 정보 ", result);
            try{
                JSONObject json = new JSONObject(result);

                stringFormatDate(json.getString("today_date"));

                engToday.setEng_seq(json.getString("eng_seq"));
                engToday.setToday_date(stringFormatDate(json.getString("today_date")));
                engToday.setEng_sentence(json.getString("eng_sentence"));
                engToday.setEng_mean(json.getString("eng_mean"));
                engToday.setEng_sound_server(json.getString("eng_sound_server"));
            }catch(JSONException e){
                Log.d("-진우- JSON", "Error parsing data " + e.toString());
            }

            handler.sendEmptyMessage(MESSAGE_OK);
        }
    }

    //컨텐츠 아랫부분에 들어갈 내용을 받아온다
    private class engThreeThread extends Thread {
        String result = "";
        URI uri;

        public engThreeThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            Log.d("-진우- ", "engThreeThread.run() 실행");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(uri);
            try{
                //HttpClient httpclient = new DefaultHttpClient();
                //HttpPost httppost = new HttpPost(uri);

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
            //자원 반환이 이루어지지않은건가?
            finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 3개 영어정보 ", result);
            try{
                JSONArray jsons = new JSONArray(result);
                Eng eng;
                for(int i = 0; i < jsons.length() ; i++){
                    eng = new Eng();
                    //Log.d("-진우-", i + "번째 " + jsons.getJSONObject(i).getString("eng_seq"));
                    eng.setEng_seq(jsons.getJSONObject(i).getString("eng_seq"));
                    eng.setToday_date(stringFormatDate(jsons.getJSONObject(i).getString("today_date")));
                    eng.setEng_sentence(jsons.getJSONObject(i).getString("eng_sentence"));
                    eng.setEng_mean(jsons.getJSONObject(i).getString("eng_mean"));
                    eng.setEng_sound_server(jsons.getJSONObject(i).getString("eng_sound_server"));
                    engThrees.add(eng);
                }
                /*for(Eng e : engThrees){
                    Log.d("-진우- 3개 결과 ", e.toString());
                }*/
            }catch(JSONException e){
                Log.d("-진우- JSON", "Error parsing data " + e.toString());
            }

            handler.sendEmptyMessage(ENGTHREE_OK);
        }
    }

    //컨텐츠 아랫부분에 들어갈 내용을 받아온다. (오른쪽, 왼쪽 이동시)
    private class engThreeReThread extends Thread {
        String result = "";
        String eng_seq = "";
        String orien = "";
        URI uri;

        public engThreeReThread(String url, String mEng_seq, String mOrien) throws URISyntaxException {
            uri = new URI(url);
            eng_seq = mEng_seq;
            orien = mOrien;
        }

        @Override
        public void run() {
            Log.d("-진우- ", "engThreeThread.run() 실행");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(uri);


            try{
                //HttpClient httpclient = new DefaultHttpClient();
                //HttpPost httppost = new HttpPost(uri);
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("eng_seq", eng_seq));
                nvps.add(new BasicNameValuePair("orien", orien));
                httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

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
            //자원 반환이 이루어지지않은건가?
            finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 3개 영어정보 ", result);
            try{
                JSONArray jsons = new JSONArray(result);
                engThrees.clear();
                Eng eng;
                for(int i = 0; i < jsons.length() ; i++){
                    eng = new Eng();
                    //Log.d("-진우-", i + "번째 " + jsons.getJSONObject(i).getString("eng_seq"));
                    eng.setEng_seq(jsons.getJSONObject(i).getString("eng_seq"));
                    eng.setToday_date(stringFormatDate(jsons.getJSONObject(i).getString("today_date")));
                    eng.setEng_sentence(jsons.getJSONObject(i).getString("eng_sentence"));
                    eng.setEng_mean(jsons.getJSONObject(i).getString("eng_mean"));
                    eng.setEng_sound_server(jsons.getJSONObject(i).getString("eng_sound_server"));
                    engThrees.add(eng);
                }
                /*for(Eng e : engThrees){
                    Log.d("-진우- 3개 결과 ", e.toString());
                }*/
            }catch(JSONException e){
                Log.d("-진우- JSON", "Error parsing data " + e.toString());
            }

            handler.sendEmptyMessage(ENGTHREE_OK);
        }
    }



    //배경이미지 위치정보를 서버에 받는다.
    private class engbgThread extends Thread {
        String result = "";
        URI uri;

        public engbgThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            Log.d("-진우- ", "engbgThread.run() 실행");
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
                Log.e("log_tag", "Error in http connection " + e.toString());
            } finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 배경이미지정보 ", result);
            try {
                JSONObject json = new JSONObject(result);

                engbg_name = json.getString("eng_bg_server");
            } catch(JSONException e){
                Log.e("-진우- JSON", "Error parsing data " + e.toString());
            }
            handler.sendEmptyMessage(ENGBG_OK);
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

    //아랫부분 터치이벤트
    private final View.OnTouchListener eng1Listener = new View.OnTouchListener(){
        int startX, endX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getActionMasked()){
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_DOWN:
                    startX = (int)motionEvent.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int)motionEvent.getX();
                    Log.d("-진우- 어떻게 된거니? ", startX + ", " + endX + " um");
                    if(startX != 0 && endX != 0) {
                        if (startX < endX && endX-startX > 30) {
                            Log.d("-진우- engthree", "증가 왼쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(2).getEng_seq(), "left");
                                async.start();
                            } catch (URISyntaxException e1){
                                e1.printStackTrace();
                            }
                        }
                        if (startX > endX && startX-endX > 30) {
                            Log.d("-진우- engthree", "감소 오른쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(0).getEng_seq(), "right");
                                async.start();
                            } catch (URISyntaxException e1){
                                e1.printStackTrace();
                            }
                        }
                        if(startX == endX || Math.abs(endX-startX) <= 30){
                            Log.d("-진우- eng1", "이동1");
                            engTodayDate.setText(engThrees.get(2).getToday_date());
                            engTodaySentence.setText(engThrees.get(2).getEng_sentence());
                            engTodayMean.setText(engThrees.get(2).getEng_mean());
                            audio_url = HTTPADDR + "/fileupload/sound/" + engThrees.get(2).getEng_sound_server();
                            Log.d("-진우- 소리파일 ", audio_url);
                            try{
                                playAudio(audio_url);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
            return true;
        }
    };
    private final View.OnTouchListener eng2Listener = new View.OnTouchListener(){
        int startX, endX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getActionMasked()){
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_DOWN:
                    startX = (int)motionEvent.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int)motionEvent.getX();
                    Log.d("-진우- 어떻게 된거니? ", startX + ", " + endX + " um");

                    if(startX != 0 && endX != 0) {
                        if (startX < endX && endX-startX > 30) {
                            Log.d("-진우- engthree", "증가 왼쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(2).getEng_seq(), "left");
                                async.start();
                            } catch (URISyntaxException e1){
                                e1.printStackTrace();
                            }
                        }
                        if (startX > endX && startX-endX > 30) {
                            Log.d("-진우- engthree", "감소 오른쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(0).getEng_seq(), "right");
                                async.start();
                            } catch (URISyntaxException e1){
                                e1.printStackTrace();
                            }
                        }
                        if(startX == endX || Math.abs(endX-startX) <= 30){
                            Log.d("-진우- eng1", "이동2");
                            engTodayDate.setText(engThrees.get(1).getToday_date());
                            engTodaySentence.setText(engThrees.get(1).getEng_sentence());
                            engTodayMean.setText(engThrees.get(1).getEng_mean());
                            audio_url = HTTPADDR + "/fileupload/sound/" + engThrees.get(1).getEng_sound_server();
                            Log.d("-진우- 소리파일 ", audio_url);
                            try{
                                playAudio(audio_url);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
            return true;
        }
    };
    private final View.OnTouchListener eng3Listener = new View.OnTouchListener(){
        int startX, endX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getActionMasked()){
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_DOWN:
                    startX = (int)motionEvent.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int)motionEvent.getX();
                    Log.d("-진우- 어떻게 된거니? ", startX + ", " + endX + " um");

                    if(startX != 0 && endX != 0) {
                        if (startX < endX && endX-startX > 30) {
                            Log.d("-진우- engthree", "증가 왼쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(2).getEng_seq(), "left");
                                async.start();
                            } catch (URISyntaxException e1){
                                e1.printStackTrace();
                            }
                        }
                        if (startX > endX && startX-endX > 30) {
                            Log.d("-진우- engthree", "감소 오른쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(0).getEng_seq(), "right");
                                async.start();
                            } catch (URISyntaxException e1){
                                e1.printStackTrace();
                            }
                        }
                        if(startX == endX || Math.abs(endX-startX) <= 30){
                            Log.d("-진우- eng1", "이동3");
                            engTodayDate.setText(engThrees.get(0).getToday_date());
                            engTodaySentence.setText(engThrees.get(0).getEng_sentence());
                            engTodayMean.setText(engThrees.get(0).getEng_mean());
                            audio_url = HTTPADDR + "/fileupload/sound/" + engThrees.get(0).getEng_sound_server();
                            Log.d("-진우- 소리파일 ", audio_url);
                            try{
                                playAudio(audio_url);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
            }
            return true;
        }
    };

    //소리파일 재생
    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(url);
        //mediaPlayer.setVolume(100f, 100f);
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

    //배경  이미지불러오기
    private class OpenHttpConnection extends AsyncTask<Object, Void, Bitmap> {

        private LinearLayout bgLinearLayout;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap mBitmap = null;
            bgLinearLayout = (LinearLayout)objects[0];
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
            bgLinearLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
        }
    }

    //날짜 변경
    private String stringFormatDate(String today){
        String[] todays = today.split("-");
        return new StringBuilder(todays[1]).append("/").append(todays[2]).toString();
    }
}
