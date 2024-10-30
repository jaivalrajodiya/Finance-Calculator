package com.all.smart.financecalculator.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.all.smart.financecalculator.CommonMethond;
import com.all.smart.financecalculator.R;

public class LoanEligibilityCal_Screen extends AppCompatActivity {

    private EditText editTextMonthlyIncome, editTextExistingEMI, editTextInterestRate, editTextLoanTenure;
    private TextView textViewLoanEligibility;
    private Spinner spinnerFOIR;
    private double selectedFOIR;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquidity_ratios_screen);

        CommonMethond.statusbarcolor(LoanEligibilityCal_Screen.this);

        // Initialize UI elements
        editTextMonthlyIncome = findViewById(R.id.editTextMonthlyIncome);
        editTextExistingEMI = findViewById(R.id.editTextExistingEMI);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextLoanTenure = findViewById(R.id.editTextLoanTenure);
        textViewLoanEligibility = findViewById(R.id.textViewLoanEligibility);
        spinnerFOIR = findViewById(R.id.spinnerFOIR);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);

        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

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
                    editTextMonthlyIncome.setText("");
                    editTextExistingEMI.setText("");
                    editTextInterestRate.setText("");
                    editTextLoanTenure.setText("");

                    editTextMonthlyIncome.setSelected(false);
                    editTextExistingEMI.setSelected(false);
                    editTextInterestRate.setSelected(false);
                    editTextLoanTenure.setSelected(false);

                    spinnerFOIR.setSelection(0);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Set up FOIR Spinner
        String[] foirOptions = {"40%", "45%","50%", "55%","60%"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foirOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFOIR.setAdapter(adapter);

        // Set listener for FOIR selection
        spinnerFOIR.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String foirText = (String) parent.getItemAtPosition(position);
                selectedFOIR = Double.parseDouble(foirText.replace("%", "")) / 100;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedFOIR = 0.5; // Default FOIR value if nothing is selected
            }
        });

        // Set listener for calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoanEligibility();
            }
        });
    }

    private void calculateLoanEligibility() {
        try {
            double monthlyIncome = Double.parseDouble(editTextMonthlyIncome.getText().toString());
            double existingEMI = Double.parseDouble(editTextExistingEMI.getText().toString());
            double annualInterestRate = Double.parseDouble(editTextInterestRate.getText().toString());
            int loanTenureYears = Integer.parseInt(editTextLoanTenure.getText().toString());

            double maxEMIAfford = (selectedFOIR * monthlyIncome) - existingEMI;

            // Convert annual interest rate to monthly and loan tenure to months
            double monthlyInterestRate = annualInterestRate / 12 / 100;
            int loanTenureMonths = loanTenureYears * 12;

            // Calculate maximum eligible loan amount using EMI formula:
            // EMI = [P * r * (1+r)^n] / [(1+r)^n - 1]
            double eligibleLoanAmount = maxEMIAfford * (1 - Math.pow(1 + monthlyInterestRate, -loanTenureMonths)) / monthlyInterestRate;

            // Display the eligible loan amount
            textViewLoanEligibility.setText(String.format("%.2f", eligibleLoanAmount));

            result_layout.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
}