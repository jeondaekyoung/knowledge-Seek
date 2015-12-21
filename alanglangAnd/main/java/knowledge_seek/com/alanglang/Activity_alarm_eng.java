package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

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
import java.io.IOException;
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
    private static final String HTTPADDR = "http://www.knowledge-seek.com";

    private static final int MESSAGE_OK = 1;
    private static final int ENGBG_OK = 2;
    private static final int ENGTHREE_OK = 3;

    private String audio_url;       //미디어파일위치
    private MediaPlayer mediaPlayer;    //MediaPlayer
    private ImageButton btn_media;       //소리끄기
    private boolean sound_act = true;   //소리 on, off

    //컨텐츠
    private LinearLayout engTodayLinear;
    String engbg_name;                  //영어배경 이미지 이름
    //윗부분
    private TextView engTodayDate;              //오늘의 예문 날짜
    private TextView engTodaySentence;      //오늘의 예문 예뮨
    private TextView engTodayMean;            //오늘의 예문 뜻
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
        if (bundle != null) {
            alarm = (Alarm) bundle.getSerializable("alarm");
        }
        engToday = new Eng();
        engThrees = new ArrayList<Eng>();

        //컨텐츠
        engTodayLinear = (LinearLayout) findViewById(R.id.engTodayLinear);
        //윗 부분
        engTodayDate = (TextView) findViewById(R.id.engTodayDate);
        engTodaySentence = (TextView) findViewById(R.id.engTodaySentence);
        engTodayMean = (TextView) findViewById(R.id.engTodayMean);
        //아랫 부분
        //engthreelinear = (LinearLayout)findViewById(R.id.engthreelinear);
        eng1linear = (LinearLayout) findViewById(R.id.eng1linear);
        eng2linear = (LinearLayout) findViewById(R.id.eng2linear);
        eng3linear = (LinearLayout) findViewById(R.id.eng3linear);
        eng1linear.setOnTouchListener(eng1Listener);
        eng2linear.setOnTouchListener(eng2Listener);
        eng3linear.setOnTouchListener(eng3Listener);
        eng1t1 = (TextView) findViewById(R.id.eng1t1);
        eng1t2 = (TextView) findViewById(R.id.eng1t2);
        eng1t3 = (TextView) findViewById(R.id.eng1t3);
        eng2t1 = (TextView) findViewById(R.id.eng2t1);
        eng2t2 = (TextView) findViewById(R.id.eng2t2);
        eng2t3 = (TextView) findViewById(R.id.eng2t3);
        eng3t1 = (TextView) findViewById(R.id.eng3t1);
        eng3t2 = (TextView) findViewById(R.id.eng3t2);
        eng3t3 = (TextView) findViewById(R.id.eng3t3);

        //서버와 통신하여 서버에 있는 배경이미지파일, 오늘의 예문을 알아온다.
        OpenHttpConnection opHttpCon = new OpenHttpConnection();
        opHttpCon.execute(engTodayLinear);

        //소리끄기
        btn_media = (ImageButton) findViewById(R.id.btn_media);
        btn_media.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sound_act == true) {
                    sound_act = false;
                    ((ImageButton) view).setImageResource(R.drawable.btn_sound_off);
                    killMediaPlayer();
                } else {
                    sound_act = true;
                    ((ImageButton) view).setImageResource(R.drawable.btn_sound_on);
                    audio_url = HTTPADDR + "/fileupload/sound/" + engToday.getEng_sound_server();
                    Log.d("-진우- 소리파일 ", audio_url);
                    try {
                        playAudio(audio_url);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Button btnFinish = (Button) findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }


    //통신후 해야할 일
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ENGTHREE_OK:
                    //Log.d("-진우- ", "handler ENGTHREE_OK 실행");
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
            //Log.d("-진우- ", "engThreeThread.run() 실행");

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(uri);
            try {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("eng_seq", eng_seq));
                nvps.add(new BasicNameValuePair("orien", orien));
                httppost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

                HttpParams params = httpclient.getParams();
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                HttpConnectionParams.setSoTimeout(params, 10000);

                HttpResponse response = httpclient.execute(httppost);
                StatusLine status = response.getStatusLine();

                //Log.d("-진우- ", String.valueOf(status.getStatusCode()));
                if (status.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 64);
                    StringBuilder sb = new StringBuilder();
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    is.close();
                    result = sb.toString();
                }
            } catch (Exception e) {
                Log.d("log_tag", "Error in http connection " + e.toString());
            }
            //자원 반환이 이루어지지않은건가?
            finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 3개 영어정보 ", result);
            try {
                JSONArray jsons = new JSONArray(result);
                engThrees.clear();
                Eng eng;
                for (int i = 0; i < jsons.length(); i++) {
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
            } catch (JSONException e) {
                Log.d("-진우- JSON", "Error parsing data " + e.toString());
            }
            handler.sendEmptyMessage(ENGTHREE_OK);
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
    private final View.OnTouchListener eng1Listener = new View.OnTouchListener() {
        int startX, endX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_DOWN:
                    startX = (int) motionEvent.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int) motionEvent.getX();
                    //Log.d("-진우- 위치 ", startX + ", " + endX + " um");
                    if (startX != 0 && endX != 0) {
                        if (startX < endX && endX - startX > 30) {
                            //Log.d("-진우- engthree", "증가 왼쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(2).getEng_seq(), "left");
                                async.start();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (startX > endX && startX - endX > 30) {
                            //Log.d("-진우- engthree", "감소 오른쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(0).getEng_seq(), "right");
                                async.start();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (startX == endX || Math.abs(endX - startX) <= 30) {
                            //Log.d("-진우- eng1", "이동1");
                            engToday = engThrees.get(2);
                            engTodayDate.setText(engToday.getToday_date());
                            engTodaySentence.setText(engToday.getEng_sentence());
                            engTodayMean.setText(engToday.getEng_mean());
                            audio_url = HTTPADDR + "/fileupload/sound/" + engToday.getEng_sound_server();
                            Log.d("-진우- 소리파일 ", audio_url);
                            if (sound_act == true) {
                                try {
                                    playAudio(audio_url);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    break;
            }
            return true;
        }
    };
    private final View.OnTouchListener eng2Listener = new View.OnTouchListener() {
        int startX, endX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_DOWN:
                    startX = (int) motionEvent.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int) motionEvent.getX();
                    //Log.d("-진우- 위치 ", startX + ", " + endX + " um");

                    if (startX != 0 && endX != 0) {
                        if (startX < endX && endX - startX > 30) {
                            //Log.d("-진우- engthree", "증가 왼쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(2).getEng_seq(), "left");
                                async.start();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (startX > endX && startX - endX > 30) {
                            //Log.d("-진우- engthree", "감소 오른쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(0).getEng_seq(), "right");
                                async.start();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (startX == endX || Math.abs(endX - startX) <= 30) {
                            //Log.d("-진우- eng1", "이동2");
                            engToday = engThrees.get(1);
                            engTodayDate.setText(engToday.getToday_date());
                            engTodaySentence.setText(engToday.getEng_sentence());
                            engTodayMean.setText(engToday.getEng_mean());
                            audio_url = HTTPADDR + "/fileupload/sound/" + engToday.getEng_sound_server();
                            Log.d("-진우- 소리파일 ", audio_url);
                            if (sound_act == true) {
                                try {
                                    playAudio(audio_url);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    break;
            }
            return true;
        }
    };
    private final View.OnTouchListener eng3Listener = new View.OnTouchListener() {
        int startX, endX;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getActionMasked()) {
                case MotionEvent.ACTION_MOVE:

                    break;
                case MotionEvent.ACTION_DOWN:
                    startX = (int) motionEvent.getX();
                    break;
                case MotionEvent.ACTION_UP:
                    endX = (int) motionEvent.getX();
                    //Log.d("-진우- 위치  ", startX + ", " + endX + " um");
                    if (startX != 0 && endX != 0) {
                        if (startX < endX && endX - startX > 30) {
                            //Log.d("-진우- engthree", "증가 왼쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(2).getEng_seq(), "left");
                                async.start();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (startX > endX && startX - endX > 30) {
                            //Log.d("-진우- engthree", "감소 오른쪽 화면 불러옴");
                            //아랫부분 다시 불러오기
                            String st = HTTPADDR + "/and/engThreeRe.do";
                            try {
                                engThreeReThread async = new engThreeReThread(st, engThrees.get(0).getEng_seq(), "right");
                                async.start();
                            } catch (URISyntaxException e1) {
                                e1.printStackTrace();
                            }
                        }
                        if (startX == endX || Math.abs(endX - startX) <= 30) {
                            //Log.d("-진우- eng1", "이동3");
                            engToday = engThrees.get(0);
                            engTodayDate.setText(engToday.getToday_date());
                            engTodaySentence.setText(engToday.getEng_sentence());
                            engTodayMean.setText(engToday.getEng_mean());
                            audio_url = HTTPADDR + "/fileupload/sound/" + engToday.getEng_sound_server();
                            Log.d("-진우- 소리파일 ", audio_url);
                            if (sound_act == true) {
                                try {
                                    playAudio(audio_url);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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
        if (alarm == null) {
            //Log.d("-진우- 알람 ", "널이다");
            //am.setStreamVolume(AudioManager.STREAM_MUSIC, 8, 0);
        } else {
            Log.d("-진우- 알람 ", alarm.toString());
            am.setStreamVolume(AudioManager.STREAM_MUSIC, alarm.getVolume(), 0);
        }

        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //배경  이미지불러오기, 오늘의 예문 읽어오기
    private class OpenHttpConnection extends AsyncTask<Object, Void, Bitmap> {

        private ProgressDialog dialog = new ProgressDialog(Activity_alarm_eng.this);
        private LinearLayout bgLinearLayout;

        @Override
        protected void onPreExecute() {
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("잠시만 기달려주세요");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... objects) {
            Bitmap mBitmap = null;
            String result = null;
            bgLinearLayout = (LinearLayout) objects[0];

            OkHttpClient client = new OkHttpClient();

            //1. 서버와 통신하여 서버에 있는 배경이미지파일을 알아온다.
            Request request1 = new Request.Builder()
                    .url(HTTPADDR + "/and/engbg.do")
                    .build();
            try {
                Response response = client.newCall(request1).execute();
                if (!response.isSuccessful()) {
                    Log.d("-진우-", "서버와의 통신이 올바르지않습니다");
                }
                result = response.body().string();
                Log.d("-진우-", "첫번째 결과 : " + result);
                JSONObject json = new JSONObject(result);
                engbg_name = json.getString("eng_bg_server");
                Log.d("-진우-", "두번째 결과 : " + engbg_name);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //이미지 불러오기
            InputStream in = null;
            try {
                in = new URL(HTTPADDR + "/fileupload/bg/" + engbg_name).openStream();
                mBitmap = BitmapFactory.decodeStream(in);
                in.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //2. 서버와 통신하여 오늘의 예문 읽어오기
            //윗부분 컨텐츠
            Request request2 = new Request.Builder()
                    .url(HTTPADDR + "/and/engToday.do")
                    .build();
            try {
                Response response = client.newCall(request2).execute();
                if (!response.isSuccessful()) {
                    Log.d("-진우-", "서버와의 통신이 올바르지않습니다");
                }
                result = response.body().string();
                Log.d("-진우-", "세번째 결과 : " + result);
                JSONObject json = new JSONObject(result);
                stringFormatDate(json.getString("today_date"));

                engToday.setEng_seq(json.getString("eng_seq"));
                engToday.setToday_date(stringFormatDate(json.getString("today_date")));
                engToday.setEng_sentence(json.getString("eng_sentence"));
                engToday.setEng_mean(json.getString("eng_mean"));
                engToday.setEng_sound_server(json.getString("eng_sound_server"));
                Log.d("-진우-", "네번째 결과 : " + engToday.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //3. 서버와 통신하여 3개 영어
            //아랫부분
            Request request3 = new Request.Builder()
                    .url(HTTPADDR + "/and/engThree.do")
                    .build();
            try {
                Response response = client.newCall(request3).execute();
                if (!response.isSuccessful()) {
                    Log.d("-진우-", "서버와의 통신이 올바르지않습니다");
                }
                result = response.body().string();
                Log.d("-진우-", "다섯번째 결과 : " + result);
                JSONArray jsons = new JSONArray(result);
                Eng eng;
                for (int i = 0; i < jsons.length(); i++) {
                    eng = new Eng();
                    //Log.d("-진우-", i + "번째 " + jsons.getJSONObject(i).getString("eng_seq"));
                    eng.setEng_seq(jsons.getJSONObject(i).getString("eng_seq"));
                    eng.setToday_date(stringFormatDate(jsons.getJSONObject(i).getString("today_date")));
                    eng.setEng_sentence(jsons.getJSONObject(i).getString("eng_sentence"));
                    eng.setEng_mean(jsons.getJSONObject(i).getString("eng_mean"));
                    eng.setEng_sound_server(jsons.getJSONObject(i).getString("eng_sound_server"));
                    engThrees.add(eng);
                }
                for (Eng e : engThrees) {
                    Log.d("-진우- 여섯번째 영어예문 3개 결과 ", e.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //1. 이미지 불러오기
            if (bitmap != null) {
                bgLinearLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
            }

            //2. 오늘의 영어예문 (컨텐츠 위부분 영어정보) 셋팅
            engTodayDate.setText(engToday.getToday_date());
            engTodaySentence.setText(engToday.getEng_sentence());
            engTodayMean.setText(engToday.getEng_mean());
            audio_url = HTTPADDR + "/fileupload/sound/" + engToday.getEng_sound_server();
            Log.d("-진우- 소리파일 ", audio_url);
            sound_act = true;
            try {
                playAudio(audio_url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //3. 컨텐츠 아랫부분 영어예문 3개
            eng1t1.setText(engThrees.get(2).getToday_date());
            eng1t2.setText(engThrees.get(2).getEng_sentence());
            eng1t3.setText(engThrees.get(2).getEng_mean());
            eng2t1.setText(engThrees.get(1).getToday_date());
            eng2t2.setText(engThrees.get(1).getEng_sentence());
            eng2t3.setText(engThrees.get(1).getEng_mean());
            eng3t1.setText(engThrees.get(0).getToday_date());
            eng3t2.setText(engThrees.get(0).getEng_sentence());
            eng3t3.setText(engThrees.get(0).getEng_mean());


            dialog.dismiss();
            super.onPostExecute(bitmap);
        }
    }

    //날짜 변경
    private String stringFormatDate(String today) {
        String[] todays = today.split("-");
        return new StringBuilder(todays[1]).append("/").append(todays[2]).toString();
    }
}
