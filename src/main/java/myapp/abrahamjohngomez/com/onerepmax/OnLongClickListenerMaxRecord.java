package myapp.abrahamjohngomez.com.onerepmax;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ryuhyoko on 8/1/2016.
 */
public class OnLongClickListenerMaxRecord implements View.OnLongClickListener {

    Context context;
    String id;


    @Override
    public boolean onLongClick(View view) {
        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Max Record").setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                dialog.dismiss();
                if(item == 0) {
                    editRecord(Integer.parseInt(id));
                }else if(item == 1) {
                    deleteRecord(Integer.parseInt(id));
                }
            }
        }).show();
        return false;
    }
    public void deleteRecord(final int maxId) {
//        final TableControllerMax tableControllerMax = new TableControllerMax(context);
        final CharSequence[] items = {"Delete", "Cancel"};
        new AlertDialog.Builder(context).setTitle("Delete?").setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                boolean deleteSuccessful = new TableControllerMax(context).delete(maxId);
                if(deleteSuccessful) {
                    Toast.makeText(context, "Record was updated.", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Unable to update record.", Toast.LENGTH_SHORT).show();
                }

                ((HistoryViewActivity) context).readRecords();
            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
                })
                .setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    public void editRecord(final int maxId) {
        final TableControllerMax tableControllerMax = new TableControllerMax(context);
        MaxObject maxObject = tableControllerMax.readSingleRecord(maxId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.max_input_form, null, false);

        final EditText editTextExerciseName = (EditText) formElementsView.findViewById(R.id.editTextExerciseName);
        final EditText editMax = (EditText) formElementsView.findViewById(R.id.editTextMax);

        editTextExerciseName.setText(maxObject.getExerciseName());
        editMax.setText(String.valueOf(maxObject.getMax()));

        new AlertDialog.Builder(context).setView(formElementsView).setTitle("Edit Record").setPositiveButton("Save Changes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MaxObject maxObject = new MaxObject();
                        maxObject.setId(maxId);
                        maxObject.setExerciseName(editTextExerciseName.getText().toString());
                        maxObject.setMax(Double.parseDouble(editMax.getText().toString()));
                        boolean updateSuccessful = tableControllerMax.update(maxObject);

                        if(updateSuccessful) {
                            Toast.makeText(context, "Record was updated.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Unable to update record.", Toast.LENGTH_SHORT).show();
                        }
                        ((HistoryViewActivity) context).readRecords();


                        dialog.cancel();
                    }
                }).show();
    }
}
