package myapp.abrahamjohngomez.com.onerepmax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HistoryViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);
        Intent intent = getIntent();

        readRecords();
    }
    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<MaxObject> maxes = new TableControllerMax(this).read();

        if(maxes.size() >0) {
            for(MaxObject obj : maxes) {
                int id = obj.id;
                String activityName = obj.exerciseName;

                Double max = obj.max;

                String textViewContents = activityName + " - " + max;

                TextView textViewMaxItem = new TextView(this);
                textViewMaxItem.setPadding(0, 10, 0, 10);
                textViewMaxItem.setText(textViewContents);
                textViewMaxItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewMaxItem);
            }
        } else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }
}
