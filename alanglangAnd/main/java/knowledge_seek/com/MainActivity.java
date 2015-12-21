package knowledge_seek.com;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

    private static final String HTTPADDR = "http://www.knowledge-seek.com";
    LinearLayout main_linearLayout;         //메인 리니어아웃

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmListView = (ListView) findViewById(R.id.listView);
        main_linearLayout = (LinearLayout)findViewById(R.id.main_linearLayout);


        //서버와 통신하여 서버에 있는 배경이미지불러오기
        OpenHttpConnection opHttpCon = new OpenHttpConnection();
        opHttpCon.execute(main_linearLayout, HTTPADDR);

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

        if("NONE".equals(NetworkUtil.getConnectivityStatusString(this))){
            Toast.makeText(MainActivity.this, "네트워크를 확인해주세요", Toast.LENGTH_SHORT).show();
        }
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

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        private LinearLayout bgLinearLayout;        //배경이미지 레이아웃
        private String url;                                    //나리지식 도메인

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
            bgLinearLayout = (LinearLayout)objects[0];
            url = (String)objects[1];
            String result = null;
            String mainbg_name = null;

            OkHttpClient client = new OkHttpClient();
            //배경 이미지 이름 알라오기
            Request request1 = new Request.Builder()
                    .url(url + "/and/mainbg.do")
                    .build();
            try {
                Response response = client.newCall(request1).execute();
                if(!response.isSuccessful()){
                    Log.d("-진우-", "서버와의 통신이 올바르지않습니다");
                }
                result = response.body().string();
                Log.d("-진우-", "첫번째 결과 : " + result);
                JSONObject json = new JSONObject(result);
                mainbg_name = json.getString("main_bg_server");
                Log.d("-진우-", "두번째 결과 : " + mainbg_name);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e){
                e.printStackTrace();
            }
            //배경 이미지 불러오기
            InputStream in = null;
            try{
                in = new URL(url + "/fileupload/bg/" + mainbg_name).openStream();
                mBitmap = BitmapFactory.decodeStream(in);
                in.close();
            }catch (Exception e){
                e.printStackTrace();
            }

            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null){
                bgLinearLayout.setBackground(new BitmapDrawable(getResources(), bitmap));
            }

            dialog.dismiss();
            super.onPostExecute(bitmap);

        }
    }

}
