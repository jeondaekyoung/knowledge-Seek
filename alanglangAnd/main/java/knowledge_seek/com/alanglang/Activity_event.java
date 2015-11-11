package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.receiver.StaticWakeLock;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_event extends Activity {

    private Alarm alarm;
    private WebView webView;
    final Activity activity = this;
    private static final String AD_ENTRY = "http://www.knowledge-seek.com/and/adEntry.do";
    //private static final String AD_ENTRY = "http://182.162.143.24/and/adEntry.do";

    private static final int MESSAGE_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Log.d("-진우- ", "당첨자 확인");

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(AD_ENTRY);
        webView.setWebViewClient(new AdEntryWebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int progress) {
                //매개변수로 제공되는 progress의 범위는 0부터 10,000까지 사용한다.
                //웹 페이지가 100% 출력될 때까지 화면 출력을 지연한다.
                activity.setProgress(progress * 100);
            }
        });

    }


    class AdEntryWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);   //HTML문서 내 존재하는 새로운 URL는 웹뷰에서 로드한다.
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.d("Error ", "Oh no");
        }
    }


}
