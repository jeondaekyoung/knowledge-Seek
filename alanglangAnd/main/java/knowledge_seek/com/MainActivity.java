package knowledge_seek.com;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

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
import java.util.List;

import knowledge_seek.com.alanglang.Activity_add;
import knowledge_seek.com.alanglang.Activity_alarm_alanglang;
import knowledge_seek.com.alanglang.Activity_alarm_eng;
import knowledge_seek.com.alanglang.Activity_event;
import knowledge_seek.com.alanglang.R;
import knowledge_seek.com.database.Database;

public class MainActivity extends BaseActivity {

    ListView alarmListView;
    AlarmListAdapter alarmListAdapter;

    //private static final String HTTPADDR = "http://www.knowledge-seek.com";
    private static final String HTTPADDR = "http://182.162.143.24";
    LinearLayout main_linearLayout;         //메인 리니어아웃
    String mainbg_name;                         //메인배경 이미지 이름

    //통신 결과
    private static final int MESSAGE_OK = 1;
    //private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmListView = (ListView) findViewById(R.id.listView);
        main_linearLayout = (LinearLayout)findViewById(R.id.main_linearLayout);


        //서버와 통신하여 서버에 있는 배경이미지파일을 알아온다.
        String st = HTTPADDR + "/and/mainbg.do";
        try {
            mainbgThread async = new mainbgThread(st);
            async.start();
        } catch (URISyntaxException e1){
            e1.printStackTrace();
        }

        //롱클릭으로 삭제
        alarmListView.setLongClickable(true);
        alarmListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                //진동발생
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);

                final Alarm alarm = (Alarm)alarmListAdapter.getItem(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("삭제");
                dialog.setMessage("이 알람을 삭제하시겠습니까?");
                dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        //데이터베이스 생성
                        Database.init(MainActivity.this);
                        //알람삭제
                        Database.deleteEntry(alarm);
                        //AlarmService를 AlarmServiceBroadcastReceiver 등록
                        MainActivity.this.callAlarmScheduleService();

                        //데이터베이스에서 알람리스트를 읽어와서 AlarmListAdapter의 alarm에 셋팅한다.
                        updateAlarmList();
                    }
                });
                dialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                dialog.show();

                return true;
            }
        });

        //AlarmService를 브로드캐스트 등록
        callAlarmScheduleService();


        //mainActivity를 파라미터로 받아서 AlarmListAdapter 객체 생성
        alarmListAdapter = new AlarmListAdapter(this);


        // 리스트뷰에 어댑터 설정
        this.alarmListView.setAdapter(alarmListAdapter);
        alarmListView.setVerticalScrollBarEnabled(false);

        // 새로 정의한 리스너로 객체를 만들어 설정
        alarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //사용자가 가상화면의 키를 눌렀을 경우 진동발생
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                //Log.d("-진우- 눌린알람 : ", String.valueOf(position));

                //클릭된 알람 불러오기
                Alarm alarm = (Alarm) alarmListAdapter.getItem(position);
                //클릭됐을 경우 클릭된 alarm과 설정화면으로 이동한다.
                Intent intent = new Intent(MainActivity.this, Activity_add.class);
                intent.putExtra("alarm", alarm);
                startActivity(intent);
            }
        });


    }

    //알람 추가 설정
    public void btn_addClick(View v) {
        Intent alarmAdd = new Intent(this, Activity_add.class);
        startActivity(alarmAdd);
    }

    public void btn_alang(View v) {
        Intent alang = new Intent(this, Activity_alarm_alanglang.class);
        startActivity(alang);
    }

    public void btn_eng(View v) {
        Intent eng = new Intent(this, Activity_alarm_eng.class);
        startActivity(eng);
    }

    public void btn_event(View v) {
        Intent event = new Intent(this, Activity_event.class);
        startActivity(event);
    }

    @Override
    protected void onPause() {
        Database.deactivate();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAlarmList();
    }

    public void updateAlarmList(){
        //데이터베이스 생성
        Database.init(MainActivity.this);
        //데이터베이스에서 알람읽어오기
        final List<Alarm> alarms = Database.getAll();
        //알람리스트에 데이터베이스에서 읽어온 알람리스트를 셋팅한다.
        alarmListAdapter.setAlarmList(alarms);

        int height = getListViewHeight(alarmListView);
        //Log.d("-진우- 높이 :  ", "getListViewHeight " + String.valueOf(height));
        alarmListView.getLayoutParams().height = height;

        runOnUiThread(new Runnable(){
            public void run(){
                MainActivity.this.alarmListAdapter.notifyDataSetChanged();
            }
        });
    }



    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case MESSAGE_OK:
                    Log.d("-진우- ", "handler MESSAGE_OK 실행");
                    String st = HTTPADDR + "/fileupload/bg/" + mainbg_name;
                    Log.d("-진우- 배경이미지", st);
                    BitmapFactory.Options bmOptions;
                    bmOptions = new BitmapFactory.Options();
                    bmOptions.inSampleSize = 1;
                    OpenHttpConnection opHttpCon = new OpenHttpConnection();
                    opHttpCon.execute(main_linearLayout, st);
                    break;
            }
        }
    };


    private class mainbgThread extends Thread {
        String result = "";
        URI uri;

        public mainbgThread(String url) throws URISyntaxException {
            uri = new URI(url);
        }

        @Override
        public void run() {
            Log.d("-진우- ", "run() 실행");
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
                Log.e("log_tag", "Error in http connection "+e.toString());
            } finally {
                httpclient.getConnectionManager().shutdown();
            }

            Log.d("-진우- 배경이미지정보 ", result);
            try {
                JSONObject json = new JSONObject(result);

                mainbg_name = json.getString("main_bg_server");
            } catch(JSONException e){
                Log.e("-진우- JSON", "Error parsing data "+e.toString());
            }
            handler.sendEmptyMessage(MESSAGE_OK);
        }
    }

    /*
    * 리스트뷰의 높이를 구함
    * */
    private int getListViewHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();
        int listViewHeight = 0;
        listView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        listViewHeight = listView.getMeasuredHeight() * adapter.getCount() + (adapter.getCount() * listView.getDividerHeight());
        return listViewHeight;
    }


    //이미지광고시 이미지불러오기
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
}
