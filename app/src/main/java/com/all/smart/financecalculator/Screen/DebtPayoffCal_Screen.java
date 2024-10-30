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

public class DebtPayoffCal_Screen extends AppCompatActivity {


    private EditText editTextDebtAmount, editTextInterestRate, editTextMonthlyPayment;
    private TextView textViewPayoffTime, textViewTotalInterest;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_repayment_cal_screen);

        CommonMethond.statusbarcolor(DebtPayoffCal_Screen.this);

        // Initialize UI elements
        editTextDebtAmount = findViewById(R.id.editTextDebtAmount);
        editTextInterestRate = findViewById(R.id.editTextInterestRate);
        editTextMonthlyPayment = findViewById(R.id.editTextMonthlyPayment);
        textViewPayoffTime = findViewById(R.id.textViewPayoffTime);
        textViewTotalInterest = findViewById(R.id.textViewTotalInterest);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);

        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        // Set listener for calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    calculateDebtPayoff();
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

                    editTextDebtAmount.setText("");
                    editTextInterestRate.setText("");
                    editTextMonthlyPayment.setText("");


                    editTextDebtAmount.setSelected(false);
                    editTextInterestRate.setSelected(false);
                    editTextMonthlyPayment.setSelected(false);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void calculateDebtPayoff() {
        try {
            double debtAmount = Double.parseDouble(editTextDebtAmount.getText().toString());
            double annualInterestRate = Double.parseDouble(editTextInterestRate.getText().toString());
            double monthlyPayment = Double.parseDouble(editTextMonthlyPayment.getText().toString());

            // Check if monthly payment is sufficient to cover interest
            double monthlyInterestRate = annualInterestRate / 12 / 100;
            if (monthlyPayment <= debtAmount * monthlyInterestRate) {
                Toast.makeText(this, "Monthly payment is too low to cover interest.", Toast.LENGTH_LONG).show();
                return;
            }

            // Calculate payoff time and total interest
            int months = 0;
            double totalInterest = 0;
            double remainingDebt = debtAmount;

            while (remainingDebt > 0) {
                double interest = remainingDebt * monthlyInterestRate;
                remainingDebt = remainingDebt + interest - monthlyPayment;
                totalInterest += interest;
                months++;
            }

            // Display results
            textViewPayoffTime.setText(String.valueOf(months));
            textViewTotalInterest.setText(String.format("%.2f", totalInterest));

            result_layout.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkValidation(){

        if (editTextDebtAmount.getText().toString().isEmpty() && editTextDebtAmount.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Debt Amount", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextInterestRate.getText().toString().isEmpty() && editTextInterestRate.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Interest Rate", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextMonthlyPayment.getText().toString().isEmpty() && editTextMonthlyPayment.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Monthly Payment", Toast.LENGTH_SHORT).show();
            return false;
        }



        return true;
    }
}