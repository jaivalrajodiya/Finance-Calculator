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

public class EmiCal_Screen extends AppCompatActivity {

    private EditText editTextPrincipal, editTextRate, editTextTenureyear,editTextTenuremonth;
    private TextView textViewResult,textViewTotalInterest,textViewTotalPayment;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi_cal_screen);

        CommonMethond.statusbarcolor(EmiCal_Screen.this);

        editTextPrincipal = findViewById(R.id.editTextPrincipal);
        editTextRate = findViewById(R.id.editTextRate);
        editTextTenureyear = findViewById(R.id.editTextTenureyear);
        editTextTenuremonth = findViewById(R.id.editTextTenuremonth);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);
        textViewResult = findViewById(R.id.textViewResult);
        textViewTotalInterest = findViewById(R.id.textViewTotalInterest);
        textViewTotalPayment = findViewById(R.id.textViewTotalPayment);
        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);


        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidation()){
                    calculateEmi();
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
                    editTextTenureyear.setText("");
                    editTextTenuremonth.setText("");

                    editTextPrincipal.setSelected(false);
                    editTextRate.setSelected(false);
                    editTextTenureyear.setSelected(false);
                    editTextTenuremonth.setSelected(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void calculateEmi() {
        try {
            double principal = Double.parseDouble(editTextPrincipal.getText().toString());
            double rate = Double.parseDouble(editTextRate.getText().toString());

            int year = Integer.parseInt(editTextTenureyear.getText().toString());

            int month = Integer.parseInt(editTextTenuremonth.getText().toString());

            int tenure = (year * 12) + month;

            double monthlyRate = rate / (12 * 100);
            double emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, tenure))
                    / (Math.pow(1 + monthlyRate, tenure) - 1);

            double totalPayment = emi * tenure;
            double totalInterest = totalPayment - principal;

            textViewResult.setText(String.format("%.2f", emi));
            textViewTotalInterest.setText(String.format("%.2f", totalInterest));
            textViewTotalPayment.setText(String.format("%.2f", totalPayment));

            result_layout.setVisibility(View.VISIBLE);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private boolean checkValidation(){

        if (editTextPrincipal.getText().toString().isEmpty() && editTextPrincipal.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Loan Amount", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextRate.getText().toString().isEmpty() && editTextRate.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Interest Rate", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextTenureyear.getText().toString().isEmpty() && editTextTenureyear.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Loan Tenure(Year)", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (editTextTenuremonth.getText().toString().isEmpty() && editTextTenuremonth.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter Loan Tenure(Month)", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}