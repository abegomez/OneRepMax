package myapp.abrahamjohngomez.com.onerepmax;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class HistoryViewActivity extends AppCompatActivity {

    List<MaxObject> maxes;
    PieChart pieChart;
    Description desc;
    ArrayList<PieEntry> entries;
    PieDataSet dataset;
    PieData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);

        readRecords();

    }
    protected void displayChart() {
        pieChart = (PieChart) findViewById(R.id.chart);
        desc = new Description();
        desc.setText("Description");
        entries = new ArrayList<>();

        dataset = new PieDataSet(entries, "# of Calls");

        if(maxes.size() > 0) {
            for(MaxObject obj : maxes) {
                entries.add(new PieEntry((float)obj.getMax(), obj.getExerciseName()));
            }
        }

        data = new PieData(dataset);
        data.setValueTextSize(17);

        dataset.setColors(ColorTemplate.LIBERTY_COLORS);
       // pieChart.setDescription(desc);
        pieChart.setData(data);
        pieChart.animateY(2500);

    }

    public void countrecords() {
        int recordCount = new TableControllerMax(this).count();
    }

    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        maxes = new TableControllerMax(this).read();

        if(maxes.size() >0) {
            for(MaxObject obj : maxes) {
                int id = obj.getId();
                String activityName = obj.getExerciseName();
                Double max = obj.getMax();
                String textViewContents = activityName + " - " + max;

                TextView textViewMaxItem = new TextView(this);
                textViewMaxItem.setPadding(0, 10, 0, 10);
                textViewMaxItem.setText(textViewContents);
                textViewMaxItem.setTag(Integer.toString(id));
                textViewMaxItem.setOnLongClickListener(new OnLongClickListenerMaxRecord());
                linearLayoutRecords.addView(textViewMaxItem);
            }
        } else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText(R.string.noRecordsTextMessage);

            linearLayoutRecords.addView(locationItem);
        }

        displayChart();
    }
}
