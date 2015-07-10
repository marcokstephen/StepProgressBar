package com.marcok.stepprogresssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.marcok.stepprogressbar.StepProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StepProgressBar mStepProgressBar;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStepProgressBar = (StepProgressBar)findViewById(R.id.stepProgressBar);

        Button prev = (Button) findViewById(R.id.button_prev);
        Button next = (Button) findViewById(R.id.button_next);
        Button remove = (Button) findViewById(R.id.button_remove);
        Button add = (Button) findViewById(R.id.button_add);
        Button cumulative = (Button) findViewById(R.id.button_cumulative);
        prev.setOnClickListener(this);
        next.setOnClickListener(this);
        remove.setOnClickListener(this);
        add.setOnClickListener(this);
        cumulative.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_prev:
                mStepProgressBar.previous();
                break;
            case R.id.button_next:
                mStepProgressBar.next();
                break;
            case R.id.button_add:
                mStepProgressBar.setNumDots(mStepProgressBar.getNumDots()+1);
                break;
            case R.id.button_remove:
                mStepProgressBar.setNumDots(mStepProgressBar.getNumDots()-1);
                break;
            case R.id.button_cumulative:
                mStepProgressBar.setCumulativeDots(counter++%2==0);
                break;
        }
    }
}
