package com.marcok.stepprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by stephen on 15-07-10.
 */
public class StepProgressBar extends View {

    private int inactiveColor;
    private int activeColor;
    private Drawable inactiveDrawable;
    private Drawable activeDrawable;

    private float dotSize;
    private float dotSpacing;

    private int maxNumDots;
    private int currentlyActiveDot;
    private boolean cumulativeDots;

    private Paint mPaint;

    private static final int MIN_DOTS = -1;
    private static final String OUT_OF_BOUNDS_ERROR = "Progress bar out of bounds!";

    public StepProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StepProgressBar, 0, 0);
        try {

            inactiveColor = ta.getInt(R.styleable.StepProgressBar_inactiveDotColor, 0);
            activeColor = ta.getInt(R.styleable.StepProgressBar_activeDotColor, 0);
            inactiveDrawable = ta.getDrawable(R.styleable.StepProgressBar_inactiveDot);
            activeDrawable = ta.getDrawable(R.styleable.StepProgressBar_activeDot);
            dotSize = ta.getDimensionPixelSize(R.styleable.StepProgressBar_dotSize, 5);
            dotSpacing = ta.getDimensionPixelSize(R.styleable.StepProgressBar_spacing, 10);
            maxNumDots = ta.getInt(R.styleable.StepProgressBar_numberDots, 5);
            currentlyActiveDot = ta.getInt(R.styleable.StepProgressBar_activeDotIndex, 0);
            cumulativeDots = ta.getBoolean(R.styleable.StepProgressBar_cumulativeDots, false);

        } finally {
            ta.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < maxNumDots; i++){
            int x = (int)(i*(dotSpacing + dotSize));
            if (i == currentlyActiveDot){
                mPaint.setColor(inactiveColor);
            } else {
                mPaint.setColor(activeColor);
            }
            canvas.drawCircle(x + dotSize/2, getPaddingTop() + dotSize / 2, dotSize / 2, mPaint);
        }
    }

    public void progressForward(){
        if (currentlyActiveDot == maxNumDots - 1){
            throw new IndexOutOfBoundsException(OUT_OF_BOUNDS_ERROR);
        }
        currentlyActiveDot++;
        invalidate();
    }
    public void progressBackward(){
        if (currentlyActiveDot == MIN_DOTS){
            throw new IndexOutOfBoundsException(OUT_OF_BOUNDS_ERROR);
        }
        currentlyActiveDot--;
        invalidate();
    }
}
