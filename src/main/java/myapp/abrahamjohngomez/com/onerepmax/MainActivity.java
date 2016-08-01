package myapp.abrahamjohngomez.com.onerepmax;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Epley");
        categories.add("Brzycki");
        categories.add("Lander");
        categories.add("Lombardi");
        categories.add("Mayhew");
        categories.add("OConnor");
        categories.add("Wathen");
        categories.add("Wendler");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }
    public double getMax(int reps, double weight) {
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        String text = spinner.getSelectedItem().toString();
        Algorithms alg = new Algorithms();
        return alg.OneRepMax(text, reps, weight);


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void saveOnClick(View view) {
        TextView tv = (TextView)findViewById(R.id.editText);
        TextView tv2 = (TextView)findViewById(R.id.editText2);
        TextView tv3 = (TextView)findViewById(R.id.exercisename);
        int reps =  Integer.parseInt(tv2.getText().toString());
        double weight = Double.parseDouble(tv.getText().toString());
        double max = getMax(reps, weight);
        String textValue2 = tv2.getText().toString();
        String activityName = tv3.getText().toString();

        MaxObject maxItem = new MaxObject();
        maxItem.exerciseName = activityName;
        maxItem.max = max;
        final Context context = this.getBaseContext();
        boolean createSuccessful = new TableControllerMax(context).create(maxItem);
        if(createSuccessful) {
            Toast.makeText(context, "Max info was saved.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Unable to save student information.", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClick(View view) {
        TextView tv = (TextView)findViewById(R.id.editText);
        TextView tv2 = (TextView)findViewById(R.id.editText2);
        TextView tv3 = (TextView)findViewById(R.id.exercisenamestring);
        String textValue = tv.getText().toString();
        String textValue2 = tv2.getText().toString();
        String activityName = tv3.getText().toString();
        double value = Double.parseDouble(textValue);
        int rep = Integer.parseInt(textValue2);
        double max = getMax(rep, value);
        tv.setText(String.valueOf(max));
        tv2.setText("1");
        MaxObject maxItem = new MaxObject();
        maxItem.exerciseName = activityName;
        maxItem.max = max;


    }
    public void viewRecords(View view) {
        Intent intent = new Intent(this, HistoryViewActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
