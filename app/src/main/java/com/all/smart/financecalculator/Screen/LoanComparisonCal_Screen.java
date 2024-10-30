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

public class LoanComparisonCal_Screen extends AppCompatActivity {

    private EditText editTextLoan1Principal, editTextLoan1InterestRate, editTextLoan1Tenure;
    private EditText editTextLoan2Principal, editTextLoan2InterestRate, editTextLoan2Tenure;
    private TextView textViewMonthlyPaymentResult1, textViewMonthlyPaymentResult2, textViewtotalpaymentReslet1,textViewtotalpaymentReslet2,textViewsIntersetpayableResult1,textViewsIntersetpayableResult2,textViewMonthlyPaymentDifference, textViewTotalPaymentDifference;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_comparison_cal_screen);

        CommonMethond.statusbarcolor(LoanComparisonCal_Screen.this);

        // Initialize UI elements
        editTextLoan1Principal = findViewById(R.id.editTextLoan1Principal);
        editTextLoan1InterestRate = findViewById(R.id.editTextLoan1InterestRate);
        editTextLoan1Tenure = findViewById(R.id.editTextLoan1Tenure);

        editTextLoan2Principal = findViewById(R.id.editTextLoan2Principal);
        editTextLoan2InterestRate = findViewById(R.id.editTextLoan2InterestRate);
        editTextLoan2Tenure = findViewById(R.id.editTextLoan2Tenure);

        textViewMonthlyPaymentResult1 = findViewById(R.id.textViewMonthlyPaymentResult1);
        textViewMonthlyPaymentResult2 = findViewById(R.id.textViewMonthlyPaymentResult2);
        textViewsIntersetpayableResult1 = findViewById(R.id.textViewsIntersetpayableResult1);
        textViewsIntersetpayableResult2 = findViewById(R.id.textViewsIntersetpayableResult2);
        textViewtotalpaymentReslet1 = findViewById(R.id.textViewtotalpaymentReslet1);
        textViewtotalpaymentReslet2 = findViewById(R.id.textViewtotalpaymentReslet2);
        textViewMonthlyPaymentDifference = findViewById(R.id.textViewMonthlyPaymentDifference);
        textViewTotalPaymentDifference = findViewById(R.id.textViewTotalPaymentDifference);
        ImageView buttonCompareLoans = findViewById(R.id.buttonCompareLoans);

        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        // Set listener for calculate button
        buttonCompareLoans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLoanComparison();
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

                    editTextLoan1Principal.setText("");
                    editTextLoan1InterestRate.setText("");
                    editTextLoan1Tenure.setText("");
                    editTextLoan2Principal.setText("");
                    editTextLoan2InterestRate.setText("");
                    editTextLoan2Tenure.setText("");

                    editTextLoan1Principal.setSelected(false);
                    editTextLoan1InterestRate.setSelected(false);
                    editTextLoan1Tenure.setSelected(false);
                    editTextLoan2Principal.setSelected(false);
                    editTextLoan2InterestRate.setSelected(false);
                    editTextLoan2Tenure.setSelected(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void calculateLoanComparison() {
        try {
            // Loan 1 input
            double principal1 = Double.parseDouble(editTextLoan1Principal.getText().toString());
            double interestRate1 = Double.parseDouble(editTextLoan1InterestRate.getText().toString()) / 100 / 12;
            int tenure1 = Integer.parseInt(editTextLoan1Tenure.getText().toString()) * 12;

            // Loan 2 input
            double principal2 = Double.parseDouble(editTextLoan2Principal.getText().toString());
            double interestRate2 = Double.parseDouble(editTextLoan2InterestRate.getText().toString()) / 100 / 12;
            int tenure2 = Integer.parseInt(editTextLoan2Tenure.getText().toString()) * 12;

            // Calculate Loan 1 details
            double monthlyEMI1 = (principal1 * interestRate1 * Math.pow(1 + interestRate1, tenure1)) / (Math.pow(1 + interestRate1, tenure1) - 1);
            double totalPayment1 = monthlyEMI1 * tenure1;
            double totalInterest1 = totalPayment1 - principal1;

            // Calculate Loan 2 details
            double monthlyEMI2 = (principal2 * interestRate2 * Math.pow(1 + interestRate2, tenure2)) / (Math.pow(1 + interestRate2, tenure2) - 1);
            double totalPayment2 = monthlyEMI2 * tenure2;
            double totalInterest2 = totalPayment2 - principal2;

            // Calculate differences
            double monthlyPaymentDifference = Math.abs(monthlyEMI1 - monthlyEMI2);
            double totalPaymentDifference = Math.abs(totalPayment1 - totalPayment2);
            double interestDifference = Math.abs(totalInterest1 - totalInterest2);

            // Display results

            textViewMonthlyPaymentResult1.setText(String.format("%.2f",monthlyEMI1));
            textViewMonthlyPaymentResult2.setText(String.format("%.2f",monthlyEMI2));
            textViewsIntersetpayableResult1.setText(String.format("%.2f",totalInterest1));
            textViewsIntersetpayableResult2.setText(String.format("%.2f",totalInterest2));
            textViewtotalpaymentReslet1.setText(String.format("%.2f",totalPayment1));
            textViewtotalpaymentReslet2.setText(String.format("%.2f",totalPayment2));

            textViewMonthlyPaymentDifference.setText(String.format("Difference: %.2f", monthlyPaymentDifference));
            textViewTotalPaymentDifference.setText(String.format("Difference: %.2f", totalPaymentDifference));

            result_layout.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
}