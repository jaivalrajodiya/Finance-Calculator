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

public class PriceCal_Screen extends AppCompatActivity {
    private EditText editTextCostPrice, editTextGrossMargin;
    private TextView textViewRevenuePrice, textViewGrossProfit, textViewMarkup;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cumulative_cal_screen);

        CommonMethond.statusbarcolor(PriceCal_Screen.this);

        // Initialize UI elements
        editTextCostPrice = findViewById(R.id.editTextCostPrice);
        editTextGrossMargin = findViewById(R.id.editTextGrossMargin);
        textViewRevenuePrice = findViewById(R.id.textViewRevenuePrice);
        textViewGrossProfit = findViewById(R.id.textViewGrossProfit);
        textViewMarkup = findViewById(R.id.textViewMarkup);
        ImageView buttonCalculatePrice = findViewById(R.id.buttonCalculatePrice);

        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        buttonCalculatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePriceDetails();
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

                    editTextCostPrice.setText("");
                    editTextGrossMargin.setText("");


                    editTextCostPrice.setSelected(false);
                    editTextGrossMargin.setSelected(false);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void calculatePriceDetails() {
        try {

            double costPrice = Double.parseDouble(editTextCostPrice.getText().toString());
            double grossMargin = Double.parseDouble(editTextGrossMargin.getText().toString());


            double revenuePrice = costPrice / (1 - (grossMargin / 100));

            double grossProfit = revenuePrice - costPrice;

            double markup = (grossProfit / costPrice) * 100;

            textViewRevenuePrice.setText(String.format("%.2f", revenuePrice));
            textViewGrossProfit.setText(String.format("%.2f", grossProfit));
            textViewMarkup.setText(String.format("%.2f%%", markup));

            result_layout.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
}