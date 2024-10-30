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

public class ProfitabilityRatios_Screen extends AppCompatActivity {

    private EditText editTextRevenue, editTextGrossProfit, editTextNetProfit, editTextTotalAssets;
    private TextView textViewGrossProfitMargin, textViewNetProfitMargin, textViewROA;

    private LinearLayout result_layout;

    private ImageView iv_reset,iv_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profitability_ratios_screen);

        CommonMethond.statusbarcolor(ProfitabilityRatios_Screen.this);

        // Initialize UI elements
        editTextRevenue = findViewById(R.id.editTextRevenue);
        editTextGrossProfit = findViewById(R.id.editTextGrossProfit);
        editTextNetProfit = findViewById(R.id.editTextNetProfit);
        editTextTotalAssets = findViewById(R.id.editTextTotalAssets);
        textViewGrossProfitMargin = findViewById(R.id.textViewGrossProfitMargin);
        textViewNetProfitMargin = findViewById(R.id.textViewNetProfitMargin);
        textViewROA = findViewById(R.id.textViewROA);
        ImageView buttonCalculate = findViewById(R.id.buttonCalculate);
        result_layout = findViewById(R.id.result_layout);
        iv_reset = findViewById(R.id.iv_reset);
        iv_goBack = findViewById(R.id.iv_goBack);

        // Set listener for calculate button
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateProfitabilityRatios();
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

                    editTextRevenue.setText("");
                    editTextGrossProfit.setText("");
                    editTextNetProfit.setText("");
                    editTextTotalAssets.setText("");

                    editTextRevenue.setSelected(false);
                    editTextGrossProfit.setSelected(false);
                    editTextNetProfit.setSelected(false);
                    editTextTotalAssets.setSelected(false);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private void calculateProfitabilityRatios() {
        try {
            double revenue = Double.parseDouble(editTextRevenue.getText().toString());
            double grossProfit = Double.parseDouble(editTextGrossProfit.getText().toString());
            double netProfit = Double.parseDouble(editTextNetProfit.getText().toString());
            double totalAssets = Double.parseDouble(editTextTotalAssets.getText().toString());

            // Calculate Gross Profit Margin
            double grossProfitMargin = (grossProfit / revenue) * 100;

            // Calculate Net Profit Margin
            double netProfitMargin = (netProfit / revenue) * 100;

            // Calculate Return on Assets (ROA)
            double returnOnAssets = (netProfit / totalAssets) * 100;

            // Display results
            textViewGrossProfitMargin.setText(String.format("%.2f%%", grossProfitMargin));
            textViewNetProfitMargin.setText(String.format("%.2f%%", netProfitMargin));
            textViewROA.setText(String.format("%.2f%%", returnOnAssets));

            result_layout.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show();
        }
    }
}