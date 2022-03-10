package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private CircleProgressBar mProgress;
    private FragmentManager mFragmentManager;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress = new CircleProgressBar();

        mFragmentManager = getSupportFragmentManager();

        mFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,CircleProgressBar.class,null)
                .setReorderingAllowed(true)
                .addToBackStack(null)
                .commit();

    }


}