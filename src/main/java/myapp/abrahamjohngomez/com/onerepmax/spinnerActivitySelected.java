package myapp.abrahamjohngomez.com.onerepmax;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by ryuhyoko on 10/22/2016.
 */

public class SpinnerActivitySelected implements AdapterView.OnItemSelectedListener {

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
}
