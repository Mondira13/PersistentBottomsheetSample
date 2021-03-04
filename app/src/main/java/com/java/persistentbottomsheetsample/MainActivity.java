package com.java.persistentbottomsheetsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {
    private CardView backCard;
    private ImageView bannerImg;
    private ConstraintLayout constraintLayout;
    private ImageView upIcon;
    private TextView textMsg;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);
        initializeView();

        bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_SETTLING);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                performCollapsing();
            }
        }, 500);
    }

    private void initializeView() {
        backCard = findViewById(R.id.backCard);
        bannerImg = findViewById(R.id.bannerImg);
        constraintLayout = findViewById(R.id.bottomsheetLayout);
        textMsg = findViewById(R.id.textMsg);
        upIcon = findViewById(R.id.upIcon);
    }

    private void performCollapsing() {
        int height = setMapImageViewWidthDynamatically(bannerImg);
        bottomSheetBehavior.setPeekHeight(height);
        bottomSheetBehavior.setHideable(false);

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    backCard.setVisibility(View.VISIBLE);
                    bannerImg.setVisibility(View.VISIBLE);
                    upIcon.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if (slideOffset == 1.0) {
                    backCard.setVisibility(View.GONE);
                    bannerImg.setVisibility(View.GONE);
                    upIcon.setVisibility(View.GONE);
                }else {
                    bannerImg.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private int setMapImageViewWidthDynamatically(ImageView upperLayouts) {
        Display display = getWindowManager().getDefaultDisplay();
        int displayHeight = display.getHeight();
        int upperLayoutsHeight = upperLayouts.getHeight();
        int height = displayHeight - upperLayoutsHeight + 50;
        return height;
    }

}