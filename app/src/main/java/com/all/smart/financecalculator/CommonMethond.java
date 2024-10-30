package com.all.smart.financecalculator;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.Window;

import com.all.smart.financecalculator.Screen.MainActivity;

public class CommonMethond {

    public static void statusbarcolor(Activity activity){

        Window window = activity.getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.setNavigationBarColor(activity.getColor(R.color.white));
        window.setStatusBarColor(activity.getColor(R.color.white));
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        if (activity instanceof MainActivity) {
            window.setStatusBarColor(Color.parseColor("#AE285D"));
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }

    }

}
