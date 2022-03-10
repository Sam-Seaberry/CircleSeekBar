package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class CircleProgressBar extends Fragment {
    private ProgressBar mProgress;
    private View mReference;

    private static float centerX;
    private static float centerY;

    private ImageView mThumb;

    private int progress = 0;
    private boolean touching = false;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.circle_progressbar,container,false);
        mProgress = view.findViewById(R.id.progress_bar);

        mThumb = view.findViewById(R.id.imageView);
        mThumb.setRotation((float)(-50*0.705));

        mReference = view.findViewById(R.id.referenceView);


        mReference.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(centerX == 0 || centerY==0){
                    centerX= mThumb.getWidth()/2+mThumb.getX();
                    centerY= mThumb.getHeight()/2+mThumb.getY();
                }

                if(event.getAction() == MotionEvent.ACTION_MOVE){
                    if(!(event.getX()<centerX && event.getY()>(centerY*1.6))) { //clicks under the progressbar are ignored
                        getprogressvalue(event.getX(), event.getY());
                    }


                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    touching = false;
                }else if(event.getAction() == MotionEvent.ACTION_DOWN){
                    if(!(event.getX()<centerX && event.getY()>centerY)) { //clicks under the progressbar are ignored
                        getprogressvalue(event.getX(), event.getY());
                    }

                }
                return true;


            }
        });


        return view;

    }


    private void updateprogressbar() {
        mProgress.setProgress(progress);
    }

    private void getprogressvalue(float touchx, float touchy){

        double angle = Math.atan((touchy-(centerY))/(touchx-centerX));
        double total = (angle*(180/Math.PI)*1.4);
        if(touchx > centerX){
            angle = angle+1.5;
            total = (angle*(180/Math.PI)*1.4)+195;
            if(total<375) {
                mProgress.setProgress((int)total);
            }

            setAngle((float)total);
        }else{

            mProgress.setProgress((int)total+65);
            setAngle((float)total+65);
        }



    }

    private void setAngle(float value){
        double i = (value-65)*0.72; //offset by 65 as the progressbar starts at 225 degrees and below inital thumb position
        if(i<225 && i>-45) {
            mThumb.setRotation((float) i);

        }
    }


}
