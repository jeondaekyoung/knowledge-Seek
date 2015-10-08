package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
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

    //private static final int MESSAGE_OK = 1;
    //List<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();


    //private String audio_url ;                     //미디어파일위치
    //private MediaPlayer mediaPlayer;        //MediaPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("-진우- ", "알랑랑 시작");
//        String st = ""

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

        /*webView = (WebView)findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
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


        //MediaPlayer
        //audio_url = "http://www.geniusjinu.com/fileupload/sound/201510AS0001.mp3";
        /*try{
            playAudio(audio_url);

        }catch (Exception e){
            e.printStackTrace();
        }*/
        //끝

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
        //killMediaPlayer();
    }

    /*class WebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/

    /*private void playAudio(String url) throws Exception {
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
    }*/
}
