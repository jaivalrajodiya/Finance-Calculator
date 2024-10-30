package com.all.smart.financecalculator.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.all.smart.financecalculator.CommonMethond;
import com.all.smart.financecalculator.R;

public class MortgageCal_Screen extends AppCompatActivity {

    private EditText editTextHomePrice, editTextDownPayment, editTextInterestRate, editTextLoanTerm;
    private TextView textViewMonthlyPayment, textViewTotalPayment, textViewTotalInterest;
    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_planner_screen);

        CommonMethond.statusbarcolor(MortgageCal_Screen.this);

        // Initialize UI elements
        editTextHomePrice = findViewById(R.id.editTextHomePrice);
        editTextDownPayment = findViewById(R.id.editTextDownPayment);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextLoanTerm = findViewById(R.id.editTextLoanTerm);
        textViewMonthlyPayment = findViewById(R.id.textViewMonthlyPayment);
        textViewTotalPayment = findViewById(R.id.textViewTotalPayment);
        textViewTotalInterest = findViewById(R.id.textViewTotalInterest);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);
        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        // Set listener for calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMortgage();
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

                    editTextHomePrice.setText("");
                    editTextDownPayment.setText("");
                    editTextInterestRate.setText("");
                    editTextLoanTerm.setText("");

                    editTextHomePrice.setSelected(false);
                    editTextDownPayment.setSelected(false);
                    editTextInterestRate.setSelected(false);
                    editTextLoanTerm.setSelected(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void calculateMortgage() {
        try {
            double homePrice = Double.parseDouble(editTextHomePrice.getText().toString());
            double downPayment = Double.parseDouble(editTextDownPayment.getText().toString());
            double annualInterestRate = Double.parseDouble(editTextInterestRate.getText().toString());
            int loanTermYears = Integer.parseInt(editTextLoanTerm.getText().toString());

            // Calculate loan amount (home price minus down payment)
            double loanAmount = homePrice - downPayment;

            // Convert annual interest rate to monthly interest rate and loan term to months
            double monthlyInterestRate = annualInterestRate / 12 / 100;
            int loanTermMonths = loanTermYears * 12;

            // Monthly payment formula
            double monthlyPayment = (loanAmount * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -loanTermMonths));

            // Calculate total payment and total interest
            double totalPayment = monthlyPayment * loanTermMonths;
            double totalInterest = totalPayment - loanAmount;

            // Display results
            textViewMonthlyPayment.setText(String.format("%.2f", monthlyPayment));
            textViewTotalPayment.setText(String.format("%.2f", totalPayment));
            textViewTotalInterest.setText(String.format("%.2f", totalInterest));

            result_layout.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
}