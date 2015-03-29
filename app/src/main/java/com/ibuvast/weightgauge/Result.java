package com.ibuvast.weightgauge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ibuvast.Weight_Gauge.R;
import java.text.DecimalFormat;

public class Result extends Activity {

    private Button tellButton;
    private Button AdviceButton;
    private TextView bmiResultTextView;
    private TextView adTexView;
    private TextView disAdTextView;
    private ScrollView resultScrollView;
    private double weight;
    private double height;
    public static double result;
    public static String adviceLink = "";
    public static String BMIStatus ;

    DecimalFormat df = new DecimalFormat("#.##");
    MyActivity m = new MyActivity();
    DBAdapter myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        myDB = new DBAdapter(this);

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffda7369")));
        tellButton = (Button) findViewById(R.id.tellButton);
        bmiResultTextView = (TextView) findViewById(R.id.bmiresult);
        resultScrollView = (ScrollView) findViewById(R.id.resultScrollView);
        adTexView = (TextView) findViewById(R.id.advantageTextView);
       disAdTextView = (TextView) findViewById(R.id.disadvantageTextView);
  //      resultScrollView.setSmoothScrollingEnabled(true);

      calculateBMI();

        tellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Result.this, More.class);
                startActivity(intent);
            }
        });
    }

    public void calculateBMI() {
        try {
            height = MyActivity.height;
            weight = MyActivity.weight;

            result =Double.valueOf(df.format(weight / (Math.pow(height, 2))))  ;//Calculates the BMI


            //Comments
            if ((height <= 0) || (weight <= 0)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Result.this);

                builder.setTitle(R.string.errorTitle); // title bar string

                // provide an OK button that simply dismisses the dialog
                builder.setPositiveButton(R.string.OK, null);

                // set the message to display
                builder.setMessage(R.string.errorMessage);

                // create AlertDialog from the AlertDialog.Builder
                AlertDialog errorDialog = builder.create();
                errorDialog.show(); // display the Dialog
                height = 0;//clear height
                weight = 0;//clear weight

            } else {
                if (result < 18.5) {
                    bmiResultTextView.setText("Your BMI is:   " + result +
                                            "\nBMI status:    You are Underweight\n");
                    adTexView.setText("Advantages are limited");
                    disAdTextView.setText( "* Miscarriage and pregnancy problems\n"
                                        + " * Male fertility issues"
                                        + "\n* Lung problem during old age  "
                                        + "\n* Arthritis  ");
                    //AdviceButton.setVisibility(View.VISIBLE);
                    adviceLink = "http://nutrition.about.com/od/dietsformedicaldisorders/f/GainWeight.htm";
                    BMIStatus = "UNDERWEIGHT";
                }


                //Normal BMI
                else if ((result >= 18.5) && (result <= 24.9)) {
                    bmiResultTextView.setText("Your BMI is:   " + result
                                            + "\nBMI status:    You have a Normal BMI");
                            adTexView.setText("* Lower risk of stroke and heart disease"
                                            + "\n* Lower risk of cancer"
                                            + "\n* More energy");
                            disAdTextView.setText("* Null");
                    adviceLink = "http://m.wikihow.com/Maintain-a-Healthy-Weight";
                    BMIStatus = "NORMAL";

                } else if ((result > 24.9) && (result <= 29.5)) {
                    bmiResultTextView.setText("Your BMI is:   " + result + "\nBMI status:    You are Overweight(Lower limit of Obessity)");
                            adTexView.setText("* More warmth is possible"
                            + "\n* Stronger bones/muscle"
                            + "\n* Overweight people can lose weight");
                    disAdTextView.setText("* vulnerable to High blood pressure"
                            + "\nHigh risk of stroke"
                            + "\n* High risk of Diabetes ");

                    //AdviceButton.setVisibility(View.VISIBLE);
                    adviceLink = "http://m.wikihow.com/Lose-Weight";
                    BMIStatus = "OVERWEIGHT";
                } else if (result > 29.5) {
                    bmiResultTextView.setText("Your BMI is:   " + result
                                            + "\nBMI status:    You are Obessed");
                            adTexView.setText(" * More warmth is possible"
                                                + "* Stronger bones/muscle"
                                            + "\n* Obessed people can lose weight");
                            disAdTextView.setText("* vulnerable to High blood pressure"
                            + "\n* High risk of stroke"
                            + "\n* High risk of Diabetes "
                            + "\n* High risk of ostheoarthritis"
                            + "\n\nWARNING: You really need to burn some fat. To know how you can do this,"
                            + "\n please click on 'advice me'.");
                    //AdviceButton.setVisibility(View.VISIBLE);
                    adviceLink = "http://m.wikihow.com/Lose-Weight";
                    BMIStatus = "OBESSED";
                }
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

    }
        public void insertRecord(){
        long id = myDB.insertData();
        if(id<0){
            Message.message(this,"Insert was unsuccessful");
        }else{
            Message.message(this,"Insert was successful");
        }


    }

}
