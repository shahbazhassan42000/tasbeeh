package shahbaz4311.tasbeeh.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import shahbaz4311.tasbeeh.R;

public class MyCustomDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //dialog box for adding student
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //modified title for the dialog box
        TextView title = new TextView(getActivity());
        title.setText(R.string.add_tasbeeh);
        title.setTextSize(30);
        title.setPadding(60, 60, 65, 20);
        //setting title icon
        Drawable icon = ContextCompat.getDrawable(getActivity(), R.drawable.add_icon);
        icon.setBounds(0, 0, 100, 100);
        title.setCompoundDrawables(icon, null,null, null);
        title.setCompoundDrawablePadding(20);
        //setting the title for the dialog box
        builder.setCustomTitle(title);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.tasbeeh_name, null);
        builder.setView(view);
        //getting the edit text for the name
        final EditText name = view.findViewById(R.id.tasbeeh_name_input);

        //listening for edit text to change the text
        name.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //check if the name is empty display error
                if (name.getText().toString().trim().isEmpty()) {
                    name.setError(getString(R.string.error_msg));
                    //disable the register button
                    ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    //enable the register button
                    ((AlertDialog) getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        //setting on click listener for the dialog box to register student
        builder.setPositiveButton(R.string.add, (dialog, which) -> {});
        builder.setOnDismissListener(dialog -> {
        });
        //setting on click listener for the dialog box to cancel the dialog box
        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());

        //make it non cancelable
        builder.setCancelable(false);


        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        // disable positive button by default
        AlertDialog dialog = (AlertDialog) getDialog();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);

        //setting on click listener for the dialog box to register student
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
            //TODO: add student to the database


        });

    }
}
