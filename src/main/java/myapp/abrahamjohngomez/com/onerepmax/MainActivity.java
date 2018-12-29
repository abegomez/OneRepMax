package myapp.abrahamjohngomez.com.onerepmax;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.*;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    private EditText etActivity;
    private EditText etReps;
    private EditText etWeight;

    private String exerciseName;
    private Double weight;
    private int reps;
    private String pounds;
    private String algorithmChoice;
    private Spinner spinner;
    AutoCompleteTextView tvAuto;
    String[] EXERCISES = {"Bench Press", "Deadlift", "Squat"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btOneRep = (Button) findViewById(R.id.btOneRep);
        EditText etReps = (EditText) findViewById(R.id.etReps);
        etReps.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    btOneRep.performClick();
                    return true;
                }
                return false;
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.select_dialog_item, EXERCISES);
        tvAuto = (AutoCompleteTextView) findViewById(R.id.etNameofactivity);
        tvAuto.setThreshold(1);
        tvAuto.setAdapter(adapter);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerAlgorithm);
        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<>();
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
        countRecords();
    }

    private MaxObject createMaxObj() {
        etWeight = (EditText) findViewById(R.id.etWeight);
        etReps = (EditText) findViewById(R.id.etReps);
        etActivity = (EditText) findViewById(R.id.etNameofactivity);
        exerciseName = etActivity.getText().toString();

        try {
            weight = Double.parseDouble(etWeight.getText().toString());
        }catch(final NumberFormatException e){
            weight = 100.0;
        }

        try {
            reps = Integer.parseInt(etReps.getText().toString());
        }catch(final NumberFormatException e) {
            reps = 3;
        }

        try {
            if(exerciseName.length() == 0)
                exerciseName = "No name";
        }catch(Exception e) {

        }

        MaxObject maxObject;
        spinner = (Spinner)findViewById(R.id.spinnerAlgorithm);
        algorithmChoice = spinner.getSelectedItem().toString();
        maxObject = new MaxObject(algorithmChoice, exerciseName, reps, weight);
        return maxObject;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void saveOnClick(View view) {
        MaxObject maxItem = createMaxObj();

        final Context context = this.getBaseContext();
        boolean createSuccessful = new TableControllerMax(context).create(maxItem);
        if(createSuccessful) {
            Toast.makeText(context, "Max info was saved.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Unable to save information.", Toast.LENGTH_SHORT).show();
        }
        countRecords();
    }
    public void onClick(View view) {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        MaxObject maxItem = createMaxObj();
        displayPercentages(maxItem);
    }

    private void displayPercentages(MaxObject maxItem) {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.layoutPercentages);
        linearLayoutRecords.removeAllViews();
        LinearLayout linearLabel = (LinearLayout) findViewById(R.id.layoutLabels);
        linearLabel.removeAllViews();
        int max = (int) Math.round(maxItem.getMax());

        for (int i = 100; i > 45; i-=5) {
            int tempMax = max;
            String textViewContents = String.valueOf(tempMax *i /100) + "lbs";
            TextView textViewMaxItem = new TextView(this);
            textViewMaxItem.setGravity(Gravity.CENTER);
            textViewMaxItem.setTextSize(20);
            textViewMaxItem.setPadding(0, 0, 0, 0);
            textViewMaxItem.setText(textViewContents);
            textViewMaxItem.setTag("tv"+i);
            linearLayoutRecords.addView(textViewMaxItem);

            String label = i + "%";
            TextView textViewLabel = new TextView(this);
            textViewLabel.setGravity(Gravity.CENTER);
            textViewLabel.setTextSize(20);
            textViewLabel.setPadding(0,0,0,0);
            textViewLabel.setText(label);
            textViewLabel.setTag("tvLabel" + i);
            linearLabel.addView(textViewLabel);
        }




//        int textViewCount = 10;
//        TextView[] tvPercentages = new TextView[textViewCount];
//
//        TextView showOneRep = (TextView) findViewById(R.id.tv100);
//
//        for
//        showOneRep.setText(pounds+ "lbs");
    }

    public final void countRecords(){
        int recordCount = new TableControllerMax(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount +" records found.");
    }

    public final void viewRecords(View view) {
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

    @Override
    public void onResume() {
        super.onResume();
        countRecords();
    }
}
