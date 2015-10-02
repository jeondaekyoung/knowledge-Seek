package knowledge_seek.com;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import knowledge_seek.com.alanglang.R;

/**
 * Created by sjw on 2015-10-02.
 */
public class AlarmListAdapter extends BaseAdapter {

    private Context context;

    //알람 리스트
    private List<Alarm> alarmList = new ArrayList<Alarm>();

    public AlarmListAdapter(Context context) {
        this.context = context;
    }

    public void addAlarm(Alarm it) {
        alarmList.add(it);
    }

    public void setAlarmList(List<Alarm> lit){
        alarmList = lit;
    }

    @Override
    public int getCount() {
        return alarmList.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return alarmList.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        AlarmView alarmView;
        if(view == null){
            //Log.d("-진우- 어덥터갯뷰 ", "------ view == null");
            alarmView = new AlarmView(context, alarmList.get(position));
        } else {
            //Log.d("-진우- 어덥터갯뷰 ", "------ view != null");
            alarmView = (AlarmView) view;

            Alarm alarm = alarmList.get(position);
            alarmView.setAlarm(alarm);
            alarmView.setSet_time(alarm.getAlarmTimeView());
            alarmView.setSet_day(alarm.getRepeatDaysString());
            alarmView.setActiveImage(alarm.getAlarmActive());
            Resources res = context.getResources();
            if (alarmList.get(position).getAlarmActive()) {
                alarmView.setSet_active(res.getDrawable(R.drawable.ic_alarm_active));
                alarmView.getAlarm_set().setAlpha(1);
            } else {
                alarmView.setSet_active(res.getDrawable(R.drawable.ic_alarm_inactive));
                alarmView.getAlarm_set().setAlpha(0.6f);
            }

            //Log.d("-진우- 어덥터갯뷰 ", alarm.toString());
        }
        return alarmView;
    }
}
