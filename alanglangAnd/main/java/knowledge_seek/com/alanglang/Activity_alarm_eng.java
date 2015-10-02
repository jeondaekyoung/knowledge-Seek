package knowledge_seek.com.alanglang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by sjw on 2015-10-02.
 */
public class Activity_alarm_eng extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_eng);

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
}
