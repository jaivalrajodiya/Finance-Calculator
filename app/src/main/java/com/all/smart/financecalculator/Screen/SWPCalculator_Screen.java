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

public class SWPCalculator_Screen extends AppCompatActivity {

    private EditText editTextInvestmentAmount, editTextReturnRate, editTextTenureYears, editTextWithdrawalAmount;
    private TextView textViewSWPResult;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swpcalculator_screen);

        CommonMethond.statusbarcolor(SWPCalculator_Screen.this);

        // Initialize UI elements
        editTextInvestmentAmount = findViewById(R.id.editTextInvestmentAmount);
        editTextReturnRate = findViewById(R.id.editTextReturnRate);
        editTextTenureYears = findViewById(R.id.editTextTenureYears);
        editTextWithdrawalAmount = findViewById(R.id.editTextWithdrawalAmount);
        textViewSWPResult = findViewById(R.id.textViewSWPResult);
        ImageView buttonCalculateSWP = findViewById(R.id.buttonCalculateSWP);
        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        // Set listener for calculate button
        buttonCalculateSWP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSWP();
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

                    editTextInvestmentAmount.setText("");
                    editTextReturnRate.setText("");
                    editTextTenureYears.setText("");
                    editTextWithdrawalAmount.setText("");

                    editTextInvestmentAmount.setSelected(false);
                    editTextReturnRate.setSelected(false);
                    editTextTenureYears.setSelected(false);
                    editTextWithdrawalAmount.setSelected(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void calculateSWP() {
        try {
            double investmentAmount = Double.parseDouble(editTextInvestmentAmount.getText().toString());
            double annualReturnRate = Double.parseDouble(editTextReturnRate.getText().toString());
            int tenureYears = Integer.parseInt(editTextTenureYears.getText().toString());
            double monthlyWithdrawal = Double.parseDouble(editTextWithdrawalAmount.getText().toString());

            double monthlyReturnRate = (annualReturnRate / 12) / 100;
            int totalMonths = tenureYears * 12;

            double remainingBalance = investmentAmount;
            int months = 0;
            while (remainingBalance > 0 && months < totalMonths) {
                remainingBalance += remainingBalance * monthlyReturnRate;
                remainingBalance -= monthlyWithdrawal;
                months++;
            }

            if (remainingBalance <= 0) {
                textViewSWPResult.setText(String.valueOf(months));
            } else {
                textViewSWPResult.setText("Nan");
            }

            result_layout.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
}