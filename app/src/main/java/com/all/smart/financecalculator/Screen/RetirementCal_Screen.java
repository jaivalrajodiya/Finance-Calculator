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

public class RetirementCal_Screen extends AppCompatActivity {

    private EditText editTextPrincipal, editTextRate, editTextYears, editTextAnnualContribution;
    private Spinner spinnerFrequency;
    private TextView textViewResult;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirement_cal_screen);

        CommonMethond.statusbarcolor(RetirementCal_Screen.this);

        editTextPrincipal = findViewById(R.id.editTextPrincipal);
        editTextRate = findViewById(R.id.editTextRate);
        editTextYears = findViewById(R.id.editTextYears);
        editTextAnnualContribution = findViewById(R.id.editTextAnnualContribution);
        spinnerFrequency = findViewById(R.id.spinnerFrequency);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRetirementSavings();
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
                    editTextYears.setText("");
                    editTextAnnualContribution.setText("");

                    editTextPrincipal.setSelected(false);
                    editTextRate.setSelected(false);
                    editTextYears.setSelected(false);
                    editTextAnnualContribution.setSelected(false);

                    spinnerFrequency.setSelection(0);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void calculateRetirementSavings() {
        try {
            // Get input values
            double principal = Double.parseDouble(editTextPrincipal.getText().toString());
            double rate = Double.parseDouble(editTextRate.getText().toString()) / 100; // Convert percentage to decimal
            int years = Integer.parseInt(editTextYears.getText().toString());
            double annualContribution = Double.parseDouble(editTextAnnualContribution.getText().toString());

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

            // Calculate future value for retirement savings
            double futureValue = calculateFutureValue(principal, rate, years, frequency, annualContribution);

            // Display result
            textViewResult.setText(String.format("%.2f", futureValue));
            result_layout.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid inputs", Toast.LENGTH_SHORT).show();
        }
    }

    public static double calculateFutureValue(double principal, double rate, int years, int compoundingFrequency, double annualContribution) {
        double futureValue = principal * Math.pow(1 + (rate / compoundingFrequency), compoundingFrequency * years)
                + annualContribution * ((Math.pow(1 + (rate / compoundingFrequency), compoundingFrequency * years) - 1) / (rate / compoundingFrequency));
        return futureValue;
    }
}