package com.all.smart.financecalculator.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.all.smart.financecalculator.CommonMethond;
import com.all.smart.financecalculator.R;

public class CompoundInterestCal_Screen extends AppCompatActivity {

    private EditText editTextPrincipal, editTextRate, editTextTime;
    private Spinner spinnerFrequency;
    private TextView textViewCompoundInterest, textViewTotalAmount,textViewPrinicplaAmmount;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_interest_cal_screen);

        CommonMethond.statusbarcolor(CompoundInterestCal_Screen.this);

        editTextPrincipal = findViewById(R.id.editTextPrincipal);
        editTextRate = findViewById(R.id.editTextRate);
        editTextTime = findViewById(R.id.editTextTime);
        spinnerFrequency = findViewById(R.id.spinnerFrequency);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewCompoundInterest = findViewById(R.id.textViewCompoundInterest);
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount);
        textViewPrinicplaAmmount = findViewById(R.id.textViewPrinicplaAmmount);
        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    calculateCompoundInterest();
                }

            }
        });

        iv_goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        iv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    result_layout.setVisibility(View.GONE);
                    editTextPrincipal.setText("");
                    editTextRate.setText("");
                    editTextTime.setText("");

                    editTextPrincipal.setSelected(false);
                    editTextRate.setSelected(false);
                    editTextTime.setSelected(false);

                    spinnerFrequency.setSelection(0);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }

    private void calculateCompoundInterest() {
        try {

            double principal = Double.parseDouble(editTextPrincipal.getText().toString());
            double rate = Double.parseDouble(editTextRate.getText().toString());
            int time = Integer.parseInt(editTextTime.getText().toString());

            String frequencySelected = spinnerFrequency.getSelectedItem().toString();
            int frequency;

            switch (frequencySelected) {
                case "Annually":
                    frequency = 1;
                    break;
                case "Semi-Annually":
                    frequency = 2;
                    break;
                case "Quarterly":
                    frequency = 4;
                    break;
                case "Monthly":
                    frequency = 12;
                    break;
                default:
                    frequency = 1;
                    break;
            }

            double compoundInterest =calculateCompoundInterest(principal, rate, time, frequency);
            double totalAmount = calculateTotalAmount(principal, rate, time, frequency);

            textViewCompoundInterest.setText(String.format("%.2f", compoundInterest));
            textViewTotalAmount.setText(String.format("%.2f", totalAmount));
            textViewPrinicplaAmmount.setText(String.format("%.2f", principal));

            result_layout.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid inputs", Toast.LENGTH_SHORT).show();
        }
    }

    public static double calculateCompoundInterest(double principal, double rate, int time, int frequency) {

        double amount = principal * Math.pow(1 + (rate / (frequency * 100)), frequency * time);

        double compoundInterest = amount - principal;
        return compoundInterest;
    }

    public static double calculateTotalAmount(double principal, double rate, int time, int frequency) {
        return principal * Math.pow(1 + (rate / (frequency * 100)), frequency * time);
    }

    private boolean checkValidation(){

        if (editTextPrincipal.getText().toString().isEmpty() && editTextPrincipal.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Principal Amount", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextRate.getText().toString().isEmpty() && editTextRate.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Interest Rate", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextTime.getText().toString().isEmpty() && editTextTime.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Time Period", Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;
    }

}