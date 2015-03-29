package com.ibuvast.weightgauge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ibuvast.Weight_Gauge.R;


public class MyActivity extends Activity implements View.OnClickListener {

    private EditText weightEditText;
    private EditText heightEditText;
    private Button resultButton;
    private Button clearButton;
    public static double height;
    public static double weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffda7369")));

        resultButton = (Button) findViewById(R.id.resultButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        resultButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resultButton:
                resultClick();
                break;
            case R.id.clearButton:
                clear();
                break;
            default:
                break;
        }
    }

    private void clear() {
        heightEditText.setText("");
        weightEditText.setText("");
    }

    private void resultClick() {
        try {
            height = Double.parseDouble(heightEditText.getText().toString())/100;
            weight = Double.parseDouble(weightEditText.getText().toString());

        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);

            builder.setTitle(R.string.errorTitle); // title bar string

            // provide an OK button that simply dismisses the dialog
            builder.setPositiveButton(R.string.OK, null);

            // set the message to display
            // set the message to display
            builder.setMessage(R.string.errorMessage);

            // create AlertDialog from the AlertDialog.Builder
            AlertDialog errorDialog = builder.create();
            errorDialog.show(); // display the Dialog

        }
        if ((height <= 0) || (weight <= 0)) {
            Toast.makeText(getBaseContext(), "Your height or weight cannot be less than or equal to zero!",Toast.LENGTH_LONG).show();
        }else {
            startActivity(new Intent(this, Result.class));
        }
    }

    private final int HISTORY_MENU_ID = Menu.FIRST;
    private final int HELP_MENU_ID = Menu.FIRST + 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return  true;
    }  // end method onCreateOptionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about_opt:
                about();
                return true;
            case R.id.feedback_opt:
                feedback();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void about()
    {
        startActivity(new Intent(this,About.class));
    }
    public void feedback()
    {
        startActivity(new Intent(this,Feedback.class));
    }
}
