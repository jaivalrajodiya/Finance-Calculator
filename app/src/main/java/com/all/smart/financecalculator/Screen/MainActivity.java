package com.all.smart.financecalculator.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.all.smart.financecalculator.CommonMethond;
import com.all.smart.financecalculator.R;

public class MainActivity extends AppCompatActivity {

    ImageView emical_layout,CompoundInterest_layout,LoanComparisonCal_layout,CumulativeCal_layout,Retirement_layout,Buget_layout,LoanRepaymet_layout,profitratio_layout,LiquidityRatios_layout,SWPCalculator_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CommonMethond.statusbarcolor(MainActivity.this);

        emical_layout = findViewById(R.id.emical_layout);
        CompoundInterest_layout = findViewById(R.id.CompoundInterest_layout);
        Retirement_layout = findViewById(R.id.Retirement_layout);
        Buget_layout = findViewById(R.id.Buget_layout);
        LoanRepaymet_layout = findViewById(R.id.LoanRepaymet_layout);
        profitratio_layout = findViewById(R.id.profitratio_layout);
        LiquidityRatios_layout = findViewById(R.id.LiquidityRatios_layout);
        SWPCalculator_layout = findViewById(R.id.SWPCalculator_layout);
        CumulativeCal_layout = findViewById(R.id.CumulativeCal_layout);
        LoanComparisonCal_layout = findViewById(R.id.LoanComparisonCal_layout);

        emical_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EmiCal_Screen.class);
                startActivity(intent);
            }
        });

        CompoundInterest_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CompoundInterestCal_Screen.class);
                startActivity(intent);
            }
        });

        Retirement_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RetirementCal_Screen.class);
                startActivity(intent);
            }
        });

        Buget_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MortgageCal_Screen.class);
                startActivity(intent);
            }
        });

        LoanRepaymet_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DebtPayoffCal_Screen.class);
                startActivity(intent);
            }
        });

        profitratio_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfitabilityRatios_Screen.class);
                startActivity(intent);
            }
        });

        LiquidityRatios_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoanEligibilityCal_Screen.class);
                startActivity(intent);
            }
        });



        SWPCalculator_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SWPCalculator_Screen.class);
                startActivity(intent);
            }
        });

        CumulativeCal_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PriceCal_Screen.class);
                startActivity(intent);
            }
        });

        LoanComparisonCal_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoanComparisonCal_Screen.class);
                startActivity(intent);
            }
        });


    }
}