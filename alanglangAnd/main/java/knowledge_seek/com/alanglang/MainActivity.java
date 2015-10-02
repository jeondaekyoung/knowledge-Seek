package knowledge_seek.com.alanglang;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import knowledge_seek.com.Alarm;
import knowledge_seek.com.AlarmListAdapter;
import knowledge_seek.com.BaseActivity;
import knowledge_seek.com.database.Database;

public class MainActivity extends BaseActivity {

    ListView alarmListView;
    AlarmListAdapter alarmListAdapter;

    //private Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmListView = (ListView) findViewById(R.id.listView);

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

    public void btn_alanglang(View v) {
        Intent alanglang = new Intent(this, Activity_alarm_alanglang.class);
        startActivity(alanglang);
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

        runOnUiThread(new Runnable() {
            public void run() {
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
}
